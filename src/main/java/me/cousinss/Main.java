package me.cousinss;

public class Main {
    public static void main(String[] args) {
        BNode vp = new BNode(
                new BNode(
                        new BNode(LItem.THE),
                        new BNode(LItem.PIG)),
                new BNode(
                        new BNode(LItem.ATE),
                        new BNode(
                                new BNode(LItem.THE),
                                new BNode(LItem.APPLE))));

        BSTLabeller labeller = new BSTTexLabeller();

        System.out.println(labeller.stringifyNode(vp));
        labeller.printCounts(vp);
    }
}