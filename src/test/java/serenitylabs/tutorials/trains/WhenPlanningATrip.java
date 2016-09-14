package serenitylabs.tutorials.trains;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import serenitylabs.tutorials.trains.questions.TheOutboundJourneySummary;
import serenitylabs.tutorials.trains.tasks.ChosenTo;
import serenitylabs.tutorials.trains.tasks.EnterHerDetails;
import serenitylabs.tutorials.trains.tasks.ViewTheAvailableTickets;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

@RunWith(SerenityRunner.class)
public class WhenPlanningATrip {

    @Managed
    WebDriver webDriver;

    Actor tracy;

    @Before
    public void setTheStage() {
        tracy = Actor.named("Tracy");
        tracy.can(BrowseTheWeb.with(webDriver));
    }

    @Test
    public void booking_a_simple_trip() {

        tracy.has(ChosenTo.bookATicket());

        // WHEN
        tracy.attemptsTo(
                ViewTheAvailableTickets.from("London Paddington").to("Oxford")
        );

        // THEN
        tracy.should(
                seeThat(TheOutboundJourneySummary.origin(), is("London Paddington")),
                seeThat(TheOutboundJourneySummary.destination(), is("Oxford"))
        );
    }

    @Test
    public void sensible_default_trip_options_are_proposed() {
        tracy.has(ChosenTo.bookATicket());
        // TODO
    }

    @Test
    public void return_date_should_be_only_enabled_for_return_trips() {
        tracy.has(ChosenTo.bookATicket());
        // TODO
    }

    @Test
    public void request_assisted_travel() {
        tracy.has(ChosenTo.requestAssistedTravel());
        tracy.attemptsTo(EnterHerDetails.as("Dr", "Tracy", "Traveller"));

        // TODO
    }
}
