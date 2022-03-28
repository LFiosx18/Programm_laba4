package collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//Класс для работы с координатами
@XmlRootElement(name = "coordinates")
@XmlType(propOrder = {"x", "y"})
public class Coordinates {
    private Integer x;  //Максимальное значение поля: 226, Поле не может быть null
    private Integer y;  //Поле не может быть null

    public Coordinates(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(){}

    @XmlElement(name = "coordinateX")
    public Integer getX() { return x; }
    @XmlElement(name = "coordinateY")
    public Integer getY() { return y; }

    public void setX(Integer x) { this.x = x; }
    public void setY(Integer y) { this.y = y; }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
