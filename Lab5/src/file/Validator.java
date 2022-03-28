package file;

import collection.CollectionMng;
import collection.Product;
import collection.UnitOfMeasure;
import reader.Scan;

import java.util.Locale;

/**
 * Валидация элементов прочитанных из файла,
 * перед добавлением их в коллекцию
 */
public class Validator {
    private CollectionMng collectionMng;
    Scan scan = new Scan();
    public Validator(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public boolean validId (Product product, CollectionMng collectionMng) {
        if (product.getId()==null || product.getId()<=0) {
            scan.printErr("Ошибка валидации ID");
            return false;
        }
        if (collectionMng.checkId(product.getId()))
            return true;
        else {
            scan.printErr("Элемент с таким ID ууже существует");
            return false;
        }
    }

    public boolean validName (Product product) {
        if (product.getName().equals("") || product.getName().equals("null")) {
            scan.printErr("Ошибка валидации Name");
            return false;
        }
        return true;
    }

    public boolean validCoord (Product product) {
        try {
            if (product.getCoordinates().getX()==null || product.getCoordinates().getX().toString().equals("") || product.getCoordinates().getX() > 226) {
                scan.printErr("Ошибка валидации X");
                return false;
            }
            if (product.getCoordinates().getY()==null || product.getCoordinates().getY().toString().equals("")) {
                scan.printErr("Ошибка валидации Y");
                return false;
            }
            return true;
        } catch (NullPointerException e) {
            scan.printErr("Некорректно задано значение X или Y");
        }
        return false;
    }

    public boolean validDate(Product product) {
        try {
            if (product.toStringDate().equals("") || product.toStringDate().equals("null")) {
                scan.printErr("Некорректно задано значение creationDate");
                return false;
            }
            return true;
        }
        catch (Exception e) {
            scan.printErr("Некорректно задано значение creationDate");
        }
        return false;
    }

    public boolean validPrice(Product product) {
        try {
            if (product.getPrice()==null) {
                product.setPrice(null);
                return true;
            }
            if (product.getPrice()<1) {
                scan.printErr("Некорректно задано значение Price");
                return false;
            }
            return true;
        }
        catch (Exception e) {
            scan.printErr("Некорректно задано значение Price");
        }
        return false;
    }

    public boolean validPartNum(Product product) {
        if (product.getPartNumber().equals("null") || product.getPartNumber().equals("") || product.getPartNumber().length()>44) {
            scan.printErr("Некорректно задано значение PartNumber");
            return false;
        }
        return true;
    }

    public boolean validManufactureCost(Product product) {
        try {
            if (product.getManufactureCost()==null) {
                scan.printErr("Некорректно задано значение ManufactureCost");
                return false;
            }
            return true;
        }
        catch (Exception e) {
            scan.printErr("Некорректно задано значение ManufactureCost");
        }
        return false;
    }

    public boolean validMeasure(Product product) {
        try {
            if (product.getUnitOfMeasure()==null) throw new Exception();
            UnitOfMeasure.valueOf(product.getUnitOfMeasure().toString().toUpperCase(Locale.ROOT));
            return true;
        }
        catch (Exception e) {
            scan.printErr("Некорректно задано значение UnitOfMeasure");
        }
        return false;
    }

    public boolean validOrg(Product product) {
        if (product.getManufacturer().getId()!=null && product.getManufacturer().getId()>0) {
            if (product.getManufacturer().getName()!=null && !product.getManufacturer().getName().equals("")) {
                if (product.getManufacturer().getAnnualTurnover()>0) {
                    if (product.getManufacturer().getEmployeesCount()==null)
                        product.getManufacturer().setEmployeesCount(null);
                    if (product.getManufacturer().getEmployeesCount()>0) {
                        try {
                            if (product.getManufacturer().getType()==null)
                                product.getManufacturer().setType(null);
                            UnitOfMeasure.valueOf(product.getUnitOfMeasure().toString().toUpperCase(Locale.ROOT));
                            return true;
                        }
                        catch (Exception e) {
                            scan.printErr("Некорректно задано значение Type");
                        }
                        return false;
                    }
                    else {
                        scan.printErr("Некорректно задано значение Manufacturer (EmployeesCount)");
                        return false;
                    }
                }
                else {
                    scan.printErr("Некорректно задано значение Manufacturer (AnnualTurnover)");
                    return false;
                }
            }
            else {
                scan.printErr("Некорректно задано значение Manufacturer (name)");
                return false;
            }
        }
        else {
            scan.printErr("Некорректно задано значение Manufacturer (id)");
            return false;
        }
    }
}
