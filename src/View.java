import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View extends JPanel {

    private Model model;
    private Question question;
    // Sample instance variables:
    private Image image;
    private Image random;
    private AudioClip sound;

    public View(Model model) {
        this.model = model;



    }


    /**
     * 画面を描画する
     * @param g  描画用のグラフィックスオブジェクト
     */
    @Override
    public void paintComponent(Graphics g) {
    	clear(g);
    	State state = model.getState();
    	state.paint(g);
    	//g.drawString("Time: " +model.getTime(), 430,730);

    }

    /**
     * 画面を黒色でクリア
     * @param g  描画用のグラフィックスオブジェクト
     */
    public void clear(Graphics g) {
        Dimension size = getSize();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, size.width, size.height);
    }

//    public void playBombSound() {
//        sound.stop(); // まず音を停めてから
//        sound.play(); // 再生する
//    }

}
