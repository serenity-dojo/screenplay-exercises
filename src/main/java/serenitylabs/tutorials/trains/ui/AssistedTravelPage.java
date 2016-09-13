package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://www.southwesttrains.co.uk/contact--help/contact-us/assisted-travel-form")
public class AssistedTravelPage extends PageObject {
    public static final Target TITLE = Target.the("title").locatedBy("//div[label[contains(.,'Title')]]/select");
}
