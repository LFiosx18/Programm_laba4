package serverLib.commands;

import serverLib.CollectionMng;

import java.util.HashSet;

public class UniqueCost {
    private CollectionMng collectionMng;
    public UniqueCost(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public String uniCost() {
        HashSet<Integer> cost = collectionMng.uniqueCost();
        if (collectionMng.getSize() == 0)
            return ("Коллекция пуста");
        return ("Уникальные значения поля manufacture_cost:\n" + cost);
    }
}
