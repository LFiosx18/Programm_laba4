package LLaba;

abstract public class Data {
    private final String Name;
    private final int Id;
    protected Data(String name, int id) {
        this.Name = name;
        this.Id = id;
    }
    public String getName() {return Name;}
    public int getId() {return Id;}
    public boolean equals(Data num){ return this.Id > num.Id; }
    public int hashCode() {
        int result = Name.hashCode();
        result = 31 * result + Id;
        return result; }
}
