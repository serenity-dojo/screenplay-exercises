package serenitylabs.tutorials.trains.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;
import serenitylabs.tutorials.trains.actions.Navigate;
import serenitylabs.tutorials.trains.ui.MainMenu;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ChosenTo implements Task {

    public static Performable bookATicket() {
        return instrumented(ChosenTo.class);
    }

    @Override
    @Step("{0} chooses to book a ticket")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Navigate.to(MainMenu.BuyTickets)
        );
    }
}
