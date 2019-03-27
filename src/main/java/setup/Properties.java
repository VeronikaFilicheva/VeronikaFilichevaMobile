package setup;

public enum Properties {
    /**
     * Enum for building property name with respect to the selected test configuration
     */
    NATIVE ("native"),
    WEB("webios");

    private String currentAppType;

    Properties(String current) {
        this.currentAppType = current;
    }

    public String getFileName() {
        return currentAppType + ".properties";
    }
}
