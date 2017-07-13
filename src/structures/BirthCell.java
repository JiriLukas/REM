package structures;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.input.KeyCode;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by George on 09.07.2017.
 */
public class BirthCell extends TableCell {

    DatePicker datePicker;

    @Override
    public void startEdit() {
        super.startEdit();
        if(datePicker == null){
            createEditor();
        }
        setText(null);
        datePicker.setValue(createEditorContent());
        setGraphic(datePicker);
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
                datePicker.setValue(createEditorContent());
                setText(null);
                setGraphic(datePicker);
            }else {
                setText(createContent());
                setGraphic(null);
            }
        }
    }

    private void createEditor(){
        datePicker = new DatePicker();
        datePicker.minWidthProperty().bind(this.widthProperty());
        datePicker.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) commitEdit(datePicker.getValue());
            if(event.getCode() == KeyCode.ESCAPE) cancelEdit();
        });
    }

    private LocalDate createEditorContent() {
        return (LocalDate) getItem();
    }

    private String createContent(){
        LocalDate localDate = ((LocalDate) getItem());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return localDate.format(format);
    }





}
