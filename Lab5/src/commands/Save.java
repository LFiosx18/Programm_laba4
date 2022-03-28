package commands;

import collection.*;
import reader.Scan;

public class Save {
    private CollectionMng collectionMng;
    Scan scan = new Scan();

    public Save(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public void save() {
        collectionMng.save();
        scan.print("Коллекция сохранена!");
    }
}
