package me.cousinss;

public class BNode {
    private BNode parent;
    //children
    private BNode left;
    private BNode right;
    //head
    private LItem item;

    protected BNode(BNode left, BNode right) {
        this.left = left;
        this.left.setParent(this);
        this.right = right;
        this.right.setParent(this);
        this.parent = null;
        this.item = null;
    }

    protected BNode(LItem item) {
        this.item = item;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public LItem getItem() {
        return item;
    }

    /**
     * Set the parent. Returns true if a change occurred.
     * @param parent the parent
     * @return {@code true} if the parent changed, {@code false} otherwise
     */
    public boolean setParent(BNode parent) {
        BNode cur = this.parent;
        this.parent = parent;
        return cur != parent;
    }

    public BNode getParent() {
        return this.parent;
    }

    public boolean isRoot() {
        return !this.hasParent();
    }

    public boolean hasParent() {
        return this.parent != null;
    }

    public BNode getLeft() {
        return this.left;
    }

    public BNode getRight() {
        return this.right;
    }

    public BNode getSister() {
        if(this.isRoot()) {
            return null;
        }
        if(this.getParent().getRight() == this) {
            return this.getParent().getLeft();
        }
        return this.getParent().getRight();
    }

    public boolean ccommands(BNode node) {
        return this.getSister() != null && this.getSister().contains(node);
    }

    public boolean contains(BNode node) {
        return this.equals(node) ||
                (this.left != null && (this.left.equals(node) || this.left.contains(node))) ||
                (this.right != null && (this.right.equals(node) || this.right.contains(node)));
    }

    @Override
    public String toString() {
        return this.getItem() == null ? ("[" + this.left + ", " + this.right + "]") : this.item.toString();
    }

}
