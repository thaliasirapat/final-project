public interface Colorable {

  public Color snake1, snake2, arena;
  public void changeColor();

}

class Pair{
    public double x;
    public double y;

    public Pair(double initX, double initY){
	     x = initX;
	     y = initY;
    }

    public Pair add(Pair toAdd){
	     return new Pair(x + toAdd.x, y + toAdd.y);
    }

    public Pair divide(double denom){
	     return new Pair(x / denom, y / denom);
    }

    public Pair times(double val){
	     return new Pair(x * val, y * val);
    }

    public void flipX(){
	     x = -x;
    }

    public void flipY(){
	     y = -y;
    }
}


public class Snake implements Colorable {

// We should decide the initialization values for these

  private Pair position;
  private Pair velocity;
  public int length;
  private int inedibleCount = 0;
  public Segment head;
  public Arraylist<Segment> body;
  private Color color;
  private int player; // we should have player 1 and 2 so the snake responds to different keys

  public Snake(int player) {
    body = new ArrayList<Segment>();
    this.player = player;

  }

  // Draw the snake on the screen
  public void draw(Graphics g){

    Color c = g.getColor();
    g.setColor(c)
    for (Segment s: body){
      g.fillRect(s.positionX, s.positionY);
     }
  }
  // End of draw method


// Makes the snake move on the screen, dictates behavior
  public void update(double time, Arena arena, ArrayList<Item> items){
    for (Segment s: body){
      s.position = s.position.add(s.velocity.times(time));
    }
    if (eatSelf() || eatFriend() || hitWall()){
      System.out.println("You died...");
      System.out.println("Your score is: " + arena.score );
      exit(0);
    }
    else if (eatItem(items)) {
      this.evolve();
    }

    if (score == 10){
      arena.changeColor();
    }
  }



  public void changeDirection(Char c) {
    if (this.player == 1) {
      if ( c == 'w') {
        this.velocity.x = 
      }
      else if (c == 's') {
        changeGravity(1);
      }
      else if ( c == 'a') {
        changeGravity(2);
      }
      else if ( c == 'd') {
        changeGravity(3);
      }
      else if ( c == 'e'){
        for (Sphere s: world.spheres)
          s.acceleration.times(2);
      }
      else if ( c == 'q') {
        for (Sphere s: world.spheres)
          s.acceleration.divide(2);
      }
  }
  else if (this.player == 2) {
    if ( c == 'i') {

    }
    else if (c == 'k') {

    }
    else if ( c == 'j') {

    }
    else if ( c == 'l') {

    }
    else if ( c == 'e'){
      for (Sphere s: world.spheres)
        s.acceleration.times(2);
    }
    else if ( c == 'q') {
      for (Sphere s: world.spheres)
        s.acceleration.divide(2);
    }
  }

  }

  public boolean eatItem(ArrayList<Item> items)

  public void evolve()

  public boolean eatSelf()

  public boolean eatFriend()

  public boolean hitWall()

  public void changeColor() {
    @override
  }



}


class Segment extends Snake {

  @override
  public Pair position;
  public int width = 20;
  public int height = 20;

  public Segment(Pair position) {
    this.position = position;
  }

}
