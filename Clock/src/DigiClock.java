import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JPanel;


public class DigiClock extends JPanel{
	public static String format,time;
	public static int ascent;
	boolean flag=true;
	public void DigiClock(){}
	
	public void init(){
		setBackground(Color.WHITE);								  /*sets the backgroud to white*/				
	}
	//method paint begins
	public void paint(Graphics g){
		super.paintComponent(g);		
		Font bigfont;
		bigfont=new Font("SansSerif",Font.PLAIN,ascent);		  /*setting the font and its ascent*/
		g.setFont(bigfont);
		FontMetrics fm=getFontMetrics(bigfont);
		if(flag=true){											  /*executes for the first time alone*/
		getformat();
		Calendar cal=new GregorianCalendar();					  /*using GregorianCalendar to fetch time*/
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		time=sdf.format(cal.getTime());
		}		
		setBackground(Color.WHITE);
        g.setColor(Color.black);        
		g.drawString(time,ascent/2,ascent*2);					  /*drawing the string in the frame created*/
		
		flag=false;
		getTime();												  /*method getTime is called which sets the new time*/
	}
	public void getTime(){
        Calendar cal=new GregorianCalendar();		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		time=sdf.format(cal.getTime());
		repaint(); //repaint function is executed
	}
	public void getformat(){
		format=SELab06Driver.Format;									  /*sets the format of the time*/
	}
	
	
}
