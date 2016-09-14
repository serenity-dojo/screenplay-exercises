package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class TicketTypeForm {
    public static final Target FROM = Target.the("Departing From field ").located(By.id("depart-from"));
    public static final Target TO = Target.the("Going To field ").located(By.id("going-to")) ;
    public static final Target BUY_TICKETS = Target.the("Buy Tickets button").located(By.className("planner__submit"));
    public static final Target HEADING = Target.the("Buy Tickets Heading").locatedBy(".planner__title");
    public static final Target SINGLE = Target.the("Single trip").locatedBy("#single");
    public static final Target RETURN = Target.the("Return trip").locatedBy("#return");
    public static final Target RETURN_LABEL = Target.the("Return trip").locatedBy("//label[text()='Return']");

    public static final Target RETURN_TIME = Target.the("Inbound time").located(By.name("InboundTime"));
}
