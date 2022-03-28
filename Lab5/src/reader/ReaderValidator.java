package reader;

import collection.OrganizationType;
import collection.UnitOfMeasure;

import java.util.Locale;

import static collection.UnitOfMeasure.outMeasure;
import static collection.OrganizationType.outType;

/**
 * Валидация введённых значений
 */
public class ReaderValidator {
    Scan scan = new Scan();

    public Long readId(String id) {
        Long newid = null;
        while(true) {
            if (id.equals("")) {
                scan.printErr("Значение ID не указано, введите ID:");
                id = scan.readLine().trim();
                continue;
            }
            try {
                newid = Long.parseLong(id);
                if (newid<=0) throw new NumberFormatException();
                return newid;
            }
            catch (NumberFormatException e) {
                scan.printErr("ID должно быть целым числом (больше 0), введите корректное значение!");
                id = scan.readLine().trim();
            }
        }
    }

    public String readName() {
        scan.print("Введите название продукта:");
        String name = scan.readLine().trim();
        while (name.equals("")) {
            scan.printErr("Название не может быть пустой строкой, введите корректрое значение!");
            name = scan.readLine().trim();
        }
        return name;
    }

    public Integer readCoordinateX() {
        scan.print("Введите координату X (целое число, максимальное значение 226):");
        Integer coordinate;
        while (true) {
            String coord = scan.readLine().trim();
            if (!coord.equals("")) {
                try {
                    coordinate = Integer.parseInt(coord);
                    if (coordinate > 226) throw new NumberFormatException();
                    return coordinate;
                }
                catch (NumberFormatException e) {
                    scan.printErr("Координата должна быть задана целым числом (тип int, не больше 226), введите корректное значение!");
                }
            }
            else {
                scan.printErr("Координата не может быть пустой строкой, введите корректное значение!");
            }
        }
    }

    public Integer readCoordinateY() {
        scan.print("Введите координату Y (целое число):");
        Integer coordinate;
        while (true) {
            String coord = scan.readLine().trim();
            if (!coord.equals("")) {
                try {
                    coordinate = Integer.parseInt(coord);
                    return coordinate;
                }
                catch (NumberFormatException e) {
                    scan.printErr("Координата должна быть задана целым числом (тип int), введите корректное значение!");
                }
            }
            else {
                scan.printErr("Координата не может быть пустой строкой, введите корректное значение!");
            }
        }
    }

    public Long readPrice() {
        scan.print("Введите стоимость продукта (целое число больше 0):");
        Long price;
        while (true) {
            String p = scan.readLine().trim();
            if (p.equals("")) return null;
            try {
                price = Long.parseLong(p);
                if (price <= 0) throw new NumberFormatException();
                return price;
            }
            catch (NumberFormatException e) {
                scan.printErr("Стоимость должна быть задана целым числом (тип long, больше 0), введите корректное значение!");
            }
        }
    }

    public String readPartNum() {
        scan.print("Введите серийный номер продукта:");
        String num = scan.readLine().trim();
        while (num.equals("") || num.length()>44) {
            scan.printErr("Серийный номер не может быть задан пустой строкой (максимальное количество символов в строке: 44), введите корректрое значение!");
            num = scan.readLine().trim();
        }
        return num;
    }

    public Integer readManufactereCost() {
        scan.print("Введите стоимость производства (целое число):");
        Integer cost;
        while (true) {
            String s = scan.readLine().trim();
            if (!s.equals("")) {
                try {
                    cost = Integer.parseInt(s);
                    return cost;
                }
                catch (NumberFormatException e) {
                    scan.printErr("Стоимость производства должна быть задана целым числом (тип int), введите корректное значение!");
                }
            }
            else {
                scan.printErr("Стоимость производства не может быть пустой строкой, введите корректное значение!");
            }
        }
    }

    public UnitOfMeasure readUnitOfMeasure() {
        scan.print("Выберите единицу измерения:");
        outMeasure();
        while (true) {
            String s = scan.readLine().trim();
            if (s.equals("")) return null;
            try {
                return UnitOfMeasure.valueOf(s.toUpperCase(Locale.ROOT));
            }
            catch (IllegalArgumentException e) {
                scan.printErr("Такого варианта нет в списке, выбрите значение из предложенного списка!");
            }
        }
    }

    public String readNameOrg() {
        scan.print("Введите название производителя:");
        String name = scan.readLine().trim();
        while (name.equals("")) {
            scan.printErr("Название не может быть пустой строкой, введите корректрое значение!");
            name = scan.readLine().trim();
        }
        return name;
    }

    public long readTurnover() {
        scan.print("Введите ежегодный оборот компании (целое число больше 0):");
        long turnover;
        while (true) {
            String p = scan.readLine().trim();
            try {
                turnover = Long.parseLong(p);
                if (turnover <= 0) throw new NumberFormatException();
                return turnover;
            }
            catch (NumberFormatException e) {
                scan.printErr("Значение должно быть задано целым числом (тип long, больше 0), введите корректное значение!");
            }
        }
    }

    public Long readCount() {
        scan.print("Введите количество сотрудников компании (целое число больше 0):");
        long count;
        while (true) {
            String p = scan.readLine().trim();
            if (p.equals("")) return null;
            try {
                count = Long.parseLong(p);
                if (count <= 0) throw new NumberFormatException();
                return count;
            }
            catch (NumberFormatException e) {
                scan.printErr("Значение должно быть задано целым числом (тип long, больше 0), введите корректное значение!");
            }
        }
    }

    public OrganizationType readType() {
        scan.print("Выберите тип организации:");
        outType();
        while (true) {
            String s = scan.readLine().trim();
            if (s.equals("")) return null;
            try {
                return OrganizationType.valueOf(s.toUpperCase(Locale.ROOT));
            }
            catch (IllegalArgumentException e) {
                scan.printErr("Такого варианта нет в списке, выбрите значение из предложенного списка!");
            }
        }
    }
}
