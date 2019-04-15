import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.ArrayList;

public class Arena implements Colorable{
  public static final int width = 1024;
  public static final int height = 750;
  public static Color color = Color.BLACK;
  public static int score = 0;
  public ArrayList<Item> items;
  public ArrayList<Snake> snakes;

  public Arena() {
    items = createItems();
    snakes = createSnakes();
  }

  public void update(int FPS) {
    updateSnakes(FPS);
    changeColor();
  }

  @Override
  public void changeColor() {
    if (score >= 0 && score <= 5) {
      color = Color.BLACK;
    }
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

  public ArrayList<Item> createItems() {
    ArrayList<Item> items = new ArrayList<Item>(4);
    boolean edible = true;
    Item apple1 = new Item(edible);
    Item apple2 = new Item(edible);
    Item rock1 = new Item(!edible);
    Item rock2 = new Item(!edible);
    while (rock1.position.equalsTo(apple1.position) || rock1.position.equalsTo(apple2.position)) {
      rock1 = new Item(!edible);
    }
    while (rock2.position.equalsTo(apple1.position) || rock2.position.equalsTo(apple2.position) || rock2.position.equalsTo(rock1.position)) {
        rock2 = new Item(!edible);
    }
    items.add(apple1);
    items.add(apple2);
    items.add(rock1);
    items.add(rock2);
    return items;
  }

  public void drawItems(Graphics g) {
    for(int i=0; i<items.size(); ++i) {
      items.get(i).drawItem(g);
    }
  }

  public ArrayList<Snake> createSnakes() {
    ArrayList<Snake> snakes = new ArrayList<Snake>(2);
    Snake s1 = new Snake(1, this);
    Snake s2 = new Snake(2, this);
    snakes.add(s1);
    snakes.add(s2);
    return snakes;
  }

  public void drawSnakes(Graphics g) {
    for (Snake s: snakes){
      s.drawSnake(g);
    }
  }

  public void updateSnakes(int FPS) {
    for (Snake s: snakes){
      s.update(((double)1/FPS));
    }
  }

  public void drawScore(Graphics g) {
    String scoreString = "Score: " + score;
    g.drawString(scoreString, 900, 50);
  }


}
