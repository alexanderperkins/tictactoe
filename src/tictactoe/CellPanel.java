package tictactoe;

import javax.swing.*;
import java.awt.*;


public class CellPanel extends JPanel {
  private static final int DEFAULT_SIZE = 130;
  private Player player;


  public CellPanel() {
    this(DEFAULT_SIZE);
  }


  public CellPanel(int size) {
    this.setPreferredSize(new Dimension(size, size));
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
