package LLaba;

public class Salesman implements GetWork{
    private String work = "Продавец";
    public Salesman(String specific) {
        if (specific == "F") {
            this.work = work + ' ' + Product.Fruit;
        } else if (specific == "V") {
            this.work = work + ' ' + Product.Vegetable;
        } else if (specific == "S") {
            this.work = work + ' ' + Product.Stocks;
        }
    }
    @Override
    public String getWork() {
        return work;
    }
    public static void sale() {
        System.out.println(" раскладывают свой товар на прилавке");
    }
    public void stock() {
        System.out.println(getWork() + " носит свои акции в кармане");
        System.out.println(getWork() + " может лишь выкрикивать их название и цену");
    }
    private int cost = (int)Math.round(Math.random()*1000);
    public int getCost() {return cost;}
    public void screamone() {
        System.out.println("Один " + getWork() + " кричит стоимость: " + getCost() + " рублей");
    }
    public void screamchange() {
        cost = (int)Math.round(Math.random()*1000);
        System.out.println("Следующий " + getWork() + " предлагает цену: " + getCost() + " рублей");
    }
}


