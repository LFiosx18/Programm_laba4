package serverLib.commands;

import serverLib.CollectionMng;

public class AverageOfPrice {
    private CollectionMng collectionMng;
    public AverageOfPrice(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public String  averagePrice() {
        return "Среднее значение по полю price равно: " + String.format("%.3f",collectionMng.averagePrice());
    }
}
