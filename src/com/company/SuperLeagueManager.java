package com.company;

import com.company.model.FootballClub;
import com.company.model.Match;
import com.company.model.Player;
import com.company.model.Referee;
import com.company.util.Config;
import com.company.util.StandingComparator;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class SuperLeagueManager {


    private final ArrayList<FootballClub> league;
    private final ArrayList<Player> player;
    private final ArrayList<Referee> referee;
    private final Scanner scanner;
    private final ArrayList<Match> matches;

    public static void main(String[] args) {
        SuperLeagueManager plm = new SuperLeagueManager();

    }

    public SuperLeagueManager() {
        league = new ArrayList<>();
        matches = new ArrayList<>();
        player = new ArrayList<>();
        referee = new ArrayList<>();
        scanner = new Scanner(System.in);
        Config config = new Config(league, matches, player, referee);
        if (!config.checkDataExist()) {
            config.addDefaultFile();
        }
        displayMenu(config);
    }


    private void displayMenu(Config config) {

        int command = 0;
        boolean ex = true;
        do {
            System.out.println("\nMalang Super League Menu");
            System.out.println("1. Create and Delete Data");
            System.out.println("2. View Data Club, Players and Referee");
            System.out.println("3. Standings");
            System.out.println("4. Exit ");
            System.out.println("\nChoice :");
            String line = scanner.nextLine();
            try {
                command = Integer.parseInt(line);
            } catch (Exception e) {
            }

            switch (command) {
                case 1:
                    createAndDelete();
                    break;
                case 2:
                    viewData();
                    break;
                case 3:
                    standing();
                    break;
                case 4:
                    config.saveExit();
                    ex = false;
                    return;
            }

        } while (ex == true);
    }

    private void standing() {
        //
        int no = 1;
        Collections.sort(league, new StandingComparator());
        System.out.println("\t\t\t" + "Malang SuperLeague Standing" + "\t\t");
        System.out.println("| Rank | Club's Name          | Match Played | GD | Points |");
        for (FootballClub club : league) {
            System.out.printf("| %-4d | %-20s | %-12d | %-2d | %-6d |\n",no, club.getName(), club.getMatchesPlayed(), (club.getScoredGoalsCount() - club.getReceivedGoalsCount()), club.getPoints());
            no++;
        }
    }

    private void viewData() {
        int command = 0;
        do {
            System.out.println("\n(View Data)");
            System.out.println("1. View team");
            System.out.println("2. View Player");
            System.out.println("3. View referee");
            System.out.println("4. View Matches");
            System.out.println("5. Back to main menu");
            System.out.println("\nChoice :");
            String line = scanner.nextLine();
            try {
                command = Integer.parseInt(line);
            } catch (Exception e) {
            }
            switch (command) {
                case 1:
                    viewTeam();
                    break;
                case 2:
                    viewPlayer();
                    break;
                case 3:
                    viewReferee();
                    break;
                case 4:
                    viewMatches();
                    break;
            }
        } while (command != 5);
    }

    private void viewMatches() {
        int no =1;
        System.out.println("DATA PERTANDINGAN\n");

        for (Match m : matches){
            System.out.println("Pertandingan KE " + no);
            System.out.printf("%-20s | %-3d VS %-3d | %-20s\n", m.getTeamA().getName(), m.getTeamAScore(), m.getTeamBScore(), m.getTeamB().getName());
            System.out.println("Wasit                | Stadion              | Date                | Kuning | Merah  |");
            System.out.printf("%-20s | %-20s | %-20s | %-2d | %-2d |", m.getReferee(), m.getStadion(), m.getDate(), m.getYellowCard(), m.getRedCard());
            no++;
        }
    }

    private void viewReferee() {
        int no = 1;
        Collections.sort(league);
        System.out.println("Data Referee\n\n");
        System.out.println("=========================");
        System.out.println("| No  | Nama Referee         | Age | Since      | Role                 |");
        for (Referee referee : referee) {
            System.out.printf("| %-3d | %-20s | %-3s | %-10s | %-20s |\n", no, referee.getName(), referee.getAge(), referee.getSince(), referee.getRole());
            no++;
        }
    }

    private void viewPlayer() {
        int number = 0;
        String tim = "";
        do {
            viewTeam();
            System.out.println("Insert Team Numbers");
            number = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < league.size(); i++) {
                if ((i + 1) == number) {
                    tim = league.get(i).getName();
                }
            }
        } while (tim.equals(""));
        System.out.printf("Data Player FC %s\n\n", tim);
        System.out.println("=========================");
        System.out.println("| No | Player Name          | Age | Position   | Red Card | Yellow Card | ");
        for (Player pl : player) {
            if (pl.getTeam().equals(tim)) {
                System.out.printf("| %-2d | %-20s | %-3d | %-10s | %-8d | %-12d|\n",
                        pl.getNumber(),
                        pl.getName(),
                        pl.getAge(),
                        pl.getPosition(),
                        pl.getRedCard(),
                        pl.getYellowCard()
                );
            }
        }
    }

    private void viewTeam() {
        int no = 1;
        Collections.sort(league);
        System.out.println("Data TIM\n\n");
        System.out.println("=========================");
        System.out.println("| No  | Nama Tim             | Alamat               | Pelatih              |");
        for (FootballClub fc : league) {
            System.out.printf("| %-3d | %-20s | %-20s | %-20s |\n", no, fc.getName(), fc.getLocation(), fc.getCoach());
            no++;
        }
    }

    private void createAndDelete() {
        int command = 0;
        do {
            System.out.println("\n(Create and Delete Data)");
            System.out.println("1. Add team");
            System.out.println("2. Add Player");
            System.out.println("3. Add referee");
            System.out.println("4. Add Matches");
            System.out.println("5. Delete data team");
            System.out.println("6. Delete data player");
            System.out.println("7. Back to main menu");
            System.out.println("\nChoice :");
            String line = scanner.nextLine();
            try {
                command = Integer.parseInt(line);
            } catch (Exception e) {
            }

            switch (command) {
                case 1:
                    addTeam();
                    break;
                case 2:
                    addPLayer();
                    break;
                case 3:
                    addReferee();
                    break;
                case 4:
                    addPlayedMatch();
                    break;
                case 5:
                    deleteTeam();
                    break;
                case 6:
                    deletePlayer();
                    break;
            }
        } while (command != 7);
    }

    private void viewAllPlayer() {
        Collections.sort(league);
        System.out.println("Data TIM\n\n");
        System.out.println("=========================");
        System.out.println("| No | Player Name          | Age | Position   | Red Card | Yellow Card | ");
        for (Player pl : player) {
            System.out.printf("| %-2d | %-20s | %-3d | %-10s | %-8d | %-12d|\n",
                    pl.getNumber(),
                    pl.getName(),
                    pl.getAge(),
                    pl.getPosition(),
                    pl.getRedCard(),
                    pl.getYellowCard()
            );
        }
    }

    private void deletePlayer() {
        viewAllPlayer();
        System.out.println("Insert player name: ");
        String line = scanner.nextLine();
        for (Player pl : player) {
            if (pl.getName().equals(line)) {
                player.remove(pl);
                System.out.println("Player " + pl.getName() + " removed from " + pl.getTeam());
                return;
            }
        }
        System.out.println("No such club in league");
    }

    private void addReferee() {
        String nama, since, age, role, rolenumber;

        System.out.println("\nInsert Name: ");
        nama = scanner.nextLine();

        System.out.println("\nInsert Age: ");
        age = scanner.nextLine();

        System.out.println("\nInsert Since: ( dd/MM/yyyy )");
        since = scanner.nextLine();

        do {
            System.out.println("Role");
            System.out.println("1. Referee");
            System.out.println("2. Assistant Referee");
            System.out.println("\nInsert Role's Number: ");
            rolenumber = scanner.nextLine();
            if (rolenumber.equals("1")) {
                role = "Referee";
            } else {
                role = "Assistant Referee";
            }
        } while (!rolenumber.equals("1") && !rolenumber.equals("2"));

        referee.add(new Referee(nama, Integer.parseInt(age), since, role));
    }

    private void addPLayer() {
        int number = 0, numbPlayer;
        String nama, tim = "", posisi, usia, again;
        do {
            viewTeam();
            System.out.println("Insert Team Numbers");
            number = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < league.size(); i++) {
                if ((i + 1) == number) {
                    tim = league.get(i).getName();
                }
            }
        } while (tim.equals(""));
        do {
            do {
                System.out.println("\nInsert Name: ");
                nama = scanner.nextLine();
            } while (nama.length() < 1 || nama.length() > 20);

            do {
                System.out.println("\nInsert position: [goalkeeper, defender, midfield, striker]");
                posisi = scanner.nextLine();
            } while (nama.equals("goalkeeper") || nama.equals("defender") || nama.equals("midfield") || nama.equals("striker"));

            System.out.println("\nInsert Usia: ");
            usia = scanner.nextLine();

            System.out.println("\nInsert Jersey Number : ");
            numbPlayer = Integer.parseInt(scanner.nextLine());

            player.add(new Player(nama, Integer.parseInt(usia), tim, posisi, numbPlayer));

            System.out.println("Player " + nama + " Added to team");
            scanner.nextLine();

            System.out.println("\n Insert player again? (Y/N) ");
            again = scanner.nextLine();
        } while (again.equals("Y"));
        return;
    }

    private void addTeam() {
        String name, location, coach;

        System.out.println("\nInsert Club Name: ");
        name = scanner.nextLine();

        for (FootballClub fc : league) {
            if (fc.getName().equals(name)) {
                System.out.println("This club is already in the league");
                return;
            }
        }

        System.out.println("Insert Club Location: ");
        location = scanner.nextLine();

        System.out.println("Insert Club Coach Name: ");
        coach = scanner.nextLine();

        league.add(new FootballClub(name, location, coach));
        System.out.println("Club " + name + " Added");
        return;
    }

    private void deleteTeam() {
        System.out.println("Insert club name: ");
        String line = scanner.nextLine();
        for (FootballClub club : league) {
            if (club.getName().equals(line)) {
                league.remove(club);
                System.out.println("Club " + club.getName() + " removed");
                return;
            }
        }
        System.out.println("No such club in league");
    }

    private void addPlayedMatch() {
        String refereeName, assistantReferee, stadion, playerName, line;
        int redCard, yellowCard;
        System.out.println("Enter date (format mm-dd-yyyy): ");
        String date = scanner.nextLine();
        viewTeam();
        System.out.println("Enter Home Team: ");
        line = scanner.nextLine();
        FootballClub home = null;
        for (FootballClub club : league) {
            if (club.getName().equals(line))
                home = club;
        }
        if (home == null) {
            System.out.println("No such club in league");
            return;
        }
        System.out.println("Enter Away Team: ");
        line = scanner.nextLine();
        FootballClub away = null;
        for (FootballClub club : league) {
            if (club.getName().equals(line))
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

        viewReferee();
        System.out.println("Enter Referee's Name : ");
        refereeName = scanner.nextLine();
        System.out.println("Enter Assistant Referee's Name : ");
        assistantReferee = scanner.nextLine();
        System.out.println("Enter Stadion Name : ");
        stadion = scanner.nextLine();
        System.out.println("Enter Red Card Amount : ");
        redCard = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < redCard; i++) {
            int ada = 0;
            do {
                System.out.println("Enter player name");
                playerName = scanner.nextLine();
                for (Player pl : player) {
                    if (pl.getName().equals(playerName)) {
                        ada = 1;
                    }
                }
            } while (ada==0);
            for (Player pl : player) {
                if (pl.getName().equals(playerName)) {
                    pl.redCard();
                }
            }
        }
        System.out.println("Enter Yellow Card Amount : ");
        yellowCard = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < yellowCard; i++) {
            int ada = 0;
            do {
                System.out.println("Enter player name");
                playerName = scanner.nextLine();
                for (Player pl : player) {
                    if (pl.getName().equals(playerName)) {
                        ada = 1;
                    }
                }
            } while (ada==0);
            for (Player pl : player) {
                if (pl.getName().equals(playerName)) {
                    pl.yellowCard();
                }
            }
        }

        matches.add(new Match(home, away, homeGoals, awayGoals, refereeName, assistantReferee, stadion, date, redCard, yellowCard));
        home.setScoredGoalsCount(home.getScoredGoalsCount() + homeGoals);
        away.setScoredGoalsCount(away.getScoredGoalsCount() + awayGoals);
        home.setRecievedGoalsCount(home.getReceivedGoalsCount() + awayGoals);
        away.setRecievedGoalsCount(away.getReceivedGoalsCount() + homeGoals);
        home.setMatchesPlayed(home.getMatchesPlayed() + 1);
        away.setMatchesPlayed(away.getMatchesPlayed() + 1);

        if (homeGoals > awayGoals) {
            home.setPoints(home.getPoints() + 3);
            home.setWinCount(home.getWinCount() + 1);
            away.setDefeatCount(away.getDefeatCount() + 1);
        } else if (homeGoals < awayGoals) {
            away.setPoints(away.getPoints() + 3);
            away.setWinCount(away.getWinCount() + 1);
            home.setDefeatCount(home.getDefeatCount() + 1);
        } else {
            home.setPoints(home.getPoints() + 1);
            away.setPoints(away.getPoints() + 1);
            home.setDrawCount(home.getDrawCount() + 1);
            away.setDrawCount(away.getDrawCount() + 1);
        }
    }
}



