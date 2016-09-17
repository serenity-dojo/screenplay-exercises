package serenitylabs.tutorials.trains;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.questions.targets.TheTarget;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import serenitylabs.tutorials.trains.questions.TheOutboundJourneySummary;
import serenitylabs.tutorials.trains.tasks.ChosenTo;
import serenitylabs.tutorials.trains.tasks.EnterHerDetails;
import serenitylabs.tutorials.trains.tasks.ViewTheAvailableTickets;
import serenitylabs.tutorials.trains.ui.AssistedTravelPage;
import serenitylabs.tutorials.trains.ui.TicketTypeForm;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;
import static net.serenitybdd.screenplay.questions.targets.TheTarget.selectOptionsOf;
import static net.serenitybdd.screenplay.questions.targets.TheTarget.selectedValueOf;
import static net.serenitybdd.screenplay.questions.targets.TheTarget.valueOf;
import static org.hamcrest.Matchers.*;

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

        tracy.should(
                seeThat(the(TicketTypeForm.HEADING), isCurrentlyVisible()),
                seeThat(the(TicketTypeForm.HEADING), containsText("Buy tickets")),

                seeThat(
                        TheTarget.attributeNamed("placeholder").forTarget(TicketTypeForm.FROM),
                        equalTo("From")
                ),

                seeThat(
                        TheTarget.cssValueNamed("font").forTarget(TicketTypeForm.HEADING),
                        containsString("sans-serif")
                ),

                seeThat(the(TicketTypeForm.SINGLE), isSelected()),
                seeThat(the(TicketTypeForm.SINGLE), isEnabled()),
                seeThat(the(TicketTypeForm.RETURN), not(isSelected()))
        );
    }

    @Test
    public void return_date_should_be_only_enabled_for_return_trips() {

        tracy.has(ChosenTo.bookATicket());

        tracy.should(
                seeThat(the(TicketTypeForm.RETURN_TIME), not(isEnabled()))
        );

        tracy.attemptsTo(
                Click.on(TicketTypeForm.RETURN_LABEL)
        );

        tracy.should(
                seeThat(the(TicketTypeForm.RETURN_TIME), isEnabled())
        );
    }

    @Test
    public void request_assisted_travel() {
        tracy.has(ChosenTo.requestAssistedTravel());
        tracy.attemptsTo(EnterHerDetails.as("Dr", "Tracy", "Traveller"));

        tracy.should(
                seeThat(valueOf(AssistedTravelPage.FULL_NAME), is("Tracy Traveller")),
                seeThat(selectedValueOf(AssistedTravelPage.TITLE), is("Dr")),
                seeThat(selectOptionsOf(AssistedTravelPage.TITLE), hasItems("Mr", "Miss", "Mrs", "Dr", "Other")),
                seeThat(the(AssistedTravelPage.TITLE), containsSelectOption("Other"))
        );
    }

}
