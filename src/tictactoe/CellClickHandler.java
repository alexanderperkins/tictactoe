package tictactoe;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellClickHandler extends MouseAdapter {
  private final TicTacToe model;
  private final TicTacToeView view;
  private final Runnable refreshBoard;


  public CellClickHandler(TicTacToe model, TicTacToeView view, Runnable refreshBoard) {
    this.model = model;
    this.view = view;
    this.refreshBoard = refreshBoard;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (model.isGameOver()) return;

    int row = e.getY() / TicTacToeSwingView.CELL_SIZE;
    int col = e.getX() / TicTacToeSwingView.CELL_SIZE;

    try {
      model.move(row, col);
      refreshBoard.run();
    } catch (IllegalArgumentException | IllegalStateException ex) {
      view.showMessage("Invalid move: " + ex.getMessage());
    }
  }
}
