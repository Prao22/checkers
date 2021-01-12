package Client.InitConnection;

import Client.*;
import Client.GUI.GameWindow;
import Connection.ConnectionService;
import Connection.IConnectionService;
import Utility.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Okno dialogowe z polami IP oraz PORT do wypelnienia
 */
public class InitWindow extends JDialog {

    /**
     * Tytuł gry
     */
    private JLabel labelTitle;

    /**
     * Pola do wypelnienia przez użytkownika
     */
    private InputField textField;

    /**
     * Obiekt za pomocą którego będziemy łączyć się z serwerem.
     */
    private ServerConnector serverConnector;

    /**
     * Przycisk zatwierdzanjący wpisane dane
     */
    private JButton joinButton;

    public InitWindow(ServerConnector connector) {
        super((JDialog) null); // Aby okno pokazało się rownież na taskbarze.
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

    /**
     * Dodajemy tytuł gry.
     */
    private void createLabels() {
        labelTitle = new JLabel("Chinese Checkers");
        labelTitle.setFont(new Font("Serif", Font.BOLD, 60));

        add(labelTitle, new GBC(1, 1).setFill(GBC.NONE).setAnchor(GBC.SOUTH).setWeight(100, 100));
    }


    /**
     * Dodajemy pola tekstowe.
     */
    private void createInputComponent() {
        textField = new InputField();
        textField.setFonts(new Font("Arial", Font.PLAIN, 20));
        add(textField, new GBC(1, 2).setAnchor(GBC.CENTER).setWeight(100, 100));
    }

    /**
     * Dodajemy przycisk.
     */
    private void createButton() {
        joinButton = new JButton("Join game");
        joinButton.setFont(new Font("Arial", Font.BOLD, 20));
        joinButton.addActionListener(new Click());
        add(joinButton, new GBC(1, 2).setAnchor(GBC.EAST).setFill(GBC.BOTH).setWeight(300, 200));
    }

    private void waitForInput() {
        setVisible(true);
    }

    /**
     * Zamyka okno.
     */
    private void close() {
        setVisible(false);
        this.dispose();
    }


    /**
     * POCZĄTEK APLIKACJI DLA KLIENTA!
     * @param args -
     */
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring_client.xml");

        InitWindow init = (InitWindow)context.getBean("initWindow");
        init.waitForInput();

        //tutaj ile zaleznosci zaoszczedzono
        /*
        //IConnectionService connectionService = new ConnectionService();
        ServerHandler serverHandler = new ServerHandler((IConnectionService)context.getBean("connectionService"));//connectionService);

        if (!serverHandler.isConnected()) {
            return;
        }

        GameController gameController = new GameController();
        GUIController guiController = new GUIController(gameController);
        gameController.setGuiController(guiController);

        GameWindow window = new GameWindow(guiController);
        guiController.setWindow(window);

        Client client = new Client();

        gameController.setSender(client);

        client.setMessageController(gameController);
        client.setHandler(serverHandler);
        */

        Client client = (Client)context.getBean("clientMainClass");
        client.mainLoop();

        System.exit(0);
    }


    /**
     * Prywatna klasa obsługująca akcje generowane przez mysz.
     */
    private class Click implements ActionListener {
        /**
         * Akcja wykonująca się po kliknięciu w mysz.
         *
         * @param event wydarzenie do obsłużenia.
         */
        @Override
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
                }
            }
        }
    }
}
