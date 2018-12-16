import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Question {

	private String sora[] = new String[7];
	private String riku[] = new String[7];

	private String Jsora[] = new String[7];
	private String Jriku[] = new String[7];

	private String question;


	Random rand = new Random();

	public Question(int qnum, int direction) {
		mondai();
		if(direction <= 2) {
			question =  sora[qnum];
		}

		else {
			question =  riku[qnum];

	    }





	}

	public static ArrayList<String> soraText = new ArrayList<String>(Arrays.asList(
			"かわいいとり",
			"ブンブンばち",
			"アイスお化け",
			"G-ドラゴン",
			"飛ぶキンギョ",
			"はとポッポ",
			"     回復"));

	public static ArrayList<String> rikuText = new ArrayList<String>(Arrays.asList(
			"　　昆虫",
			"蜘蛛お化け",
			" バースデイ",
			" トランプ",
			"　　魔術",
			"マンドラゴ",
			"ジンジャマン"));

 void mondai() {
		sora[0] = "kawaiitori";
		sora[1] = "bunbunbati";
		sora[2] = "aisuobake";
		sora[3] = "g-doragon";
		sora[4] = "tobukingyo";
		sora[5] = "hatopoppo";
		sora[6] = "kaifuku";

		riku[0] = "kontyuu";
		riku[1] = "kumoobake";
		riku[2] = "ba-sudei";
		riku[3] = "toranpu";
		riku[4] = "mazyutu";
		riku[5] = "mandorago";
		riku[6] = "zinzyaman";

	}

	public void japanese() {
		Jsora[0] = "かわいいとり";
		Jsora[1] = "ブンブンばち";
		Jsora[2] = "アイスモンスター";
		Jsora[3] = "ジードラゴン";
		Jsora[4] = "飛ぶきんぎょ";
		Jsora[5] = "はとポッポ";
		Jsora[6] = "回復";

		Jriku[0] = "こんちゅう";
		Jriku[1] = "スパイダーマン";
		Jriku[2] = "バースデイ";
		Jriku[3] = "トランプ";
		Jriku[4] = "まじゅつ";
		Jriku[5] = "マンドラゴ";
		Jriku[6] = "ミスタークッキー";

	}

	public String getQuestion() {
		return question;

	}

}