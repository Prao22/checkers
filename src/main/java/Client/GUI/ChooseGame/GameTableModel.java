package Client.GUI.ChooseGame;

import Game.GameParameters;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.Map;

public class GameTableModel extends AbstractTableModel {

    private Map<Integer, GameParameters> data;
    private final Integer[] dataKeys;
    private final String[] cols = {"ID", "PLAYERS", "COUNTERS", "SIZE"};

    public GameTableModel(Map<Integer, GameParameters> gameData) {
        data = gameData;
        dataKeys = data.keySet().toArray(new Integer[0]);
    }

    @Override
    public int getRowCount() {
        return dataKeys.length;
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        GameParameters parameters = data.get(dataKeys[row]);

        if(parameters == null) {
            return null;
        }

        switch (col) {
            case 0: return dataKeys[row];
            case 1: return parameters.getNumberPlayers();
            case 2: return parameters.getNumberCounter();
            case 3: return parameters.getNumberFields();
            default: return null;
        }

    }

    @Override
    public String getColumnName(int column) {
        return cols[column];
    }
}
