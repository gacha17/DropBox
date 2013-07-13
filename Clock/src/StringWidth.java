import java.awt.Font;
import java.awt.FontMetrics;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JPanel;


public class StringWidth extends JPanel{
	private int wid;
	public StringWidth(){}											/*default constructor*/
	public StringWidth(int ascent,String format){					/*parameterized constructor to determine string width*/
		Font bigfont;
		bigfont=new Font("SansSerif",Font.PLAIN,ascent);
		FontMetrics fm=getFontMetrics(bigfont);
		Calendar cal=new GregorianCalendar();		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String time=sdf.format(cal.getTime());						/*calculating a similar time string to determine string width*/
		setWid(fm.stringWidth(time));
	}
	public int getWid() {											/*function that returns string width*/
		return wid;
	}
	public void setWid(int wid) {									/*function that sets the string width*/
		this.wid = wid;
	}
//function that vakidates invalid argument
public void validatestring(String argument,String [] argm){
		int argLength=argm.length;
		boolean invalid=false;
		for(int i=0;i<argLength;i++){
			if(!argm[i].equals("-f") && !argm[i].equals("-s") && !argm[i].equals("-m")){  /*condition check for invalid argument*/
				if(i==0){
				invalid=true;										/*if the first argument is invalid, set flag*/
				}
				else{
				if(!argm[i-1].equals("-f")){						/*all other invalid arguments are captured here*/
					invalid=true;
				}
				}
			}
		}
		if(invalid==true)
			System.err.println("Invalid argument:"+argument);		/*printing the error message*/
	}
}
