package serenitylabs.tutorials.trains.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import serenitylabs.tutorials.trains.ui.LiveUpdates;

import java.util.List;

public class LiveUpdateIncidents {
    public static Question<List<String>> forLineUpdates() {
        return actor -> Text.of(LiveUpdates.LINE_UPDATES).viewedBy(actor).asList();
    }
}
