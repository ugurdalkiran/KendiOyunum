package kendioyunum;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Engel{

    Rectangle r;
    int vX, vY;
    int active;
    
    public Engel(int x, int y, int width, int height, int vX, int vY){
    
        r = new Rectangle(x, y, width, height);
        this.vX = vX;
        this.vY = vY;
        this.active = 1;
    
    }
    
    public void ekranaCiz(Graphics g, Color c){

        g.setColor(c);
        g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
    
    }
    
    public void ucakCiz(Graphics g, Color c){

        g.setColor(c);
        g.drawOval((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
    
    }
    
    public void hareketEt(ArrayList<Engel> atesler, ArrayList<Engel> engeller, Engel sinir){
        
        // Ateşler üzerinde işlem yapıyoruz. r -> aktif ateş üzerindeki işlemlerdir.
        
        r.setLocation((int)r.getX() + vX, (int)r.getY() + vY);
        
        // Oyun alanından dışarıya çıkmayı engellemek!
        
        if ( r.getX() > sinir.r.width || r.getY() > sinir.r.height || r.getX() < 0 || r.getY() < 0 ){ active = 0; }
        for ( int i = 0; i < atesler.size(); i++ ){
            if ( ( atesler.get(i).active == 0 ) || ( atesler.get(i).vX == 0 && atesler.get(i).vY == 0 ) ){ atesler.remove(i); }
        }
        
        // Kenardaki engellere çarpmayı yakalamak!
        
        for ( int i = 0; i < engeller.size(); i++ ){
        
            if ( r.intersects( engeller.get(i).r ) ){
                
                active = 0; // Bu active değeri ateşin aktifliğidir.
                engeller.get(i).active = 0;
                
            }
            
            if ( engeller.get(i).active == 0 ){ engeller.remove(i); }
        
        }
        
        System.out.println("Engel Sayısı: " + engeller.size());
        System.out.println("Ateş Sayısı: " + atesler.size());
    
    }

}