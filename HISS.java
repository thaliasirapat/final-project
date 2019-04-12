import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

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
      arena.player1.update(1/FPS arena.items);
      arena.player2.update(1/FPS, arena.items);
      repaint();
    }


  }

  public void keyPressed(KeyEvent e) {
    char c = e.getKeyChar();
    arena.player1.changeDirection(c);
    arena.player2.changeDirection(c);


}
