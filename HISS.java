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
  public Arena arena;
  public char c;

  public HISS (){
    arena = new Arena();
    this.setPreferredSize(new Dimension(arena.width, arena.height));
    addKeyListener(this);
    Thread mainThread = new Thread(new Runner());
    mainThread.start();
  }

  @Override
  public void keyPressed(KeyEvent e) {
    char c = e.getKeyChar();
    Snake snake;
    Segment head;


      if (c == 'w'){
        snake = arena.snakes.get(0);
        snake.positionOfChange = snake.position;
        head = snake.body.get(0);
        head.velocity = new Pair(0, -40);
      }
      else if (c == 's'){
        snake = arena.snakes.get(0);
        snake.positionOfChange = snake.position;
        head = snake.body.get(0);
        head.velocity = new Pair(0, 40);
      }
      else if (c == 'a'){
        snake = arena.snakes.get(0);
        snake.positionOfChange = snake.position;
        head = snake.body.get(0);
        head.velocity = new Pair(-40, 0);
      }
      else if (c == 'd'){
        snake = arena.snakes.get(0);
        snake.positionOfChange = snake.position;
        head = snake.body.get(0);
        head.velocity = new Pair(40, 0);
      }



      if (c == 'i'){
        snake = arena.snakes.get(1);
        snake.positionOfChange = snake.position;
        head = snake.body.get(0);
        head.velocity = new Pair(0, -40);
      }
      else if (c == 'k'){
        snake = arena.snakes.get(1);
        snake.positionOfChange = snake.position;
        head = snake.body.get(0);
        head.velocity = new Pair(0, 40);
      }
      else if (c == 'j'){
        snake = arena.snakes.get(1);
        snake.positionOfChange = snake.position;
        head = snake.body.get(0);
        head.velocity = new Pair(-40, 0);
      }
      else if (c == 'l'){
        snake = arena.snakes.get(1);
        snake.positionOfChange = snake.position;
        head = snake.body.get(0);
        head.velocity = new Pair(40, 0);
      }
    }


  @Override
  public void keyReleased(KeyEvent e) {
     char c = e.getKeyChar();
   }

  @Override
  public void keyTyped(KeyEvent e) {
    char c = e.getKeyChar();
  }

  public void addNotify() {
      super.addNotify();
      requestFocus();
  }

  class Runner implements Runnable {
    public void run() {
      while (true) {
        arena.update(FPS);
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
    arena.drawItems(g);
    arena.drawSnakes(g);
    arena.drawScore(g);
  }
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
}

interface Colorable {
  public void changeColor();
}
