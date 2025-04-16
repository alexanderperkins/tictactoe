package tictactoe;

public enum Player {
    X, O;

    @Override
    public String toString() {

        return this == X ? "X" : "O";
    }
}
