package commands;

import collection.CollectionMng;
import reader.ReaderValidator;
import reader.Scan;

public class Update {
    private CollectionMng collectionMng;
    Scan scan = new Scan();
    ReaderValidator valid = new ReaderValidator();
    public Update(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public void updateId(String id) {
        Long newid = valid.readId(id);
        if (collectionMng.update(newid))
            scan.print("Элемент обновлён!");
        else
            scan.printErr("Элемента с таким id не существует");
    }
}
