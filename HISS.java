import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;

public class HISS extends JPanel implements KeyListener {

  public static final int FPS = 60;
  public char c;
  public Arena arena;


  public HISS (){
    arena = new Arena();
    this.setPreferredSize(new Dimension(arena.width, arena.height));
    addKeyListener(this);
    Thread mainThread = new Thread(new Runner());
    mainThread.start();
  }

  class Runner implements Runnable {
    public void run() {
      while (true) {
        arena.update();
        repaint();
        try{
    		    Thread.sleep(1000/FPS);
    		}
    		catch(InterruptedException e){
        }
      }
    }
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Two Player Snake!");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(new HISS());
    frame.pack();
    frame.setVisible(true);
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);

    g.setColor(arena.color);
    g.fillRect(0, 0, arena.width, arena.height);

    for (Snake s: arena.snakes){
      s.drawSnake(g);
    }

    arena.drawScore(g);
    arena.drawItems(g);

  }

  public void keyPressed(KeyEvent e) {
    char c = e.getKeyChar();

    Snake s1 = arena.snakes.get(0);
    Snake s2 = arena.snakes.get(1);

    if ( c == 'w') {
      change(0,-20, s1);
    }
    else if (c == 's') {
      change(0,20, s1);
    }
    else if ( c == 'a') {
      change(-20,0,s1);
    }
    else if ( c == 'd') {
      change(20,0,s1 );
    }

    if ( c == 'i') {
      change(0,-20, s2);
    }
    else if (c == 'k') {
      change(0,20, s2);
    }
    else if ( c == 'j') {
      change(-20,0, s2);
    }
    else if ( c == 'l') {
      change(20,0, s2);
    }
  }

// NOT WORKING; REWRITE
  public void change(int x, int y, Snake snake) {
    Pair changePosition = snake.position.add(new Pair(5,5));

    for (Segment s: snake.body) {
      if (s.position.equalsTo(changePosition))
        s.velocity = new Pair(x,y);
    }
  }

  public void keyReleased(KeyEvent e) {
     char c = e.getKeyChar();
     System.out.println("\tYou let go of: " + c);

  }

  public void keyTyped(KeyEvent e) {
    char c = e.getKeyChar();
    System.out.println("You typed: " + c);
  }

   public void addNotify() {
      super.addNotify();
      requestFocus();
  }


  //ends the game when user presses a key
  public void endGame(char c) {
    if ( c == 'b'){
      System.out.println("Game Over!");
      System.out.println("Your score is: " + arena.score);
      System.exit(0);
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

  public boolean isPositiveX() {
    if (this.x > 0) {
      return true;
    }
    return false;
  }

  public boolean isPositiveY() {
    if (this.y > 0) {
      return true;
    }
    return false;
  }
} // end of class Pair

interface Colorable {
  public Color snake1 = Color.GREEN;
  public Color snake2 = Color.GREEN;
  public Color arena = Color.BLACK;
  public void changeColor();

}
