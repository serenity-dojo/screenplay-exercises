package serenitylabs.tutorials.trains;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.serenitybdd.screenplay.questions.targets.TheTarget;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import serenitylabs.tutorials.trains.ui.*;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;
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

        carrie.attemptsTo(
                Open.browserOn().the(TFLHomePage.class),
                Enter.theValue("Waterloo").into(TFLHomePage.SEARCH).thenHit(Keys.ENTER)
        );

        carrie.should(seeThat(TheTarget.textOf(SearchResultsPage.SEARCH_RESULTS_HEADING), equalTo("Search: Waterloo")));
    }

    @Test
    public void should_see_status_updates() {
        carrie.attemptsTo(
                Open.browserOn().the(TFLHomePage.class),
                Click.on(STATUS_UPDATES.menuOption())
        );

        carrie.should(seeThat(TheTarget.textValuesOf(StatusUpdatePage.SERVICE_LINES), hasItems("Bakerloo", "Circle","Central")));
    }


    @Test
    public void should_be_able_to_contact_tfl() {

        carrie.attemptsTo(
                Open.browserOn().the(TFLHomePage.class)
        );
        carrie.attemptsTo(
                Click.on(HELP_AND_CONTACTS.menuOption()),
                Click.on(HelpAndContacts.AboutOyster.TFLApp)
        );

        carrie.attemptsTo(
                SelectFromOptions.byVisibleText("Mrs").from(ContactForm.TITLE),
                Enter.theValue("Sarah-Jane").into(ContactForm.FIRST_NAME),
                Enter.theValue("Smith").into(ContactForm.LAST_NAME)
        );

        carrie.should(
                seeThat(TheTarget.selectedValueOf(ContactForm.TITLE), equalTo("Mrs")),
                seeThat(TheTarget.valueOf(ContactForm.FIRST_NAME), equalTo("Sarah-Jane")),
                seeThat(TheTarget.valueOf(ContactForm.LAST_NAME), equalTo("Smith"))
        );
    }
}
