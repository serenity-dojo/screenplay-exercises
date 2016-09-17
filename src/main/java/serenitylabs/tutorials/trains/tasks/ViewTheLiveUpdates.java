package serenitylabs.tutorials.trains.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;
import serenitylabs.tutorials.trains.ui.LiveUpdates;

public class ViewTheLiveUpdates implements Task {

    @Override
    @Step("{0} views the live updates for #updateType")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(LiveUpdates.LINE_UPDATE_TOGGLE)
        );
    }

    public static Performable forLineUpdate() {
        return Instrumented.instanceOf(ViewTheLiveUpdates.class).newInstance();
    }
}
