package utils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Team {

    private String name;
    private int score;
    private int numOfVictories;
    private int numOfDefeats;
    private int numOfDraws;
    private List<Game> gameTeamList;

    @Override
    public String toString() {
        return name + ";" + numOfVictories + ";" + numOfDraws + ";" + numOfDefeats + ";" + score + ";" + "\n";
    }

    public void convert(String teamName, List<Game> games){

        name = teamName;
        gameTeamList = games;

        games.forEach(game -> {
                    if (game.getHomeTeamGoals() > game.getVisitorTeamGoals()){
                        numOfVictories ++;
                    }else if (game.getHomeTeamGoals() == game.getVisitorTeamGoals()){
                        numOfDraws ++;
                    }else{
                        numOfDefeats++;
                    }
                });

        calculateScore();
    }

    public void calculateScore(){
        score = (numOfVictories * 3)+ numOfDraws;
    }
}
