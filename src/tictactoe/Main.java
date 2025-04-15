package tictactoe;


public class Main {
  public static void main(String[] args) {
    TicTacToe model = new TicTacToeModel();
    TicTacToeSwingView view = new TicTacToeSwingView("Tic Tac Toe");
    new TicTacToeSwingController(model, view); // No need to call playGame()
  }
}
