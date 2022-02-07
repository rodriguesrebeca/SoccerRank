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
    private static final Set<Game> gamesList = new HashSet<>();
    private static final ArrayList<Game> sortedGamesList = new ArrayList<>();
    private static Map<String, List<Game>> gamesMap;
    private static final Set<Team> ranking = new HashSet<>();
    private static final ArrayList<Team> sortedRanking = new ArrayList<>();

    public static void readFile(String path) {
        String line;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                List<String> teamLine = Splitter.on(";").splitToList(line);

                Game game = new Game(
                        teamLine.get(0),
                        teamLine.get(1),
                        (Integer.parseInt(teamLine.get(2))),
                        (Integer.parseInt(teamLine.get(3))),
                        (LocalDate.parse(teamLine.get(4))));
                FileHandler.gamesList.add(game);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sortedList(){
        sortedGamesList.addAll(gamesList);
        sortedGamesList.sort(Comparator.comparing(Game::getDateAndTime)
                .thenComparing(Game::getHomeTeamName).
                thenComparing(Game::getVisitorTeamName));
    }

    public static void separateByTeams(){
        gamesMap = sortedGamesList.stream()
                .collect(Collectors.groupingBy(Game::getHomeTeamName));

        Map<String, List<Game>> gamesVisitorTeamMap = sortedGamesList.stream()
                .collect(Collectors.groupingBy(Game::getVisitorTeamName));

        gamesMap.forEach((teamName,games)->{
            Team team = new Team();
            team.convert(teamName, games, gamesVisitorTeamMap.get(teamName));
            ranking.add(team);});
    }


    public static void generateTeamsFile() {
        gamesMap.forEach((teamName, games) -> {
            try (PrintWriter printWriter = new PrintWriter(
                    new FileOutputStream("src/main/resources/"+teamName+".csv"))){
                printWriter.println(
                        "Time: " + teamName + games.toString()
                );
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

