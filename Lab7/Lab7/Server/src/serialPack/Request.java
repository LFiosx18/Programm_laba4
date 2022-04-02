package serialPack;

import serialPack.collection.Product;
import serialPack.user.User;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 2L;

    private User user;
    private String command;
    private String argument;
    private Product product;

    public Request(String command, String argument, Product product) {
        this.command = command;
        this.argument = argument;
        this.product = product;
    }

    public Request(User user, String command, String argument, Product product) {
        this.user = user;
        this.command = command;
        this.argument = argument;
        this.product = product;
    }

    public Request (User user) {
        this.user = user;
    }

    public Request() {}

    public void setUser(User user) {this.user = user;}
    public void setCommand(String command) {this.command = command;}
    public void setArgument(String argument) {this.argument = argument;}
    public void setProduct(Product product) {this.product = product;}

    public User getUser() { return user; }
    public String getCommand() { return command; }
    public String getArgument() { return argument; }
    public Product getProduct() { return product; }

    @Override
    public String toString() {
        return "Request{" +
                "user=" + user +
                ", command='" + command + '\'' +
                ", argument='" + argument + '\'' +
                ", product=" + product +
                '}';
    }
}
