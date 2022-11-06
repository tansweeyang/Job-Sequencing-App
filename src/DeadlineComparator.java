import java.util.Comparator;
//sort by Deadline

public class DeadlineComparator implements Comparator<Task>{ 
	public int compare(Task t1,Task t2) {
		if(t1.getDeadline()<t2.getDeadline()) {
			return 1;
		}
		else if(t1.getDeadline()>t2.getDeadline()) {
			return -1;
				
		}
		else {
			return 0;
		}
	}
}