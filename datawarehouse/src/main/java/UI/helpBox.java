package UI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class helpBox {

    public void display(String title , String message){
        Stage window = new Stage();
        window.setTitle("title");
        //modality要使用Modality.APPLICATION_MODEL
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(300);
        window.setMinHeight(150);

        Label label = new Label(message);
        label.setFont(new Font(20));

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(50, 50, 50, 50));
        layout.getChildren().addAll(label);
        layout.setAlignment(Pos.TOP_LEFT);

        Scene scene = new Scene(layout, 700, 500);
        scene.setFill(Color.ALICEBLUE);
        window.setScene(scene);
        //使用showAndWait()先处理这个窗口，而如果不处理，main中的那个窗口不能响应
        window.showAndWait();
    }
}