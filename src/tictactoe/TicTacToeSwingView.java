package tictactoe;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;


/**
 * A basic Swing view for a Tic Tac Toe game.
 */
public class TicTacToeSwingView extends JFrame implements TicTacToeView {
  // Adjusted for approximately 500x400 total window
  public static final int CELL_SIZE = 130;
  private final JLabel messageLabel;
  private final CellPanel[][] cells;


  public TicTacToeSwingView(String title) {
    super(title);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());


    JPanel boardPanel = new JPanel(new GridLayout(3, 3));
    cells = new CellPanel[3][3];


    // Create 3x3 cells
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        CellPanel cell = new CellPanel();
        cells[row][col] = cell;
        boardPanel.add(cell);
      }
    }


    messageLabel = new JLabel("Welcome to Tic Tac Toe!", SwingConstants.CENTER);
    messageLabel.setPreferredSize(new Dimension(CELL_SIZE * 3, 40));


    this.add(messageLabel, BorderLayout.SOUTH);
    this.add(boardPanel, BorderLayout.CENTER);


    // Total preferred size = 3 * cell size in width, cell height * 3 + label height
    int totalWidth = CELL_SIZE * 3;
    int totalHeight = CELL_SIZE * 3 + 40;
    this.setPreferredSize(new Dimension(totalWidth, totalHeight));
    this.pack(); // respects preferred sizes
    this.setVisible(true);
  }


  /**
   * Updates the symbol displayed in the given cell.
   */
  public void setCell(int row, int col, Player player) {
    cells[row][col].setPlayer(player);
  }


  /**
   * Displays a message below the game board.
   */
  public void showMessage(String message) {
    messageLabel.setText(message);
  }


  /**
   * Adds a MouseListener to the whole board (for simplicity).
   */
  public void addClickListener(MouseListener listener) {
    this.getContentPane().addMouseListener(listener);
  }


  /**
   * A custom panel that displays a single cell (X, O, or empty).
   */
  private static class CellPanel extends JPanel {
    private Player player;


    public CellPanel() {
      this.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
      this.setBackground(Color.WHITE);
      this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }


    public void setPlayer(Player player) {
      this.player = player;
      repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      if (player == Player.X) {
        g.setColor(Color.BLUE);
        g.drawLine(10, 10, getWidth() - 10, getHeight() - 10);
        g.drawLine(10, getHeight() - 10, getWidth() - 10, 10);
      } else if (player == Player.O) {
        g.setColor(Color.RED);
        g.drawOval(10, 10, getWidth() - 20, getHeight() - 20);
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


}
