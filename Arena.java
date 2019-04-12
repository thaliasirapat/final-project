import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

// testing to see if this works


public class Arena extends JPanel implements Colorable {

  public static final int width = 1024;
  public static final int height = 768;
  public static Color color = Color.BLACK;
  public static int score = 0;
  public Snake player1, player2;
  public ArrayList<Item> items;

  public Arena () {
    this.setPreferredSize(new Dimension(width, height));
    player1 = new Snake(1, this);
    player2 = new Snake(2, this);
    items = new ArrayList<Item>(4);
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

    g.setColor(color);
    g.fillRect(0, 0, width, height);

    player1.drawSnake(g);
    player2.drawSnake(g);

    createItems();
    drawItems(g);

  }

  //changes color of the arena as score gets to a value
  @Override
  public void changeColor() {
    if (score > 5 && score <= 10) {
      color = Color.PINK;
    }
    if (score > 10 && score <= 15) {
      color = Color.BLUE;
    }
    if (score > 15 && score <= 20) {
      color = Color.MAGENTA;
    }
    repaint();
  }
  //end of changeColor

  //creates a list of items
  public void createItems() {
    boolean edible = true;
    Item apple = new Item(edible);
    Item orange = new Item(edible);
    Item rock = new Item(!edible);
    items.add(apple);
    items.add(orange);
    items.add(rock);
    items.add(rock);
  }
  //end of createItems

  //draws the items
  public void drawItems(Graphics g) {
    for(int i=0; i<items.size(); ++i) {
      items.get(i).drawItem(g);
    }
  }
  //end of drawItems


}
