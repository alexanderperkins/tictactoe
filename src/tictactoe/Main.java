package tictactoe;

/**
 * Run a tictactoe.TicTacToe game interactively on the console.
 */
public class Main {
  /**
   * Run a tictactoe.TicTacToe game interactively on the console.
   *
   * @param args not used
   */
  public static void main(String[] args) {
    TicTacToe model = new TicTacToeModel();
    TicTacToeSwingView view = new TicTacToeSwingView("Tic Tac Toe");
    new TicTacToeSwingController(model, view); // No need to call playGame()
    // c.playGame();
  }
}
