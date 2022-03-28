package commands;

import collection.CollectionMng;
import collection.Product;
import reader.Scan;

public class AddIfMax {
    private CollectionMng collectionMng;
    Scan scan = new Scan();
    public AddIfMax(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public void addMax() {
        scan.print("Создайте элемент, который хотите добавить");
        Product element = new Add(collectionMng).creationProduct();
        Product maxProduct = collectionMng.findMax();
        if (element.compareTo(maxProduct)<0) {
            scan.print("Значение этого элемента меньше, чем значение максимального элемента коллекции\nЭлемент не добавлен!");
        }
        if (element.compareTo(maxProduct)==0) {
            scan.print("Значение этого элемента равно значению максимального элемента коллекции\nЭлемент не добавлен!");
        }
        if (element.compareTo(maxProduct)>0) {
            collectionMng.add(element);
            scan.print("Элемент добавлен в коллекцию!");
        }
    }
}
