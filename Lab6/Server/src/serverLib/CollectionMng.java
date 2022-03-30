package serverLib;

import serialPack.collection.Product;
import serverLib.file.FileWorker;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Основной клаа для работы с коллекцией
 */
@XmlRootElement(name = "Products")
public class CollectionMng {
    @XmlElement(name = "Product")
    private HashSet<Product> products;
    private LocalDateTime time;

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
    public LocalDateTime getDate() {return time;}

    public void clear() { products.clear(); }
    public void save(){
        FileWorker.save(this);
    }
    public void add(Product product) {
        products.add(product);
    }



    public boolean checkId(Long id) {
        for (Product vals : products) {
            if (id.equals(vals.getId()))
                return false;
        }
        return true;
    }

    public String show() {
        List<Product> list = new ArrayList<>(products);
        Collections.sort(list);
        String mes = "Коллекция:";
        for (Product vals : list) {
            mes+= "\n" + vals.toString();
        }
        return mes;
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

    public boolean update(Long id, Product product) {
        for (Product vals : products) {
            if (vals.getId()==id) {
                updateEl(vals, product);
                return true;
            }
        }
        return false;
    }

    public void updateEl(Product oldProduct, Product newProduct) {
        if (newProduct.getName()!=null)
            oldProduct.setName(newProduct.getName());
        if (newProduct.getCoordinates().getX()!=null)
            oldProduct.getCoordinates().setX(newProduct.getCoordinates().getX());
        if (newProduct.getCoordinates().getY()!=null)
            oldProduct.getCoordinates().setY(newProduct.getCoordinates().getY());
        oldProduct.setPrice(newProduct.getPrice());
        if (newProduct.getPartNumber()!=null)
            oldProduct.setPartNumber(newProduct.getPartNumber());
        if (newProduct.getManufactureCost()!=null)
            oldProduct.setManufactureCost(newProduct.getManufactureCost());
        oldProduct.setUnitOfMeasure(newProduct.getUnitOfMeasure());
        if (newProduct.getManufacturer().getName()!=null)
            oldProduct.getManufacturer().setName(newProduct.getManufacturer().getName());
        if (newProduct.getManufacturer().getAnnualTurnover() != 0)
            oldProduct.getManufacturer().setName(newProduct.getManufacturer().getName());
        oldProduct.getManufacturer().setEmployeesCount(newProduct.getManufacturer().getEmployeesCount());
        oldProduct.getManufacturer().setType(newProduct.getManufacturer().getType());
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

    public String filterName(String name) {
        String el = "";
        for (Product val:products) {
            if (val.getName().toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT))) {
                el+=val.toString() + "\n";
            }
        }
        return el;
    }

    public HashSet<Integer> uniqueCost() {
        HashSet<Integer> cost = new HashSet<Integer>();
        for (Product val:products) {
            cost.add(val.getManufactureCost());
        }
        return cost;
    }
}
