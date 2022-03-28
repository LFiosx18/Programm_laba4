package commands;

import collection.CollectionMng;

import java.util.HashSet;

public class UniqueCost {
    private CollectionMng collectionMng;
    public UniqueCost(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public void uniCost() {
        HashSet<Integer> cost = collectionMng.uniqueCost();
        if (collectionMng.getSize() == 0)
            System.out.println("Коллекция пуста");
        else System.out.println("Уникальные значения поля manufacture_cost:\n" + cost);
    }
}
