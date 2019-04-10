import java.awt.image;
import java.util.Random;

public class Item {

  public Pair position;
  public final int width, height;
  public boolean edible;
  public ArrayList<Item> items;

  public Item(boolean edible) {
    Random rand = new Random();
    position = new Pair((double)rand.nextInt(1024), (double)rand.nextInt(768));
    this.edible = edible;
    items = new ArrayList<Item>();

  }
  // Called at the start of each game, creates array list of items
  public void drawstartItems(Graphics g){
    Item n;
    for (int i = 0 ; i < 4 ; ++i) {
      if ( i == 0 || i == 1){
        n = new Item(true);
        items.add(n);
      }
      else{
        n = new Item(false);
        items.add(n);
      }

    }

    for(Item n; items) {
      //Draw it here I guess
    }

  } // End of drawstartItems


  // Draw new items once one is eaten
  public void drawItem(Graphics g)


  // Erase item once eaten
  public void eraseItem(){
    items.remove(this);
  }

}
