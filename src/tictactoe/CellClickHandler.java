package tictactoe;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class CellClickHandler extends MouseAdapter {
    private final TicTacToe model;
    private final TicTacToeView view;
    private final Runnable refreshCallback;
    private final CellPanel[][] cells;


    public CellClickHandler(TicTacToe model, TicTacToeView view,
                            Runnable refreshCallback, CellPanel[][] cells) {
        this.model = model;
        this.view = view;
        this.refreshCallback = refreshCallback;
        this.cells = cells;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();


        // Determine which cell was clicked
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                if (cells[row][col] == source) {
                    handleMove(row, col);
                    return;
                }
            }
        }
    }


    private void handleMove(int row, int col) {
        try {
            model.move(row, col);
            refreshCallback.run();
        } catch (IllegalArgumentException ex) {
            view.showMessage("Invalid move. Try again.");
        } catch (IllegalStateException ex) {
            view.showMessage("Game is over.");
        }
    }
}
