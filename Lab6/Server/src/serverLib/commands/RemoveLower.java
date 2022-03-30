package serverLib.commands;

import serverLib.CollectionMng;
import serialPack.collection.Product;

public class RemoveLower {
    private CollectionMng collectionMng;
    public RemoveLower(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public String removeLower(Product product) {
        Product maxProduct = collectionMng.findMin();
        int count = 0;
        while (product.compareTo(maxProduct)>0) {
            collectionMng.removeById(maxProduct.getId());
            maxProduct = collectionMng.findMin();
            count++;
        }
        return ("Из коллекции удалено " + count + " элементов!");
    }
}
