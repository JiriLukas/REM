package structures;

import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;




public class DoubleCell extends TableCell{

    TextField doubleTF;

    @Override
    public void startEdit() {
        super.startEdit();
        if(doubleTF == null){
            createEditor();
        }
        setText(null);
        doubleTF.setText(createEditorContent());
        setGraphic(doubleTF);
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(createContent());
        setGraphic(null);

    }



    @Override
    protected void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);

        /** If we are editing empty cell*/
        if(empty){
            setText(null);
            setGraphic(null);
        }else {
            if(isEditing()){
                doubleTF.setText(createEditorContent());
                setText(null);
                setGraphic(doubleTF);
            }else {
                setText(createContent());
                setGraphic(null);
            }
        }
    }

    private void createEditor(){
        doubleTF = new TextField();
        doubleTF.minWidthProperty().bind(this.widthProperty());
        doubleTF.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER && isDouble(doubleTF.getText())) commitEdit(Double.parseDouble(doubleTF.getText()));
            if(event.getCode() == KeyCode.ESCAPE) cancelEdit();
        });
    }



    private String createEditorContent() {
        return  getItem().toString();
    }

    private String createContent(){
        return getItem().toString();
    }

    private boolean isDouble(String text){
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("VALUE ERROR");
            alert.setHeaderText("THIS IS NOT A NUMBER");
            alert.showAndWait();
        }
        return false;
    }

}
