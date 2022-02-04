package services;

import utils.Game;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FileHandler {
    public static  Set<Game> list = new HashSet<>();
    public static  ArrayList<Game> sortedList = new ArrayList<>();

    public static void readFile() {
        final String GAME_PATH = "src/main/resources/soccerlist.csv";
        String line;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(GAME_PATH));
            reader.readLine();

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            while ((line = reader.readLine()) != null) {
                String[] teamLine = line.split(";");

                Game games = new Game(
                        teamLine[0],
                        teamLine[1],
                        (Integer.parseInt(teamLine[2])),
                        (Integer.parseInt(teamLine[3])),
                        LocalDate.parse(teamLine[4],dateTimeFormatter));
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
        sortedList.stream() //deve ser gerado a partir da lista ordenada
                .collect(Collectors.groupingBy(Game::getHomeTeamName))
                .forEach((team,game) -> {
                    try (PrintWriter printWriter = new PrintWriter(
                            new FileOutputStream("src/main/resources/"+team+".csv"))){
                        printWriter.println(
                                "Time: " + team
                        );
                        printWriter.println(
                                "Jogos: " + game
                        );
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                });
    }

}

