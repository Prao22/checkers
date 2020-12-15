package Client.InitConnection;

import javax.swing.*;
import java.awt.*;

public class FormLine extends JPanel {
    private final JTextField textField;
    private final JLabel label;

    public FormLine(String labelText, String inputText) {
        textField = new JTextField(inputText);
        label = new JLabel(labelText, SwingConstants.RIGHT);

        textField.setPreferredSize(new Dimension(200, 24));

        setLayout(new GridLayout(1, 2));

        add(label);
        add(textField);
    }

    public void setFonts(Font font) {
        label.setFont(font);
        textField.setFont(font);
    }

    public String getInput() {
        return textField.getText();
    }
}
