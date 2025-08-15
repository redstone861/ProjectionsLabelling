package me.cousinss;

public class BSTTexLabeller extends BSTLabeller {

    protected String mmSuff(BNode root) {
        if (isMinimal(root) && isMaximal(root)) {
            return "{\\textsuperscript{$\\pm$}}";
        }
        if (isMaximal(root)) {
            return "{\\textsuperscript{+}}";
        }
        if (isMinimal(root)) {
            return "{\\textsuperscript{-}}";
        }
        return "";
    }

    protected String stringifySelectionStack(SelectionStack selectionStack) {
        if (selectionStack.isEmpty() && selectionStack.numChecked() == 0) {
            return "[ ]";
        }
        return selectionStack.asString(this::stringifyLabel);
    }

    public String stringifyNode(BNode root) {
        Label label = getLabel(root);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("{").append(stringifyLabel(label, false, mmSuff(root))).append("}");
        if (root.getLeft() != null) {
            sb.append(" ");
            sb.append(stringifyNode(root.getLeft()));
            sb.append(" ");
            sb.append(stringifyNode(root.getRight()));
        } else {
            sb.append("\\\\");
            sb.append('`');
            sb.append(root.getItem().getSpell());
            sb.append('\'');
        }
        sb.append(']');
        return sb.toString();
    }

    protected String stringifyLabel(Label label, boolean checked, String mmSuff) {
        return (checked ? "\\sout" : "") +
                "{{\\textlangle}" +
                label.getCategory() + mmSuff +
                ", " +
                stringifySelectionStack(label.getSelectionStack()) +
                "{\\textrangle}}";
    }

}
