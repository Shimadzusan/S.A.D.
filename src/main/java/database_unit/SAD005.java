package database_unit;

public class SAD005 {
/*	..в теории должен генерироваться автоматически*/
	
	private int id; 
	private String date;
	private int begin_cash;
	private int end_cash;
	private int salary;
	private String info; 

	public SAD005() {
	}

	public SAD005(String date, int begin_cash, int end_cash, int salary, String info) {
		this.date = date;
		this.begin_cash = begin_cash;
		this.end_cash = end_cash;
		this.salary = salary;
		this.info = info;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBegin_cash() {
		return begin_cash;
	}

	public void setBegin_cash(int begin_cash) {
		this.begin_cash = begin_cash;
	}

	public int getEnd_cash() {
		return end_cash;
	}

	public void setEnd_cash(int end_cash) {
		this.end_cash = end_cash;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getDate() {
			return date;
		}

	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Data:\n" +
				"date: " + this.date + "\n";
		/*many other*/
	}
}
