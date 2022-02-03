package services;

import utils.Game;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FileHandler {
    public static  Set<Game> list = new HashSet<>();


    public static Game readFile() {
        final String GAME_PATH = "src/main/resources/soccerlist.csv";
        String line;

        Game games = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(GAME_PATH));
            line = reader.readLine(); //para não ler a primeira linha(cabeçalho)

            while ((line = reader.readLine()) != null) {
                String[] teamLine = line.split(";");

                games = new Game(
                        teamLine[0],
                        teamLine[1],
                        (Integer.parseInt(teamLine[2])),
                        (Integer.parseInt(teamLine[3])),
                        teamLine[4]);
                list.add(games);
                //                   System.out.println(games);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return games;
    }

    public static void separateByTeams() {
        list.stream()
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

