package serenitylabs.tutorials.trains.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import serenitylabs.tutorials.trains.ui.AssistedTravelPage;

public class EnterHerDetails implements Task{

    private final String title;
    private final String firstName;
    private final String lastName;

    public EnterHerDetails(String title, String firstName, String lastName) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SelectFromOptions.byVisibleText("Dr").from(AssistedTravelPage.TITLE),
                Enter.theValue(firstName + " " + lastName).into(AssistedTravelPage.FULL_NAME)
        );
    }

    public static Performable as(String title, String firstName, String surname) {
        return Instrumented.instanceOf(EnterHerDetails.class).withProperties(title, firstName, surname);
    }
}
