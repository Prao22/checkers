package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientWindow extends JFrame {

    private JLabel serverMessage = new JLabel();
    private JButton button = new JButton("Wyslij");
    private JTextField textField = new JTextField();
    private Controller controller; // *tego tu nie ma*

    public ClientWindow(Controller controller) {

//        this.controller = controller;
//        setLayout(new BorderLayout());
//
//        serverMessage.setPreferredSize(new Dimension(200,24));
//        textField.setPreferredSize(new Dimension(200,24));
//
//        add(serverMessage, BorderLayout.NORTH);
//        add(button, BorderLayout.CENTER);
//        add(textField, BorderLayout.SOUTH);
//
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                ClientWindow.this.controller.answer(textField.getText());
//            }
//        });
//
//        setSize(new Dimension(300, 300));
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
    }

    public void updateLabel(String text) {
        serverMessage.setText(text);
    }





}
