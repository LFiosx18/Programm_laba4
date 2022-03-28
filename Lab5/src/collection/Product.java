package collection;

import file.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

/**
 * Класс Product
 * Определяем корневой элемент и последовательность тегов
 */
@XmlRootElement(name = "Product")
@XmlType(propOrder = {"id", "name", "coordinates", "creationDate", "price", "partNumber", "manufactureCost", "unitOfMeasure", "manufacturer"})
public class Product implements Comparable<Product> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long price; //Поле может быть null, Значение поля должно быть больше 0
    private String partNumber; //Длина строки не должна быть больше 44, Значение этого поля должно быть уникальным, Строка не может быть пустой, Поле не может быть null
    private Integer manufactureCost; //Поле не может быть null
    private UnitOfMeasure unitOfMeasure; //Поле может быть null
    private Organization manufacturer; //Поле может быть null

    /**
     * Конструктор без указания id элемента
     *
     * @param name
     * @param coordinates
     * @param creationDate
     * @param price
     * @param partNumber
     * @param manufactureCost
     * @param unitOfMeasure
     * @param manufacturer
     */

    public Product(Long id, String name, Coordinates coordinates, LocalDateTime creationDate, Long price, String partNumber, Integer manufactureCost, UnitOfMeasure unitOfMeasure, Organization manufacturer) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.partNumber = partNumber;
        this.manufactureCost = manufactureCost;
        this.unitOfMeasure = unitOfMeasure;
        this.manufacturer = manufacturer;
    }

    public Product(){}

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCoordinates(Coordinates coordinates) { this.coordinates = coordinates; }
    public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }
    public void setPrice(Long price) { this.price = price; }
    public void setPartNumber(String partNumber) { this.partNumber = partNumber; }
    public void setManufactureCost(Integer manufactureCost) { this.manufactureCost = manufactureCost; }
    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) { this.unitOfMeasure = unitOfMeasure; }
    public void setManufacturer(Organization manufacturer) {this.manufacturer = manufacturer; }

    @XmlAttribute
    public Long getId() { return id; }
    @XmlElement
    public String getName() { return name; }
    @XmlElement
    public Coordinates getCoordinates() { return coordinates; }
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDateTime getCreationDate() { return creationDate; }
    @XmlElement
    public Long getPrice() { return price; }
    @XmlElement
    public String getPartNumber() { return partNumber; }
    @XmlElement
    public Integer getManufactureCost() { return manufactureCost; }
    @XmlElement
    public UnitOfMeasure getUnitOfMeasure() { return unitOfMeasure; }
    @XmlElement
    public Organization getManufacturer() { return manufacturer; }

    public String toStringDate() {
        return creationDate.toString();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", price=" + price +
                ", partNumber='" + partNumber + '\'' +
                ", manufactureCost=" + manufactureCost +
                ", unitOfMeasure=" + unitOfMeasure +
                ", manufacturer=" + manufacturer +
                '}';
    }

    @Override
    public int compareTo(Product p) {
        Long productPrice;
        if (p.getPrice() == null)
            productPrice = 0L;
        else
            productPrice = p.getPrice();
        Long comparingFlag = (this.getPrice() - productPrice);
        if (comparingFlag > 0) return 1;
        if (comparingFlag < 0) return -1;
        return 0;
    }
}