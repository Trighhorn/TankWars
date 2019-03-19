package tankwars.tanks;

import java.io.File;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import tankwars.*;

/**
 *
 * @author psoderquist
 */
public class MiloTank extends Tank {

    boolean hasPlayed = false;
    public void playSound() {
    try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Basshunter - DotA1.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    } catch(Exception ex) {
        System.out.println("Error with playing sound.");
        ex.printStackTrace();
    }
}
    public MiloTank(String filename, Arena a) {
        super(filename, a); 
    }
            
    @Override
    public void tankAction() {
        if (!Arena.gameHasStarted())
        {
            return;
        }
        else if (!hasPlayed)
        {
            playSound();
            hasPlayed = true;
        }
        
        List<Bullet> bullets = getBullets();
            for (Bullet b : bullets)
            {
            // this next part dodges a bullet coming from left
                if (b.getX() < this.getX() && Math.abs(b.getX() - this.getX()) < 3 || b.getX() > this.getX() && Math.abs(b.getX() - this.getX()) < 3 ) { 
                    if (b.getY() == this.getY()) // same row as us
                    {
                        if (b.getRotateDegrees() == 0 || b.getRotateDegrees() == 180)
                        {
                            moveUp();
                        }

                    }
                }

            }


    }  
    
}
