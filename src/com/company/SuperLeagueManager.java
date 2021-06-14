package com.company;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.company.Path.URL;


public class SuperLeagueManager implements LeagueManager {

    private final int numberOfClubs;

    private final ArrayList<FootballClub> league;
    private final Scanner scanner;
    private final ArrayList<Match> matches;

    public static void main(String[] args) {
        SuperLeagueManager plm = new SuperLeagueManager(5);


    }

    public SuperLeagueManager(int numberOfClubs) {

        this.numberOfClubs = numberOfClubs;
        league = new ArrayList<>();
        matches = new ArrayList<>();
        scanner = new Scanner(System.in);
        displayMenu();
    }


    private void displayMenu() {

        int command = 0;
        do {
            System.out.println("\nMalang Super League menu");
            System.out.println("1. Create new team and add it to league");
            System.out.println("2. Delete existing team from league ");
            System.out.println("3. Display Statistics for team ");
            System.out.println("4. Display the Malang Super League Table ");
            System.out.println("5. Add a Played Match ");
            System.out.println("6. Display Calendar and Find Match ");
            System.out.println("7. Exit ");
            System.out.println("\nChoice :");
            String line = scanner.nextLine();
            try {
                command = Integer.parseInt(line);
            } catch (Exception e) {
            }

            switch(command) {
                case 1 :
                    addTeam();
                    break;
                case 2 :
                    deleteTeam();
                    break;
                case 3 :
                    displayStatistics();
                    break;
                case 4 :
                    displayLeagueTable();
                    break;
                case 5:
                    addPlayedMatch();
                    break;
                case 6:
                    displayCalendar();
                    break;
                default:
                    System.out.println("Wrong Command");
            }

        } while(command != 7);
    }

    private void addTeam(){

        if(league.size() == numberOfClubs) {
            System.out.println("Can't add more clubs to league");
            return;
        }

        FootballClub club = new FootballClub();
        System.out.println("\nInsert Club Name: ");
        String line = scanner.nextLine();
        club.setName(line);

        if(league.contains(club)){
            System.out.println("This club is already in the league");
            return;
        }
        System.out.println("Insert Club Location: ");
        line = scanner.nextLine();
        club.setLocation(line);
        league.add(club);

        System.out.println("Insert Club Coach Name: ");
        line = scanner.nextLine();
        club.setCoach(line);
        PrintWriter writerObj1;
        JSONArray jsonArray = new JSONArray();
        JSONObject objs = new JSONObject();
        objs.put("club", jsonArray);
        for (int i = 0;i < league.size() ; i++) {
            JSONObject obj = new JSONObject();
            JSONObject objItem =  new JSONObject();
            objItem.put("name",  league.get(i).getName());
            objItem.put("location",  league.get(i).getLocation());
            objItem.put("coach",  league.get(i).getCoach());
            jsonArray.put(objItem);
        }
        try {
            writerObj1 = new PrintWriter(new File(URL +"player.json"));
            writerObj1.write(objs.toString());
            writerObj1.flush();
            writerObj1.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void deleteTeam() {
        System.out.println("Insert club name: ");
        String line = scanner.nextLine();
        for(FootballClub club : league) {
            if(club.getName().equals(line)){
                league.remove(club);
                System.out.println("Club "+ club.getName()+" removed");
                return;
            }
        }
        System.out.println("No such club in league");
    }

    private void displayStatistics() {
        FootballClub footballClub;
        ArrayList<FootballClub> fb = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(URL +"player.json"));
            org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) obj;
            org.json.simple.JSONArray companyList = (org.json.simple.JSONArray) jsonObject.get("club");
            Iterator<org.json.simple.JSONObject> iterator = companyList.iterator();
//            JSONObject object = new JSONObject(new FileReader(URL +"player.json"));
//            JSONObject getObject = object.getJSONObject();
//            JSONArray getArray = getObject.getJSONArray("JArray1");
//            for (int i = 0; i < companyList.size(); i++) {
//
//            }
            while (iterator.hasNext()) {
//                for (int i = 0; i < iterator.next().size() ; i++) {
                System.out.println("Club Name : " + iterator.next().get("name"));
                System.out.println("Club Location : " + iterator.next().get("location"));
                System.out.println("Club Coach : " + iterator.next().get("coach"));
                System.out.println("size : " + iterator.next());
//                }
            }
        } catch (Exception i) {
            i.printStackTrace();
            return;
        }

//        System.out.println("Deserialized Club...");
//        for (FootballClub fc : fb) {
//            System.out.println("Name: " + footballClub.getName());
//            System.out.println("Address: " + footballClub.getLocation());
//            System.out.println("Coach: " + footballClub.getCoach());
//        }

        System.out.println("\n Insert club name: ");
        String line = scanner.nextLine();
        for (FootballClub club : league) {
            if(club.getName().equals(line)){
                System.out.println("\n" + club.getName());
                System.out.println(" matches won: " + club.getWinCount());
                System.out.println(" matches lost: " + club.getDefeatCount());
                System.out.println(" matches draw: " + club.getDrawCount());
                System.out.println(" scored goals: " + club.getScoredGoalsCount());
                System.out.println(" recieved goals: " + club.getReceivedGoalsCount());
                System.out.println(" points: " + club.getPoints());
                System.out.println(" matches played: " + club.getMatchesPlayed());
                return;
            }
        }
        System.out.println("No such club in league");
    }

    private void displayLeagueTable() {
        Collections.sort(league, new CustomComparator());

        System.out.println("\t\t\t"+"MLS Standing"+"\t\t");
        System.out.println("\n");
        System.out.println("Club"+"\t\t"+"Match Played"+"\t"+"GD"+"\t"+"Points");
        for(FootballClub club : league) {
            System.out.println(club.getName()+"\t\t"+club.getMatchesPlayed()+"\t\t\t\t"+(club.getScoredGoalsCount()-club.getReceivedGoalsCount())+"\t\t"+club.getPoints());
            //System.out.println("Club: " + club.getName()+" Points: "+ club.getPoints()+" goal difference: "+ (club.getScoredGoalsCount()-club.getReceivedGoalsCount()));
        }

    }

    private void addPlayedMatch(){
        System.out.println("Enter date (format mm-dd-yyyy): ");
        String line = scanner.nextLine();
        Date date;
        try {
            date = new SimpleDateFormat("MM-dd-yyyy").parse(line);
        } catch (ParseException ex) {
            System.out.println("You have to enter date in format mm-dd-yyyy");
            return;
        }
        System.out.println("Enter Home Team: ");
        line = scanner.nextLine();
        FootballClub home = null;
        for(FootballClub club : league){
            if(club.getName().equals(line))
                home = club;
        }
        if (home == null) {
            System.out.println("No such club in league");
            return;
        }
        System.out.println("Enter Away Team: ");
        line = scanner.nextLine();
        FootballClub away = null;
        for(FootballClub club : league){
            if(club.getName().equals(line))
                away = club;
        }
        if (away == null) {
            System.out.println("No such club in league");
            return;
        }

        System.out.println("Enter home team goals: ");
        line = scanner.nextLine();
        int homeGoals = -1;
        try {
            homeGoals = Integer.parseInt(line);
        } catch (Exception e) {
        }
        if (homeGoals == -1) {
            System.out.println("You have to enter number of goals");
            return;
        }

        System.out.println("Enter away team goals: ");
        line = scanner.nextLine();
        int awayGoals = -1;
        try {
            awayGoals = Integer.parseInt(line);
        } catch (Exception e) {
        }
        if (awayGoals == -1) {
            System.out.println("You have to enter number of goals");
            return;
        }


        Match match = new Match();
        match.setDate(date);
        match.setTeamA(home);
        match.setTeamB(away);
        match.setTeamAScore(awayGoals);
        match.setTeamBScore(homeGoals);
        matches.add(match);
        home.setScoredGoalsCount(home.getScoredGoalsCount()+homeGoals);
        away.setScoredGoalsCount(away.getScoredGoalsCount()+awayGoals);
        home.setRecievedGoalsCount(home.getReceivedGoalsCount()+awayGoals);
        away.setRecievedGoalsCount(away.getReceivedGoalsCount()+homeGoals);
        home.setMatchesPlayed(home.getMatchesPlayed()+1);
        away.setMatchesPlayed(away.getMatchesPlayed()+1);

        if (homeGoals > awayGoals) {
            home.setPoints(home.getPoints()+3);
            home.setWinCount(home.getWinCount()+1);
            away.setDefeatCount(away.getDefeatCount()+1);
        }

        else if (homeGoals < awayGoals) {
            away.setPoints(away.getPoints()+3);
            away.setWinCount(away.getWinCount()+1);
            home.setDefeatCount(home.getDefeatCount()+1);
        }
        else {
            home.setPoints(home.getPoints()+1);
            away.setPoints(away.getPoints()+1);
            home.setDrawCount(home.getDrawCount()+1);
            away.setDrawCount(away.getDrawCount()+1);
        }
    }

    private void displayCalendar() {

        System.out.println("Enter year: ");
        String line = scanner.nextLine();
        int Y = -7777;
        try {
            Y = Integer.parseInt(line);
        } catch (Exception e) {
        }
        if (Y == -7777) {
            System.out.println("You have to enter a year");
            return;
        }

        System.out.println("Enter Month: ");
        line = scanner.nextLine();
        int M = 0;
        try {
            M = Integer.parseInt(line);
        } catch (Exception e) {
        }
        if (M == 0) {
            System.out.println("You have to enter a month");
            return;
        }

        String[] months = {
                "",
                "January", "February", "March",
                "April", "May", "June",
                "July", "August", "September",
                "October", "November", "December"
        };

        int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        if (M == 2 && isLeapYear(Y)) days[M] = 29;

        System.out.println("    " + months[M] + " " + Y);
        System.out.println("S  M  Tu  W  Th  F  S");

        int d = day(M, 1, Y);
        String space = "";

        for (int i = 0; i < d; i++)
            System.out.print("   ");
        for (int i = 1; i <= days[M]; i++) {
            if (i<10)
                System.out.print(i +"  ");
            else
                System.out.print(i+" ");
            if (((i + d) % 7 == 0) || (i == days[M])) System.out.println();
        }

        System.out.println("Enter day: ");
        line = scanner.nextLine();
        int D = 0;
        try {
            D= Integer.parseInt(line);
        }  catch (Exception e) {
        }
        if (D == 0 || days[M] < D) {
            System.out.println("You have t enter day in month");
            return;
        }

        Calendar cal = Calendar.getInstance();
        cal.set(Y, M-1, D);
        for (Match m : matches) {
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(m.getDate());
            if (cal.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) || cal.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)) {
                System.out.println(m.getTeamA().getName()+ " "+m.getTeamAScore() + " : "+ m.getTeamBScore()+ " "+ m.getTeamB().getName());
            }
        }
    }

    public int day(int M, int D, int Y) {
        int y = Y - (14 - M) / 12;
        int x = y + y/4 - y/100 + y/400;
        int m = M + 12 * ((14-M) / 12) - 2;
        int d = (D + x + (31*m)/12) % 7;
        return d;
    }

    public boolean isLeapYear(int year) {

        if ((year % 4 ==0) && (year % 100 !=0 )) return true;
        if (year % 400 == 0) return true;
        return false;
    }
}



