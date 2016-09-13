package serenitylabs.tutorials.trains.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Step;
import serenitylabs.tutorials.trains.ui.MainMenu;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ChosenTo implements Task {

    private final MainMenu menuChoice;

    @Override
    @Step("{0} chooses to book a ticket")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(menuChoice.getUrl())
        );
    }

    public ChosenTo(MainMenu menuChoice) {
        this.menuChoice = menuChoice;
    }

    public static Performable bookATicket() {
        return instrumented(ChosenTo.class, MainMenu.BuyTickets);
    }

    public static Performable requestAssistedTravel() {
        return instrumented(ChosenTo.class, MainMenu.GetTravelAssistance);
    }
}
