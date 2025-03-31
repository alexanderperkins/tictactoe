package tictactoe;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * Tic Tac Toe game model that manages the game status, tracks players,
 * and figures out the outcome of the game.
 */
public class TicTacToeModel implements TicTacToe {
  private final Player[][] gameBoard;
  private Player currentPlayer;
  private boolean isGameOver;
  private Player winner;

  /**
   * Initializes the Tic Tac Toe game with an empty 3x3 gameBoard.
   * tictactoe.Player X always starts the game.
   */
  public TicTacToeModel() {
    this.gameBoard = new Player[3][3];
    this.currentPlayer = Player.X;
    this.isGameOver = false;
    this.winner = null;
  }

  @Override
  public String toString() {
    /**
     // Using Java stream API to save code:
     return Arrays.stream(getBoard()).map(
     row -> " " + Arrays.stream(row).map(
     p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
     .collect(Collectors.joining("\n-----------\n"));
     */
    // This is the equivalent code as above, but using iteration, and still using
    // the helpful built-in String.join method.

    List<String> rows = new ArrayList<>();
    for (Player[] row : getBoard()) {
      List<String> rowStrings = new ArrayList<>();
      for (Player p : row) {
        if (p == null) {
          rowStrings.add(" ");
        } else {
          rowStrings.add(p.toString());
        }
      }
      rows.add(" " + String.join(" | ", rowStrings));
    }
    return String.join("\n-----------\n", rows);

  }

  /**
   * From interface.
   */
  @Override
  public void move(int row, int col) throws IllegalArgumentException, IllegalStateException {
    if (isOutsideOfBoard(row, col)) {
      throw new IllegalArgumentException("Stay within the board please!");
    } else if (this.gameBoard[row][col] != null) {
      throw new IllegalArgumentException("Slot is already taken!");
    } else if (this.isGameOver) {
      throw new IllegalStateException("Game finished!");
    }

    this.gameBoard[row][col] = this.currentPlayer;
    checkGameStatus(row, col);

    if (!this.isGameOver) {
      this.currentPlayer = (this.currentPlayer == Player.X) ? Player.O : Player.X;
    }
  }

  /**
   * From interface.
   */
  @Override
  public Player getTurn() {
    return this.currentPlayer;
  }

  /**
   * From interface.
   */
  @Override
  public boolean isGameOver() {
    return this.isGameOver;
  }

  /**
   * From interface.
   */
  @Override
  public Player getWinner() {
    return this.winner;
  }

  /**
   * From interface.
   */
  @Override
  public Player[][] getBoard() {
    Player[][] copyBoard = new Player[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        copyBoard[i][j] = this.gameBoard[i][j];
      }
    }
    return copyBoard;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Player getMarkAt(int row, int col) throws IllegalArgumentException {
    if (isOutsideOfBoard(row, col)) {
      throw new IllegalArgumentException("Stay within the board please!");
    }
    Player[][] copyBoard = this.getBoard();
    return copyBoard[row][col];
  }

  /**
   * Checks if the given row or column index is outside the valid range (0-2).
   */
  private boolean isOutsideOfBoard(int row, int col) {
    return row < 0 || row > 2 || col < 0 || col > 2;
  }

  /**
   * Updates the game status after each move, checking for win or draw.
   */
  private void checkGameStatus(int row, int col) {
    // Check main diagonal
    if (row == col && checkLine(0, 0, 1, 1)) {
      setWinner();
      return;
    }

    // Check anti-diagonal
    if (row + col == 2 && checkLine(0, 2, 1, -1)) {
      setWinner();
      return;
    }

    // Check row
    if (checkLine(row, 0, 0, 1)) {
      setWinner();
      return;
    }

    // Check column
    if (checkLine(0, col, 1, 0)) {
      setWinner();
      return;
    }

    // Check for draw
    if (Arrays.stream(gameBoard).flatMap(Arrays::stream).noneMatch(p -> p == null)) {
      this.isGameOver = true;
      this.winner = null;
    }
  }

  private boolean checkLine(int startRow, int startCol, int rowStep, int colStep) {
    for (int i = 0; i < 3; i++) {
      if (gameBoard[startRow + i * rowStep][startCol + i * colStep] != currentPlayer) {
        return false;
      }
    }
    return true;
  }

  private void setWinner() {
    this.isGameOver = true;
    this.winner = currentPlayer;
  }

}
