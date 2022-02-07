import services.FileHandler;

public class SoccerRank {

    public static void main(String[] args) {
        FileHandler.readFile("src/main/resources/matchesResult.csv");
        FileHandler.sortedList();
        FileHandler.separateByTeams();
        FileHandler.generateTeamsFile();
        FileHandler.generateRanking();
    }
}
