package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.screenplay.targets.Target;

public class LiveUpdates {

    public static final Target LINE_UPDATE_BADGE = Target.the("Line updates badge").locatedBy("#line_updatesCount");
    public static final Target LINE_UPDATE_TOGGLE = Target.the("Line updates button").locatedBy("//span[contains(.,'Line Updates')]");
    public static final Target LINE_UPDATES = Target.the("Line updates").locatedBy("#line_updates .incident");
}
