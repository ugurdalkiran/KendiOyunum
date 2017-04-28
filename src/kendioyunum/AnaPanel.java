package kendioyunum;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.JPanel;

public class AnaPanel extends JPanel implements MouseListener, ActionListener{
    
    ArrayList<Engel> engeller;
    Engel sinir;
    Engel ucak;
    ArrayList<Engel> atesler;
    Timer zaman;
    Timer saniyeTut;
    
    JLabel zamanAlani;
    JLabel engelAlani;
    
    int gecenSure;
    
    public AnaPanel(){
        
        super();
        
        addMouseListener(this);
        
        sinir = new Engel(0, 0, AnaPencere.mGenislik - 20, AnaPencere.mYuksekik - 40, 0, 0);
        ucak = new Engel(((AnaPencere.mGenislik - 20) / 2) - 15, ((AnaPencere.mYuksekik - 40) / 2) - 15, 30, 30, 0, 0);
        
        engeller = new ArrayList<Engel>();
        atesler = new ArrayList<Engel>();
        
        for ( int i = 0; i < AnaPencere.mGenislik - 20; i = i + 20 ){
            engeller.add( new Engel(i, 0, 20, 20, 0, 0) );
            engeller.add( new Engel(i, AnaPencere.mYuksekik - 60, 20, 20, 0, 0) );
        }
        for ( int i = 0; i < AnaPencere.mYuksekik - 40; i = i + 20 ){
            engeller.add( new Engel(0, i, 20, 20, 0, 0) );
            engeller.add( new Engel(AnaPencere.mGenislik - 40, i, 20, 20, 0, 0) );
        }
        
        zaman = new Timer(50, this);
        zaman.start();
        
        saniyeTut = new Timer(1000, this);
        saniyeTut.start();
        
        gecenSure = 0;
        
        engelAlani = new JLabel("Kalan Engel: " + engeller.size());
        this.add(engelAlani);
        
        zamanAlani = new JLabel("Geçen Zaman: " + gecenSure);
        this.add(zamanAlani);
    
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for ( int i = 0; i < engeller.size(); i++ ){
            engeller.get(i).ekranaCiz(g, Color.red);
        }
        for ( int i = 0; i < atesler.size(); i++ ){
            atesler.get(i).ucakCiz(g, Color.blue);
        }
        sinir.ekranaCiz(g, Color.black);
        ucak.ucakCiz(g, Color.blue);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        int nesnePosX = ((AnaPencere.mGenislik - 20) / 2) - 5;
        int nesnePosY = ((AnaPencere.mYuksekik - 40) / 2) - 5;
        
        int tikX = e.getX();
        int tikY = e.getY();
        
        int yeniX = tikX - nesnePosX;
        int yeniY = tikY - nesnePosY;
        
        if ( yeniX > 16 || yeniY > 16 ){
            
            System.out.println("DAR YERE TIKLA!");
        
        }else{
        
            atesler.add( new Engel(nesnePosX, nesnePosY, 10, 10, yeniX, yeniY) );
            
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if ( e.getSource() == zaman ){
        
            if ( engeller.size() == 0 ){

                zaman.stop();
                saniyeTut.stop();
                JOptionPane.showMessageDialog(this, "TEBRİKLER OYUN BİTTİ");

            }else{

                for ( int i = 0; i < atesler.size(); i++ ){
                    atesler.get(i).hareketEt(atesler, engeller, sinir);
                }
                repaint();

            }
            
            engelAlani.setText("Kalan Engel: " + engeller.size());
            
        }
        
        if ( e.getSource() == saniyeTut ){
            gecenSure++;
            zamanAlani.setText("Geçen Zaman: " + gecenSure + " saniye");
        }
        
    }

}