package serenitylabs.tutorials.trains.ui;

public enum MainMenu {
    BuyTickets("https://buytickets.southwesttrains.co.uk/journey"),
    GetTravelAssistance("https://www.southwesttrains.co.uk/contact--help/contact-us/assisted-travel-form/"),
    CheckForLiveUpdates("https://www.journeycheck.southwesttrains.co.uk/");

    private final String url;

    public String getUrl() {
        return url;
    }

    MainMenu(String url) {
        this.url = url;
    }
}
