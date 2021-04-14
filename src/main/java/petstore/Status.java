package petstore;

public enum Status {

    available("available"),
    pending("pending"),
    sold("sold"),
    invalidStatus("invalidStatus"),
    nullStatus(null);

    private String value;

    Status(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
