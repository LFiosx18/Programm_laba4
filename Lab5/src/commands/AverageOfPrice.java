package commands;

import collection.CollectionMng;

public class AverageOfPrice {
    private CollectionMng collectionMng;
    public AverageOfPrice(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public void averagePrice() {
        System.out.println("Среднее значение по полю price равно: " + String.format("%.3f",collectionMng.averagePrice()));
    }
}
