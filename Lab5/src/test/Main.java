package test;


/**
 * Проверка на наличие переменной окружения
 * Запуск программы
 * @author Kurnosova Irina
 */
public class Main {
    public static void main(String[] args) {
        try{
            String inputFile = System.getenv("FILE_PATH");
            Aplication aplication = new Aplication();
            aplication.start(inputFile);
        } catch (NullPointerException e) {
            System.err.println("Укажите переменную окружения FILE_PATH!");
        }
    }
}
