/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m.view;

/**
 * This class extends from OutputStream to redirect output to a TextArea
 *
 * @author Fabrice
 */
import java.io.IOException;
import java.io.OutputStream;
import javafx.scene.control.TextArea;
import javafx.application.Platform;

public class Console extends OutputStream {

    private TextArea console;

    public Console(TextArea console) {
        this.console = console;
    }

    private void addText(String str) {
        Platform.runLater(() -> console.appendText(str));
    }

    @Override

    public void write(int b) throws IOException {
        //redirects data into the text area
        addText(String.valueOf((char) b));
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        addText(new String(b, off, len));
    }

    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }
}
