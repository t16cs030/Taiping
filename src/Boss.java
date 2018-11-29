import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;


public class Boss implements State  {
	private Model model;
	private Teki enemy;
	private LinkedList<Teki> enemys;
	 
	Image haikei1;
	Image zyouou;
	Image anss;
	int idou=0;
	 Image monwaku;
	  private Image zyuunomal; 
	 
	 String yourAnswer;
	 
	 
		
	    //private Question question;

		int tekiflag = 0;


	    int gunFlag=2;

	 
	    int tekitime = 0;
	    private int timee=0;
	    private int time = 60;
	    Image hati;
	  String s="";
	  String ss="";

	    Image waku;
	    private String que[]= new String[6];
	    private String kanzi[]= new String[6];
	    boolean ans = false;
	    private int aristime=30;
	    int hit=0;
	    
		
		private AudioClip hand;
		private AudioClip miss;
		private AudioClip tekikougeki;
	    
	    
	public Boss(Model model){
		this.model=model;
		 enemys = new LinkedList<Teki>();
        mon();
	        yourAnswer ="";
		  haikei1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("haikei2.png"));
		  zyouou = Toolkit.getDefaultToolkit().getImage(getClass().getResource("zyouou.png"));
		  anss = Toolkit.getDefaultToolkit().getImage(getClass().getResource("ansd.png"));
		  monwaku = Toolkit.getDefaultToolkit().getImage(getClass().getResource("monhaii.png"));
		  hati = Toolkit.getDefaultToolkit().getImage(getClass().getResource("honey1.png"));
		  zyuunomal = Toolkit.getDefaultToolkit().getImage(getClass().getResource("zyuunomal.png"));
		 
		  tekikougeki= Applet.newAudioClip(getClass().getResource("tekikita.wav"));
		  hand = Applet.newAudioClip(getClass().getResource("hand.wav"));
		  miss = Applet.newAudioClip(getClass().getResource("miss.wav"));
		  
	}
	
	public void processTimeElapsed(int msec){
	
    	timee++;
    	aristime++;
    	if(timee>=10){
    	time--;
    	timee=0;
    	model.time++;
    	}
    	model.changeState(gameOver());

		if(idou>400){	tekitime++;}
	       else {idou+=10 ;}
		if(tekitime>=115){
			tekikougeki.stop();
			tekikougeki.play();
			tekitime=0;
			mon();
			yourAnswer="";
			model.hp+=50;
		}
	}
public void mon(){
	int i=0;
	que[0]="fushiginokuninoarisu";
	que[1]="maddohatta-nootyakai";
	que[2]="tisyanekoninottekita";
	que[3]="watashihazyououdearu";
	que[4]="donomitiwoittaraiino";
	que[5]="usagisanmattekudasai";
	
	kanzi[0]="  不思議の国のアリス";
	kanzi[1]="マッドハッターのお茶会";
	kanzi[2]=" チシャ猫に乗ってきた";
	kanzi[3]= "   私は女王である";
	kanzi[4]="どの道を行ったらいいの";
	kanzi[5]="ウサギさん待ってください";
	 Random rand=new Random();
	 int a=rand.nextInt(6) ;
	 s=que[a];
	 ss=kanzi[a];
}

	public State process(String event) {
	LinkedList<Teki> enemy = new LinkedList<Teki>();
if(event.charAt(0) == 'E'){
		
			if(yourAnswer.equals(s)){
	
					hand.play();
					hit++;
					mon();
					tekitime=0;
					aristime=0;
					}
			else miss.play();
			yourAnswer="";
			
}
else if(event.charAt(0) == ' '){
	return new BossCome(this,model);
}
else	if(event.charAt(0)=='B'){
	
	if(yourAnswer.isEmpty()){
		yourAnswer="";
		}
		else{
          int nagasa=yourAnswer.length();
String okikaeAto=yourAnswer.substring(0,nagasa-1);
yourAnswer=okikaeAto;
}
}


else{if(yourAnswer.length()<=28)
	yourAnswer += event;
else{}
}
	return this;
	
	}




	public void paint(Graphics g) {
		   g.setFont(new Font(Font.SERIF, Font.BOLD, 36));
       Color color = new Color(87, 30, 20);
       g.setColor(color);
       g.drawImage(  haikei1, 0, 0,model.getView());
       if(hit<3&&(aristime<2||(aristime>3&&aristime<5))){
	        }  
       else if((hit>=3&&(aristime<2||(aristime>3&&aristime<5)||aristime>7))){
    	   
       }
     else  
       g.drawImage(  zyouou, 300, -400+idou,model.getView());
       
      
       
    if(idou>=400&&hit<3)   {
 g.drawImage(  hati, 100,tekitime*4,model.getView());
    g.drawImage(  anss, 210,370,model.getView());
    g.drawImage(  monwaku, 245,660,model.getView());
    g.setColor(Color.RED);
    g.fillRect(300,620,250,24);
    g.setColor(Color.BLUE);
    g.fillRect(300,620,250-model.hp,24);
    g.setColor(Color.WHITE);
    g.setFont(new Font(Font.SERIF, Font.TYPE1_FONT, 30));
    g.drawString( 250-model.hp+"/250", 350, 645);
    g.setFont(new Font(Font.SERIF, Font.TYPE1_FONT, 36)); 
    
    g.drawString( "hp", 250, 640); 
    g.setColor(Color.black );
      g.drawString(yourAnswer,260,700);
      g.setColor(color);
      g.drawString(ss,340,450);
      g.drawString(s,340,500);
      if(tekitime<112)
      g.drawImage( zyuunomal, 100,550,model.getView());   
    
    
    }
    	   }
	
	public State gameOver(){
		if(model.hp>=250){
	
			   model.boss.stop();
			 return new Badend(model);
		}
		else	if(hit>=3&&aristime>=20){
		model.i=22;
		
		model.boss.stop();
			 return  new kaiwa(model);
			
		}
			else
				return this;
		}
}


