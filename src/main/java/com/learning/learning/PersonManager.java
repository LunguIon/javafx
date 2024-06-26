package com.learning.learning;

import com.learning.learning.controller.PersonController;
import com.learning.learning.model.Person;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PersonManager extends Application {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private TableView<Person> personTableView;

    @FXML
    private TableColumn<Person, Long> idColumn;

    @FXML
    private TableColumn<Person, String> nameColumn;

    @FXML
    private TableColumn<Person, Integer> ageColumn;

    @FXML
    private MenuItem showCarsMenuItem;

    @FXML
    private MenuItem showPersonsMenuItem;

    private PersonController personController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PersonView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        primaryStage.setTitle("Person Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    public void initialize() {
        personController = new PersonController();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        loadPersons();
    }

    @FXML
    public void addPerson() {
        Person person = new Person();
        person.setName(nameTextField.getText());
        person.setAge(Integer.parseInt(ageTextField.getText()));
        personController.savePerson(person);
        loadPersons();
    }

    @FXML
    public void updatePerson() {
        Person selectedPerson = personTableView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            selectedPerson.setName(nameTextField.getText());
            selectedPerson.setAge(Integer.parseInt(ageTextField.getText()));
            personController.updatePerson(selectedPerson);
            loadPersons();
        }
    }

    @FXML
    public void deletePerson() {
        Person selectedPerson = personTableView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            personController.deletePerson(selectedPerson);
            loadPersons();
        }
    }

    private void loadPersons() {
        ObservableList<Person> persons = personController.getAllPersons();
        personTableView.setItems(persons);
    }

    @FXML
    public void showCars() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CarView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage stage = (Stage) showCarsMenuItem.getParentPopup().getOwnerWindow();
        stage.setScene(scene);
    }

    @FXML
    public void showPersons() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PersonView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage stage = (Stage) showPersonsMenuItem.getParentPopup().getOwnerWindow();
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
