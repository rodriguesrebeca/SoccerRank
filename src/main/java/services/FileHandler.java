package services;

import com.google.common.base.Splitter;
import utils.Game;
import utils.Team;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class FileHandler {
    public static  Set<Game> list = new HashSet<>();
    public static  ArrayList<Game> sortedList = new ArrayList<>();
    public static Set<Team> ranking = new HashSet<>();
    public static  ArrayList<Team> sortedRanking = new ArrayList<>();

    public static void readFile() {
        final String GAME_PATH = "src/main/resources/matchesResult.csv";
        String line;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(GAME_PATH));
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                List<String> teamLine = Splitter.on(";").splitToList(line);

                Game games = new Game(
                        teamLine.get(0),
                        teamLine.get(1),
                        (Integer.parseInt(teamLine.get(2))),
                        (Integer.parseInt(teamLine.get(3))),
                        (LocalDate.parse(teamLine.get(4))));
                list.add(games);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sortedList(){
        sortedList.addAll(list);
        sortedList.sort(Comparator.comparing(Game::getDateAndTime)
                .thenComparing(Game::getHomeTeamName).
                thenComparing(Game::getVisitorTeamName));
    }

    public static void separateByTeams() {
        Map<String, List<Game>> collect = sortedList.stream()
                .collect(Collectors.groupingBy(Game::getHomeTeamName));

        collect.forEach((teamName,games) -> {
            try (PrintWriter printWriter = new PrintWriter(
                    new FileOutputStream("src/main/resources/"+teamName+".csv"))){
                printWriter.println(
                        "Time: " + teamName + games.toString()
                );

                Team team = new Team();
                team.convert(teamName,games);
                ranking.add(team);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    public static void generateRanking(){
        sortedRanking.addAll(ranking);
        sortedRanking.stream()
                .sorted(Comparator.comparing(Team::getScore, Comparator.reverseOrder())
                        .thenComparing(Team::getNumOfVictories,Comparator.reverseOrder()))
                .forEach(team -> {
                    try (FileOutputStream fileOutputStream = new FileOutputStream( "src/main/resources/_ranking.csv", true)) {
                        fileOutputStream.write(team.toString().getBytes(StandardCharsets.UTF_8));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}

