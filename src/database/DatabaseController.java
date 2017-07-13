package database;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import structures.Anime;
import structures.User;
import java.sql.*;
import java.util.ArrayList;


/**
 * Class DatabaseController
 * is class for work with database (SELECT, INSERT, UPDATE, DELETE)
 * @author George Lukas
 * @version 1.0
 */
public class DatabaseController {

    /* Connection type */
    private final String CONNTYPE  = "org.sqlite.JDBC";

    /* Database name */
    private final String NAME      = "AnimeDatabase.sqlite";

    /* Connection where */
    private final String CONN      = "jdbc:sqlite:src/database/" + NAME;

    /* Connection to the database */
    private Connection connection;

    /* Database instance */
    private static DatabaseController ourInstance = new DatabaseController();


    /**
     * Constructor
     * initializing connection to null
     */
    private DatabaseController() {
        this.connection = null;
    }

    /**
     * Method getInstance
     * @return - instance of database controller
     */
    public static DatabaseController getInstance(){
        return ourInstance;
    }


    /**
     * Method connect
     * is creating connetion with database if the connection
     * doesn't exist.
     */
    public void connect() {
        if(connection == null){
            try {

                Class.forName(CONNTYPE);
                connection = DriverManager.getConnection(CONN);

            } catch (ClassNotFoundException e) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("CONNECTION FAILED");
                alert.setHeaderText("DATABASE NOT FOUND");
                alert.showAndWait();
                e.printStackTrace();

                Platform.exit();
            } catch (SQLException e) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("CONNECTION FAILED");
                alert.setHeaderText("SQL COMMAND MISTAKE");
                alert.showAndWait();
                e.printStackTrace();

                Platform.exit();
            }
        }
    }


    /**
     * Method insertAnime
     * adding one anime to the database
     * @param table - anime table
     * @param anime - added anime
     * @param id - id for anime
     * @return - true if it is successful, false if it is not
     */
    public boolean insertAnime(String table, Anime anime, int id) {
        if(connection == null){
            connect();
        }
        String sql = "INSERT INTO " + table + "(id, picture, name, genres, personalRating, internetRating, date, recommended, description) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            if (id != 0)preparedStatement.setInt(1, id);
            preparedStatement.setBytes (2, anime.getPicture().toByteArray());
            preparedStatement.setString(3, anime.getName());
            preparedStatement.setString(4, anime.getGenres());
            preparedStatement.setDouble(5, anime.getPersonalRating());
            preparedStatement.setDouble(6, anime.getInternetRating());
            preparedStatement.setString(7, anime.getDate());
            preparedStatement.setString(8, anime.getRecomended());
            preparedStatement.setString(9, anime.getDescription());
            preparedStatement.setString(10,anime.getStatus().name());

            preparedStatement.execute();
            preparedStatement.close();

            return true;

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("INSET FAILED");
            alert.setHeaderText("SQL COMMAND MISTAKE");
            alert.showAndWait();
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method insertUser
     * adding new user to the database.
     * @param table - user table
     * @param newUser - new user
     * @return - true if user is added, false if he is not
     */
    public boolean insertUser(String table, User newUser){
        if(connection == null){
            connect();
        }
        String sql = "INSERT INTO " + table + "(id, login, password, name, lastName, avatar) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            if (newUser.getUserID() != 0) preparedStatement.setInt(1, newUser.getUserID());
            preparedStatement.setString(2, newUser.getLogin());
            preparedStatement.setString(3, newUser.getPassword());
            preparedStatement.setString(4, newUser.getName());
            preparedStatement.setString(5, newUser.getLastName());
            if(newUser.getAvatar() != null) preparedStatement.setBytes (6, newUser.getAvatar().toByteArray());

            preparedStatement.execute();
            preparedStatement.close();

            return true;

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("INSET USER FAILED");
            alert.setHeaderText("SQL COMMAND ERROR CONTACT SUPPORT");
            alert.showAndWait();
            e.printStackTrace();
        }
        return false;

    }

    /**
     * Method selectAll
     * is returning all data from database
     * @param table - database table
     * @return - data from database
     */
    public ResultSet select(String table){
        if (connection == null) {
            connect();
        }
        String sql = "SELECT * FROM " + table;

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            return resultSet;

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("GET DATA FAILED");
            alert.setHeaderText("SQL COMMAND ERROR CONTACT SUPPORT");
            alert.showAndWait();
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Method select
     * is selecting data from table by column and value
     * @param table - database table
     * @param column - column name
     * @param value - value in column
     * @return - all rows witch contains this column with value
     */
    public ResultSet select(String table, String column, String value){
        if (connection == null) {
            connect();
        }
        String sql = "SELECT * FROM " + table + " WHERE " + column +"=" + "'" + value + "'";

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            return resultSet;

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("GET DATA FAILED");
            alert.setHeaderText("SQL COMMAND ERROR CONTACT SUPPORT");
            alert.showAndWait();
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Method select
     * is selecting data from table by column and value
     * @param table - database table
     * @param column1 - column1 name
     * @param column2 - column2 name
     * @param value1 - value in column1
     * @param value2 - value in column2
     * @return - all rows witch contains this column with value
     */
    public ResultSet select(String table, String column1, String column2, String value1, String value2){
        if (connection == null) {
            connect();
        }
        String sql = "SELECT * FROM " + table + " WHERE " + column1 +"=" + "'" + value1 + "'" + " AND " + column2 +"=" + "'" + value2 + "'" ;

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            return resultSet;

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("GET DATA FAILED");
            alert.setHeaderText("SQL COMMAND ERROR CONTACT SUPPORT");
            alert.showAndWait();
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Method delete
     * deleting element from database
     * @param table - database table
     * @param ID - id of element
     * @return - true if it is successful, false when it is not
     */
    public boolean delete(String table, int ID){
        if(connection == null){
            connect();
        }

        String sql  = "DELETE FROM " + table +" WHERE id='" + ID + "'";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);

            statement.close();
            return true;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("DELETE DATA FAILED");
            alert.setHeaderText("SQL COMMAND ERROR CONTACT SUPPORT");
            alert.showAndWait();
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Method Disconnect
     * is canceling database connection
     */
    public void disconnect(){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("DISCONNECT FAILED");
                alert.setHeaderText("SQL COMMAND ERROR CONTACT SUPPORT");
                alert.showAndWait();
                e.printStackTrace();
            }
        }
    }




    /**
     * Class UserTable
     * controlling data in database for user table.
     * Adding, deleting user from user table.
     */
    public static class UserTable {

        private final String TABLENAME = "UserTable";
        private final IDGenerator IDGenerator = new IDGenerator();
        private final DatabaseController database = DatabaseController.getInstance();


        /**
         * Constructor
         * creating instance and setting free ids for id generator
         */
        public UserTable() {
            setIDGenerator();
        }


        /**
         * Method addUser
         * adding user to the database. Only if user doesn't exist in database.
         * @param newUser - added user
         * @return - true if he is added, false if he is not
         */
        public boolean addUser(User newUser){
            try {
                if(!isUserInDatabase(newUser)){
                    if (IDGenerator.isFreeID()){
                        newUser.setUserID(IDGenerator.getID());
                        database.insertUser(TABLENAME, newUser);
                    }else {
                        database.insertUser(TABLENAME,newUser);
                    }
                    return true;
                }else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ADD USER");
                    alert.setHeaderText("THIS USER OR LOGIN IS ALREADY EXIST");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ADD USER");
                alert.setHeaderText("SQL COMMAND ERROR CONTACT SUPPORT");
                alert.showAndWait();
                e.printStackTrace();
            }
            return false;
        }


        /**
         * Method delete user
         * @param login - user login
         * @param password - user password
         * @return - true if the user is deleted, false if he is not
         */
        public boolean deleteUser(String login, String password){
            try {
                ResultSet access = database.select(TABLENAME, "login", "password", login, password);
                if(access.next()){
                    database.delete(TABLENAME, access.getInt("id"));
                    return true;
                }
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("DELETE USER");
                alert.setHeaderText("SQL COMMAND ERROR CONTACT SUPPORT");
                alert.showAndWait();
                e.printStackTrace();
            }
            return false;
        }


        /**
         * Method isGrantAccess
         * checking user in database if login and password is ok
         * access granted.
         * @param login - user login
         * @param password - user password
         * @return - true if access is granted, false if it is not
         */
        public boolean isGrantAccess(String login, String password) {
            try {
                ResultSet access = database.select(TABLENAME, "login", "password", login, password);
                return access.next();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ACCESS GRANTING");
                alert.setHeaderText("SQL ERROR CONTACT SUPPORT");
                alert.showAndWait();
                e.printStackTrace();
            }
            return false;
        }


        /**
         * Method setIDGenerator
         * setting id generator. Filling id generator with free ids.
         */
        private void setIDGenerator(){
            try {
                IDGenerator.setFreeIds(getFreeIDList());
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("SET ID GENERATOR");
                alert.setHeaderText("SQL ERROR CONTACT SUPPORT");
                alert.showAndWait();
                e.printStackTrace();
            }
        }

        /**
         * Method getFreeIDList
         * searching in database of users for free ids.
         * @return - list of free ids
         * @throws SQLException - Result set errors
         */
        private ArrayList<Integer> getFreeIDList() throws SQLException {
            ResultSet resultSet = database.select(TABLENAME);
            ArrayList<Integer> freeID = new ArrayList<>();
            int index = 1;

            while(resultSet.next()){
                int id = resultSet.getInt("id");

                if(index != id){
                    while(index != id){
                        freeID.add(index);
                        index++;
                    }
                }

                index++;
            }
            return freeID;
        }


        /**
         * Method isUserInDatabase
         * @param user - this is the user we are searching for
         * @return - true if the user is in database, false if he is not
         * @throws SQLException - ResultSet Exception
         */
        private boolean isUserInDatabase(User user) throws SQLException {
            ResultSet nameCheck = database.select(TABLENAME, "name", "lastName", user.getName(), user.getLastName());
            ResultSet loginCheck = database.select(TABLENAME, "login", user.getLogin());

            return nameCheck.next() || loginCheck.next();
        }







    }
}
