package serenitylabs.tutorials.trains.actions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Open;
import serenitylabs.tutorials.trains.ui.BuyTicketsPage;
import serenitylabs.tutorials.trains.ui.MainMenu;

public class Navigate implements Interaction {

    private final MainMenu menuChoice;

    BuyTicketsPage buyTicketsPage;

    public Navigate(MainMenu menuChoice) {
        this.menuChoice = menuChoice;
    }

    public static Performable to(MainMenu menuChoice) {
        return Instrumented.instanceOf(Navigate.class).withProperties(menuChoice);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.browserOn(buyTicketsPage));
    }
}
