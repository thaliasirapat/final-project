// imports =======================================================================

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.*;
import java.util.*;


public class Snake implements Colorable {

// We should decide the initialization values for these

  public Pair position;
  public Pair velocity;
  public int length;
  private int inedibleCount = 0;
  public Segment head; //we may not need this
  public Arraylist<Segment> body;
  private Color color;
  private int player; // we should have player 1 and 2 so the snake responds to different keys
  public Arena arena; // i think we need to have this so that we can use arena as an argument

  public Snake(int player, Arena arena) {
    body = new ArrayList<Segment>();
    this.player = player;
    this.arena = arena;

  }

  // Draw the snake on the screen
  public void drawSnake(Graphics g){

    Color c = g.getColor();
    g.setColor(c);
    for (Segment s: body){
      g.fillRect(s.position.x, s.position.y);
     }
  }
  // End of draw method


// Makes the snake move on the screen, dictates behavior ** DONE **
  public void update(double time, Arena arena, ArrayList<Item> items){
    Snake friend;

    for (Segment s: body){
      s.position = s.position.add(s.velocity.times(time));
    }

    if (this.player == 1){
      friend = arena.player2;
    }
    else {
      friend = arena.player1;
    }

    if (eatSelf() || eatFriend(friend) || hitWall(arena)){
      System.out.println("Game Over!");
      System.out.println("Your score is: " + arena.score );
      exit(0);
    }
    else if (eatItem(items, arena)) {
      this.evolve();
    }

  }

  public void changeDirection(char c, Arena arena) {
    if (this.player == 1) {
      if ( c == 'w') {
        if (this.velocity.y > 0) velocity.flipY();
      }
      else if (c == 's') {
        if (this.velocity.y < 0) velocity.flipY();
      }
      else if ( c == 'a') {
        if(this.velocity.x > 0) velocity.flipX();
      }
      else if ( c == 'd') {
        if(this.velocity.x < 0) velocity.flipX();
      }
    }
    else if (this.player == 2) {
      if ( c == 'i') {
        if (this.velocity.y > 0) velocity.flipY();
      }
      else if (c == 'k') {
          if (this.velocity.y < 0) velocity.flipY();
      }
      else if ( c == 'j') {
        if(this.velocity.x > 0) velocity.flipX();
      }
      else if ( c == 'l') {
        if(this.velocity.x < 0) velocity.flipX();
      }
    }
  }
    // End of changeDirection

  // -----------------------------------------------------------//

  public Item eatItem(ArrayList<Item> items, Arena arena) {
    for (Item i: items) {
      if (i.position.equalsTo(this.position))
        return i;
    }
    return null;

  } // End of eatItem ; I made it return an item instead of a boolean bc I think
  // it might be more helpful

  public void evolve(Item i, Arena arena){
    Segment s;
    Pair p;
    if (i.edible) {
      s = body.get(body.size());
      p = s.position.add(new Pair(20,20));
      body.add(p);
      length++;
      arena.score++;
    }
    else{
      inedibleCount++;
    }
    if (inedibleCount > 3){
      System.out.println("Game over!");
      System.out.println("Your score is " + arena.score);
      exit(0);
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

  public boolean hitWall(Arena arena) {
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
  public void changeColor(char c) {
    ArrayList<Color> colors = new ArrayList<Color>();
    colors.add(Color.RED);
    colors.add(Color.GREEN);
    colors.add(Color.YELLOW);
    Random rand = new Random();
    if (this.player == 1) {
      if (c == 'q') {
        this.color = colors.get(rand.nextInt(3));
      }
    }
    else if (this.player = 2) {
      if (c == 'o') {
        this.color = colors.get(rand.nextInt(3));
      }
    }
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
