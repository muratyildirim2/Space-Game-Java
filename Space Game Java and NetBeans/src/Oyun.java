
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MURAT
 */
class Ates
{
private int x;
private int y;
 public Ates (int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setX (int x) {
        this.x = x;
    }

    public void setY (int y) {
        this.y = y;
    }

    public int getX () {
        return x;
    }

    public int getY () {
        return y;
    }

   
}



public class Oyun extends JPanel implements  KeyListener,ActionListener{
    
    Timer timer = new Timer(5,this);

  
    private BufferedImage image;
    
    private ArrayList<Ates> atesler = new ArrayList<Ates>();
    
    private  int atesdirY = 12;
    
    private int topX=0;
    
    private int topdirX=4;
    
    private int uzaygemisiX=0;
    private int harcanan_ates=0;
    
     private int diruzayX=20;
    
    public boolean kontrolEt()
    {
    for (Ates ates :atesler) 
    {
    if (new Rectangle(ates.getX(),ates.getY(),10,20).intersects(new Rectangle(topX,0,15,15)))
    {
    return true;
    }
    
    
    }
    
return false;
    
    }
    
    
       public Oyun ()  {
           
           timer.start();
         
        try {
            image = ImageIO.read(new FileImageInputStream(new File ("uzaygemisi.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
         
           setBackground(Color.black);
           
           
        
    }

    @Override
    public void paint (Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        
      
        
        g.setColor(Color.red);
        g.fillOval(topX, 0, 20, 20);
        
        g.drawImage(image, uzaygemisiX, 490, image.getWidth()/10,image.getHeight()/10,this);
        
        
        for (Ates ates : atesler)
        {
        if(ates.getY()<0)
        {
        atesler.remove(ates);
        }
        g.setColor(Color.GRAY);
        
        
        }
        
        
        for (Ates ates : atesler )
        {
        g.fillRect(ates.getX(), ates.getY(), 10, 20);
        }
        
        if(kontrolEt())
        {
        timer.stop();
        String message ="Kazandınız !! \n" +
                "Harcanan Mermi "+harcanan_ates ;
        
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
        
    }

    @Override
    public void repaint () {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
        topX+=topdirX;
        
        if(topX>=750 || topX<=0)
        {
        topdirX*=-1;
        }
        for(Ates ates : atesler)
        {
        ates.setY(ates.getY()-atesdirY);
                }
        
        
      repaint();
        
            }

    @Override
    public void keyTyped (KeyEvent e) {
      }

    @Override
    public void keyPressed (KeyEvent e) {
        
        int c = e.getKeyCode();
        
        if(c== KeyEvent.VK_A)
        {
            if(uzaygemisiX<=0)
            {
            uzaygemisiX=0;
            }
            else
            {
            uzaygemisiX-=diruzayX;
            }
        
        }
       else if(c== KeyEvent.VK_D)
        {
            
            
            if(uzaygemisiX>=740)
            {
            uzaygemisiX=740;
            }
            else
            {
            uzaygemisiX+=diruzayX;
            }
        
        }
        
        
       else if(c== KeyEvent.VK_W)
       {
         atesler.add(new Ates(uzaygemisiX+15,470 ));
         
         harcanan_ates++;
         
         
       
       }
        
        
      }

    @Override
    public void keyReleased (KeyEvent e) {
        }
    
    
    
}
