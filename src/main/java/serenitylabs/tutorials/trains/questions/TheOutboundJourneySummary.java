package serenitylabs.tutorials.trains.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.targets.TheTarget;
import serenitylabs.tutorials.trains.ui.OutboundJourneySummary;


public class TheOutboundJourneySummary {
    public static Question<String> origin() {
        return TheTarget.textOf(OutboundJourneySummary.ORIGIN);
    }

    public static Question<String> destination() {
        return TheTarget.textOf(OutboundJourneySummary.DESTINATION);
    }
}
