package serverLib.commands;

import serverLib.CollectionMng;

public class RemoveById {
    private CollectionMng collectionMng;
    public RemoveById(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public String removeId(String id) {
        Long newid = Long.parseLong(id);

        if (collectionMng.removeById(newid))
            return "Элемент удалён!";
        else
            return "Элемента с таким id не существует";
    }
}
