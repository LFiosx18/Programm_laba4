package collection;

import file.FileWorker;
import reader.ReaderValidator;
import reader.Scan;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Locale;

/**
 * Основной клаа для работы с коллекцией
 */
@XmlRootElement(name = "Products")
public class CollectionMng {
    @XmlElement(name = "Product")
    private HashSet<Product> products;
    private LocalDateTime time;
    Scan scan = new Scan();
    ReaderValidator valid = new ReaderValidator();

    public CollectionMng() {
        products = new HashSet<Product>();
        this.time = LocalDateTime.now();
    }

    /**
     * Методы, реализующие комменды пользователей
     */
    public int getSize() {return products.size();}
    public LocalDateTime getTime() { return time;}
    public HashSet<Product> getProducts() {return products;}

    public void clear() { products.clear(); }
    public void save(){
        FileWorker.save(this);
    }
    public void add(Product product) { products.add(product); }

    public boolean checkId(Long id) {
        for (Product vals : products) {
            if (id.equals(vals.getId()))
                return false;
        }
        return true;
    }

    public void show() {
        for (Product vals : products) {
            System.out.println(vals.toString());
        }
    }

    public boolean removeById(Long newid) {
        for (Product vals : products) {
            if (vals.getId() == newid) {
                products.remove(vals);
                return true;
            }
        }
        return false;
    }

    public boolean update(Long id) {
        for (Product vals : products) {
            if (vals.getId()==id) {
                System.out.println("Текущий элемент:");
                System.out.println(vals.toString());
                updateEl(vals);
                return true;
            }
        }
        return false;
    }

    public void updateEl(Product product) {
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
                case("end"): return;
                default: scan.print("Атрибут не найден, попробуйте ещё раз");
            }
            scan.print("\nВведите следующий атрибут для изменения (для сохранения изменений введите 'end')");
        }
    }

    public Product findMax() {
        Product maxProduct = null;
        for (Product val:products) {
            maxProduct = val;
            break;
        }
        for (Product val:products) {
            if (val.compareTo(maxProduct) > 0)
                maxProduct = val;
        }
        return maxProduct;
    }

    public Product findMin() {
        Product minProduct = null;
        for (Product val:products) {
            minProduct = val;
            break;
        }
        for (Product val:products) {
            if (val.compareTo(minProduct) < 0)
                minProduct = val;
        }
        return minProduct;
    }

    public double averagePrice() {
        int count = 0;
        double sum = 0;
        for (Product val:products) {
            sum+=val.getPrice();
            count++;
        }
        return sum/count;
    }

    public int filterName(String name) {
        int count = 0;
        for (Product val:products) {
            if (val.getName().toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT))) {
                System.out.println(val.toString());
                count++;
            }
        }
        return count;
    }

    public HashSet<Integer> uniqueCost() {
        HashSet<Integer> cost = new HashSet<Integer>();
        for (Product val:products) {
            cost.add(val.getManufactureCost());
        }
        return cost;
    }
}
