package serverLib.commands;

import serverLib.CollectionMng;
import serialPack.collection.Product;

public class AddIfMax {
    private CollectionMng collectionMng;
    public AddIfMax(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public String addMax(Product product) {
        Product maxProduct = collectionMng.findMax();
        if (product.compareTo(maxProduct)<=0) {
            return  "Значение этого элемента меньше (либо равно), чем значение максимального элемента коллекции\nЭлемент не добавлен!";
        }
        return new Add(collectionMng).add(product);
    }
}
