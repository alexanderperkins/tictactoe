package tictactoe;

// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;

public class TicTacToeSwingController {
  private final TicTacToe model;
  private final TicTacToeView view;


  public TicTacToeSwingController(TicTacToe model, TicTacToeView view) {
    this.model = model;
    this.view = view;


    view.addClickListener(new CellClickHandler(model, view, this::refreshBoard));  // Show initial game state
  }

  private void refreshBoard() {
    view.refresh(model.getBoard());  // Use the view's refresh method


    if (model.isGameOver()) {
      Player winner = model.getWinner();
      String message = (winner != null)
              ? "Game Over! " + winner + " wins!"
              : "Game Over! It's a tie.";
      view.showMessage(message);
    } else {
      view.showMessage("Turn: " + model.getTurn());
    }
  }


}




