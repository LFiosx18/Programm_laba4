package collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//Класс Organization
@XmlRootElement(name = "Organization")
@XmlType(propOrder = {"id", "name", "annualTurnover", "employeesCount", "type"})
public class Organization {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long annualTurnover; //Значение поля должно быть больше 0
    private Long employeesCount; //Поле может быть null, Значение поля должно быть больше 0
    private OrganizationType type; //Поле может быть null

    public Organization(Integer id, String name, long annualTurnover, Long employeesCount, OrganizationType type) {
        this.id = id;
        this.name = name;
        this.annualTurnover = annualTurnover;
        this.employeesCount = employeesCount;
        this.type = type;
    }

    public Organization(){}

    @XmlElement(name = "id")
    public Integer getId() { return id; }
    @XmlElement(name = "name")
    public String getName() { return name; }
    @XmlElement(name = "annualTurnover")
    public long getAnnualTurnover() { return annualTurnover; }
    @XmlElement(name = "employeesCount")
    public Long getEmployeesCount() { return employeesCount; }
    @XmlElement(name = "type")
    public OrganizationType getType() { return type; }

    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAnnualTurnover(long annualTurnover) { this.annualTurnover = annualTurnover; }
    public void setEmployeesCount(Long employeesCount) { this.employeesCount = employeesCount; }
    public void setType(OrganizationType type) { this.type = type; }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", annualTurnover=" + annualTurnover +
                ", employeesCount=" + employeesCount +
                ", type=" + type +
                '}';
    }
}
