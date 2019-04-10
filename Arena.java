import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

// testing to see if this works


public class Arena extends JPanel implements Colorable {

  public static final int width = 1024;
  public static final int height = 768;
  public static int score = 0;

  public Arena () {
    this.setPreferredSize(new Dimension(width, height));
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Two Player Snake!");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(new Arena());
    frame.pack();
    frame.setVisible(true);

  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, width, height);




  }

  @Override
  public void changeColor()


}
