package utils;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Game {

        private String homeTeamName;
        private String visitorTeamName;
        private int homeTeamGoals;
        private int visitorTeamGoals;
        private LocalDate dateAndTime;


        @Override
        public String toString() {
                return  homeTeamName + " X " + visitorTeamName + " ; " + homeTeamGoals + " X "
                        + visitorTeamGoals + " - " + dateAndTime;

        }
}

