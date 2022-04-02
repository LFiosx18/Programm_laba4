package serverLib.commands;

import database.DataBaseMng;
import serialPack.user.User;
import serverLib.CollectionMng;
import serialPack.collection.Product;

public class RemoveLower {
    private CollectionMng collectionMng;
    private DataBaseMng dataBaseMng;

    public RemoveLower(CollectionMng collectionMng, DataBaseMng dataBaseMng) {
        this.collectionMng = collectionMng;
        this.dataBaseMng = dataBaseMng;
    }

    public String removeLower(Product product, User user) {
        if (dataBaseMng.removeLower(product.getPrice(), user.getUsername())) {
            Product maxProduct = collectionMng.findMin();
            int count = 0;
            while (product.compareTo(maxProduct)>0) {
                collectionMng.removeById(maxProduct.getId());
                maxProduct = collectionMng.findMin();
                count++;
            }
            return ("Из коллекции удалено " + count + " элементов!");
        }
        return ("Ошибка при удалении элементов, возможно у данного пользователя нет таких элементов, которые подходят под условия удаления");
    }
}
