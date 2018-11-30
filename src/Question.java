import java.util.Random;

public class Question {


	private String sora[]= new String[7];
	private String riku[]= new String[7];

	private String question;

	Random rand=new Random();

	public Question(int qnum, int direction) {
		mondai();
	if(direction <= 2)
		question =  sora[qnum];
	else
		question =  riku[qnum];
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

public String getQuestion(){
		return question;

}

}