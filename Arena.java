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
  public ArrayList<Snake> snakes;


  public Arena () {
    items = createItems();
    snakes = new ArrayList<Snake>(2);
    snakes.add(new Snake(1, this));
    snakes.add(new Snake(2, this));
  }

  public void update() {
    for (Snake s: snakes){
      s.update((double)1/FPS);
    }
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

  //creates a list of items
  public ArrayList<Item> createItems() {
    ArrayList<Item> items = new ArrayList<Item>(4);
    boolean edible = true;
    Item apple = new Item(edible);
    Item rock = new Item(!edible);
    items.add(apple);
    items.add(rock);
    items.add(rock);
    return items;
  }
  //end of createItems



  //draws the items
  public void drawItems(Graphics g) {
    for(int i=0; i<items.size(); ++i) {
      items.get(i).drawItem(g);
    }
  }
  //end of drawItems

  public void drawScore(Graphics g) {
    String scoreString = "Score: " + score;
    g.drawString(scoreString, 900, 100);
  }



}
