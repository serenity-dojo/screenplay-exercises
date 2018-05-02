package serenitylabs.tutorials.trains.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.targets.TheTarget;
import serenitylabs.tutorials.trains.ui.SearchResultsPage;

public class SearchResults {
    public static Question<String> heading() {
        return TheTarget.textOf(SearchResultsPage.SEARCH_RESULTS_HEADING);
    }
}
