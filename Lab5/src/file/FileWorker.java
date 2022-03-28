package file;

import collection.CollectionMng;
import collection.Product;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * Класс, предназначенный для парсинга объектов типа Product в файл формата xml и обратно
 */
public class FileWorker {
    private CollectionMng collectionMng;
    public FileWorker(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    Validator validator = new Validator(collectionMng);

    /**Метод для сохранения дынных в файл формата xml
     * @param collectionMng задает объект типа HashSet
     */
    public static void save(CollectionMng collectionMng) {
        String fileName = System.getenv("FILE_PATH");
        convertObjectToXml(collectionMng, fileName);
    }

    /**
     * Метод для парсинга их xml в объект
     */
    public void fromXmlToObject() {
        try {
            String fileName = System.getenv("FILE_PATH");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            JAXBContext jaxbContext = JAXBContext.newInstance(CollectionMng.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            CollectionMng collectionMng1 = (CollectionMng) unmarshaller.unmarshal(bufferedReader);
            for (Product val : collectionMng1.getProducts()) {
                if (validator.validId(val, collectionMng) && validator.validName(val) && validator.validCoord(val) &&
                        validator.validDate(val) && validator.validPrice(val) && validator.validPartNum(val) &&
                        validator.validManufactureCost(val) && validator.validMeasure(val) && validator.validOrg(val)) {
                    this.collectionMng.add(val);
                }
                else System.err.println("Элемент с именем " + val.getName() + " не был добавлен в коллекцию!");
            }
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e2) {
            System.err.println("Не удается найти файл!");
        } catch (FileNotFoundException e3) {
            System.out.println(e3.getMessage());
        } catch (Exception e4) {
            System.err.println("Ошибка при загрузки коллекции, возможно некоторое атрибуты (ID) указаны некорректно");
            System.exit(0);
        }
    }

    /**
     * Метод для парсинга из объекта в xml
     * @param collectionMng задает объект типа HashSet
     * @param filePath задает путь к файлу
     */
    private static void convertObjectToXml(CollectionMng collectionMng, String filePath) {
        try {
            PrintWriter pr = new PrintWriter(new File(filePath));
            StringWriter stringWriter = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(CollectionMng.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // маршаллинг объекта в файл
            marshaller.marshal(collectionMng, stringWriter);
            pr.print(stringWriter);
            pr.close();
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
