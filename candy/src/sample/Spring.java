package sample;

import javafx.geometry.Insets;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

public class Spring {
    public int springHeight = 40;

    public int getSpringHeight() { return springHeight; }

    public void setSpringHeight(int springHeight) { this.springHeight = springHeight; }

    public void refreshSpring(int stackSize){
        setSpringHeight(40 - (6 * stackSize));
    }

    public VBox getSpring(){
        VBox vbox = new VBox();
        vbox.setPrefWidth(10);
        VBox.setMargin(vbox, new Insets(5,0,0,0));
        VBox.setVgrow(vbox, Priority.ALWAYS);

        vbox.getChildren().add(new Line(0,0,150,0));

        for(int i = 0; i < 7; i++){
            Line line;
            if(i % 2 == 0){
                line = new Line(0,0,150,springHeight);
            }else{
                line = new Line(150,0,0,springHeight);
            }
            vbox.getChildren().add(line);
        }
        vbox.getChildren().add(new Line(0,0,150,0));

        return vbox;
    }

}
