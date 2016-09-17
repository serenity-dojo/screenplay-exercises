package serenitylabs.tutorials.trains.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;
import serenitylabs.tutorials.trains.ui.LiveUpdates;

import static serenitylabs.tutorials.trains.tasks.UpdateType.GeneralUpdates;
import static serenitylabs.tutorials.trains.tasks.UpdateType.LineUpdates;

public class ViewTheLiveUpdates implements Task {

    public ViewTheLiveUpdates(UpdateType updateType) {
        this.updateType = updateType;
    }

    private final UpdateType updateType;

    @Override
    @Step("{0} views the live updates for #updateType")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(LiveUpdates.toggleForUpdatesOfType(updateType))
        );
    }

    public static Performable forLineUpdate() {
        return Instrumented.instanceOf(ViewTheLiveUpdates.class).withProperties(LineUpdates);
    }

    public static Performable forGeneralUpdates() {
        return Instrumented.instanceOf(ViewTheLiveUpdates.class).withProperties(GeneralUpdates);
    }
}
