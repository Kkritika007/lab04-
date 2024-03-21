//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.georgiancollege.test1;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;


public class EmployeeController implements Initializable {
    @FXML
    private TableView<Employee> tableView;
    @FXML
    private TableColumn<Employee, Integer> employeeIdColumn;
    @FXML
    private TableColumn<Employee, String> firstNameColumn;
    @FXML
    private TableColumn<Employee, String> lastNameColumn;
    @FXML
    private TableColumn<Employee, String> addressColumn;
    @FXML
    private TableColumn<Employee, String> cityColumn;
    @FXML
    private TableColumn<Employee, String> provinceColumn;
    @FXML
    private TableColumn<Employee, String> phoneColumn;
    @FXML
    private CheckBox ontarioOnlyCheckBox;
    @FXML
    private ComboBox<String> areaCodeComboBox;
    @FXML
    private Label noOfEmployeesLabel;

    public EmployeeController() {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        this.firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        this.addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        this.cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        this.provinceColumn.setCellValueFactory(new PropertyValueFactory<>("province"));
        this.phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        this.areaCodeComboBox.getItems().add("All");
        List<Employee> returnEmployees = DBUtility.getDataFromDB();
        this.tableView.getItems().addAll(returnEmployees);
        this.populateAreaCodes(returnEmployees);
        this.updateNoOfEmployeesLabel();
    }

    private void populateAreaCodes(List<Employee> employees) {
        TreeSet<String> areaCodes = new TreeSet<>();

        for (Employee employee : employees) {
            String phone = employee.getPhoneNo();
            if (phone != null && phone.length() >= 3) {
                areaCodes.add(phone.substring(0, 3));
            }
        }

        this.areaCodeComboBox.getItems().addAll(areaCodes);
    }

    private void updateNoOfEmployeesLabel() {
        int numOfEmployees = this.tableView.getItems().size();
        this.noOfEmployeesLabel.setText("Number of Employees: " + numOfEmployees);
    }

    @FXML
    void ontarioOnlyCheckBox_OnClick(ActionEvent event) {
        if (this.ontarioOnlyCheckBox.isSelected()) {
            this.areaCodeComboBox.getSelectionModel().clearAndSelect(0);
            ObservableList<Employee> items = this.tableView.getItems();
            items.removeIf((employee) -> {
                return !employee.getProvince().equals("ON");
            });
            this.updateNoOfEmployeesLabel();
        } else {
            List<Employee> returnEmployees = DBUtility.getDataFromDB();
            this.tableView.getItems().setAll(returnEmployees);
            this.populateAreaCodes(returnEmployees);
            this.updateNoOfEmployeesLabel();
        }

    }

    @FXML
    void areaCodeComboBox_OnClick(ActionEvent event) {
        String selectedAreaCode = (String)this.areaCodeComboBox.getSelectionModel().getSelectedItem();
        if (selectedAreaCode != null && !selectedAreaCode.equals("All")) {
            this.ontarioOnlyCheckBox.setSelected(false);
            ObservableList<Employee> items = this.tableView.getItems();
            items.removeIf((employee) -> {
                return !employee.getPhoneNo().startsWith(selectedAreaCode);
            });
            this.updateNoOfEmployeesLabel();
        } else {
            List<Employee> returnEmployees = DBUtility.getDataFromDB();
            if (!this.ontarioOnlyCheckBox.isSelected()) {
                this.tableView.getItems().setAll(returnEmployees);
                this.updateNoOfEmployeesLabel();
            } else {
                ObservableList<Employee> items = this.tableView.getItems();
                items.removeIf((employee) -> {
                    return !employee.getProvince().equals("ON");
                });
                this.updateNoOfEmployeesLabel();
            }
        }

    }
}
