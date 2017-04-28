package kendioyunum;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class AnaPencere extends JFrame{
    
    public static int mGenislik = 320;
    public static int mYuksekik = 460;
    
    public AnaPencere(){
    
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pencereOrtala();
        this.setResizable(false);
        this.setTitle("Kendi Oyunum");
        
        AnaPanel panelim = new AnaPanel();
        this.add(panelim);
    
    }
    
    private void pencereOrtala(){
    
        Dimension Ekran = Toolkit.getDefaultToolkit().getScreenSize();
        
        int PosX = (Ekran.width - mGenislik) / 2;
        int PosY = (Ekran.height - mYuksekik) / 2;
        
        this.setBounds(PosX, PosY, mGenislik, mYuksekik);
        
    }
    
}