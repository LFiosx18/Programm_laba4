package serverLib.commands;

import database.DataBaseMng;
import serialPack.user.User;
import serverLib.CollectionMng;
import serialPack.collection.Product;

public class AddIfMax {
    private CollectionMng collectionMng;
    private DataBaseMng dataBaseMng;
    public AddIfMax(CollectionMng collectionMng, DataBaseMng dataBaseMng) {
        this.collectionMng = collectionMng;
        this.dataBaseMng = dataBaseMng;
    }

    public String addMax(Product product, User user) {
        Product maxProduct = collectionMng.findMax();
        if (product.compareTo(maxProduct)<=0) {
            return  "Значение этого элемента меньше (либо равно), чем значение максимального элемента коллекции\nЭлемент не добавлен!";
        }
        return new Add(collectionMng, dataBaseMng).add(product, user);
    }
}
