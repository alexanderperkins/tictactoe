package tictactoe;


public class TicTacToeSwingController {
    private final TicTacToe model;
    private final TicTacToeView view;


    public TicTacToeSwingController(TicTacToe model, TicTacToeView view) {
        this.model = model;
        this.view = view;


        /*if (!(view instanceof TicTacToeSwingView)) {
            throw new IllegalArgumentException("Expected a TicTacToeSwingView for click handling.");
        }*/


        TicTacToeSwingView swingView = (TicTacToeSwingView) view;
        CellPanel[][] cells = swingView.getCells();


        view.addClickListener(new CellClickHandler(model, view, this::refreshBoard, cells));


        refreshBoard(); // Show initial game state
    }


    private void refreshBoard() {
        view.refresh(model.getBoard());


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
