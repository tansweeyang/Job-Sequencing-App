
public class Task{
	private String id;
	private int profit;
	private int deadline;
	
	public Task(String id, int profit, int deadline) {
		this.id = id;
		this.profit = profit;
		this.deadline = deadline;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	

	public int getProfit() {
		return profit;
	}
	
	public void setProfit(int profit) {
		this.profit = profit;
	}
	
	public int getDeadline() {
		return deadline;
	}
	
	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	@Override
	public String toString() {
		return String.format("%1$-8s %2$-12d %3$-6d %n", id, deadline, profit);	}
	
}
