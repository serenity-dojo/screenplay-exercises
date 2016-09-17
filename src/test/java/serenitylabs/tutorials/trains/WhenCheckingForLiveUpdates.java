package serenitylabs.tutorials.trains;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.questions.Text;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import serenitylabs.tutorials.trains.questions.LiveUpdateIncidents;
import serenitylabs.tutorials.trains.tasks.ChosenTo;
import serenitylabs.tutorials.trains.tasks.ViewTheLiveUpdates;
import serenitylabs.tutorials.trains.ui.LiveUpdates;

import static net.serenitybdd.screenplay.EventualConsequence.eventually;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static net.serenitybdd.screenplay.questions.AggregateQuestions.theTotalNumberOf;
import static org.hamcrest.Matchers.equalTo;
@RunWith(SerenityRunner.class)
public class WhenCheckingForLiveUpdates {

    @Managed
    WebDriver webDriver;

    Actor tracy;

    @Before
    public void setTheStage() {
        tracy = Actor.named("Tracy");
        tracy.can(BrowseTheWeb.with(webDriver));
    }

    @Test
    public void line_updates_should_be_available() {
        givenThat(tracy).has(ChosenTo.checkTheLiveUpdates());

        int lineUpdateBadgeCount = Text.of(LiveUpdates.LINE_UPDATE_BADGE)
                .viewedBy(tracy).asInteger();

        when(tracy).attemptsTo(ViewTheLiveUpdates.forLineUpdate());

        then(tracy).should(
                eventually(
                        seeThat("the number of incident messages",
                                theTotalNumberOf(LiveUpdateIncidents.forLineUpdates()),
                                equalTo(lineUpdateBadgeCount))
                )
        );
    }

    @Test
    public void general_updates_should_be_available() {
        // TODO
    }
}
