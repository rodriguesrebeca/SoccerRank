import services.FileHandler;

public class SoccerRank {

    public static void main(String[] args) {
        FileHandler.readFile();
        FileHandler.sortedList();
        FileHandler.separateByTeams();

        //FileHandler.sortedList.forEach(System.out::println);
    }
}
