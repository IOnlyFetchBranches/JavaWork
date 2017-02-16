import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.awt.*;

public class ClickMe extends Application {
    private static DataFormat BUTTON =new DataFormat("javafx.scene.control.Button");
    public void start(Stage primaryStage){
        primaryStage.resizableProperty().setValue(false);
        primaryStage.setTitle("Click Me! (Maybe Even Drag Me?)");
        primaryStage.initStyle(StageStyle.UNDECORATED);


        StackPane root=new StackPane();
        Button button=new Button();
        button.setText("Click Me!");
        button.setFont(Font.font("Times New Roman"));
        Rectangle trash=new Rectangle(250,20,Color.BLACK);
        Button exit=new Button();
        exit.setOnMouseClicked((MouseEvent event)->{
            System.exit(0);
        });
        exit.setMaxWidth(10);
        exit.setMaxHeight((10));
        exit.setText("X");
        exit.setBackground(new Background(new BackgroundFill(Color.RED,CornerRadii.EMPTY,null)));
        Label dragHere=new Label("Drag Here");

        Bloom bloom=new Bloom();
        dragHere.setEffect(bloom);
        trash.setOnDragOver((DragEvent event) ->{
            if(event.getGestureSource() != trash && event.getDragboard().hasImage()){
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);

            }
            else{
                Toolkit.getDefaultToolkit().beep();
            }
        });
        trash.setOnDragDropped((DragEvent event) -> {
            System.out.println("Gobble Gobble");

            try{

                ImageView view=new ImageView();
                view.setImage(event.getDragboard().getImage());
                view.setOnMouseClicked((MouseEvent mevent)->{
                    root.getChildren().removeAll(view);
                    JOptionPane.showMessageDialog(null,"Delicious","Yum",JOptionPane.PLAIN_MESSAGE);
                    Toolkit.getDefaultToolkit().beep();
                });
                root.getChildren().add(view);
                root.setAlignment(view,Pos.BOTTOM_RIGHT);

      }catch(Exception e){System.out.println(e.getLocalizedMessage());}
        });





        root.getChildren().add(trash);
        root.getChildren().add(dragHere);
        root.setAlignment(exit,Pos.TOP_LEFT);
        root.getChildren().add(exit);
        root.setAlignment(trash, Pos.TOP_RIGHT);
        root.setAlignment(dragHere,Pos.TOP_CENTER);

        button.setOnMouseClicked((t)-> {

            Color color=new Color(Math.random(),Math.random(),Math.random(),1);
            Color color2=new Color(Math.random(),Math.random(),Math.random(),1);
            BackgroundFill backfill=new BackgroundFill(color, CornerRadii.EMPTY,null);
            BackgroundFill backfill2=new BackgroundFill(color2, CornerRadii.EMPTY,null);
            Background back=new Background(backfill);
            Background back2=new Background(backfill2);
            root.setBackground(back);
            button.setBackground(back2);



        });
        button.setOnDragDetected((MouseEvent event) -> {
            System.out.println("Drag Detected");
            Dragboard db=button.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content=new ClipboardContent();
            Image img=new Image("http://vanessalougoon.com/wp-content/uploads/2014/06/Dragged-Kicking.jpg");

            content.putImage(img);
            db.setContent(content);
            //JOptionPane.showMessageDialog(null,"You Cant Do That!","uhuhuh",JOptionPane.ERROR_MESSAGE);
        });

        root.getChildren().add(button);
        Scene scene=new Scene(root,200,200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args){
        launch(args);
    }

}
