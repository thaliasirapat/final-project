import java.awt.Image;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;


public class Item {

  public Pair position;
  public final int width, height;
  public boolean edible;

  public Item(boolean edible) {
    Random rand = new Random();
    this.position = new Pair((double)rand.nextInt(1024), (double)rand.nextInt(768));
    this.width = 20;
    this.height = 20;
    this.edible = edible;
  }

  // Draw new items once one is eaten
  public void drawItem(Graphics g) {
    Random rand = new Random();
    boolean randomBoolean = rand.nextBoolean();
    if (edible == true) {
      if (randomBoolean) {
        g.setColor(Color.RED); //red for apple
      }
      else {
        g.setColor(Color.ORANGE); //orange for an orange
      }
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
    this.position = new Pair((double)rand.nextInt(1024), (double)rand.nextInt(768));
  }
  //end of eraseItem
}
