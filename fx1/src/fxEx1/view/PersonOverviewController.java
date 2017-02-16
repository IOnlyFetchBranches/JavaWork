package fxEx1.view;
//continue http://code.makery.ch/library/javafx-8-tutorial/part3/
import fxEx1.MainApp;
import fxEx1.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.*;

@SuppressWarnings("ALL")
public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person,String> firstNameColumn;
    @FXML
    private TableColumn<Person,String> lastNameColumn;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    //ref the MainApp

    private MainApp mainApp;
    public PersonOverviewController() {
    } //no clue what this is for works without but will keep

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.

        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp main) {

        this.mainApp = main;
        personTable.setItems(mainApp.getPersonData());

        // Add observable list data to the table


    }


}
