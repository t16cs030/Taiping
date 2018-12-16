
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Arrays;


public class kaiwa implements State  {
	private Model model;
	private String kaiwa[]= new String[30];
	private String kaiwaa[]= new String[30];
	Image haikei1;
	Image waku;
	Image arisu;
	Image arisukuro;
	Image bousikuro;
	Image bousi;
	Image honey;
	int time=0;
	int timee=0;
	int nameflag=1;
	int tekitime=0;
	int sound=0;


	Image kaifuku;

	private AudioClip aris;
	private AudioClip hati;
	private AudioClip setumei;
	private AudioClip e;




	  String lineSepa=System.getProperty("line.separator");

	public kaiwa(Model model){
		this.model=model;
		 haikei1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("haikei2.png"));
		  waku= Toolkit.getDefaultToolkit().getImage(getClass().getResource("waku.png"));
		  arisu = Toolkit.getDefaultToolkit().getImage(getClass().getResource("arice.png"));
		  bousi = Toolkit.getDefaultToolkit().getImage(getClass().getResource("bousiya.png"));
		  arisukuro = Toolkit.getDefaultToolkit().getImage(getClass().getResource("aricekuro.png"));
		  bousikuro = Toolkit.getDefaultToolkit().getImage(getClass().getResource("bousiyakuro.png"));
		  honey = Toolkit.getDefaultToolkit().getImage(getClass().getResource("honey1.png"));
		  kaifuku = Toolkit.getDefaultToolkit().getImage(getClass().getResource("kaifuku1.png"));

		  aris = Applet.newAudioClip(getClass().getResource("aris.wav"));
		  hati = Applet.newAudioClip(getClass().getResource("hati.wav"));
		  setumei = Applet.newAudioClip(getClass().getResource("setumei.wav"));
		  e = Applet.newAudioClip(getClass().getResource("e.wav"));


		   aris.play();
		   Arrays.fill(kaiwaa, "");

		  kaiwa[0]="私はアリス！蟹座の21歳☆";
		  kaiwa[1]="今日は帽子屋さんから森のお茶会に誘われたけど";
		 kaiwaa[1]="どうやら道に迷ってしまったみたい...。";
		  kaiwa[2]="おーい！";
		  kaiwa[3]="？ 誰かしら。";
		  kaiwa[4]="あまりにも遅いから迷ってると思って向かいに来たよ";
		  kaiwa[5]="あら！帽子屋さん！";
		  kaiwa[6]="君は天才だね！";
	    kaiwaa[6]="どうやったらこんな森の奥までこれるんだい？";
		  kaiwa[7]="ううぅ...";
		  kaiwa[8]="ここはハートの女王が飼っているモンスターが";
		  kaiwaa[8]="たくさんいるんだ。早く会場に行こう！";
		  kaiwa[9]=" ! ! !";
		  kaiwa[10]="きゃぁぁ！";
		  kaiwa[11]="おっと、噂をすれば出てきたな。";
		  kaiwa[12]="この数じゃ逃げ切れない...ここは戦おう！";
		  kaiwa[13]="え、でもどうやって ?";
		  kaiwa[14]="いいかい？敵は四方向から攻撃してくる。画面上から来る";
		 kaiwaa[14]= "敵はライフルガンで、下からくる敵はハンドガンで倒すんだ";
		  kaiwa[15]="銃の持ち替えはそれぞれ'rifle'、'hand'と入力する";
		 kaiwaa[15]= "ことでできる。";
		  kaiwa[16]="敵は、書いてある文字列を入力することで攻撃が出来るよ。";
		 kaiwa[17]="文字列の入力後にエンターキーを押すのを忘れないでね。";
		  kaiwa[18]="疲れちゃったらどうしよう...";
			 kaiwa[19]="たまに森の妖精がHPを回復してくれる。";
	  kaiwaa[19]="敵とまぎれて出てくるけど、妖精は殺しちゃダメだぞ。";
		  kaiwa[20]="分かったわ。...頑張る！";
		  kaiwa[21]="";
		  kaiwa[22]="助かったわ...";
		  kaiwa[23]=" すごいじゃないかアリス！！";
	    	kaiwaa[23]="君に狙撃の才能があったなんて！";
		  kaiwa[24]="自分でもびっくりしたわ、私って天才だったのね";
		  kaiwa[25]="......。";
		  kaiwaa[25]= "まあ、お茶会に行こう。みんな待ってる";
		  kaiwa[26]="うん！";
		  kaiwa[27]="こうしてアリスと帽子屋は楽しくティーパーティーを";
		  kaiwaa[27]="しました";
		  kaiwa[28]="その後、アリスという殺し屋が裏世界に現れたのは";
		  kaiwaa[28]="また別のお話...。";
		  kaiwa[29]="";
		 if(model.i==0) {
		  model.st.stop();
			model.st.play();}
	}

	public void processTimeElapsed(int msec){
		if (model.i==9)
			tekitime+=10;
		timee++;
    	if(timee>=5){
    	time++;
    	timee=0;
    	}
    	model.changeState(gamestate());
	}

	public State process(String event) {
		if(event.charAt(0) == 'E'){
			if(model.i==9&&tekitime<200 ){

			}
			else {
			sound=1;
			e.stop();
			e.play();
			model.i++;

			time=0;


			}}
		if(event.charAt(0) == ' '){
			return new BossCome(this,model);
		}
		if(event.charAt(0) == 's'){
			if(model.i<=21){
				aris.stop();
		return new PlayingState(model);}
			else
				return new RecordState(model);

		}
		return this;
		}



	public void paint(Graphics g) {


		g.setFont(new Font(Font.SERIF, Font.BOLD, 26));
       g.setColor(Color.PINK);


		  g.drawImage(  haikei1, 0, 0,model.getView());



		  if(model.i<=1){

		  g.drawImage(  arisu, 0, 300,model.getView());
		  nameflag=1;}
		  if(model.i==2){
		  g.drawImage(  arisukuro, 0, 300,model.getView());
		  nameflag=3;}

		  if(model.i==3){
			  g.drawImage(  arisu, 0, 300,model.getView());
		  nameflag=1;}

		  if(model.i==4||model.i==6||model.i==8||model.i==11||model.i==12||model.i==14||model.i==15||model.i==16||model.i==17||model.i==19||model.i==23||model.i==25){
			  g.drawImage(  bousi, 600, 250,model.getView());
		  g.drawImage(  arisukuro, 0, 300,model.getView());
		  nameflag=2;}

		  if(model.i==5||model.i==7||model.i==10||model.i==13||model.i==18||model.i==20||model.i==22||model.i==24||model.i==26){
		  g.drawImage(  bousikuro, 600, 250,model.getView());
		  g.drawImage(  arisu, 0, 300,model.getView());
		  nameflag=1;}
		  if(model.i==27)
			  nameflag=0;

		  if(model.i==9||model.i==21){
			  g.drawImage(  bousikuro, 600, 250,model.getView());
		  g.drawImage(  arisukuro, 0, 300,model.getView());
	nameflag=0;}

		  if(model.i==9){
			  g.drawImage( honey, tekitime, tekitime,model.getView());
		  g.drawImage( honey, 500, tekitime,model.getView());
		  g.drawImage( honey, 1000-tekitime, tekitime,model.getView());
		if(sound==1){
		  aris.stop();
		hati.play();
		sound=0;
		}
		  }
		  if(model.i==10){
			  if(sound==1){
			  hati.stop();
			  setumei.play();
			  sound=0;
		  }}
		  g.setColor(Color.WHITE);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 45));
		  if(model.i==15)  {

          g.drawString( "rifle", 480, 400);
          g.drawString( "hand", 480, 450);
          g.setColor(Color.RED);
         	 g.drawString( "▼", 420, 452);
          }

		  if(model.i==16){
		  g.drawImage( honey, 500, 350,model.getView());
		  g.drawString( "honey",500, 480);
			  }
		  g.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		  g.setColor(Color.PINK);

		  g.drawImage(  waku, 80, 580,model.getView());
		if (time>3){
		  if(time%2==0){
			  g.drawString("Enter", 950, 675);
			  g.drawString("▼", 970, 700);}
		  else {
		 g.drawString("▼", 970, 710);
		  g.drawString("Enter", 950, 685);}
		}

		if(nameflag==0)
			  g.drawString("", 200, 620);
		  if(nameflag==1)
		  g.drawString("アリス", 200, 620);
		  else if(nameflag==2)
		  g.drawString("帽子屋", 200, 620);
		  else if(nameflag==3)
		  g.drawString("？？？", 200, 620);

		  if(model.i==19)
			  g.drawImage( kaifuku, 500, 350,model.getView());

		  g.setColor(Color.WHITE);

		  if(model.i==14) g.drawString(kaiwaa[model.i], 160, 703);
		  else  g.drawString(kaiwaa[model.i], 170, 703);





		  g.drawString(  kaiwa[model.i], 170, 660);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
		  g.drawString("'S'を押すとスキップするよ！", 680, 40);
		}

	public State gamestate(){
		if(model.i==21){
			setumei.stop();
			 return new PlayingState(model);
		}

		else	if(model.i==29){
			aris.stop();
			 return new RecordState(model);
		}
		else	return this;}


}