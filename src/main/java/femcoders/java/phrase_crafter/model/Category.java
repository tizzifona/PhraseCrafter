package femcoders.java.phrase_crafter.model;

public enum Category {
    INSPIRATIONAL("Inspirational"),
    JOKES("Jokes"),
    LOREM_IPSUM("Lorem Ipsum");


    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
