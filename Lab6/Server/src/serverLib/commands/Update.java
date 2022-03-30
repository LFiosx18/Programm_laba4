package serverLib.commands;

import serverLib.CollectionMng;
import serialPack.collection.Product;

public class Update {
    private CollectionMng collectionMng;
    public Update(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public String updateId(String id, Product product) {
        Long newid = Long.parseLong(id);
        if (collectionMng.update(newid, product))
            return "Элемент обновлён!";
        else
            return "Элемента с таким id не существует";
    }
}
