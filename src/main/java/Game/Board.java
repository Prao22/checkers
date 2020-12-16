package Game;

import Utility.BoardCreator;

import java.io.FileReader;

public class Board {

    protected Field[][] board;
    protected final int size;
    protected final int rows;
    protected final int cols;

    public Board(int size) {
        this.size = size;
        this.rows = 4 * size + 1;
        this.cols = 3 * size + 1;
        board = new Field[rows][cols];
        fillBoard();
        createRelationBetweenFields();
    }

    public void moveCounter(Move move) {
        int[] fromCoordinates = move.getFrom();
        int[] toCoordinates = move.getTo();

        Field from = board[fromCoordinates[Move.ROW]][fromCoordinates[Move.COLUMN]];
        Field to = board[toCoordinates[Move.ROW]][toCoordinates[Move.COLUMN]];

        to.setCounter(from.getCounter());
        from.setCounter(null);
    }

    private void fillBoard() {

        boolean[][] b_board = BoardCreator.createBoard(size);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                board[row][col] = b_board[row][col] ? new Field() : null;
            }
        }

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
