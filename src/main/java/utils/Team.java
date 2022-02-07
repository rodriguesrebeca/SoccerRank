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
    private List<Game> gamesAsHomeTeam;
    private List<Game> gamesAsVisitorTeam;

    @Override
    public String toString() {
        return name + ";" + numOfVictories + ";" + numOfDraws + ";" + numOfDefeats + ";" + score + ";" + "\n";
    }

    public void convert(String teamName, List<Game> gamesAsHomeTeam, List<Game> gamesAsVisitorTeam){

        this.name = teamName;
        this.gamesAsHomeTeam = gamesAsHomeTeam;
        this.gamesAsVisitorTeam = gamesAsVisitorTeam;

        gamesAsHomeTeam.forEach(game -> {
                    if (game.getHomeTeamGoals() > game.getVisitorTeamGoals()){
                        numOfVictories ++;
                    }else if (game.getHomeTeamGoals() == game.getVisitorTeamGoals()){
                        numOfDraws ++;
                    }else{
                        numOfDefeats++;
                    }
                });

        gamesAsVisitorTeam.forEach(game -> {
            if (game.getVisitorTeamGoals() > game.getHomeTeamGoals()){
                numOfVictories ++;
            }else if (game.getVisitorTeamGoals() == game.getHomeTeamGoals()){
                numOfDraws++;
            }else if (game.getVisitorTeamGoals() < game.getHomeTeamGoals()) {
                numOfDefeats++;
            }
        });

        calculateScore();
    }

    public void calculateScore(){
        score = (numOfVictories * 3)+ numOfDraws;
    }
}
