package com.learning.learning;

import com.learning.learning.controller.CarController;
import com.learning.learning.model.Car;
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

public class CarManager extends Application {

    @FXML
    private TextField modelTextField;

    @FXML
    private TextField brandTextField;

    @FXML
    private TextField yearTextField;

    @FXML
    private TableView<Car> carTableView;

    @FXML
    private TableColumn<Car, Long> idColumn;

    @FXML
    private TableColumn<Car, String> modelColumn;

    @FXML
    private TableColumn<Car, String> brandColumn;

    @FXML
    private TableColumn<Car, Integer> yearColumn;

    @FXML
    private MenuItem showCarsMenuItem;

    @FXML
    private MenuItem showPersonsMenuItem;

    private CarController carController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CarView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        primaryStage.setTitle("Car Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    public void initialize() {
        carController = new CarController();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        loadCars();
    }

    @FXML
    public void addCar() {
        Car car = new Car();
        car.setModel(modelTextField.getText());
        car.setBrand(brandTextField.getText());
        car.setYear(Integer.parseInt(yearTextField.getText()));
        carController.saveCar(car);
        loadCars();
    }

    @FXML
    public void updateCar() {
        Car selectedCar = carTableView.getSelectionModel().getSelectedItem();
        if (selectedCar != null) {
            selectedCar.setModel(modelTextField.getText());
            selectedCar.setBrand(brandTextField.getText());
            selectedCar.setYear(Integer.parseInt(yearTextField.getText()));
            carController.updateCar(selectedCar);
            loadCars();
        }
    }

    @FXML
    public void deleteCar() {
        Car selectedCar = carTableView.getSelectionModel().getSelectedItem();
        if (selectedCar != null) {
            carController.deleteCar(selectedCar);
            loadCars();
        }
    }

    private void loadCars() {
        ObservableList<Car> cars = carController.getAllCars();
        carTableView.setItems(cars);
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
