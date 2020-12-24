package Client.GUI;

import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.*;

import Client.BoardObserver;
import Game.CounterColor;
import Utility.BoardCreator;


public class Board extends JPanel {

    private static final int MARGINS = 100;
    private static final int MARGIN_BETWEEN = BoardField.getDiameter() / 8;
    private static final int OFFSET = BoardField.getDiameter() / 2;
    private final int size;
    private final int rows;
    private final int cols;
    private final int numberOfPlayers;
    private final int numberOfCounters;
    private final BoardObserver observer;
    private BoardField[][] fields;

    public Board(int size, int numberOfPlayers, int numberOfCounters, BoardObserver observer) {
        this.size = size;
        this.rows = BoardCreator.howManyRows(size);
        this.cols = BoardCreator.howManyCols(size);
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfCounters = numberOfCounters;
        this.observer = observer;
        observer.attach(this);

        createBoard();
        setPreferredSize(new Dimension(2 * MARGINS + cols * (BoardField.getDiameter() + MARGIN_BETWEEN),
                2 * MARGINS + rows * (BoardField.getDiameter() + MARGIN_BETWEEN)));

        addMouseListener(new ClickAction());
    }

    private void createBoard() {
        fields = new BoardField[rows][cols];
        boolean[][] template = BoardCreator.createBoard(size);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (template[i][j]) {
                    fields[i][j] = new BoardField(i, j, Color.LIGHT_GRAY);

                    if (i % 2 == 0) {
                        fields[i][j].x = MARGINS + j * (BoardField.getDiameter() + MARGIN_BETWEEN);
                    } else {
                        fields[i][j].x = MARGINS + OFFSET + j * (BoardField.getDiameter() + MARGIN_BETWEEN);
                    }

                    fields[i][j].y = MARGINS + (BoardField.getDiameter() + MARGIN_BETWEEN) * i;
                } else {
                    fields[i][j] = null;
                }
            }

        }

        paintCorners(template);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i < 3 * size + 1; i++) {
            for (int j = 0; j < 4 * size + 1; j++) {
                if (fields[j][i] != null) {
                    fields[j][i].draw(g2);
                }
            }
        }
    }

    public void move(int[] pairA, int[] pairB) {

        //zapisujemy kolor pola A
        Color temporary = fields[pairA[0]][pairA[1]].getColor();

        //zamnieniamy kolor pola A
        fields[pairA[0]][pairA[1]].setColor(fields[pairB[0]][pairB[1]].getColor());
        repaint(fields[pairA[0]][pairA[1]].getBounds());

        //zapisany kolor pola A przypisujemy do pola B
        fields[pairB[0]][pairB[1]].setColor(temporary);
        repaint(fields[pairB[0]][pairB[1]].getBounds());
    }

    public void highlightField(int row, int col) {
        fields[row][col].setHighlighted(true);
        repaint(fields[row][col].getBounds());
    }

    public void normalField(int row, int col) {
        fields[row][col].setHighlighted(false);
        repaint(fields[row][col].getBounds());
    }

    public Color getColorOfField(int row, int col) {
        return fields[row][col].getColor();
    }

    private class ClickAction extends MouseAdapter {

        public void mousePressed(MouseEvent event) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (fields[i][j] != null && fields[i][j].contains(event.getPoint())) {
                        observer.clickNotify(i, j);
                    }
                }
            }
        }
    }

    private void paintCorners(boolean[][] board) {
        switch (numberOfPlayers) {
            case 2:
                paintCorner(BoardCreator.cornerA(board, size, numberOfCounters), CounterColor.getFromNumber(1));
                paintCorner(BoardCreator.cornerD(board, size, numberOfCounters), CounterColor.getFromNumber(2));
                break;

            case 3:
                paintCorner(BoardCreator.cornerA(board, size, numberOfCounters), CounterColor.getFromNumber(1));
                paintCorner(BoardCreator.cornerC(board, size, numberOfCounters), CounterColor.getFromNumber(2));
                paintCorner(BoardCreator.cornerE(board, size, numberOfCounters), CounterColor.getFromNumber(3));
                break;

            case 4:
                paintCorner(BoardCreator.cornerB(board, size, numberOfCounters), CounterColor.getFromNumber(1));
                paintCorner(BoardCreator.cornerC(board, size, numberOfCounters), CounterColor.getFromNumber(2));
                paintCorner(BoardCreator.cornerE(board, size, numberOfCounters), CounterColor.getFromNumber(3));
                paintCorner(BoardCreator.cornerF(board, size, numberOfCounters), CounterColor.getFromNumber(4));
                break;

            case 6:
                paintCorner(BoardCreator.cornerA(board, size, numberOfCounters), CounterColor.getFromNumber(1));
                paintCorner(BoardCreator.cornerB(board, size, numberOfCounters), CounterColor.getFromNumber(2));
                paintCorner(BoardCreator.cornerC(board, size, numberOfCounters), CounterColor.getFromNumber(3));
                paintCorner(BoardCreator.cornerD(board, size, numberOfCounters), CounterColor.getFromNumber(4));
                paintCorner(BoardCreator.cornerE(board, size, numberOfCounters), CounterColor.getFromNumber(5));
                paintCorner(BoardCreator.cornerF(board, size, numberOfCounters), CounterColor.getFromNumber(6));

                break;
        }
    }

    private void paintCorner(int[][] corner, CounterColor color) {
        for (int i = 0; i < numberOfCounters; i++) {
            fields[corner[i][0]][corner[i][1]].setColor(color.getJavaColor());
        }
    }

}

