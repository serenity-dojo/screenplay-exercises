package serenitylabs.tutorials.trains.model;

import net.serenitybdd.screenplay.targets.Target;
import serenitylabs.tutorials.trains.ui.HelpAndContacts;

public enum FeedbackSubject {
    TheOysterApp(HelpAndContacts.AboutOyster.TFLApp);

    private final Target target;

    FeedbackSubject(Target target) {
        this.target = target;
    }

    public Target menu() {
        return target;
    }
}
