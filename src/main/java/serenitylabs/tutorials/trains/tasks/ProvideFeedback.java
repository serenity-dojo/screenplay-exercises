package serenitylabs.tutorials.trains.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import serenitylabs.tutorials.trains.model.FeedbackSubject;

import static serenitylabs.tutorials.trains.ui.MenuBar.HELP_AND_CONTACTS;

public class ProvideFeedback {
    public static Performable about(FeedbackSubject subject) {
        return Task.where("{0} provides feedback about #subject",
                SelectMenu.option(HELP_AND_CONTACTS),
                Click.on(subject.menu())
        ).with("subject").of(subject);
    }
}
