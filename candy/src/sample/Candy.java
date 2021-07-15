package sample;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Candy {
    public StackPane createCandy(String val){
        StackPane stackPane = new StackPane();

        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(35);
        rectangle.setWidth(150);
        rectangle.setArcHeight(18);
        rectangle.setArcWidth(18);
        rectangle.setStyle("-fx-fill: #336699");

        Text text = new Text(val);
        text.setFill(Color.WHITE);
        stackPane.getChildren().addAll(rectangle,text);

        return stackPane;
    }
}
