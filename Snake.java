import java.awt.Color;
import java.util.ArrayList;
import java.awt.Graphics;
import java.lang.*;


public class Snake implements Colorable {
  public int length = 2;
  private int inedibleCount = 0;
  public ArrayList<Segment> body;
  private Color color = Color.GREEN;
  public int player; // we should have player 1 and 2 so the snake responds to different keys
  public Arena arena; // i think we need to have this so that we can use arena as an argument
  private Snake friend;
  public Pair positionOfChange = new Pair(0,0);

  public Snake(int player, Arena arena) {
    this.player = player;
    this.arena = arena;
    Pair velocity = new Pair(0, -40);
    Segment s1;
    Pair position = new Pair(341,384);
    if (player == 2) {
      position = new Pair(682,384);
    }
    s1 = new Segment(position, velocity);
    body = new ArrayList<Segment>();
    body.add(s1);
    Segment s2 = new Segment(s1.position.add(new Pair(0,20)), velocity);
    body.add(s2);
    Segment s3 = new Segment(s2.position.add(new Pair(0,20)), velocity);
    body.add(s3);
    for (Segment s: body) {
      s.velocity = velocity;
    }
  }

  public void drawSnake(Graphics g){
    g.setColor(Color.GREEN);
    for (Segment s: body){
      g.fillRect((int)s.position.x, (int)s.position.y, s.width, s.height);
     }
  }

  public void move(double time) {
    for (int i=1; i<body.size(); ++i) {
      if (!positionOfChange.equalsTo(new Pair(0,0)) && body.get(i).position.equalsTo(positionOfChange)) {
        body.get(i).velocity = body.get(0).velocity;
      }
    }
    for (Segment s: body) {
      s.position = s.position.add(s.velocity.times(time));
    }
  }

  public void changeVelocity(char c) {
    if (player == 1) {
      if (isMovingUp() || isMovingDown()) {
        if (c == 'a') {
          arena.snakes.get(0).body.get(0).velocity = new Pair(-40, 0);
          positionOfChange = arena.snakes.get(0).body.get(0).position;
        }
        if (c == 'd') {
          arena.snakes.get(0).body.get(0).velocity = new Pair(40, 0);
          positionOfChange = arena.snakes.get(0).body.get(0).position;
        }
      }
      if (isMovingRight() || isMovingLeft()) {
        if (c == 'w') {
          arena.snakes.get(0).body.get(0).velocity = new Pair(0, -40);
          positionOfChange = arena.snakes.get(0).body.get(0).position;
        }
        if (c == 's') {
          arena.snakes.get(0).body.get(0).velocity = new Pair(0, 40);
          positionOfChange = arena.snakes.get(0).body.get(0).position;
        }
      }
    }
    if (player == 2) {
      if (isMovingUp() || isMovingDown()) {
        if (c == 'j') {
          arena.snakes.get(1).body.get(0).velocity = new Pair(-40, 0);
          positionOfChange = arena.snakes.get(0).body.get(0).position;
        }
        if (c == 'l') {
          arena.snakes.get(1).body.get(0).velocity = new Pair(40, 0);
          positionOfChange = arena.snakes.get(0).body.get(0).position;
        }
      }
      if (isMovingRight() || isMovingLeft()) {
        if (c == 'i') {
          arena.snakes.get(1).body.get(0).velocity = new Pair(0, -40);
          positionOfChange = arena.snakes.get(0).body.get(0).position;
        }
        if (c == 'k') {
          arena.snakes.get(1).body.get(0).velocity = new Pair(0, 40);
          positionOfChange = arena.snakes.get(0).body.get(0).position;
        }
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
