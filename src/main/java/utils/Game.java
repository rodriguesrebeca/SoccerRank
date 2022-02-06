package utils;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return "\n" + dateTimeFormatter.format(dateAndTime) + ": " + homeTeamName + " " + homeTeamGoals +
                        " X " + visitorTeamGoals + " " + visitorTeamName;

        }
}

