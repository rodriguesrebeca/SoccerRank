package utils;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Game {

        private String homeTeamName;
        private String visitorTeamName;
        private int homeTeamGoals;
        private int visitorTeamGoals;
        private String dateAndTime; //mudar para LocalDate


        @Override
        public String toString() {
            return homeTeamName + " X " + visitorTeamName + " ; " + homeTeamGoals + " X "
                    + visitorTeamGoals + " - " + dateAndTime;

        }
}
