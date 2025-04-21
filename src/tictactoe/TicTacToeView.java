package tictactoe;


import java.awt.event.MouseListener;


/**
 * Represents a view for a graphical Tic Tac Toe game.
 * This interface supports display updates and user feedback.
 */
public interface TicTacToeView {
    /**
     * Updates the display of the game board with the current model state.
     *
     * @param board a 2D array showing the current game state,
     *              where each element is either Player.X, Player.O, or null
     */
    void refresh(Player[][] board);


    /**
     * Shows a message to the user (e.g., "Player X's turn", "Invalid move", "Game Over").
     *
     * @param message the message to display
     */
    void showMessage(String message);


    /**
     * Attaches a mouse listener to handle click events on the board.
     *
     * @param listener the MouseListener to respond to clicks
     */
    void addClickListener(MouseListener listener);
}


