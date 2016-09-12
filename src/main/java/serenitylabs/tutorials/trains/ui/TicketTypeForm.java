package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class TicketTypeForm {
    public static final Target DEPARTURE = Target.the("Departing From field ").located(By.id("depart-from"));
    public static final Target DESTINATION = Target.the("Going To field ").located(By.id("going-to")) ;
    public static final Target BUY_TICKETS = Target.the("Buy Tickets button").located(By.className("planner__submit"));
}
