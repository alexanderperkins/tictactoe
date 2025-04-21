package tictactoe;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;


public class TicTacToeSwingView extends JFrame implements TicTacToeView {
    private final JLabel messageLabel;
    private final CellPanel[][] cells;


    public TicTacToeSwingView(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());


        int cellSize = 130;


        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        cells = new CellPanel[3][3];


        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                CellPanel cell = new CellPanel(cellSize);
                cells[row][col] = cell;
                boardPanel.add(cell);
            }
        }


        messageLabel = new JLabel("Welcome to Tic Tac Toe!", SwingConstants.CENTER);
        messageLabel.setPreferredSize(new Dimension(cellSize * 3, 40));


        this.add(messageLabel, BorderLayout.SOUTH);
        this.add(boardPanel, BorderLayout.CENTER);


        int totalWidth = cellSize * 3;
        int totalHeight = cellSize * 3 + 40;
        this.setPreferredSize(new Dimension(totalWidth, totalHeight));


        this.pack();
        this.setVisible(true);
    }


    public void setCell(int row, int col, Player player) {
        cells[row][col].setPlayer(player);
    }


    public void showMessage(String message) {
        messageLabel.setText(message);
    }


    public void addClickListener(MouseListener listener) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                cells[row][col].addMouseListener(listener); // Add to each cell
            }
        }
    }


    @Override
    public void refresh(Player[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                setCell(row, col, board[row][col]);
            }
        }
    }


    public CellPanel[][] getCells() {
        return cells;
    }
}
