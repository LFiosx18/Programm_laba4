package database;

import serialPack.collection.*;
import serialPack.user.User;
import serverLib.CollectionMng;
import serverLib.util.DateConverter;

import java.sql.*;
import java.time.LocalDate;

public class DataBaseMng  extends DBParamConnect {
    private static final String ADD = "INSERT INTO products (username, name, x, y, date, price, partnum, manufacturecost, unitofmeasure, orgname, annualturnover, employeescount, type) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String LOAD = "SELECT * FROM products";
    private static final String DELETE = "DELETE FROM products WHERE username=?";
    private static final String FIND_BY_USERNAME = "SELECT (username) FROM users WHERE username=?";
    private static final String DELETE_BY_ID = "DELETE FROM products WHERE id=? AND username=?";
    private static final String INSERT_USER = "INSERT INTO users (username, password) VALUES (? , ?)";
    private static final String GET_PASSWORD_BY_USERNAME = "SELECT (password) FROM users WHERE username=?";
    private static final String REMOVE_GREATER = "DELETE FROM products WHERE price>? AND username=?";
    private static final String REMOVE_LOWER = "DELETE FROM products WHERE price<? AND username=?";
    DBConnect dbConnect = new DBConnect();
    Connection connect = dbConnect.connect();
    Statement statement = connect.createStatement();

    public DataBaseMng() throws SQLException {
    }


    public void load(CollectionMng collectionMng) {
        try {
            ResultSet resultSet = statement.executeQuery(LOAD);
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String name = resultSet.getString("name");
                Long id = resultSet.getLong("id");
                Coordinates coordinates = new Coordinates(
                        resultSet.getInt("x"),
                        resultSet.getInt("y")
                );
                LocalDate localDate = DateConverter.convertToLocalDate(resultSet.getDate("date"));
                Long price = resultSet.getLong("price");
                String partNumber = resultSet.getString("partNum");
                Integer manufactureCost = resultSet.getInt("manufacturecost");
                UnitOfMeasure unitOfMeasure = UnitOfMeasure.valueOf(resultSet.getString("unitOfMeasure").toUpperCase());
                Organization manufacturer = new Organization(
                        resultSet.getInt("orgId"),
                        resultSet.getString("orgName"),
                        resultSet.getLong("annualTurnover"),
                        resultSet.getLong("employeesCount"),
                        OrganizationType.valueOf(resultSet.getString("type").toUpperCase())
                );
                Product product = new Product(
                        id,
                        name,
                        coordinates,
                        localDate,
                        price,
                        partNumber,
                        manufactureCost,
                        unitOfMeasure,
                        manufacturer,
                        username
                );
                collectionMng.getProducts().add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addUserToDataBase(String username, String password) {
        if (isThatUserNameContains(username)) {
            return false;
        }
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean isThatUserNameContains(String username) {
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(FIND_BY_USERNAME);
            preparedStatement.setString(1, username);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) return true;
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String authorize(String username, String password) {
        if (!isThatUserNameContains(username)) return "Пользователя с таким именем нет!";
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(GET_PASSWORD_BY_USERNAME);
            preparedStatement.setString(1, username);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("password").equals(password)) {
                    return "Авторазиция прошла успешно! Для выхода из рабочего профиля введите 'out'";
                } else return "Неправильно введен пароль!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public boolean addProduct(Product product, User user) {
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setInt(3, product.getCoordinates().getX());
            preparedStatement.setInt(4, product.getCoordinates().getY());
            preparedStatement.setDate(5, DateConverter.convertToDate(product.getCreationDate()));
            preparedStatement.setLong(6, product.getPrice());
            preparedStatement.setString(7, product.getPartNumber());
            preparedStatement.setInt(8, product.getManufactureCost());
            preparedStatement.setString(9, product.getUnitOfMeasure().toString());
            preparedStatement.setString(10, product.getManufacturer().getName());
            preparedStatement.setLong(11, product.getManufacturer().getAnnualTurnover());
            preparedStatement.setLong(12, product.getManufacturer().getEmployeesCount());
            preparedStatement.setString(13, product.getManufacturer().getType().toString());
            if (preparedStatement.executeUpdate() != 0) {
                var resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    Integer orgId = resultSet.getInt("orgid");
                    product.setId(id);
                    product.getManufacturer().setId(orgId);
                }
                System.out.println("Product added!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean clear(String username) {
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(DELETE);
            preparedStatement.setString(1, username);
            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeId(String username, Long id) {
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(DELETE_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, username);
            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeGreater(Long price, String username) {
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(REMOVE_GREATER);
            preparedStatement.setLong(1, price);
            preparedStatement.setString(2, username);
            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeLower(Long price, String username) {
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(REMOVE_LOWER);
            preparedStatement.setLong(1, price);
            preparedStatement.setString(2, username);
            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}