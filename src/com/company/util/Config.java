package com.company.util;

import com.company.model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Config {
    private final ArrayList<FootballClub> team;
    private final ArrayList<Match> match;
    private final ArrayList<Player> player;
    private final ArrayList<Referee> referee;

    public Config(ArrayList<FootballClub> team, ArrayList<Match> match, ArrayList<Player> player, ArrayList<Referee> referee) {
        this.team = team;
        this.match = match;
        this.player = player;
        this.referee = referee;
    }

    public boolean checkDataExist() {
        return !team.isEmpty() && !match.isEmpty() && !player.isEmpty() && !referee.isEmpty();
    }

    public void addDefaultFile() {
        if (new File(Path.F_TEAM).exists()) {
            readJson(Path.F_TEAM);
        }
        if (new File(Path.F_MATCH).exists()) {
            readJson(Path.F_MATCH);
        }
        if (new File(Path.F_REFEREE).exists()) {
            readJson(Path.F_REFEREE);
        }
        if (new File(Path.F_PLAYER).exists()) {
            readJson(Path.F_PLAYER);
        }
    }

    public void readJson(String filename) {
        JSONParser json = new JSONParser();

        try(FileReader file = new FileReader(filename)) {
            Object object = json.parse(file);
            JSONArray arr = (JSONArray) object;

            switch (filename) {
                case Path.F_TEAM:
                    arr.forEach(o -> {
                        getJsonTeam((JSONObject) o);
                    });
                    break;
                case Path.F_PLAYER:
                    arr.forEach(o -> {
                        getJsonPlayer((JSONObject) o);
                    });
                    break;
                case Path.F_REFEREE:
                    arr.forEach(o -> {
                        getJsonReferee((JSONObject) o);
                    });
                    break;
                case Path.F_MATCH:
                    arr.forEach(o -> {
                        getJsonMatch((JSONObject) o);
                    });
                    break;
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void getJsonPlayer(JSONObject object) {
        JSONObject obj = (JSONObject) object.get("player");

        String name = (String) obj.get("name");
        int age = Integer.parseInt(String.valueOf(obj.get("age")));
        int number = Integer.parseInt(String.valueOf(obj.get("number")));
        String team = (String) obj.get("team");
        String position = (String) obj.get("position");
        int redCard = Integer.parseInt(String.valueOf(obj.get("redCard")));
        int yellowCard = Integer.parseInt(String.valueOf(obj.get("yellowCard")));

        player.add(new Player(name, age, number, team, position, redCard, yellowCard));
    }

    private void getJsonReferee(JSONObject object) {
        JSONObject obj = (JSONObject) object.get("referee");

        String name = (String) obj.get("name");
        int age = Integer.parseInt(String.valueOf(obj.get("age")));
        String since = (String) obj.get("since");
        String role = (String) obj.get("role");

        referee.add(new Referee(name, age, since, role));
    }

    private void getJsonTeam(JSONObject object) {
        JSONObject obj = (JSONObject) object.get("team");

        String name = (String) obj.get("name");
        String location = (String) obj.get("location");
        String coach = (String) obj.get("coach");
        int winCount = Integer.parseInt(String.valueOf(obj.get("winCount")));
        int drawCount = Integer.parseInt(String.valueOf(obj.get("drawCount")));
        int defeatCount = Integer.parseInt(String.valueOf(obj.get("defeatCount")));
        int scoredGoalCount = Integer.parseInt(String.valueOf(obj.get("scoredGoalCount")));
        int receivedGoalCount = Integer.parseInt(String.valueOf(obj.get("receivedGoalCount")));
        int points = Integer.parseInt(String.valueOf(obj.get("points")));
        int matchesPlayed = Integer.parseInt(String.valueOf(obj.get("matchesPlayed")));

        team.add(new FootballClub(name, location, coach, winCount, drawCount, defeatCount, scoredGoalCount, receivedGoalCount, points, matchesPlayed));
    }

    private void getJsonMatch(JSONObject object) {
        JSONObject obj = (JSONObject) object.get("match");

        String nameA = (String) obj.get("nameTeamA");
        String locationA = (String) obj.get("locationTeamA");
        String coachA = (String) obj.get("coachTeamA");
        int winCountA = Integer.parseInt(String.valueOf(obj.get("winTeamA")));
        int drawCountA = Integer.parseInt(String.valueOf(obj.get("drawTeamA")));
        int defeatCountA = Integer.parseInt(String.valueOf(obj.get("defeatTeamA")));
        int scoredA = Integer.parseInt(String.valueOf(obj.get("scoredTeamA")));
        int receivedA = Integer.parseInt(String.valueOf(obj.get("receivedTeamA")));
        int pointsA = Integer.parseInt(String.valueOf(obj.get("pointsTeamA")));
        int matchA = Integer.parseInt(String.valueOf(obj.get("matchTeamA")));

        String nameB = (String) obj.get("nameTeamB");
        String locationB = (String) obj.get("locationTeamB");
        String coachB = (String) obj.get("coachTeamB");
        int winCountB = Integer.parseInt(String.valueOf(obj.get("winTeamB")));
        int drawCountB = Integer.parseInt(String.valueOf(obj.get("drawTeamB")));
        int defeatCountB = Integer.parseInt(String.valueOf(obj.get("defeatTeamB")));
        int scoredB = Integer.parseInt(String.valueOf(obj.get("scoredTeamB")));
        int receivedB = Integer.parseInt(String.valueOf(obj.get("receivedTeamB")));
        int pointsB = Integer.parseInt(String.valueOf(obj.get("pointsTeamB")));
        int matchB = Integer.parseInt(String.valueOf(obj.get("matchTeamB")));

        int teamAScore = Integer.parseInt(String.valueOf(obj.get("teamAScore")));
        int teamBScore = Integer.parseInt(String.valueOf(obj.get("teamBScore")));
        String referee = (String) obj.get("referee");
        String assistantReferee = (String) obj.get("assistantReferee");
        String stadion = (String) obj.get("stadion");
        String date = (String) obj.get("date");
        int redCard = Integer.parseInt(String.valueOf(obj.get("redCard")));
        int yellowCard = Integer.parseInt(String.valueOf(obj.get("yellowCard")));

        match.add(new Match(
                new FootballClub(nameA, locationA, coachA, winCountA, drawCountA, defeatCountA, scoredA, receivedA, pointsA, matchA),
                new FootballClub(nameB, locationB, coachB, winCountB, drawCountB, defeatCountB, scoredB, receivedB, pointsB, matchB),
                teamAScore,
                teamBScore,
                referee,
                assistantReferee,
                stadion,
                date,
                redCard,
                yellowCard
        ));
    }

    public void saveExit() {
        JSONArray arr;

        if (!team.isEmpty()) {
            arr = new JSONArray();

            for (FootballClub fc : team) {
                JSONObject obj = new JSONObject();
                obj.put("name", fc.getName());
                obj.put("location", fc.getLocation());
                obj.put("coach", fc.getCoach());
                obj.put("winCount", fc.getWinCount());
                obj.put("drawCount", fc.getDrawCount());
                obj.put("defeatCount", fc.getDefeatCount());
                obj.put("scoredGoalCount", fc.getScoredGoalsCount());
                obj.put("receivedGoalCount", fc.getReceivedGoalsCount());
                obj.put("points", fc.getPoints());
                obj.put("matchesPlayed", fc.getMatchesPlayed());

                JSONObject objects = new JSONObject();
                objects.put("team", obj);
                arr.add(objects);
            }
            try (FileWriter fr = new FileWriter(Path.F_TEAM)){
                fr.write(arr.toJSONString());
                fr.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!player.isEmpty()) {
            arr = new JSONArray();

            for (Player pl : player) {
                JSONObject obj = new JSONObject();
                obj.put("name", pl.getName());
                obj.put("age", pl.getAge());
                obj.put("number", pl.getNumber());
                obj.put("team", pl.getTeam());
                obj.put("position", pl.getPosition());
                obj.put("redCard", pl.getRedCard());
                obj.put("yellowCard", pl.getYellowCard());

                JSONObject objects = new JSONObject();
                objects.put("player", obj);
                arr.add(objects);
            }
            try (FileWriter fr = new FileWriter(Path.F_PLAYER)){
                fr.write(arr.toJSONString());
                fr.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!match.isEmpty()) {
            arr = new JSONArray();

            for (Match match : match) {
                JSONObject obj = new JSONObject();
                obj.put("nameTeamA", match.getTeamA().getName());
                obj.put("locationTeamA", match.getTeamA().getLocation());
                obj.put("coachTeamA", match.getTeamA().getCoach());
                obj.put("winTeamA", match.getTeamA().getWinCount());
                obj.put("drawTeamA", match.getTeamA().getDrawCount());
                obj.put("defeatTeamA", match.getTeamA().getDefeatCount());
                obj.put("scoredTeamA", match.getTeamA().getScoredGoalsCount());
                obj.put("receivedTeamA", match.getTeamA().getReceivedGoalsCount());
                obj.put("pointsTeamA", match.getTeamA().getPoints());
                obj.put("matchTeamA", match.getTeamA().getMatchesPlayed());

                obj.put("nameTeamB", match.getTeamB().getName());
                obj.put("locationTeamB", match.getTeamB().getLocation());
                obj.put("coachTeamB", match.getTeamB().getCoach());
                obj.put("winTeamB", match.getTeamB().getWinCount());
                obj.put("drawTeamB", match.getTeamB().getDrawCount());
                obj.put("defeatTeamB", match.getTeamB().getDefeatCount());
                obj.put("scoredTeamB", match.getTeamB().getScoredGoalsCount());
                obj.put("receivedTeamB", match.getTeamB().getReceivedGoalsCount());
                obj.put("pointsTeamB", match.getTeamB().getPoints());
                obj.put("matchTeamB", match.getTeamB().getMatchesPlayed());

                obj.put("teamAScore", match.getTeamAScore());
                obj.put("teamBScore", match.getTeamBScore());
                obj.put("referee", match.getReferee());
                obj.put("assistantReferee", match.getAssistantReferee());
                obj.put("stadion", match.getStadion());
                obj.put("date", match.getDate());
                obj.put("redCard", match.getRedCard());
                obj.put("yellowCard", match.getYellowCard());

                JSONObject objects = new JSONObject();
                objects.put("match", obj);
                arr.add(objects);
            }
            try (FileWriter fr = new FileWriter(Path.F_MATCH)){
                fr.write(arr.toJSONString());
                fr.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!referee.isEmpty()) {
            arr = new JSONArray();

            for (Referee ref : referee) {
                JSONObject obj = new JSONObject();
                obj.put("name", ref.getName());
                obj.put("age", ref.getAge());
                obj.put("since", ref.getSince());
                obj.put("role", ref.getRole());

                JSONObject objects = new JSONObject();
                objects.put("referee", obj);
                arr.add(objects);
            }
            try (FileWriter fr = new FileWriter(Path.F_REFEREE)){
                fr.write(arr.toJSONString());
                fr.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
