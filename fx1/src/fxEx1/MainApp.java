package fxEx1;


import fxEx1.model.Person;
import fxEx1.view.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class MainApp extends Application {
    private static MainApp main=new MainApp();
    private Stage mainStage;
    private BorderPane rootLayout;
    private ObservableList<Person> personData= FXCollections.observableArrayList();
    public  void initData(){
        //debug data add
        personData.add(new Person("Hans", "Muster"));
        personData.add(new Person("Ruth", "Mueller"));
        personData.add(new Person("Heinz", "Kurz"));
        personData.add(new Person("Cornelia", "Meier"));
        personData.add(new Person("Werner", "Meyer"));
        personData.add(new Person("Lydia", "Kunz"));
        personData.add(new Person("Anna", "Best"));
        personData.add(new Person("Stefan", "Meier"));
        personData.add(new Person("Martin", "Mueller"));
    }
    public static void main(String[] args) {

        launch(args);
    }





    public ObservableList<Person> getPersonData() {
        return personData;
    }


    @Override
    public void start(Stage primaryStage) {
        this.mainStage=primaryStage;
        this.mainStage.setTitle("AddressApp");
        initData();
        initRootLayout();
        showPersonOverview();

    }
    public void initRootLayout() {
        //load layout from FXML file
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
        try {
            rootLayout = (BorderPane) loader.load();



            //set the scene, containing the root layout
            Scene scene= new Scene(rootLayout);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ie ) {
            ie.printStackTrace();
        }
    }

    public void showPersonOverview(){


        try{
            //load person overbiew
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane po= (AnchorPane) loader.load();

            // Allign in center

            rootLayout.setCenter(po);

            // Give the controller access to the main app.
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);
        }catch(IOException ioe){ioe.printStackTrace();}
    }
    public Stage getMainStage(){
        return mainStage;
    }

}
