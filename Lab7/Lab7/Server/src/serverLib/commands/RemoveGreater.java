package serverLib.commands;

import database.DataBaseMng;
import serialPack.user.User;
import serverLib.CollectionMng;
import serialPack.collection.Product;

public class RemoveGreater {
    private CollectionMng collectionMng;
    private DataBaseMng dataBaseMng;

    public RemoveGreater(CollectionMng collectionMng, DataBaseMng dataBaseMng) {
        this.collectionMng = collectionMng;
        this.dataBaseMng = dataBaseMng;
    }

    public String removeGreater(Product product, User user) {
        if (dataBaseMng.removeGreater(product.getPrice(), user.getUsername())) {
            Product maxProduct = collectionMng.findMaxThisUser(user.getUsername());
            int count = 0;
            while (product.compareTo(maxProduct)<0) {
                collectionMng.removeById(maxProduct.getId());
                maxProduct = collectionMng.findMax();
                count++;
            }
            return ("Из коллекции удалено " + count + " элементов!");
        }
        return ("Ошибка при удалении элементов, возможно у данного пользователя нет таких элементов, которые подходят под условия удаления");
    }
}
