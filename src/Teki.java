import java.awt.Image;
import java.awt.Toolkit;

public class Teki {

	private int direction;
	private int tekiidou;
	private int qnum;
	private Question question;
	private Image image;
	
	public Teki(int direction, int qnum){
		tekiidou = 0;
		this.direction = direction;
		this.qnum = qnum;
		question = new Question(qnum,direction);
		readImg();
	}
	
	public String getQuestion()
	{
		return question.getQuestion();
	}
	public void update(){
		tekiidou++;
	}
	
	public int getTekiidou(){
		return tekiidou;
	}
	
	public int getDirection(){
		return direction;
	}
	
	private void readImg(){
		switch(direction){
		case 1:
			if(qnum==0)
			image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("lovebard1.png"));
			if(qnum==1)
				image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("honey1.png"));
			if(qnum==2)
				image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("icemonster1.png"));
			if(qnum==3)
				image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("g-dragon1.png"));
				if(qnum==4)
					image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("goldfish1.png"));
				if(qnum==5)
					image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("pizyon1.png"));
				if(qnum==6)
					image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("kaifuku1.png"));
			break;
			
		case 2:
			if(qnum==0)
				image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("lovebard2.png"));
				if(qnum==1)
					image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("honey2.png"));
				if(qnum==2)
					image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("icemonster2.png"));
			if(qnum==3)
			image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("g-dragon2.png"));
			if(qnum==4)
				image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("goldfish2.png"));
			if(qnum==5)
				image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("pizyon2.png"));
			if(qnum==6)
				image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("kaifuku.png"));
			break;
			
		case 3:
			if(qnum==0)
				image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imomusi3.png"));
		    if(qnum==1)
					image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("kumo3.png"));
			if(qnum==2)
					image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("birthday3.png"));
			if(qnum==3)
				image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("tranp3.png"));
				if(qnum==4)
					image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("witchtch3.png"));
				if(qnum==5)
					image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("mandragora3.png"));
				if(qnum==6)
					image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("coo3.png"));

			break;
			
		case 4:
			if(qnum==0)
				image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imomusi4.png"));
		    if(qnum==1)
					image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("kumo4.png"));
			if(qnum==2)
					image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("birthday4.png"));
			if(qnum==3)
				image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("tranp4.png"));
				if(qnum==4)
					image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("witchtch4.png"));
				if(qnum==5)
					image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("mandragora4.png"));
				if(qnum==6)
					image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("coo4.png"));

			break;
			
		default:
			break;
			
		}
	}
	
	public Image getImage(){
		return image;
	}
	
}
