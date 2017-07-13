package application;


import database.DatabaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import structures.BirthCell;
import structures.DoubleCell;
import structures.Person;
import structures.User;


import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    TableView<Person> personTableTV;

    @FXML
    TableColumn<Person, String> firstNameTC;

    @FXML
    TableColumn<Person, LocalDate> birthTC;

    @FXML
    TableColumn<Person, Double> ratingTC;


    private ObservableList<Person> personsModel = FXCollections.observableArrayList();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTablePerson();
    }



    private void initTablePerson(){
        firstNameTC.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        firstNameTC.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameTC.setOnEditCommit(event -> onEditCommitFirstName(event));



        birthTC.setCellValueFactory(new PropertyValueFactory<Person, LocalDate>("birth"));
        birthTC.setCellFactory(param -> new BirthCell());
        birthTC.setOnEditCommit(event -> onEditCommitBirth(event));


        ratingTC.setCellValueFactory(new PropertyValueFactory<Person, Double>("rating"));
        ratingTC.setCellFactory(param -> new DoubleCell());
        ratingTC.setOnEditCommit(event -> onEDitCommitRating(event));


        personTableTV.setItems(personsModel);





    }

    private void onEDitCommitRating(TableColumn.CellEditEvent<Person, Double> event) {
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setRating(event.getNewValue());

    }

    private void onEditCommitFirstName(TableColumn.CellEditEvent<Person, String> event) {
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setFirstName(event.getNewValue());
    }

    private void onEditCommitBirth(TableColumn.CellEditEvent<Person, LocalDate> event){
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setBirth(event.getNewValue());
    }




    public void addClick(){
        DatabaseController.UserTable table  = new DatabaseController.UserTable();
        System.out.println(); //GIT TEST
        //System.out.println(table.deleteUser("dudy","dazwd"));
        //table.addUser(new User("dudy", "dazwd", "wad59", "fs59"));
        /*Person person = new Person("LADA", LocalDate.now(), 5);
        personsModel.add(person);*/
    }

    public void delete(){
       if(!personTableTV.getSelectionModel().getSelectedItems().isEmpty()){
           personsModel.removeAll(personTableTV.getSelectionModel().getSelectedItems());
       }
    }


}
