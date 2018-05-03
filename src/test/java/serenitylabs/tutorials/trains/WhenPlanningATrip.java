package serenitylabs.tutorials.trains;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.serenitybdd.screenplay.questions.targets.TheTarget;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import serenitylabs.tutorials.trains.ui.SearchResultsPage;
import serenitylabs.tutorials.trains.ui.TFLHomePage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class WhenPlanningATrip {

    @Managed
    WebDriver browser;

    Actor carrie = Actor.named("Carrie");

    @Before
    public void setTheStage() {
        carrie.can(BrowseTheWeb.with(browser));

    }

    @Test
    public void the_TFL_page_title_should_be_visible() {

        carrie.attemptsTo(Open.browserOn().the(TFLHomePage.class));

        carrie.should(
                seeThat(
                        TheWebPage.title(),
                        containsString("Transport for London")));
    }

    @Test
    public void should_be_able_to_search_for_station_details() {

        carrie.attemptsTo(
                Open.browserOn().the(TFLHomePage.class),
                Enter.theValue("Waterloo").into(TFLHomePage.SEARCH).thenHit(Keys.ENTER)
        );

        carrie.should(
                seeThat(
                        TheTarget.textOf(SearchResultsPage.SEARCH_RESULTS_HEADING), equalTo("Search: Waterloo"))
        );
    }

}
