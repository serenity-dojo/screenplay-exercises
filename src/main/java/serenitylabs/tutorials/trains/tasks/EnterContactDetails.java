package serenitylabs.tutorials.trains.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.thucydides.core.annotations.Step;
import serenitylabs.tutorials.trains.ui.ContactForm;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class EnterContactDetails implements Performable {

    private final String title;
    private final String firstName;
    private final String lastName;

    public EnterContactDetails(String title, String firstName, String lastName) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Performable forCustomer(String title, String firstName, String lastName) {
        return instrumented(EnterContactDetails.class, title, firstName, lastName);
    }

    @Override
    @Step("{0} enters contact details for #title #firstName #lastName")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SelectFromOptions.byVisibleText(title).from(ContactForm.TITLE),
                Enter.theValue(firstName).into(ContactForm.FIRST_NAME),
                Enter.theValue(lastName).into(ContactForm.LAST_NAME)
        );
    }
}
