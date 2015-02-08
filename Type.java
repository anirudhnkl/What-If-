import java.util.*;

public class Type
{
	String name;
	double weightage;
	ArrayList<Assignment> assignments = new ArrayList<Assignment>();

	public Type(String _name, double _weightage)
	{
		name = _name;
		weightage = _weightage;
	}

	public String getName()
	{
		return name;
	}

	public double getWeightage()
	{	
		return weightage;
	}

	public double getPercent()
	{

		return 100*getTotalScore()/getTotal();
	}

	public double getTotalScore()
	{
		double totalScore = 0.0;
		for(Assignment a : assignments)
		{
			totalScore += a.getScore();
		}
		return totalScore;
	}

	public double getTotal()
	{
		double total = 0.0;
		for(Assignment a : assignments)
		{
			total += a.getScore();
		}
		return total;
	}

	public void addAssignment(String date, double score, double total, String description)
	{
		assignments.add(new Assignment(date, score, total, description));
	}
}