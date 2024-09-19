import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class Leite extends JApplet implements Runnable { 
    
    public void init() {
        getContentPane().setLayout(null);
        setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        setIgnoreRepaint(true);
   
        backBuffer = new BufferedImage(DISPLAY_WIDTH, DISPLAY_HEIGHT, BufferedImage.TYPE_INT_RGB);
        bbGraphics = (Graphics2D) backBuffer.getGraphics();

        sheetleite = getImage(getCodeBase(), "sheetleite.gif");
 
        MediaTracker m = new MediaTracker(this);
        m.addImage(sheetleite, 0); 
 
        try {
            m.waitForID(0);
        }
        catch(InterruptedException e) {
            System.out.println(e);
        } 
    }
 
    public void start() {
        loop = new Thread(this);
        loop.start();
    }
 
    public void stop() {
        loop = null;
    }
 
    public void run() {
        long startTime, waitTime, elapsedTime;
        int delayTime = 500;
 
        Thread thisThread = Thread.currentThread();
        while(loop==thisThread) {
            startTime = System.currentTimeMillis();
 
            changeState();
            render(bbGraphics);
            
            Graphics g = getGraphics();
            g.drawImage(backBuffer, 0, 0, null);
            g.dispose();
 
            elapsedTime = System.currentTimeMillis() - startTime;
            waitTime = Math.max(delayTime - elapsedTime, 5);
 
            try { 
                Thread.sleep(waitTime); 
            }
            catch(InterruptedException e) {}
        }
    }
 
 
    public void changeState() {
       if (counter<1) counter++;
        else counter = 0;
    }
    
    public void render(Graphics g) {
        g.clearRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
   
        int srcX0 = counter*FRAME_WIDTH; 
        int srcY0 = 0;
        int srcX1 = srcX0+FRAME_WIDTH; 
        int srcY1 = FRAME_HEIGHT;
 
        int dstX0 = (DISPLAY_WIDTH-FRAME_WIDTH)/2;
        int dstY0 = (DISPLAY_HEIGHT-FRAME_HEIGHT)/2;
        int dstX1 = dstX0+FRAME_WIDTH;
        int dstY1 = dstY0+FRAME_HEIGHT;
 
        g.drawImage(sheetleite, dstX0, dstY0, dstX1, dstY1, srcX0, srcY0, srcX1, srcY1, null);
    }
  
    private Image sheetleite;
    private int counter = 0; 
 
    private static final int FRAME_WIDTH = 400; 
    private static final int FRAME_HEIGHT = 400;
 
    private Thread loop;
    private BufferedImage backBuffer;
    private Graphics2D bbGraphics;
 
    private static final int DISPLAY_WIDTH = 800; 
    private static final int DISPLAY_HEIGHT = 600;
}
