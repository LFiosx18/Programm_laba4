package serverLib.commands;

import database.DataBaseMng;
import serialPack.user.User;
import serverLib.CollectionMng;

public class Clear {
    private CollectionMng collectionMng;
    private DataBaseMng dataBaseMng;

    public Clear(CollectionMng collectionMng, DataBaseMng dataBaseMng) {
        this.collectionMng = collectionMng;
        this.dataBaseMng = dataBaseMng;
    }

    public String clearCollection(User user) {
        if (dataBaseMng.clear(user.getUsername())) {
            collectionMng.clear(user.getUsername());
            return "Элементы коллекции текущего пользователя удалены";
        }
        return "Ошибка при удалении элементов";
    }
}
