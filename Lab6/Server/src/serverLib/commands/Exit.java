package serverLib.commands;

import serverLib.CollectionMng;

public class Exit {
    private CollectionMng collectionMng;

    public Exit(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public String progExit() {
        collectionMng.save();
        return "Коллекция сохранена, сервер звершил работу";
    }
}
