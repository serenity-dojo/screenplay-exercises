package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.screenplay.targets.Target;
import serenitylabs.tutorials.trains.tasks.UpdateType;

import java.util.HashMap;
import java.util.Map;

public class LiveUpdates {

    public static final Target LINE_UPDATE_BADGE = Target.the("Line updates badge").locatedBy("#line_updatesCount");
    public static final Target GENERAL_UPDATE_BADGE = Target.the("General updates badge").locatedBy("#general_updatesCount");

    private static Map<UpdateType, Target> UPDATE_TOGGLES = new HashMap<>();
    private static Map<UpdateType, Target> UPDATE_MESSAGES = new HashMap<>();
    static {
        UPDATE_TOGGLES.put(UpdateType.LineUpdates, Target.the("Line updates button").locatedBy("//span[contains(.,'Line Updates')]"));
        UPDATE_TOGGLES.put(UpdateType.GeneralUpdates, Target.the("General updates button").locatedBy("//span[contains(.,'General Updates')]"));

        UPDATE_MESSAGES.put(UpdateType.LineUpdates, Target.the("Line updates").locatedBy("#line_updates .incident"));
        UPDATE_MESSAGES.put(UpdateType.GeneralUpdates, Target.the("General updates").locatedBy("#general_updates .incident"));
    }

    public static Target toggleForUpdatesOfType(UpdateType updateType) {
        return UPDATE_TOGGLES.get(updateType);
    }

    public static Target messagesForUpdatesOfType(UpdateType updateType) {
        return UPDATE_MESSAGES.get(updateType);
    }
}
