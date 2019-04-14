import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.ArrayList;

public class Arena implements Colorable {
  public static final int width = 1024;
  public static final int height = 768;
  public static final int FPS = 60;
  public static Color color = Color.BLACK;
  public static int score = 0;
  public ArrayList<Item> items;
  public Snake player1;
  public Snake player2;

  public Arena () {
    items = new ArrayList<Item>(4);
    player1 = new Snake(1);
    player2 = new Snake(2);
  }

  public void update() {
    player1.update((double)(1/FPS), this);
    player2.update((double)(1/FPS), this);
    changeColor();
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
  }
  //end of changeColor

  public void drawScore(Graphics g) {
    String scoreString = "Score: " + score;
    g.drawString(scoreString, 900, 100);
  }

  //creates a list of items
  public void createItems() {
    boolean edible = true;
    Item apple = new Item(edible);
    Item rock = new Item(!edible);
    items.add(apple);
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
