These exercises are part of the [Serenity Dojo](http://www.serenity-dojo.com/) training program, and are designed to help developers and testers learn how to work with Serenity Screenplay on real-world applications.

## The problem domain

In this series of exercises, we will be building up a test suite for a real-world web site: [SouthWestTrains](https://www.southwesttrains.co.uk/). Each tutorial will focus on implementing a particular test scenario. 

## Problem definition

The aim of these scenarios is to work with timeouts and asynchronous waits. You will be adding a test to check that the number of General Updates displayed matches the total count displayed

### Step 1 - Implement the "Given" stage of the test

Complete the `general_updates_should_be_available()` test in the `WhenCheckingForLiveUpdates` test class. The aim of this test is to check that you can click on the toggle button for the General Updates and display a list of updates:

[General updates](src/documentation/images/live-updates-general-list.png)

In this test, write a `givenThat()` clause to open the Live Updates page:

        givenThat(tracy).has(ChosenTo.checkTheLiveUpdates());

### Step 2 - Implement the "When" stage of the test

Record the current value of the General Updates badge, e.g.

       int generalUpdateBadgeCount = Text.of(LiveUpdates.GENERAL_UPDATE_BADGE) 
                                         .viewedBy(tracy).asInteger(); 

Add the `GENERAL_UPDATE_BADGE` constant to the `LiveUpdates` class, e.g. 

    public static final Target GENERAL_UPDATE_BADGE = Target.the("General updates badge").locatedBy("#general_updatesCount");

Now add the `when` clause:

        when(tracy).attemptsTo(ViewTheLiveUpdates.forGeneralUpdates());

The existing `ViewTheLiveUpdates` class is no longer adequate, and needs to be refactored to cater for multiple update types.

Add an enum called `UpdateTypes`:

    public enum UpdateType {
        LineUpdates, GeneralUpdates
    }

Add a field of type `UpdateType' to the ViewTheLiveUpdates class, assigned in the constructor:

    public ViewTheLiveUpdates(UpdateType updateType) {
        this.updateType = updateType;
    }

    private final UpdateType updateType;
        
Refactor `LiveUpdates` to use a map from `UpddateType` to `Target`, e.g.

    public class LiveUpdates {
    
        public static final Target LINE_UPDATE_BADGE = Target.the("Line update badge").locatedBy("#line_updatesCount");

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


Refactor the `performAs` method in `ViewTheLiveUpdates` to use the new map-backed methods:
    
    @Override
    @Step("{0} views the live updates for #updateType")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(LiveUpdates.toggleForUpdatesOfType(updateType))
        );
    }    

Refactor the `TheLiveUpdateIncidents` class to use the new map=backed methods:

    public class TheLiveUpdateIncidents {
        public static Question<List<String>> forLineUpdates() {
            return actor -> Text.of(LiveUpdates.messagesForUpdatesOfType(UpdateType.LineUpdates)).viewedBy(actor).asList();
        }
    }
    

Implement the new `forGeneralUpdates()` method:
    
    public static Performable forGeneralUpdates() {
        return Instrumented.instanceOf(ViewTheLiveUpdates.class).withProperties(GeneralUpdates);
    }

### Step 3 - Implement the "Then" stage of the test

Check that the total number of general update messages displayed should equal the number in the badge. Cater for the time it takes to fully display the list. e.g.

        then(tracy).should(
                eventually(
                        seeThat("the number of general updates messages",
                                theTotalNumberOf(LiveUpdateIncidents.forGeneralUpdates()),
                                equalTo(generalUpdateBadgeCount))
                )
        );

Implement the `LiveUpdateIncidents.forGeneralUpdates()` method using a lambda expression, e.g.

    public static Question<List<String>> forGeneralUpdates() {
        return actor -> Text.of(LiveUpdates.messagesForUpdatesOfType(UpdateType.GeneralUpdates)).viewedBy(actor).asList();
    }
    
### Step 4 - Repeat this exercise for Station Updates and Train Cancellations