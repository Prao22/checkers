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

        switch (numberOfPlayers) {
            case 2:
                paintSideA(CounterColor.getFromNumber(1));
                paintSideD(CounterColor.getFromNumber(2));
                break;

            case 3:
                paintSideA(CounterColor.getFromNumber(1));
                paintSideC(CounterColor.getFromNumber(2));
                paintSideE(CounterColor.getFromNumber(3));

            case 4:
                paintSideB(CounterColor.getFromNumber(1));
                paintSideC(CounterColor.getFromNumber(2));
                paintSideE(CounterColor.getFromNumber(3));
                paintSideF(CounterColor.getFromNumber(4));

                break;

            case 6:
                paintSideA(CounterColor.getFromNumber(1));
                paintSideB(CounterColor.getFromNumber(2));
                paintSideC(CounterColor.getFromNumber(3));
                paintSideD(CounterColor.getFromNumber(4));
                paintSideE(CounterColor.getFromNumber(5));
                paintSideF(CounterColor.getFromNumber(6));
                break;
        }
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

    private void paintSideA(CounterColor color) {

        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j < 2 * size + 1; j++)
                if (fields[i][j] != null) {
                    fields[i][j].setColor(color.getJavaColor());
                }
        }
    }

    private void paintSideB(CounterColor color) {

        int counter;
        for (int i = size; i < 2 * size; i++) {
            counter = size - i % size;
            for (int j = 0; j < size && counter != 0; j++)
                if (fields[i][j] != null) {
                    fields[i][j].setColor(color.getJavaColor());
                    counter--;

                }
        }
    }

    private void paintSideC(CounterColor color) {
        int counter = 0;
        int set = 0;
        for (int i = 2 * size + 1; i < 3 * size + 1; i++) {
            set++;
            counter = set;
            for (int j = 0; j < size && counter != 0; j++) {
                if (fields[i][j] != null) {
                    fields[i][j].setColor(color.getJavaColor());
                    counter--;
                }
            }
        }

    }

    private void paintSideD(CounterColor color) {

        for (int i = 3 * size + 1; i < 4 * size + 1; i++) {
            for (int j = 4; j < 2 * size; j++) {
                if (fields[i][j] != null) {
                    fields[i][j].setColor(color.getJavaColor());
                }
            }
        }
    }

    private void paintSideE(CounterColor color) {
        int counter = 0;
        int set = 0;
        for (int i = 2 * size + 1; i < 3 * size + 1; i++) {
            set++;
            counter = set;
            for (int j = 3 * size; j > 2 * size && counter != 0; j--) {
                if (fields[i][j] != null) {
                    fields[i][j].setColor(color.getJavaColor());
                    counter--;
                }
            }
        }
    }

    private void paintSideF(CounterColor color) {

        int counter;
        for (int i = size; i < 2 * size; i++) {
            counter = size - i % size;
            for (int j = 3 * size; j > 2 * size && counter != 0; j--)
                if (fields[i][j] != null) {
                    fields[i][j].setColor(color.getJavaColor());
                    counter--;
                }
        }
    }

}

