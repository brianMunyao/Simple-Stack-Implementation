package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private final int HEIGHT = 350;
    private final int WIDTH = 600;

    private VBox candyHolder;
    private VBox candies;

    public static Stack stack = new Stack();
    public PopUp popup = new PopUp();
    public Spring spring;

    @Override
    public void start(Stage primaryStage) throws Exception{
        HBox hBox = new HBox();
        hBox.getChildren().addAll(renderLeftCon(), renderBtnCon());

        Scene scene = new Scene(hBox, WIDTH, HEIGHT);
        scene.getStylesheets().add("sample/style.css");

        primaryStage.setTitle("Candy Dispenser");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public HBox renderLeftCon(){
        HBox hbox = new HBox();
        hbox.setPrefWidth(400);
        hbox.setAlignment(Pos.TOP_CENTER);

        candyHolder = new VBox();
        candyHolder.setPadding(new Insets(5));
        candyHolder.setMinWidth(160);
        candyHolder.setPrefHeight(HEIGHT);
        candyHolder.setBorder(new Border(new BorderStroke(Color.TEAL, Color.TEAL, Color.TEAL,Color.TEAL,BorderStrokeStyle.NONE,BorderStrokeStyle.SOLID,BorderStrokeStyle.SOLID,BorderStrokeStyle.SOLID, new CornerRadii(5),new BorderWidths(1),new Insets(20,0,20,0))));

        candies = new VBox();
        candies.setAlignment(Pos.TOP_CENTER);
        candies.setStyle("-fx-spacing: 5px");

        spring = new Spring();

        candyHolder.getChildren().addAll(candies, spring.getSpring());
        hbox.getChildren().add(candyHolder);
        return hbox;
    }

    public VBox renderBtnCon(){
        Label label = new Label();
        label.setText("Candy Dispenser");
        label.setStyle("-fx-font-weight: bold");

        VBox vbox = new VBox();
        vbox.setPrefWidth(200);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.getStyleClass().add("btnCon");

        Button pushBtn = new Button("push()");
        pushBtn.getStyleClass().add("btn");
        pushBtn.setOnAction(e -> {
            int val;
            try {
                if(stack.isFull()){
                    popup.showPopUp("Dispenser full.");
                }else{
                    val = popup.showPopUpInput();
                    if(stack.push(val)){
                        refreshUI();
                    }
                }
            } catch (Exception ignored) {}

        });

        Button popBtn = new Button("pop()");
        popBtn.getStyleClass().add("btn");
        popBtn.setOnAction(e -> {
            try {
                int popped = stack.pop();
                refreshUI();
                popup.showPopUp(popped+ " popped");
            } catch (Exception exception) {
                popup.showPopUp("Dispenser is empty.");
            }
        });

        Button isEmptyBtn = new Button("isEmpty()");
        isEmptyBtn.getStyleClass().add("btn");
        isEmptyBtn.setOnAction(e-> {
            if(stack.isEmpty()){
                popup.showPopUp("isEmpty: True");
            }else{
                popup.showPopUp("isEmpty: False");
            }
        });

        Button topBtn = new Button("top()");
        topBtn.getStyleClass().add("btn");
        topBtn.setOnAction(e -> {
            try {
                popup.showPopUp("Top candy: " + stack.peek());
            } catch (Exception exception) {
                popup.showPopUp("No top candy. Dispenser is empty.");
            }
        });

        Button sizeBtn = new Button("size()");
        sizeBtn.getStyleClass().add("btn");
        sizeBtn.setOnAction(e -> popup.showPopUp("Size: " + stack.size()));

        vbox.getChildren().addAll(label, pushBtn, popBtn, isEmptyBtn, topBtn, sizeBtn);
        return vbox;
    }

    public void refreshUI(){
        int stackSize = stack.size();
        int topIndex = stack.getTopIndex();

        candyHolder.getChildren().clear();
        spring.refreshSpring(stackSize);
        candyHolder.getChildren().addAll(candies,spring.getSpring());
        candies.getChildren().clear();

        for (int i = 0; i < stackSize; i++){
            candies.getChildren().add(new Candy().createCandy(stack.getStack()[topIndex--] + ""));
        }
    }
}
