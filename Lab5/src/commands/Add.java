package commands;

import collection.*;
import reader.ReaderValidator;
import reader.Scan;

import java.time.LocalDateTime;

public class Add {
    private CollectionMng collectionMng;
    Scan scan = new Scan();
    ReaderValidator valid = new ReaderValidator();

    public Add(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public Product creationProduct() {
        String name = valid.readName();
        Integer x = valid.readCoordinateX();
        Integer y = valid.readCoordinateY();
        Long price = valid.readPrice();
        String partNumber = valid.readPartNum();
        Integer manufactureCost = valid.readManufactereCost();
        UnitOfMeasure measure = valid.readUnitOfMeasure();
        scan.print("Заполните данные о производителе");
        String manufacturerName = valid.readNameOrg();
        long annualTurnover = valid.readTurnover();
        Long employeesCount = valid.readCount();
        OrganizationType type = valid.readType();

        Coordinates coordinates = new Coordinates(x, y);
        Organization organization = new Organization(orgId(collectionMng), manufacturerName, annualTurnover, employeesCount, type);

        return new Product(generatorId(collectionMng), name, coordinates, LocalDateTime.now(), price, partNumber, manufactureCost, measure, organization);
    }

    public void add() {
        collectionMng.add(creationProduct());
        scan.print("Элемент добавлен в коллекцию");
    }

    public Long generatorId(CollectionMng collectionMng) {
        Long newid = 1L;
        for (Product val:collectionMng.getProducts()) {
            if (newid != val.getId()) {
                return newid;
            }
            newid++;
        }
        return newid;
    }

    public Integer orgId(CollectionMng collectionMng) {
        return Integer.parseInt(Long.toString(generatorId(collectionMng)));
    }
}
