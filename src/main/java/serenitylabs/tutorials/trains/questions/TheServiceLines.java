package serenitylabs.tutorials.trains.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.targets.TheTarget;
import serenitylabs.tutorials.trains.ui.StatusUpdatePage;

import java.util.List;

public class TheServiceLines {
    public static Question<List<String>> displayed() {
        return TheTarget.textValuesOf(StatusUpdatePage.SERVICE_LINES);
    }
}
