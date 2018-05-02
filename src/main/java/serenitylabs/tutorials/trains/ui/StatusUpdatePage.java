package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.screenplay.targets.Target;

public class StatusUpdatePage {
    public static final Target SERVICE_LINES = Target.the("Service Lines")
            .locatedBy(".service-name span");
}
