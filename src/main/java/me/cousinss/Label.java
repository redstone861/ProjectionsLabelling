package me.cousinss;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Label {
    private final Category category;
    private final SelectionStack selectionStack;

    public Label(Category category, SelectionStack sel) {
        this.category = category;
        this.selectionStack = sel;
    }

    public Label(Category category) {
        this(category, SelectionStack.empty());
    }

    public Category getCategory() {
        return this.category;
    }

    public SelectionStack getSelectionStack() {
        return this.selectionStack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Label label)) return false;
        if(!label.getCategory().equals(this.getCategory())) return false;
        if(label.getSelectionStack().size() != this.getSelectionStack().size()) return false;
        for(int i = 0; i < this.getSelectionStack().size(); i++) {
            if(!this.getSelectionStack().peek(i).equals(label.getSelectionStack().peek(i))) return false;
        }
        return true;
    }
}
