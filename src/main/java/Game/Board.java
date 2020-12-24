package Game;

import Utility.BoardCreator;

import java.io.FileReader;

public class Board {

    protected Field[][] board;
    protected final int size;
    protected final int rows;
    protected final int cols;

    public Board(int size, int players, int counters) {
        this.size = size;
        this.rows = 4 * size + 1;
        this.cols = 3 * size + 1;
        board = new Field[rows][cols];
        fillBoard(players, counters);
        createRelationBetweenFields();
    }

    public Field getField(int row, int col) {
        return board[row][col];
    }

    public void moveCounter(Move move) {
        int[] fromCoordinates = move.getFrom();
        int[] toCoordinates = move.getTo();

        Field from = board[fromCoordinates[Move.ROW]][fromCoordinates[Move.COLUMN]];
        Field to = board[toCoordinates[Move.ROW]][toCoordinates[Move.COLUMN]];

        to.setCounter(from.getCounter());
        from.setCounter(null);
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
        switch (players) {
            case 2:
                setCounters(BoardCreator.cornerA(bBoard, size, counters), 1, counters);
                setCounters(BoardCreator.cornerD(bBoard, size, counters), 2, counters);
                break;
            case 3:
                setCounters(BoardCreator.cornerA(bBoard, size, counters), 1, counters);
                setCounters(BoardCreator.cornerC(bBoard, size, counters), 2, counters);
                setCounters(BoardCreator.cornerE(bBoard, size, counters), 3, counters);
                break;

            case 4:
                setCounters(BoardCreator.cornerB(bBoard, size, counters), 1, counters);
                setCounters(BoardCreator.cornerC(bBoard, size, counters), 2, counters);
                setCounters(BoardCreator.cornerE(bBoard, size, counters), 3, counters);
                setCounters(BoardCreator.cornerF(bBoard, size, counters), 4, counters);
                break;

            case 6:
                setCounters(BoardCreator.cornerA(bBoard, size, counters), 1, counters);
                setCounters(BoardCreator.cornerB(bBoard, size, counters), 2, counters);
                setCounters(BoardCreator.cornerC(bBoard, size, counters), 3, counters);
                setCounters(BoardCreator.cornerD(bBoard, size, counters), 4, counters);
                setCounters(BoardCreator.cornerE(bBoard, size, counters), 5, counters);
                setCounters(BoardCreator.cornerF(bBoard, size, counters), 6, counters);
                break;
        }
    }

    private void setCounters(int[][] corner, int playerId, int numberOfCounters)
    {
        for (int i = 0; i < numberOfCounters; i++) {
            board[corner[i][0]][corner[i][1]].setCounter(new Counter(playerId));
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
            System.out.println("");
        }

        System.out.println(board[1][2]);
    }
}
