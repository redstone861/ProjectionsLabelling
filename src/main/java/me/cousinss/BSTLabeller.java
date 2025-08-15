package me.cousinss;

import java.util.HashMap;
import java.util.Map;

public abstract class BSTLabeller implements Labeller {

    private final Map<BNode, Integer> COMPUTATION_COUNT;

    public BSTLabeller() {
        COMPUTATION_COUNT = new HashMap<>();
    }

    public abstract String stringifyNode(BNode root);

    protected abstract String mmSuff(BNode root);

    protected abstract String stringifySelectionStack(SelectionStack selectionStack);

    protected abstract String stringifyLabel(Label label, boolean checked, String minmaxSuffix);

    protected String stringifyLabel(Label label, boolean checked) {
        return stringifyLabel(label, checked, "");
    }

    protected String stringifyLabel(Label label) {
        return stringifyLabel(label, false);
    }

    @Override
    public boolean isMinimal(BNode node) {
        return node.getItem() != null;
    }

    @Override
    public boolean isMaximal(BNode node) {
        if(null == node.getParent()) {
            return true;
        }
        Map<BNode, Integer> save = new HashMap<>(COMPUTATION_COUNT);
        boolean out = getLabel(node.getParent()).getCategory() != getLabel(node).getCategory();
        COMPUTATION_COUNT.putAll(save);
        return out;
    }

    @Override
    public boolean isPhrase(BNode node) {
        return isMaximal(node);
    }

    @Override
    public boolean isHead(BNode node) {
        return isMinimal(node) && !isMaximal(node);
    }

    @Override
    public Label getLabel(BNode node) {
        this.COMPUTATION_COUNT.compute(node, (k, v) -> v == null ? 1 : v + 1);
        if(isMinimal(node)) {
            return new Label(node.getItem().getCategory(), node.getItem().getSelects());
        }
        Label aL = getLabel(node.getLeft());
        Label bL = getLabel(node.getRight());
        if(aL.getSelectionStack().isEmpty()) {
            Label temp = aL;
            aL = bL;
            bL = temp;
        }
        SelectionStack ss = new SelectionStack(aL.getSelectionStack());
        Label first = ss.isEmpty() ? null : ss.pop();
        if(!(bL.equals(first))) {
            return new Label(Category.X, SelectionStack.empty());
        }
        return new Label(aL.getCategory(), ss);
    }

    public int getComputationCount(BNode node) {
        return COMPUTATION_COUNT.get(node);
    }

    public void printCounts(BNode root) {
        if(root == null) {
            return;
        }
        printCounts(root.getLeft());
        System.out.println(getComputationCount(root) + ": " + root);
        printCounts(root.getRight());
    }
}
