/**
 * A Program to display the Digital clock
 * within a Java Frame by providing command line arguments
 * @author GaneshKumar Munusamy
 */
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class SELab06Driver extends JFrame{
public static boolean seconds,fulltime,validate=true;
public static String Format;
	public static void main(String [] args){
		int fontascent=30,count=args.length,wid;		
		JFrame frame=new JFrame();
		String str="";
		for(int i=0;i<count;i++){
			str=str+" "+args[i];													/*str - string that holds all command line arguments*/
			if(args[i].equals("-s")){
				seconds=true;														/*if -s is present set seconds to true*/
			}
			if(args[i].equals("-m")){
				fulltime=true;														/*if -m is present set fulltime to true*/
			}
			if(args[i].equals("-f")){
				if(i==(count-1)){													/*check if -f is the last argument*/
					System.err.println("No arguments for -f");
				}
				else{
					try{
						fontascent=Integer.parseInt(args[i+1]);
						if(fontascent<0){											/*check if the fontascent is negative*/
							fontascent=30;
							System.err.println("Argument for -f must be Positive");
						}
							
					}
					catch(NumberFormatException nfe){						
						System.err.println("Argument for -f must be a number");		/*catch java exception if the argument for -f is not number*/
					}
				}
			}
		}			
		DigiClock.ascent=fontascent;		
		frame.setTitle("Clock");
		setformat();	//function that sets the format of time to be displayed	
		StringWidth strwidth=new StringWidth(fontascent,Format);					/*creates an object for class StringWidth and calls constructor*/	
		wid=strwidth.getWid();
		strwidth.validatestring(str,args);											/*calls validate function to check for invalid arguments*/
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DigiClock dclock=new DigiClock();
		frame.setSize((wid+fontascent)+15,4*fontascent);							/*setting the width and height of frame*/
		frame.add(dclock,BorderLayout.CENTER);		
		frame.setVisible(true);
	}
	//defining setFormat function
	public static void setformat(){
		if(seconds==true){
			Format="hh:mm:ss";														
		}
		if(fulltime==true){
			Format="HH:mm";
		}
		if(seconds==true && fulltime==true){
			Format="HH:mm:ss";
		}
		if(seconds!=true && fulltime!=true){
			Format="hh:mm";
		}
	}	
}
