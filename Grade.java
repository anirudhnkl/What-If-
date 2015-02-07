
import java.io.*;
import java.util.*;
import java.net.*;
import java.math.*;
import org.jsoup.Jsoup;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Grade
{
	private String text;
	private ArrayList<String> gradeList;
	private String [] type;
	private String [] date;
	private String [] description;
	private double [] score;
	private double [] total;
	private Scanner keyboard = new Scanner(System.in);
	private double [] percent;

	public static void main(String[] args)
	{
		Grade grade = new Grade();
		grade.run();
	}

	public Grade()
	{
		text = "";
		gradeList = new ArrayList<String>();
	}

	public void run()
	{
		try
		{
		  getText();
		}
		catch(IOException e)
		{
		  e.printStackTrace();
		}
		separate();
		ask();
	}

	public void getText() throws IOException 
	{
		System.out.print("Enter the file name with your grades:\t"); 
		String fileName = keyboard.nextLine();
		Scanner input = null;

		/*try
		{
			input = new Scanner(new File(fileName+".txt"));
		} catch(FileNotFoundException e)
		{
			System.err.println("ERROR: Cannot open " +
				fileName + " for reading.");
			System.exit(1);
		}

		while(input.hasNext())
   		{
   			gradeList.add(input.nextLine());
   		}
   		*/
   		URL url = new URL("https://montavista.schoolloop.com/progress_report/report?d=x&id=1376458845011&period_id=1407475277927&mark_id=current&template=print");
        Document doc = Jsoup.parse(url, 3*1000);

        String text = doc.body().text();
        text = text.substring(text.indexOf("Submission: ") + 12, text.lastIndexOf("   Zeros No"));
        int i = 0;
        while(text.length() != 0)
        {
        	gradeList.add(text.substring(0,text.indexOf("%") + 1));
        	text = text.substring(text.indexOf("%") + 1);
        }
        System.out.println(gradeList);
   		input.close();
	}

	public void separate()
	{
		String [] list = new String[10];
		type = new String[gradeList.size()];
		date = new String[gradeList.size()];
		description = new String[gradeList.size()];
		score = new double[gradeList.size()];
		total = new double[gradeList.size()];
		percent = new double[gradeList.size()];

		String str = "";

		for(int i = 0; i < gradeList.size(); i++)
		{
			str = gradeList.get(i);
			list = str.split("\\t");
			type[i] =  list[0];
			date[i] = list[1];
			description[i] = list[2];
			//score[i] = Double.parseDouble(list[3]);
			if(list[4].indexOf('=') != -1)
				total[i] = Double.parseDouble(list[4].substring(list[4].indexOf('/') + 2,list[4].indexOf('=') - 1));
			else
				total[i] = Double.parseDouble(list[5].substring(0,list[5].indexOf('=') - 1));
			percent[i] = round(100*(score[i]/total[i]),2);
		}
	}

	public static double round(double value, int places) 
	{
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	public void ask()
	{
		System.out.println("What should be done next:");
		System.out.println("Enter 1 - to print\nEnter 2 - to add new assignment\nEnter 3 - to find out to calculate needed grade for final\n\n");
		String choice = keyboard.next();
		printGrade();
	}

	public void printGrade()
	{
		double sum = 0;
		for (int z = 0; z < type.length; z++) 
		{
			//System.out.println(type[z] + " " + date[z] + " " + description[z] + " " + score[z] + " " + total[z] + " " + percent[z] + " ");
			if(type[z].equals("Assignment"))
				sum += total[z];
		}

		System.out.println(sum);

	}
}