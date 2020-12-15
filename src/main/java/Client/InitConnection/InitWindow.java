package Client.InitConnection;

import Client.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InitWindow extends JDialog {

    private JLabel labelTitle;
    private InputField textField;
    private ServerConnector serverConnector;
    private JButton joinButton;

    public InitWindow(ServerConnector connector) {
        super((JDialog)null); // Aby okno pokazało się rownież na taskbarze.
        serverConnector = connector;

        setModal(true);
        setTitle("Chinese Checkers");

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        createLabels();
        createInputComponent();
        createButton();

        setLocationRelativeTo(null);
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void createLabels() {
        labelTitle = new JLabel("Chinese Checkers");
        labelTitle.setFont(new Font("Serif", Font.BOLD, 60));

        add(labelTitle, new GBC(1, 1).setFill(GBC.NONE).setAnchor(GBC.SOUTH).setWeight(100, 100));
    }

    private void createInputComponent() {
        textField = new InputField();
        textField.setFonts(new Font("Arial", Font.PLAIN, 20));
        add(textField, new GBC(1, 2).setAnchor(GBC.CENTER).setWeight(100, 100));
    }

    private void createButton() {
        joinButton = new JButton("Join game");
        joinButton.setFont(new Font("Arial", Font.BOLD, 20));
        joinButton.addActionListener(new Click());
        add(joinButton, new GBC(1, 2).setAnchor(GBC.EAST).setFill(GBC.BOTH).setWeight(300, 200));
    }

    private void waitForInput() {
        setVisible(true);
    }

    private void close() {
        setVisible(false);
        this.dispose();
    }


    public static void main(String[] args) {

        Client client = new Client();
        ServerConnector connector = new ServerConnector(client);

        InitWindow init = new InitWindow(connector);
        init.waitForInput();

        if(!client.isConnected()) {
            return;
        }

        Controller controller = new Controller();

        client.setGameService(controller);
        controller.setSender(client);

        EventQueue.invokeLater(() ->
        {
            ClientWindow frame = new ClientWindow(controller);
            controller.setWindow(frame);
        });

        client.mainLoop();

        System.out.println("konic");
    }


    private class Click implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            String ip = textField.getIp();
            String port = textField.getPort();

            if (ip.equals("")) {
                JOptionPane.showMessageDialog(null, "Wprowadz adres IP!");
            } else if (port.equals("")) {
                JOptionPane.showMessageDialog(null, "Wprowadz numer PORT'u!");
            } else {
                boolean success = serverConnector.connectionRequest(ip, port);
                if (success) {
                    close();
                } else {
                    JOptionPane.showMessageDialog(null, "Nie mozna polaczyc sie z serwerem o podanym adresie IP i PORT");
                    ;
                }
            }
        }
    }
}
