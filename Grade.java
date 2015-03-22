
import java.io.*;
import java.util.*;
import java.net.*;
import java.math.*;

/**
* @author Anirudh Balasubramaniam
*/
public class Grade
{
	private String text;
	private ArrayList<String> gradeList;
	private ArrayList<Type> types = new ArrayList<Type>();
	private Scanner keyboard = new Scanner(System.in);

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

	public String askUser(String ask)
	{
		System.out.print(ask+"\t"); 
		return keyboard.nextLine();
	}

	public void getText() throws IOException 
	{
		
		String fileName = askUser("Enter the file name with your grades");
		Scanner input = null;

		try
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
   		input.close();
	}

	public void separate()
	{
		String [] list = new String[10];
		String str = "";
		boolean inTypes = false;
		ArrayList<String> result = new ArrayList<>();
		HashSet<String> set = new HashSet<>();
		ArrayList<String> typeNames = new ArrayList<String>(gradeList.size());

		for(int y = 0; y < gradeList.size(); y++)
		{
			str = gradeList.get(y);
			list = str.split("\\t");
			typeNames.add(list[0]);
		}


		for (String item : typeNames) 
		{
		    if (!set.contains(item)) 
		    {
				result.add(item);
				set.add(item);
		    }
		}

		for(int w = 0; w < result.size(); w++)
			types.add(new Type(result.get(w), findWeightage(result.get(w))));

		for(int i = 0; i < gradeList.size(); i++)
		{
			str = gradeList.get(i);
			list = str.split("\\t");
			double total;
			if(list[4].indexOf('=') != -1)
			{
				total = Double.parseDouble(list[4].substring(list[4].indexOf('/') + 2,list[4].indexOf('=') - 1));
			}
			else
			{
				total = Double.parseDouble(list[5].substring(0,list[5].indexOf('=') - 1));
			}
			findType(list[0]).addAssignment(list[1], Double.parseDouble(list[3]), total, list[2]);
		}
	}

	public Type findType(String s)
	{
	    for(Type t : types)
	    {
	        if(t.getName().equals(s))
	        {
	            return t;
	        }
	    }
	    return null;
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
		System.out.println("Enter 1 - to print\nEnter 2 - to add new assignment to get desired grade\nEnter 3 - to find out to calculate needed grade for a certain category to get desired grade\n\n");
		int choice = Integer.parseInt(keyboard.next());
		switch(choice)
		{
			case 1: print(); break;
			case 2:
			case 3: runCalculation(choice); break;
		}
	}

	public double findWeightage(String type)
	{
		return Double.parseDouble(askUser("What is the weightage for " + type + "?");
	}

	public void print()
	{

	}

	public void runCalculation(int choice)
	{
		String desiredScore = askUser("What is your desired score?");
		double neededGrade = 0.0;

		if(choice == 2)
		{

		}

		else()
		{
			neededGrade = calculateNeededGradeCategory();
		}
	}

	public double calculateNeededGradeCategory(Type t, double desiredScore)
	{
		double weightage = t.getWeightage();
		double neededGrade = (desiredScore-calculateOverall()*(1-weightage))/weightage;
		return neededGrade;
	}

	public double calculateNeededGradeAssignment(Type t, double desiredScore, double total)
	{
		double typeGrade = calculateNeededGrade(t, desiredScore);//the required grade in the overall type
		double neededScore = (typeGrade)*t.getTotal()-(t.getTotalScore()+total);
		return neededScore;
	}
}