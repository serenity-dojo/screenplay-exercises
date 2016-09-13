package serenitylabs.tutorials.trains.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import serenitylabs.tutorials.trains.ui.AssistedTravelPage;

public class EnterHerDetails implements Task{
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SelectFromOptions.byVisibleText("Dr").from(AssistedTravelPage.TITLE)
        );
    }

    public static Performable as(String title, String firstName, String surname) {
        return Instrumented.instanceOf(EnterHerDetails.class).withProperties(title, firstName, surname);
    }
}
