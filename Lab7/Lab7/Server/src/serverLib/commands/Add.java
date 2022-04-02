package serverLib.commands;

import database.DataBaseMng;
import serialPack.collection.*;
import serialPack.user.User;
import serverLib.CollectionMng;

public class Add {
    private CollectionMng collectionMng;
    DataBaseMng dataBaseMng;

    public Add(CollectionMng collectionMng, DataBaseMng dataBaseMng) {
        this.collectionMng = collectionMng;
        this.dataBaseMng = dataBaseMng;
    }

    public String add(Product product, User user) {
        product.setUsername(user.getUsername());
        if (dataBaseMng.addProduct(product, user)) {
            collectionMng.add(product);
            return "Элемент добавлен в коллекцию";
        }
        return "Ошибка при добавлении объекта";
    }
}
