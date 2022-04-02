package clientLib.command;

import clientLib.reader.ReaderValidator;
import clientLib.reader.Scan;
import serialPack.collection.Coordinates;
import serialPack.collection.Organization;
import serialPack.collection.Product;

import java.util.Locale;

/**
 * Основной клаа для работы с коллекцией
 */
public class Update {
    Scan scan = new Scan();
    ReaderValidator valid = new ReaderValidator();

    public Product updateEl() {
        Product product = new Product(null, new Coordinates(null, null), null, null, null, null, null,
                new Organization(null, 0, null, null));
        scan.print("\nВедите атрибут элемента, который хотите обновить:");

        while (true) {
            switch (scan.readLine().toLowerCase(Locale.ROOT)) {
                case("name"):
                    scan.print("Вы хотите изменить название продукта (введите: 1) или название производителя (введите: 2)?");
                    if (scan.readLine().equals("1")) {
                        product.setName(valid.readName());
                    }
                    else {
                        product.getManufacturer().setName(valid.readNameOrg());
                    }
                    scan.print("Поле изменено!");
                    break;
                case("x"):
                    product.getCoordinates().setX(valid.readCoordinateX());
                    scan.print("Поле изменено!");
                    break;
                case("y"):
                    product.getCoordinates().setY(valid.readCoordinateY());
                    scan.print("Поле изменено!");
                    break;
                case("price"):
                    product.setPrice(valid.readPrice());
                    scan.print("Поле изменено!");
                    break;
                case("partnumber"):
                    product.setPartNumber(valid.readPartNum());
                    scan.print("Поле изменено!");
                    break;
                case("manufacturecost"):
                    product.setManufactureCost(valid.readManufactereCost());
                    scan.print("Поле изменено!");
                    break;
                case("unitofmeasure"):
                    product.setUnitOfMeasure(valid.readUnitOfMeasure());
                    scan.print("Поле изменено!");
                    break;
                case("annualturnover"):
                    product.getManufacturer().setAnnualTurnover(valid.readTurnover());
                    scan.print("Поле изменено!");
                    break;
                case("employeescount"):
                    product.getManufacturer().setEmployeesCount(valid.readCount());
                    scan.print("Поле изменено!");
                    break;
                case("type"):
                    product.getManufacturer().setType(valid.readType());
                    scan.print("Поле изменено!");
                    break;
                case("id"):
                    scan.print("Элемент ID изменить нельзя!");
                    break;
                case("end"): return product;
                default: scan.print("Атрибут не найден, попробуйте ещё раз");
            }
            scan.print("\nВведите следующий атрибут для изменения (для сохранения изменений введите 'end')");
        }
    }
}
