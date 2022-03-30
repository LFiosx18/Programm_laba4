package serialPack;

import serialPack.collection.Product;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 2L;

    private String command;
    private String argument;
    private Product product;

    public Request(String command, String argument, Product product) {
        this.command = command;
        this.argument = argument;
        this.product = product;
    }

    public Request() {}

    public void setCommand(String command) {this.command = command;}
    public void setArgument(String argument) {this.argument = argument;}
    public void setProduct(Product product) {this.product = product;}

    public String getCommand() { return command; }
    public String getArgument() { return argument; }
    public Product getProduct() { return product; }

    @Override
    public String toString() {
        return "Request{" +
                "command='" + command + '\'' +
                ", argument='" + argument + '\'' +
                ", product=" + product +
                '}';
    }
}
