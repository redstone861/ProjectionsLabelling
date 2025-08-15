package me.cousinss;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

public class SelectionStack {

    private final List<Label> list;
    private int head;

    public SelectionStack(Label... selects) {
        list = new LinkedList<>(List.of(selects));
    }

    public SelectionStack(SelectionStack stack) {
        this.list = new LinkedList<>(stack.list);
        this.head = stack.head;
    }

    public static SelectionStack of(List<Label> selects) {
        Label[] sArr = new Label[selects.size()];
        for(int i = 0; i < selects.size(); i++) {
            sArr[i] = selects.get(i);
        }
        return new SelectionStack(sArr);
    }

    public static SelectionStack empty() {
        return new SelectionStack();
    }

    public int size() {
        return list.size() - head;
    }

    public Label peek() {
        return this.peek(0);
    }

    public Label peek(int i) {
        if(i >= size()) {
            return null;
        }
        return list.get(this.head + i);
    }

    public Label getFull(int i) {
        return this.list.get(i);
    }

    // public boolean push() {
    // return false; //TODO
    // }

    public Label pop() {
        if(size() == 0) {
            throw new NoSuchElementException();
        }
        return list.get(head++);
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int totalSize() {
        return list.size();
    }

    public int numChecked() {
        return this.totalSize() - this.size(); //TODO (?)
    }

    public Label getChecked(int i) {
        if(i >= numChecked()) {
            throw new IndexOutOfBoundsException();
        }
        return list.get(i);
    }

    public String asString(BiFunction<Label, Boolean, String> labelToString) {
        List<String> sl = IntStream.range(0, list.size()).mapToObj((int i) -> labelToString.apply(list.get(i), i < numChecked())).toList();
        return sl.toString();
    }
}
