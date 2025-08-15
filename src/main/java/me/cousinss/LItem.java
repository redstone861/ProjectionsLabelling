package me.cousinss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LItem {

    public static final LItem THE = new LItem(Category.D, "the", new Label(Category.N));
    public static final LItem PIG = new LItem(Category.N, "pig");
    public static final LItem APPLE = new LItem(Category.N, "apple");
    public static final LItem ATE = new LItem(Category.V, "ate", new Label(Category.D), new Label(Category.D));

    private final Category category;
    private final SelectionStack selects;
    private final String spell;

    public LItem(Category category, String spell, SelectionStack selects) {
        this.category = category;
        this.spell = spell;
        this.selects = new SelectionStack(selects);
    }

    public LItem(Category category, String spell, Label... selects) {
        this.category = category;
        this.spell = spell;
        this.selects = new SelectionStack(selects);
    }

    public Category getCategory() {
        return category;
    }

    public SelectionStack getSelects() {
        return selects;
    }

    public String getSpell() {
        return spell;
    }

    @Override
    public String toString() {
        return this.spell;
    }
}
