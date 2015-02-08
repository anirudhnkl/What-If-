public class Assignment
{
	String date;
	String name;
	double score;
	double total;
	double percent;

	public Assignment(String _date, double _score, double _total, String _name)
	{
		date = _date;
		name = _name;
		score = _score;
		total = _total;
		percent = 0.0;
	}
	
	public void setPercent(double _percent)
	{
		this.percent = _percent;
	}
	
	public String getDate()
	{
		return date;
	}

	public String getName()
	{
		return name;
	}

	public double getScore()
	{
		return score;
	}

	public double getTotal()
	{
		return total;
	}

	public double getPercent()
	{
		return percent;
	}
}