import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Color;


interface Colorable {
  public Color snake1 = Color.GREEN;
  public Color snake2 = Color.GREEN;
  public Color arena = Color.BLACK;
  public void changeColor();

}

public class HISS implements KeyListener {

  public static final int FPS = 60;
  public char c;
  public Arena arena;

  public HISS (){
    addKeyListener(this);
  }

  public static void main(String[] args) {

  }

  public void run() {
    while (true) {
      arena.player1.update(1/FPS, arena, arena.items);
      arena.player2.update(1/FPS, arena, arena.items);
      repaint();
    }
  }

  public void keyPressed(KeyEvent e) {
    char c = e.getKeyChar();
    arena.player1.changeDirection(c, arena);
    arena.player2.changeDirection(c, arena);
    endGame(c);
  }

  //ends the game when user presses a key
  public void endGame(char c) {
    if ( c == 'b'){
      System.out.println("Game Over!");
      System.out.println("Your score is: " + arena.score);
      exit(0);
    }
  }
  //end of endGame
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
  public boolean equalsTo(Pair p) {
    if (p.x == this.x && p.y == this.y){
      return true;
    }
    return false;
  }
} // end of class Pair
