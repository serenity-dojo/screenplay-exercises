These exercises are part of the [Serenity Dojo](http://www.serenity-dojo.com/) training program, and are designed to help developers and testers learn how to work with Serenity Screenplay on real-world applications.

## The problem domain

In this series of exercises, we will be building up a test suite for a real-world web site: [SouthWestTrains](https://www.southwesttrains.co.uk/). Each tutorial will focus on implementing a particular test scenario. 

## Problem definition

The aim of this scenario is to view the available train trips between London Paddington and Oxford stations. You will be implementing the `booking_a_simple_trip` scenario in the `WhenPlanningATrip` class. 

### Step 1 - Add the Serenity Screenplay WebDriverdependency

Add the latest Serenity Screenplay WebDriver dependency to the `pom.xml` file, e.g.

```
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-screenplay-webdriver</artifactId>
            <version>${serenity.version}</version>
        </dependency>
```
 

### Step 2 - Design the test

Write a simple, high-level outline, in comments of what the test should do from the actor's perspective. For example, if we are thinking from the perspective of Tracy the Traveller, we could write:

```
    @Test
    public void booking_a_simple_trip() {

       // Tracy decides to book a ticket
       // Tracy views the tickets from London Paddington to Oxford
       // Tracy should see that the departure and destination stations are correctly displayed
    }
```

### Step 3 - Make this a web test

Add a `@Managed` WebDriver field to the `WhenPlanningATrip` class:

```
    @Managed
    WebDriver webDriver;
```

### Step 4 - Add an actor 

In a `@Before` method, add an actor called Tracy who has the ability to browse the web with the WebDriver instance, e.g.

```
    Actor tracy;

    @Before
    public void setTheStage() {
        tracy = Actor.named("Tracy");
        tracy.can(BrowseTheWeb.with(webDriver));
    }
```


### Step 5 - Implement the first step
 
Implement the step where Tracy navigates to the Buy Tickets page. As this is a pre-condition, use the `Actor`'s `has()` method. Imagine a class and static method name combination that describes the intent behind opening the Buy Tickets page, e.g. `ChosenTo.bookATicket()` as shown here:

    @Test
    public void booking_a_simple_trip() {

       tracy.has(ChosenTo.bookATicket());
        
       // Tracy views the tickets from London Paddington to Oxford
       // Tracy should see that the departure and destination stations are correctly displayed
    }
    
#### Implementing the Task class

Now create your `Task` class (`ChosenTo` in the example above) in the `serenitylabs.tutorials.trains.tasks` package. Make sure it implements the `Task` interface:

```
public class ChosenTo implements Task {}
```

#### Implementing the performAs() method

Next, implement a method skeleton for the `performAs` method defined in the `Task` interface:

    public class ChosenTo implements Task {
    
        @Override
        public <T extends Actor> void performAs(T actor) {}
    }
    
#### Implementing the static builder

Implement the static builder method that you defined in your test, and be sure to return an instrumented instance of your class:

    import static net.serenitybdd.screenplay.Tasks.instrumented;
    
    public class ChosenTo implements Task {
    
        public static Performable bookATicket() {
            return instrumented(ChosenTo.class);
        }
    
        @Override
        @Step("{0} chooses to book a ticket")
        public <T extends Actor> void performAs(T actor) {}
    }
    
#### Opening the Buy Tickets page    
    
Now use the `Open` interaction class inside the `performAs()` method to open the Buy Tickets page. Use a simple Page Object to represent the Buy Tickets page:

    private BuyTicketsPage theBuyTicketsPage;

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn(theBuyTicketsPage)
        );
    }

#### Implementing the Page Object

And implement the Page Object in the `serenitylabs.tutorials.trains.ui` package, using the `@DefaultUrl` annotation to define the correct URL:

    @DefaultUrl("https://buytickets.southwesttrains.co.uk/journey")
    public class BuyTicketsPage extends PageObject {}

#### Adding support for reporting

Finally, add the `@Step` annotation so that the task will appear in the reports:
 
     @Override
     @Step("{0} chooses to book a ticket")
     public <T extends Actor> void performAs(T actor) {
         actor.attemptsTo(
                 Open.browserOn(theBuyTicketsPage)
         );
     }

### Step 6 - Implement the "View available tickets" step

Create a `Task` class that lets you define the departure and destination station using a builder strategy, e.g.

        // WHEN
        tracy.attemptsTo(
                ViewTheAvailableTickets.from("London Paddington").to("Oxford")
        );

#### Implement the `ViewAvailableTasks` builder

Create a `ViewAvailableTasks` class in the `tasks` package with departure and destination fields, and a builder to create an instrumented instance of the class in a readable manner, e.g:

    public class ViewTheAvailableTickets implements Task {
        private final String departureStation;
        private final String destinationStation;
    
        @Override
        public <T extends Actor> void performAs(T actor) {
        }
    
        public ViewTheAvailableTickets(String departureStation, String destinationStation) {
            this.departureStation = departureStation;
            this.destinationStation = destinationStation;
        }
    
        public static ViewTheAvailableTicketsBuilder from(String departureStation) {
            return new ViewTheAvailableTicketsBuilder(departureStation);
        }
    
    
        public static class ViewTheAvailableTicketsBuilder {
            private final String departureStation;
    
            public ViewTheAvailableTicketsBuilder(String departureStation) {
                this.departureStation = departureStation;
            }
    
            public Performable to(String destinationStation) {
                return Instrumented.instanceOf(ViewTheAvailableTickets.class).withProperties(departureStation, destinationStation);
            }
        }
    }

#### Add actions to the steps:

In the `performAs()` method, use the `Enter` and `Click` interaction classes to simulate how the user would fill in the departure and destination fields, and then click on the 'Buy Tickets' button. You may need to use the `thenHit()` method with TAB to ensure that the fields are updated correctly, e.g:

        actor.attemptsTo(
                Enter.theValue(departureStation).into(TicketTypeForm.DEPARTURE).thenHit(Keys.TAB),
                Enter.theValue(destinationStation).into(TicketTypeForm.DESTINATION).thenHit(Keys.TAB),
                Click.on(TicketTypeForm.BUY_TICKETS)

        );


#### Implement the `TicketTypeForm` Page Object

Create a `TicketTypeForm` class in the `serenitylabs.tutorials.trains.ui` package. Study the actual fields on the 'Buy Tickets' page and identify the best way to locate them, then use the `Target` class as illustrated here: 

    public class TicketTypeForm {
        public static final Target DEPARTURE = Target.the("Departing From field ").located(By.id("depart-from"));
        public static final Target DESTINATION = Target.the("Going To field ").located(By.id("going-to")) ;
        public static final Target BUY_TICKETS = Target.the("Buy Tickets button").located(By.className("planner__submit"));
    }


### Step 7 - Implement the final step

The final step involves checking that the departure and destination fields are correctly displayed. First check that the departure station is displayed correctly, e.g.

    tracy.should(
            seeThat(TheOutboundJourneySummary.origin(), is("London Paddington")),
            seeThat(TheOutboundJourneySummary.destination(), is("Oxford"))
    );

Next implement the `TheOutboundJourneySummary` question class in the `serenitylabs.tutorials.trains.questions` package. You can use the `TheTarget` helper class to retreive the text value of a field from the target as a Question object like this:

    public class TheOutboundJourneySummary {
        public static Question<String> origin() {
            return TheTarget.textOf(OutboundJourneySummary.ORIGIN);
        }
    
        public static Question<String> destination() {
            return TheTarget.textOf(OutboundJourneySummary.DESTINATION);
        }
    }

If you are using Java 8, you can also use a Lambda expression like this:

    public class TheOutboundJourneySummary {
        public static Question<String> origin() {
            return actor -> Text.of(OutboundJourneySummary.ORIGIN).viewedBy(actor).asString();
        }
    }
    
But in this case, make sure you add a label for your question in the assertion statement like this:
    
    tracy.should(
            seeThat("the departure station", TheOutboundJourneySummary.origin(), is("London Paddington"))
    );

    
Now implement the `OutboundJourneySummary` class in the `serenitylabs.tutorials.trains.ui` package. Study the actual web page to determine the best locators:

    public class OutboundJourneySummary {
        public static Target ORIGIN = Target.the("Departure").locatedBy(".planner-header__origin");
        public static Target DESTINATION = Target.the("Departure").locatedBy(".planner-header__destination");
    }

### Step 8 - Run the tests

Now run the tests and ensure that everything works.