package serverLib.commands;

import database.DataBaseMng;
import serialPack.user.User;
import serverLib.CollectionMng;
import serialPack.collection.Product;

public class Update {
    private CollectionMng collectionMng;
    DataBaseMng dataBaseMng;

    public Update(CollectionMng collectionMng, DataBaseMng dataBaseMng) {
        this.collectionMng = collectionMng;
        this.dataBaseMng = dataBaseMng;
    }

    public String updateId(String id, Product product, User user) {
        Long newid = Long.parseLong(id);
        Product upProd = collectionMng.findId(newid);
        if (upProd!=null) {
            if (upProd.getUsername().equals(user.getUsername())) {
                dataBaseMng.addProduct(product, user);
                collectionMng.update(newid, product);
                return "Элемент обновлён!";
            }
            return "Элемента с таким id не существует у данного пользователя";
        }
        return "Элемента с таким id не существует";
    }
}
