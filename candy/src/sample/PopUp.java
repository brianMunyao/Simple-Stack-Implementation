package sample;

import javafx.scene.control.*;

import java.util.Optional;

public class PopUp {
    public void showPopUp(String title){
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Alert");
        dialog.setContentText(title);

        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(type);

        dialog.showAndWait();
    }

    public int showPopUpInput() throws Exception {
        TextInputDialog td = new TextInputDialog();
        td.setHeaderText("Enter an integer");
        Optional<String> result = td.showAndWait();

        int res;
        if(result.isPresent()){
            try{
                res = Integer.parseInt(td.getEditor().getText());
            }catch(Exception e){
                showPopUp("Enter a valid number");
                res = showPopUpInput();
            }
        }else{
            td.close();
            throw new Exception();
        }
        return res;
    }
}
