package serenitylabs.tutorials.trains.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import serenitylabs.tutorials.trains.ui.MenuBar;

public class SelectMenu {
    public static Performable option(MenuBar menuOption) {
        return Click.on(menuOption.menuOption());
    }
}
