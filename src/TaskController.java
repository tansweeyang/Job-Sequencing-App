import java.util.*;

public class TaskController implements GreedyMethod, DynamicMethod {
	static Scanner scanner = new Scanner(System.in);

	private ArrayList<Task> taskList = new ArrayList<>();

	public void add() {
		System.out.print(" Enter Task ID: ");
		String id = scanner.next();
		int profit = 0;

		boolean noError = false;
		do {
			try {
				System.out.print(" Enter Task Profit: ");
				profit = scanner.nextInt();
				noError = true;
			} catch (InputMismatchException e) {
				System.out.println(" Please enter an integer.");
				scanner.next();
			}
		} while (noError == false);

		noError = false;
		int deadline = 0;
		do {
			try {
				System.out.print(" Enter Task Deadline: ");
				deadline = scanner.nextInt();
				noError = true;
			} catch (InputMismatchException e) {
				System.out.println(" Please enter date only without month and year.");
				scanner.next();
			}
		} while (noError == false);

		System.out.println(" Task Added" + "\n");

		Task task = new Task(id, profit, deadline);

		taskList.add(task);
	}

	public static void sortTasksByProfit(List<Task> taskList) {

		ProfitComparator profitComparator = new ProfitComparator();
		Collections.sort(taskList, profitComparator);
	}

	public static void sortTasksByDeadline(List<Task> taskList) {
		DeadlineComparator deadlineComparator = new DeadlineComparator();
		Collections.sort(taskList, deadlineComparator);
	}

	public static void displayTasksTable(List<Task> tasks) {
		System.out.println("\n*******************************");
		System.out.println("	Tasks List");
		System.out.println("*******************************");
		System.out.printf("%5s%12s%12s%n", "Task", "Deadline", "Profit");
		System.out.println("-------------------------------");
		for (Task t : tasks) {
			System.out.print(t.toString());
		}
		System.out.println("-------------------------------");

	}

	public void greedyPrintJobScheduling(List<Task> taskList) {

		int t = 0;
		boolean noError = false;
		do {
			try {
				System.out.print("\nEnter total time to complete all the task: ");
				t = scanner.nextInt();
				noError = true;
			} catch (InputMismatchException e) {
				System.out.println("Please enter an integer.");
				scanner.next();
			}

		} while (noError == false);

		// Length of array
		int n = taskList.size();

		// check for time slot availability
		boolean slotAvailability[] = new boolean[t];

		// To store result (Sequence of tasks in maximum profit)
		Task result[] = new Task[t];

		// Find free slot
		for (int i = 0; i < n; i++) {
			if (taskList.get(i).getDeadline() <= t) {
				for (int j = Math.min(t - 1, taskList.get(i).getDeadline() - 1); j >= 0; j--) {
					// Free slot found
					if (slotAvailability[j] == false) {
						slotAvailability[j] = true;
						result[j] = taskList.get(i);
						break;
					}
				}
			}
		}
		// Print the sequence of maximum profit
		System.out.println("\n***************************************");
		System.out.println("Tasks In Sequence Of Maximum Profit");
		System.out.println("***************************************");
		System.out.printf("%1$-8s %2$-12s %3$-6s %n", "Task", "Deadline", "Profit");
		System.out.println("---------------------------------------");
		for (int i = 0; i < result.length; i++) {
			if (result[i] != null)
				System.out.print(result[i].toString());
		}
		System.out.println("---------------------------------------");

		System.out.print("\nTotal profit: ");
		int totalProfit = 0;

		// Remove null and transfer to arrayList
		List<Task> taskList2 = new ArrayList<Task>();
		for (int i = 0; i < result.length; i++) {
			if (result[i] != null) {
				taskList2.add(result[i]);
			}
		}

		// Print total profit
		for (int i = 0; i < taskList2.size(); i++) {
			totalProfit += taskList2.get(i).getProfit();
			if (i < taskList2.size() - 1)
				System.out.print(taskList2.get(i).getProfit() + " + ");
			else
				System.out.print(taskList2.get(i).getProfit());
		}

		System.out.println(" = " + totalProfit);

	}

	public static void dynamicPrintJobScheduling(ArrayList<Task> taskList) {
		PriorityQueue<Task> deadlinesorttaskList = new PriorityQueue<>(new DeadlineComparator());
		deadlinesorttaskList.addAll(taskList);
		Deque<Task> tasklistafterselect = new LinkedList<>();
		dynamicAlgoritm(deadlinesorttaskList, tasklistafterselect);
		taskList.removeAll(taskList);
		taskList.addAll(tasklistafterselect);
		System.out.println("\n***************************************");
		System.out.println("Tasks In Sequence Of Maximum Profit");
		System.out.println("***************************************");
		System.out.printf("%1$-8s %2$-12s %3$-6s %n", "Task", "Deadline", "Profit");
		System.out.println("---------------------------------------");
		for (Task i : tasklistafterselect) {
			System.out.print(i.toString());
		}
		System.out.println("---------------------------------------");

		System.out.print("\nTotal profit: ");
		int sum = 0;
		for (Task i : tasklistafterselect) {
			sum = sum + i.getProfit();
			if (i != tasklistafterselect.getLast()) {
				System.out.print(i.getProfit() + " + ");
			} else {
				System.out.print(i.getProfit());
			}
		}
		System.out.println(" = " + sum);
	}

	public static Deque<Task> dynamicAlgoritm(PriorityQueue<Task> arr, Deque<Task> taskList) {
		
		if (arr.size() == 0) 
		{
			return taskList;
		} 
		else if (taskList.size() == 0)
		{			 
				taskList.add(arr.peek());
			
		} else if (arr.peek().getDeadline() != taskList.getLast().getDeadline()) 
		{	
				taskList.add(arr.peek());
				arr.poll();
				
		} else if (arr.peek().getProfit() <= taskList.getLast().getProfit()) 
		{
				arr.poll();
					
		} else {		
			taskList.removeLast();			
			taskList.addLast(arr.poll());					
		}
				
			return dynamicAlgoritm(arr, taskList);
	}
	
	public void Greedy() {
		sortTasksByProfit(taskList);
		displayTasksTable(taskList);
		greedyPrintJobScheduling(taskList);
	}

	public void Dynamic() {
		sortTasksByDeadline(taskList);
		displayTasksTable(taskList);
		dynamicPrintJobScheduling(taskList);
	}
}
