package GUI;

import javax.sound.sampled.*;

public class sonidos {

    public void sonido(String pSonido) {
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sonidos.class.getResource(pSonido));


            Clip clip = AudioSystem.getClip();

            clip.open(audioInputStream);
            clip.start();


            Thread.sleep(clip.getMicrosecondLength() / 1000);


            clip.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}