import java.awt.*;
import java.awt.geom.*;
import java.applet.*; 
import javax.swing.*;
import java.util.Random;
import java.awt.event.*;

/**
 * Class BouncingBall - a graphical ball that observes the effect of gravity. The ball
 * has the ability to move. Details of movement are determined by the ball itself. It
 * will fall downwards, accelerating with time due to the effect of gravity, and bounce
 * upward again when hitting the ground.
 *
 * This movement can be initiated by repeated calls to the "move" method.
 * 
 * @author Michael Kölling (mik)
 * @author David J. Barnes
 * @author Bruce Quig
 *
 * @version 2016.02.29
 */

public class BouncingBall {
    private static final int GRAVITY = 5;  // effect of gravity

    private int ballDegradation = 2;
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final int groundPosition;      // y position of ground
    private Canvas canvas;
    private int ySpeed = 1;                // initial downward speed

    /**
     * Constructor for objects of class BouncingBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param groundPos  the position of the ground (where the wall will bounce)
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BouncingBall(int xPos, int yPos, int ballDiameter, Color ballColor,
                        int groundPos, Canvas drawingCanvas){
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        groundPosition = groundPos;
        canvas = drawingCanvas;
    }

    
    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw() {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase() {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move() {
        // remove from canvas at the current position
        erase();
            
        // compute new position
        ySpeed += GRAVITY;
        yPosition += ySpeed;
        xPosition +=2;

        // check if it has hit the ground
        if (yPosition >= (groundPosition - diameter) && ySpeed > 0) {
            yPosition = (int)(groundPosition - diameter);
            ySpeed = -ySpeed + ballDegradation; 
        }

        // draw again at new position
        draw();
    }    

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition() {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition() {
        return yPosition;
    }

    public class BoxBall extends Applet implements Runnable { 
      private BouncingBall ball1;
      private int canvasWidth;
      private int canvasHeight;
      int xpos1 = 150; 
      int ypos1 = 200; 
      int radius1 = 20; 
      int xpos2 = 250; 
      int ypos2 = 200; 
      int radius2 = 20;
      private float ballspeedx1 = -3;   
      private float ballspeedy1 = 0;
      private float ballspeedx2 = 3;   
      private float ballspeedy2 = 0; 
      
         
      
      public void init() {} 
    
      public void start() { 
          Thread thread = new Thread (this); 
          thread.start (); } 
              
      public void stop() {} 
              
      public void destroy() {} 
              
      public void run () {
          Thread.currentThread().setPriority(Thread.MIN_PRIORITY);    
              while (true) {    
                  xpos1 += ballspeedx1;
                  ypos1 += ballspeedy1;
                  xpos2 += ballspeedx2;
                  ypos2 += ballspeedy2;  


              repaint(); 
              
      
            if (xpos1  < 100) {
                ballspeedx1 =- ballspeedx1; 
                xpos1 = 100; 
              } 
             else if (xpos2  < 100) {
                ballspeedx2 =- ballspeedx2; 
                xpos2 = 100; 
              }  
              
              else if (xpos1  > 300) {
                ballspeedx1 =- ballspeedx1; 
                xpos1 = 300; 
              } 
              else if (xpos2  > 300) {
                ballspeedx2 =- ballspeedx2; 
                xpos2 = 300; 
              }  
            
              else if (Math.abs(xpos2-xpos1)<radius1+radius2){
                 ballspeedx1 =- ballspeedx1;
                 ballspeedx2 =- ballspeedx2;                              
              } 
            
              else {  System.out.print("Where is the ball?");
                
              }
                  
                  
               try { Thread.sleep (15); } 

                  catch (InterruptedException ex) {} 
                
                Thread.currentThread().setPriority(Thread.MAX_PRIORITY); 
            }
        } 
              
              
            public void paint(Graphics g){

                g.setColor (Color.red); 
                g.fillOval (xpos1 - radius1, ypos1 - radius1, 2 * radius1, 2 * radius1); 
                
                g.setColor (Color.blue); 
                g.fillOval (xpos2 - radius2, ypos2 - radius2, 2 * radius2, 2 * radius2);    
            }
   
            public void boxBounce(int randBalls, int width, int height){ 
                
               canvasWidth = width;
               canvasHeight = height;
                
               // Create Random location and Rectangle 
               Random randomLocation = new Random();
               Random randColor = new Random();
               Random randNum = new Random();
               Rectangle rec = new Rectangle(xpos1,ypos1,xpos2,ypos2);
               
               // Random Color and Number of Balls times 12
               new Color((int)(Math.random() * 0x1000000));
               int numberOfBalls = randNum.nextInt(randBalls)* 12;
               
               
               int radius = 200;
               int x = randomLocation.nextInt(canvasWidth - radius * 2 - 20) + radius + 10;
               int y = randomLocation.nextInt(canvasHeight - radius * 2 - 20) + radius + 10;
               
               int angleInDegree = randomLocation.nextInt(360); 
               
               // Create the ball inside rectangle 
               ball1 = new BouncingBall(x, y, radius, Color.YELLOW, angleInDegree, canvas);  
 
             }
         } 
     }