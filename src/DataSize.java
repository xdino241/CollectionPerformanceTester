public enum DataSize {
    HUNDRED(100), FIVE_HUNDRED(500), THOUSAND(1000), TEN_THOUSAND(10000), CUSTOM(0);
    private int size;

    DataSize(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }
}
