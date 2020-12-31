package Client.InitConnection;/*
 *@author Karol Melanczuk
 */

import javax.swing.*;
import java.awt.*;
/**
 * 
 * Panelu z polami wypełnianymi przez użytkownika.
 *
 */
public class InputField extends JPanel {
	/**
	 * Pole do którego użytkownik wpisuje adres IP
	 */
    private final FormLine ipField;
    
    /**
     * Pole do którego użytkownik wpisuje adres PORT
     */
    private final FormLine portField;

    public InputField() {
        ipField = new FormLine("IP:  ", "127.0.0.1");
        portField = new FormLine("Port:  ", "59001");

        setLayout(new GridLayout(2, 1));
        add(ipField);
        add(portField);
    }

    public String getIp() {
        return ipField.getInput();
    }

    public String getPort() {
        return portField.getInput();
    }

    public void setFonts(Font font) {
        ipField.setFonts(font);
        portField.setFonts(font);
    }
}
