package me.cousinss;

public interface Labeller {

    boolean isMinimal(BNode node);

    boolean isMaximal(BNode node);

    boolean isPhrase(BNode node);

    boolean isHead(BNode node);

    Label getLabel(BNode node);
}
