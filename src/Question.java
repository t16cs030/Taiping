import java.util.Random;

public class Question {


	private String sora[]= new String[7];
	private String riku[]= new String[7];

	private String Jsora[]= new String[7];
	private String Jriku[]= new String[7];


	private String question;
	private String Jquestion;

	Random rand=new Random();

	public Question(int qnum, int direction) {
		mondai();
	if(direction <= 2) {
		question =  sora[qnum];
	Jquestion =  Jsora[qnum];}

	else {
		question =  riku[qnum];
		Jquestion =  Jriku[qnum];
	}
    }



public void mondai(){
	sora[0]="kawaiitori";
	sora[1]="bunbunbati";
	sora[2]="icemonster";
	sora[3]="g-doragon";
	sora[4]="tobukingyo";
	sora[5]="hatopoppo";
	sora[6]="kaifuku";


	riku[0]="kontyuu";
	riku[1]="spiderman";
	riku[2]="birthday";
	riku[3]="toranpu";
	riku[4]="mazyutu";
	riku[5]="mandrago";
	riku[6]="mrcookie";

}

public void japanese(){
	Jsora[0]="かわいいとり";
	Jsora[1]="ブンブンばち";
	Jsora[2]="アイスモンスター";
	Jsora[3]="ジードラゴン";
	Jsora[4]="飛ぶきんぎょ";
	Jsora[5]="はとポッポ";
	Jsora[6]="回復";


	Jriku[0]="こんちゅう";
	Jriku[1]="スパイダーマン";
	Jriku[2]="バースデイ";
	Jriku[3]="トランプ";
	Jriku[4]="まじゅつ";
	Jriku[5]="マンドラゴ";
	Jriku[6]="ミスタークッキー";

}

public String getQuestion(){
		return question;

}
public String getJAPANESE(){
	return Jquestion;

}
}