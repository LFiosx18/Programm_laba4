package serverLib.commands;

import serverLib.CollectionMng;
import serialPack.collection.Product;

public class RemoveGreater {
    private CollectionMng collectionMng;
    public RemoveGreater(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public String removeGreater(Product product) {
        Product maxProduct = collectionMng.findMax();
        int count = 0;
        while (product.compareTo(maxProduct)<0) {
            collectionMng.removeById(maxProduct.getId());
            maxProduct = collectionMng.findMax();
            count++;
        }
        return ("Из коллекции удалено " + count + " элементов!");
    }
}
