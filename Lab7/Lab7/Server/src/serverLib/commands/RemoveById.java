package serverLib.commands;

import database.DataBaseMng;
import serialPack.user.User;
import serverLib.CollectionMng;

public class RemoveById {
    private CollectionMng collectionMng;
    private DataBaseMng dataBaseMng;

    public RemoveById(CollectionMng collectionMng, DataBaseMng dataBaseMng) {
        this.collectionMng = collectionMng;
        this.dataBaseMng = dataBaseMng;
    }

    public String removeId(String id, User user) {
        Long newid = Long.parseLong(id);

        if (dataBaseMng.removeId(user.getUsername(), newid)) {
            collectionMng.removeById(newid);
            return "Элемент удалён!";
        }
        return "Элемента с таким id не существует у данного пользователя";
    }
}
