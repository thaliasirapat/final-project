import java.awt.Color;
import java.util.ArrayList;
import java.awt.Graphics;
import java.lang.*;


// Start of class Snake
public class Snake implements Colorable {

// We should decide the initialization values for these

  public Pair position;
  public int length = 2;
  private int inedibleCount = 0;
  public ArrayList<Segment> body;
  private Color color = Color.GREEN;
  private int player; // we should have player 1 and 2 so the snake responds to different keys
  public Arena arena; // i think we need to have this so that we can use arena as an argument
  private Snake friend;



  public Snake(int player, Arena arena) {
    this.player = player;
    this.arena = arena;
    Segment s1;

    if (player == 1) {
      position = new Pair(341,384);
      s1 = new Segment(position);
    }
    else {
      position = new Pair(682,384);
      s1 = new Segment(position);
    }

    body = new ArrayList<Segment>();
    body.add(s1);
    Segment s2;
    s2 = new Segment(position.add(new Pair(20,20)));
    body.add(s2);
  }




  // Draw the snake on the screen
  public void drawSnake(Graphics g){
    Color c = g.getColor();
    g.setColor(c);

    for (Segment s: body){
      g.fillRect((int)s.position.x, (int)s.position.y, s.width, s.height);
     }
  }
  // End of draw method

   public void move(double time) {
    for (Segment s: body){
      position = position.add(velocity.times(time));
      s.position = s.position.add(velocity.times(time));
    }
  }


// Makes the snake move on the screen, dictates behavior ** DONE **
  public void update(double time) {
    this.move(time);

    if (this.player == 1){
      friend = arena.player2;
    }
    else {
      friend = arena.player1;
    }

    if (eatSelf() || eatFriend(friend) || hitWall()){
      System.out.println("Game Over!");
      System.out.println("Your score is: " + arena.score );
      System.exit(0);
    }
    else if (hasEatenItem(arena.items)) {
      Item i = itemEaten(arena.items);
      this.evolve(i);
      i.eraseItem();
    }
  }  // end of update method


  public void changeDirection(char c) {
    if (this.player == 1) {
      if ( c == 'w') {
        change(0,-20);
      }
      else if (c == 's') {
        change(0,20);
      }
      else if ( c == 'a') {
        change(-20,0);
      }
      else if ( c == 'd') {
        change(20,0);
      }
    }
    else if (this.player == 2) {
      if ( c == 'i') {
        change(0,-20);
      }
      else if (c == 'k') {
        change(0,20);
      }
      else if ( c == 'j') {
        change(-20,0);
      }
      else if ( c == 'l') {
        change(20,0);
      }
    }
  }

  public void change(int x, int y) {
    Pair changePosition = this.position;
    for (Segment s: this.body) {
      if (s.position.equalsTo(changePosition))
        s.velocity = new Pair(x,y);
    }
  }


  // ----------------------------------------------------------

  //this method returns the item that the snake eats
  public Item itemEaten(ArrayList<Item> items) {
    for (Item item: items) {
      if (item.position.equalsTo(this.position))
        return item;
    }
    return null;

  } // End of itemEaten

  public boolean hasEatenItem(ArrayList<Item> items) {
    for (Item item: items) {
      if (item.position.equalsTo(this.position)) {
        return true;
      }
    }
    return false;
  }


  public void evolve(Item item){
    Segment s;
    Segment toAdd;
    if (item.edible) {
      s = body.get(body.size());

      if (this.velocity.isPositiveX()) {
        toAdd = new Segment(new Pair(s.position.x-20, s.position.y));
      }
      else{
        toAdd = new Segment(new Pair(s.position.x + 20, s.position.y));
      }

      if (this.velocity.isPositiveY()) {
        toAdd = new Segment(new Pair(s.position.x, s.position.y - 20));
      }
      else{
        toAdd = new Segment(new Pair(s.position.x, s.position.y + 20));
      }

      body.add(toAdd);
      length++;
      arena.score++;
      changeColor();
    }
    else{
      inedibleCount++;
    }
    if (inedibleCount > 3){
      System.out.println("Game over!");
      System.out.println("Your score is " + arena.score);
      System.exit(0);
    }
  }

  public boolean eatSelf(){
    Segment s;
    boolean b = false;
    for (int i = 1 ; i < body.size() ; ++i) {
      s = body.get(i);
      if (s.position.equalsTo(this.position)){
        b = true;
      }
    }
    return b;
  }

  public boolean eatFriend(Snake s){
    boolean b = false;
    Segment t;
    for (int i = 0; i < s.body.size() ; ++i){
      t = body.get(i);
      if (t.position.equalsTo(this.position)){
        b = true;
      }
    }
    return b;
  }

  public boolean hitWall() {
    boolean hit = false;
    if (position.x <= 0){
        hit = true;
    }
    else if (position.x  >= arena.width){
        hit = true;
    }
    if (position.y  < 0){
        hit = true;
    }
    else if(position.y >=  arena.height){
        hit = true;
    }
    return hit;
  }

  @Override
  public void changeColor() { /*i changed this method so that the snake does not change color based on user input, put does so automatically based on score...like arena */
    if (arena.score > 5 && arena.score <= 10) {
      color = Color.PINK;
    }
    if (arena.score > 10 && arena.score <= 15) {
      color = Color.BLUE;
    }
    if (arena.score > 15 && arena.score <= 20) {
      color = Color.MAGENTA;
    }
  }
// debugging method
  public void printSnake(){
    for (Segment s: body)
      System.out.println(s);
  }

}

class Segment {
  public Pair position;
  public Pair velocity;
  public int width = 20;
  public int height = 20;

  public Segment(Pair position) {
    this.position = position;
    this.velocity = new Pair (0,-20);
  }

  public String toString(){
    return "X: " + position.x + "  " + "Y: " + position.y;
  }

}
