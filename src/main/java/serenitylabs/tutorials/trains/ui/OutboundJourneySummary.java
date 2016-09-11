package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.screenplay.targets.Target;

public class OutboundJourneySummary {
    public static Target ORIGIN = Target.the("Departure").locatedBy(".planner-header__origin");
    public static Target DESTINATION = Target.the("Departure").locatedBy(".planner-header__destination");
}
