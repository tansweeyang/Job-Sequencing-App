import java.util.Comparator;

public class ProfitComparator implements Comparator<Task>{
	@Override
	public int compare(Task t1, Task t2) {
		if(t1.getProfit() < t2.getProfit())
			return 1;
		else if(t1.getProfit() > t2.getProfit())
			return -1;
		else
			return 0;
	}
}


