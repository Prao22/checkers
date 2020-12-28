package Game;

import Utility.BoardCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    protected Field[][] board;
    protected Map<Integer, List<Counter>> counters;
    protected final int size;
    protected final int rows;
    protected final int cols;

    public Board(int size, int players, int howManyCounters) {
        this.size = size;
        this.rows = 4 * size + 1;
        this.cols = 3 * size + 1;
        board = new Field[rows][cols];
        counters = new HashMap<>(6);
        fillBoard(players, howManyCounters);
        createRelationBetweenFields();
    }

    public Field getField(int row, int col) {
        return board[row][col];
    }

    public Map<Integer, List<Counter>> getCounters() {
        return counters;
    }

    public void moveCounter(Move move) {
        int[] fromCoordinates = move.getFrom();
        int[] toCoordinates = move.getTo();

        Field from = board[fromCoordinates[Move.ROW]][fromCoordinates[Move.COLUMN]];
        Field to = board[toCoordinates[Move.ROW]][toCoordinates[Move.COLUMN]];

        Counter toCounter = to.getCounter();

        to.setCounter(from.getCounter());
        from.setCounter(toCounter);
    }

    private void fillBoard(int players, int counters) {

        boolean[][] b_board = BoardCreator.createBoard(size);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                board[row][col] = b_board[row][col] ? new Field(row, col) : null;
            }
        }

        initializeCounters(b_board, players, counters);
    }
    private void createRelationBetweenFields() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Field field = board[row][col];

                if(field == null) {
                    continue;
                }

                Field[] neighbours = new Field[Field.MAX_NEIGHBOURS];
                boolean evenRow = row % 2 == 0;
                neighbours[Field.Direction.NE] = evenRow ? (row == 0 ? null : board[row - 1][col]) : (col == cols - 1 ? null : board[row-1][col+1]);
                neighbours[Field.Direction.E] = col == cols - 1 ? null : board[row][col + 1];
                neighbours[Field.Direction.SE] = evenRow ? (row == rows - 1 ? null : board[row + 1][col]) : (col == cols - 1 || row == rows - 1  ? null : board[row+1][col+1]);
                neighbours[Field.Direction.SW] = !evenRow ? (row == rows - 1 ? null : board[row + 1][col]) : (col == 0 || row == rows - 1 ? null : board[row+1][col-1]);
                neighbours[Field.Direction.W] = col == 0 ? null : board[row][col - 1];
                neighbours[Field.Direction.NW] = !evenRow ? (board[row - 1][col]) : (col == 0 || row == 0 ? null : board[row-1][col-1]);
                field.setNeighbours(neighbours);
            }
        }
    }

    private void initializeCounters(boolean[][] bBoard, int players, int counters) {

        int[][] cornerA = BoardCreator.cornerA(bBoard, size, counters);
        int[][] cornerAD = BoardCreator.cornerA(bBoard, size, BoardCreator.maxCounters(size));
        int[][] cornerB = BoardCreator.cornerB(bBoard, size, counters);
        int[][] cornerBD = BoardCreator.cornerB(bBoard, size, BoardCreator.maxCounters(size));
        int[][] cornerC = BoardCreator.cornerC(bBoard, size, counters);
        int[][] cornerCD = BoardCreator.cornerC(bBoard, size, BoardCreator.maxCounters(size));
        int[][] cornerD = BoardCreator.cornerD(bBoard, size, counters);
        int[][] cornerDD = BoardCreator.cornerD(bBoard, size, BoardCreator.maxCounters(size));
        int[][] cornerE = BoardCreator.cornerE(bBoard, size, counters);
        int[][] cornerED = BoardCreator.cornerE(bBoard, size, BoardCreator.maxCounters(size));
        int[][] cornerF = BoardCreator.cornerF(bBoard, size, counters);
        int[][] cornerFD = BoardCreator.cornerF(bBoard, size, BoardCreator.maxCounters(size));

        switch (players) {
            case 2:
                setCounters(cornerA, 1, counters);
                setDestination(cornerDD, 1);
                setCounters(cornerD, 2, counters);
                setDestination(cornerAD, 2);
                break;
            case 3:
                setCounters(cornerA, 1, counters);
                setDestination(cornerDD, 1);
                setCounters(cornerC, 2, counters);
                setDestination(cornerFD, 2);
                setCounters(cornerE, 3, counters);
                setDestination(cornerBD, 3);
                break;

            case 4:
                setCounters(cornerB, 1, counters);
                setDestination(cornerED, 1);
                setCounters(cornerC, 2, counters);
                setDestination(cornerFD, 2);
                setCounters(cornerE, 3, counters);
                setDestination(cornerBD, 3);
                setCounters(cornerF, 4, counters);
                setDestination(cornerCD, 4);
                break;

            case 6:
                setCounters(cornerA, 1, counters);
                setDestination(cornerDD, 1);
                setCounters(cornerB, 2, counters);
                setDestination(cornerED, 2);
                setCounters(cornerC, 3, counters);
                setDestination(cornerFD, 3);
                setCounters(cornerD, 4, counters);
                setDestination(cornerAD, 4);
                setCounters(cornerE, 5, counters);
                setDestination(cornerBD, 5);
                setCounters(cornerF, 6, counters);
                setDestination(cornerCD, 6);
                break;
        }
    }

    private void setCounters(int[][] corner, int playerId, int numberOfCounters)
    {
        List<Counter> counterList = new ArrayList<>();

        for (int i = 0; i < numberOfCounters; i++) {
            Counter newCounter = new Counter(playerId);
            counterList.add(newCounter);
            board[corner[i][0]][corner[i][1]].setCounter(newCounter);
        }

        counters.put(playerId, counterList);
    }

    private void setDestination(int[][] corner, int destination) {
        for (int[] coords : corner) {
            board[coords[0]][coords[1]].setDestination(destination);
        }
    }

    public void print() {

        for(int i = 0; i < rows; i++) {
//            if(i % 2 == 1) {
//                System.out.print(" ");
//            }
            for(int j = 0; j < cols; j++) {
                System.out.print(board[i][j] == null ? "*" : "-");
            }
            System.out.println(" ");
        }

        System.out.println(board[1][2]);
    }
}
