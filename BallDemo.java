import java.awt.Color;
import java.util.HashMap;
import java.util.Random;
import java.util.Iterator;


public class BallDemo   
{
    private Canvas myCanvas;
    private HashMap<Integer, BouncingBall> map;
    private int i;
    private int k;
    private BouncingBall ball;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce(int numBalls)
    {
        map = new HashMap<Integer, BouncingBall>();
        int ground = 400;   // position of the ground line
        Random randomPos = new Random();
        Random randomXPos = new Random();
        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        
        for(int i = 0; i < numBalls; i++) {
            BouncingBall ball = new BouncingBall(randomXPos.nextInt(300), 50, 
            randomPos.nextInt(200), Color.BLUE, ground, myCanvas);
            map.put(i, ball);
        }
        

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            finished = true;
            myCanvas.wait(50);           // small delay
            for(int k = 0; k < map.size(); k++) {
                BouncingBall selectedBall = map.get(k);
                selectedBall.draw();
            
            if (selectedBall.getXPosition() < 550) {
                selectedBall.move();
                
                
                // stop once ball has travelled a certain distance on x axis
            if(selectedBall.getXPosition() < 550){
                finished = false;
                
            }
        }
    }
    myCanvas.wait(50);
    }
  }
}

