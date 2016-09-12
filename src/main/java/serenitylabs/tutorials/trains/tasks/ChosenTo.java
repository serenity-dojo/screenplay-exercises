package serenitylabs.tutorials.trains.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Step;
import serenitylabs.tutorials.trains.ui.BuyTicketsPage;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ChosenTo implements Task {

    public static Performable bookATicket() {
        return instrumented(ChosenTo.class);
    }

    private BuyTicketsPage theBuyTicketsPage;

    @Override
    @Step("{0} chooses to book a ticket")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn(theBuyTicketsPage)
        );
    }
}
