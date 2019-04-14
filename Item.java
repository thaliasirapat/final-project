import java.awt.Image;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;


public class Item {

  public Pair position;
  public final int width;
  public final int height;
  public boolean edible;

  public Item(boolean edible) {
    Random rand = new Random();
    this.width = 20;
    this.height = 20;
    this.position = new Pair((double)rand.nextInt(Arena.width - width), (double)rand.nextInt(Arena.height - height));
    this.edible = edible;
  }

  // Draw new items once one is eaten
  public void drawItem(Graphics g) {
    if (edible == true) {
      g.setColor(Color.RED); //red for apple
    }
    else {
      g.setColor(Color.GRAY); //gray for rocks
    }
    g.fillRect((int)position.x, (int)position.y, this.width, this.height);

  }
  //end of drawItem

  //erases an item...technically it just moves it to a new positon
  public void eraseItem() {
    Random rand = new Random();
    this.position = new Pair((double)rand.nextInt(Arena.width - width), (double)rand.nextInt(Arena.height - height));
  }
  //end of eraseItem
}
