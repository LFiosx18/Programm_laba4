package commands;

import collection.CollectionMng;
import reader.Scan;

public class FilterContainsName {
    private CollectionMng collectionMng;
    Scan scan = new Scan();
    public FilterContainsName(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public void filter(String name) {
        while(name=="") {
            scan.printErr("Значение name не указано, введите name:");
            name = scan.readLine();
        }
        int count = collectionMng.filterName(name);
        if(count > 0)
            System.out.println("\nНайдено и выведено " + count + " элементов!");
        else scan.printErr("Элементов не найдено");
    }
}
