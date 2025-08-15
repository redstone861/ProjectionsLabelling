package me.cousinss;

public class BSTVanillaLabeller extends BSTLabeller {

    @Override
    protected String mmSuff(BNode root) {
        if (isMinimal(root) && isMaximal(root)) {
            return "'";
        }
        if (isMaximal(root)) {
            return "+";
        }
        if (isMinimal(root)) {
            return "-";
        }
        return "";
    }

    @Override
    protected String stringifySelectionStack(SelectionStack selectionStack) {
        return selectionStack.asString(this::stringifyLabel);
    }

    @Override
    public String stringifyNode(BNode root) {
        Label label = getLabel(root);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(stringifyLabel(label, false, mmSuff(root)));
        if (root.getLeft() != null) {
            sb.append(" ");
            sb.append(stringifyNode(root.getLeft()));
            sb.append(" ");
            sb.append(stringifyNode(root.getRight()));
        } else {
            sb.append(" ");
            sb.append('\'');
            sb.append(root.getItem().getSpell());
            sb.append('\'');
        }
        sb.append(']');
        return sb.toString();
    }

    @Override
    protected String stringifyLabel(Label label, boolean checked, String mmSuff) {
        return "<" +
                (checked ? "-" : "") +
                label.getCategory() + mmSuff +
                (checked ? "-" : "") +
                ", " +
                stringifySelectionStack(label.getSelectionStack()) +
                ">";
    }
}
