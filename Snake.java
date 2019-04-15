import java.awt.Color;
import java.util.ArrayList;
import java.awt.Graphics;
import java.lang.*;


public class Snake implements Colorable {
  public int length = 2;
  private int inedibleCount = 0;
  public ArrayList<Segment> body;
  private Color color = Color.GREEN;
  public Pair position;
  public Pair velocity;
  public int player;
  public Arena arena;
  private Snake friend;
  public Pair positionOfChange = new Pair(0,0);

  public Snake(int player, Arena arena) {
    this.player = player;
    this.arena = arena;
    velocity = new Pair(0, -40);
    if (player == 1) {
      position = new Pair(341,384);
    }
    else if (player == 2) {
      position = new Pair(682,384);
    }
    Segment s1 = new Segment(position, velocity);
    body = new ArrayList<Segment>();
    body.add(s1);
    Segment s2 = new Segment(s1.position.add(new Pair(0,20)), velocity);
    body.add(s2);
    Segment s3 = new Segment(s2.position.add(new Pair(0,20)), velocity);
    body.add(s3);

  }

  public void drawSnake(Graphics g){
    g.setColor(Color.GREEN);
    for (Segment s: body){
      g.fillRect((int)s.position.x, (int)s.position.y, s.width, s.height);
     }
  }

  public void move(double time) {
    for (int i = 0 ; i < body.size() ; ++ i){
      Segment s = body.get(i);
      if (!s.position.equalsTo(positionOfChange)) {
        s.velocity = this.velocity;
      }
    }
    for (Segment s: body) {
      position = position.add(velocity.times(time));
      s.position = s.position.add(s.velocity.times(time));
    }
  }

  public void changeVelocity(char c) {
    Segment head = this.body.get(0);
    changeHeadVelocity(c);
    if (!head.velocity.equalsTo(this.body.get(1).velocity)){
      changeBodyVelocity();
    }
  }

  public void changeHeadVelocity(char c) {

    Segment s = this.body.get(0);

    if (isMovingUp() || isMovingDown()) {
      if (c == 'a' || c == 'j') {
        positionOfChange = s.position;
        s.velocity = new Pair(-40,0);
      }
      if (c == 'd'|| c == 'l') {
        positionOfChange = s.position;
        s.velocity = new Pair(40, 0);
      }
    }
    if (isMovingRight() || isMovingLeft()) {
      if (c == 'w' || c == 'i') {
        positionOfChange = s.position;
        s.velocity = new Pair(0, -40);
      }
      if (c == 's' || c == 'k') {
        positionOfChange = s.position;
        s.velocity = new Pair(0, 40);
      }
    }
  }

    /*  if (isMovingUp() || isMovingDown()) {
        if (c == 'j') {
          positionOfChange = s.position;
          s.velocity = new Pair(-40, 0);
        }
        if (c == 'l') {
          positionOfChange = s.position;
          s.velocity = new Pair(40, 0);
        }
      }
      if (isMovingRight() || isMovingLeft()) {
        if (c == 'i') {
          positionOfChange = s.position;
          s.velocity = new Pair(0, -40);
        }
        if (c == 'k') {
          positionOfChange = s.position;
          s.velocity = new Pair(0, 40);
        } */




  public void changeBodyVelocity(){
    Segment head = this.body.get(0);
    for (Segment b: body) {
      if (b.position.equalsTo(positionOfChange)){
        b.velocity = head.velocity;
      }
    }
  }

  public void update(double time) {
    this.move(time);
  }

  public boolean isMovingUp() {
    Pair upVelocity = new Pair(0, -40);
    if (body.get(0).velocity.equalsTo(upVelocity)){
      return true;
    }
    return false;
  }

  public boolean isMovingDown() {
    Pair downVelocity = new Pair(0, 40);
    if (body.get(0).velocity.equalsTo(downVelocity)) {
      return true;
    }
    return false;
  }

  public boolean isMovingRight() {
    Pair rightVelocity = new Pair(40, 0);
    if (body.get(0).velocity.equalsTo(rightVelocity)) {
      return true;
    }
    return false;
  }

  public boolean isMovingLeft() {
    Pair leftVelocity = new Pair(-40, 0);
    if (body.get(0).velocity.equalsTo(leftVelocity)) {
      return true;
    }
    return false;
  }

  @Override
  public void changeColor() {
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

}

class Segment {
  public Pair position;
  public Pair velocity;
  public int width = 20;
  public int height = 20;

  public Segment(Pair position, Pair velocity) {
    this.position = position;
    this.velocity = velocity;
  }
}
