package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class SearchResultsPage {
    public static final Target SEARCH_RESULTS_HEADING = Target.the("Search headline")
            .located(By.className("hero-headline"));


}
