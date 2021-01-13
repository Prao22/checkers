package Client.GUI.ChooseGame;

import Game.GameParameters;
import Utility.Log;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ChoosingDialog extends JDialog {

    private JLabel mainLabel;
    private JTable table;
    private JButton chooseGameButton;
    private int selectedGame = -1;

    public ChoosingDialog(Map<Integer, GameParameters> games) {
        super((JDialog) null);

        setModal(true);
        setTitle("Chinese Checkers");

        Font font = new Font("Arial", Font.PLAIN, 20);

        mainLabel = new JLabel("Wybierz gre: ");
        mainLabel.setHorizontalAlignment(SwingConstants.CENTER);

        mainLabel.setFont(font);
        createButton(font);
        createTable(games, font);


        setLayout(new BorderLayout());

        add(mainLabel, BorderLayout.NORTH);
        add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
        add(chooseGameButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setResizable(false);
        setVisible(true);
    }

    public int getId() {
        return selectedGame;
    }

    private void createButton(Font font) {
        chooseGameButton = new JButton("Wybierz");
        chooseGameButton.setFont(font);
        chooseGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        chooseGameButton.addActionListener(actionEvent -> buttonClicked());
    }

    private void createTable(Map<Integer, GameParameters> data, Font font) {
        table = new JTable(new GameTableModel(data));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.setDefaultRenderer(Object.class, centerRenderer);

        table.setFont(font);
        table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(new Dimension(420, 250));
        table.getTableHeader().setReorderingAllowed(false);

    }

    private void buttonClicked() {
        //Log.log("Klik: " + table.getSelectedRow() + "  " + table.getValueAt(table.getSelectedRow(), 0));
        selectedGame = (Integer) table.getValueAt(table.getSelectedRow(), 0);
        this.dispose();
    }

    public static void main(String[] args) {

        Map<Integer, GameParameters> games = new HashMap<>();

        GameParameters parameters = new GameParameters();
        games.put(1, parameters);
        parameters = new GameParameters();
        parameters.setNumberPlayers(12);
        games.put(2, parameters);
        parameters = new GameParameters();
        parameters.setNumberCounter(12);
        games.put(3, parameters);
        parameters = new GameParameters();
        parameters.setNumberFields(12);
        games.put(4, parameters);
        for(int i = 0; i < 20; i++)
        games.put(5+i, new GameParameters());


        ChoosingDialog dialog = new ChoosingDialog(games);

        Log.log("Id: " + dialog.getId());
        System.exit(0);
    }
}
