public class Assignment
{
	String date;
	String name;
	String type;
	String description;
	double score;
	double total;
	double percent;
	public Assignment()
	{
		date = "1/1/15";
		name = "nullName";
		type = "noType";
		description = "noDescription";
		score = 0.0;
		total = 0.0;
		percent = 0.0;
	}
	public void setDate(String _date)
	{
		this.date = _date;
	}
	public void setName(String _name)
	{
		this.name = _name;
	}
	public void setType(String _type)
	{
		this.type = _type;
	}
	public void setScore(double _score)
	{
		this.score = _score;
	}
	public void setTotal(double _total)
	{
		this.total = _total;
	}
	public void setPercent(double _percent)
	{
		this.percent = _percent;
	}
	public void setDescription(String _description)
	{
		this.description = _description;
	}
	public String getDate()
	{
		return date;
	}
	public String getName()
	{
		return name;
	}
	public String getType()
	{
		return type;
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
	public String getDescription()
	{
		return description;
	}
}