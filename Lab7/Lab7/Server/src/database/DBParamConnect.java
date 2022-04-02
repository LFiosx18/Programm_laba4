package database;

public class DBParamConnect {
    protected String DB_BASE = "jdbc:postgresql://";
    protected String DB_NAME = ""; //Имя БД
    protected int DB_PORT = 5432;
    protected String DB_HOST = ""; //Хост БД

    protected String SV_LOGIN = ""; //Логин для подключения к серверу
    protected String SV_PASS = ""; //Пароль
    protected String SV_ADDR = ""; //Адрес подключения

    protected int SV_PORT;

    protected int SSH_PORT; //SSH порт
    protected int FORWARDING_PORT = 9191;
}
