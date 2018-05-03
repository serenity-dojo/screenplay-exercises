package serenitylabs.tutorials.trains;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import serenitylabs.tutorials.trains.questions.SearchResults;
import serenitylabs.tutorials.trains.questions.TheContactDetails;
import serenitylabs.tutorials.trains.questions.TheServiceLines;
import serenitylabs.tutorials.trains.tasks.EnterContactDetails;
import serenitylabs.tutorials.trains.tasks.ProvideFeedback;
import serenitylabs.tutorials.trains.tasks.Search;
import serenitylabs.tutorials.trains.tasks.SelectMenu;
import serenitylabs.tutorials.trains.ui.HelpAndContacts;
import serenitylabs.tutorials.trains.ui.TFLHomePage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;
import static serenitylabs.tutorials.trains.model.FeedbackSubject.TheOysterApp;
import static serenitylabs.tutorials.trains.ui.MenuBar.HELP_AND_CONTACTS;
import static serenitylabs.tutorials.trains.ui.MenuBar.STATUS_UPDATES;

@RunWith(SerenityRunner.class)
public class WhenPlanningATrip {

    @Managed
    WebDriver browser;

    Actor carrie = Actor.named("Carrie");

    @Before
    public void setTheStage() {
        carrie.can(BrowseTheWeb.with(browser));

        carrie.attemptsTo(
                Open.browserOn().the(TFLHomePage.class)
        );
    }

    @Test
    public void the_TFL_page_title_should_be_visible() {

        carrie.should(
                seeThat(
                        TheWebPage.title(),
                        containsString("Transport for London")));
    }

    @Test
    public void should_be_able_to_search_for_station_details() {

        carrie.attemptsTo(Search.forStation("Waterloo"));

        carrie.should(seeThat(SearchResults.heading(), equalTo("Search: Waterloo")));
    }

    @Test
    public void should_see_status_updates() {
        carrie.attemptsTo(SelectMenu.option(STATUS_UPDATES));

        carrie.should(seeThat(TheServiceLines.displayed(), hasItems("Bakerloo", "Circle","Central")));
    }


    @Test
    public void should_be_able_to_contact_tfl() {

        carrie.attemptsTo(
                SelectMenu.option(HELP_AND_CONTACTS),
                ProvideFeedback.about(TheOysterApp),
                EnterContactDetails.forCustomer("Mrs","Sarah-Jane","Smith")
        );

        carrie.should(
                seeThat(TheContactDetails.title(), equalTo("Mrs")),
                seeThat(TheContactDetails.firstName(), equalTo("Sarah-Jane")),
                seeThat(TheContactDetails.lastName(), equalTo("Smith"))
        );
    }
}
