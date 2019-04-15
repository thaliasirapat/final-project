import java.awt.Image;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;

public class Item {

  public Pair position;
  public final int width = 20;
  public final int height = 20;
  public boolean edible;

  public Item(boolean edible) {
    Random rand = new Random();
    this.position = new Pair((double)rand.nextInt(Arena.width - width), (double)rand.nextInt(Arena.height - height));
    this.edible = edible;
  }

  public void drawItem(Graphics g) {
    if (edible == true) {
      g.setColor(Color.RED); //red for apple
    }
    else {
      g.setColor(Color.GRAY); //gray for rocks
    }
    g.fillRect((int)position.x, (int)position.y, this.width, this.height);
  }
  
  public void eraseItem() {
    Random rand = new Random();
    this.position = new Pair((double)rand.nextInt(Arena.width - width), (double)rand.nextInt(Arena.height - height));
  }
}
