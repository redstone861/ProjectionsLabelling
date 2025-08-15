package me.cousinss;

public enum Category {
    V("V"),
    N("N"),
    A("A"),
    X("X"),
    D("D");

    private final String name;
    Category(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
