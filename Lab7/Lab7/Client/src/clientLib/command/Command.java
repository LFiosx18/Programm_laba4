package clientLib.command;

import serialPack.collection.Product;
import clientLib.reader.ReaderValidator;
import serialPack.Request;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;

public class Command {
    private ReaderValidator readerValidator;

    public Command() {
        this.readerValidator = new ReaderValidator();
    }

    public Request toObject(String command) {
        Request request = new Request();

        String[] com = command.trim().toLowerCase(Locale.ROOT).split("\\s+");
        String[] args = Arrays.copyOfRange(com, 1, com.length);

        switch (com[0]) {
            case "show":
                request.setCommand("show");
                break;
            case "help":
                request.setCommand("help");
                break;
            case "info":
                request.setCommand("info");
                break;
            case "clear":
                request.setCommand("clear");
                break;
            case "exit":
                request.setCommand("exit");
                break;
            case "average_of_price":
                request.setCommand("average_of_price");
                break;
            case "print_unique_manufacture_cost":
                request.setCommand("print_unique_manufacture_cost");
                break;
            case "remove_by_id":
                request.setCommand("remove_by_id");
                if (args.length==0)
                    request.setArgument(readerValidator.readId(""));
                else
                    request.setArgument(readerValidator.readId(args[0]));
                break;
            case "filter_contains_name":
                request.setCommand("filter_contains_name");
                if (args.length==0)
                    request.setArgument(readerValidator.readFilterName());
                else
                    request.setArgument(args[0]);
                break;
            case "update":
                request.setCommand("update");
                if (args.length==0)
                    request.setArgument(readerValidator.readId(""));
                else
                    request.setArgument(readerValidator.readId(args[0]));
                request.setProduct(new Update().updateEl());
                break;
            case "add":
                request.setCommand("add");
                request.setProduct(new Product(
                        readerValidator.readName(),
                        readerValidator.readCoordinates(),
                        LocalDate.now(),
                        readerValidator.readPrice(),
                        readerValidator.readPartNum(),
                        readerValidator.readManufactereCost(),
                        readerValidator.readUnitOfMeasure(),
                        readerValidator.readOrganization()
                ));
                break;
            case "add_if_max":
                request.setCommand("add_if_max");
                request.setProduct(new Product(
                        readerValidator.readName(),
                        readerValidator.readCoordinates(),
                        LocalDate.now(),
                        readerValidator.readPrice(),
                        readerValidator.readPartNum(),
                        readerValidator.readManufactereCost(),
                        readerValidator.readUnitOfMeasure(),
                        readerValidator.readOrganization()
                ));
                break;
            case "remove_greater":
                request.setCommand("remove_greater");
                request.setProduct(new Product(
                        readerValidator.readName(),
                        readerValidator.readCoordinates(),
                        LocalDate.now(),
                        readerValidator.readPrice(),
                        readerValidator.readPartNum(),
                        readerValidator.readManufactereCost(),
                        readerValidator.readUnitOfMeasure(),
                        readerValidator.readOrganization()
                ));
                break;
            case "remove_lower":
                request.setCommand("remove_lower");
                request.setProduct(new Product(
                        readerValidator.readName(),
                        readerValidator.readCoordinates(),
                        LocalDate.now(),
                        readerValidator.readPrice(),
                        readerValidator.readPartNum(),
                        readerValidator.readManufactereCost(),
                        readerValidator.readUnitOfMeasure(),
                        readerValidator.readOrganization()
                ));
                break;
            default: return null;
        }
        return request;
    }
}
