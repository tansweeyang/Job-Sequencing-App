import java.util.Scanner;

public class TaskSequencingApp {
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		TaskController taskList = new TaskController();
		System.out.println("\n---------------------------------------------------------------------------------------------");
		System.out.println("                                    TASK SEQUENCING ");
		System.out.println("---------------------------------------------------------------------------------------------");
		
		
		
		char continueInputTask = 'y';

		do {
			if(continueInputTask == 'y' || continueInputTask == 'Y') {
				taskList.add();
				System.out.print("Continue adding tasks?(y/n): ");
				continueInputTask = scanner.next().charAt(0);
				System.out.println();
			}
			else {
				System.out.println(" Please enter Y/N/y/n only.");
				System.out.print("Continue adding tasks?(y/n): ");
				continueInputTask = scanner.next().charAt(0);
				System.out.println();
			}
		}while(continueInputTask != 'n' && continueInputTask != 'N'); //if yes repeat
		
		char continueMethod ='y';
		do {
			if(continueMethod == 'y' || continueMethod == 'Y') {
				int choice =0;
				System.out.println("---------------------------------------------------------------------------------------------");
				System.out.println(" Choose a method");
				System.out.println(" 1. Greedy Method");
				System.out.println(" 2. Weighted Problem with Dynamic Programming");
				System.out.println("---------------------------------------------------------------------------------------------");
				do {
					System.out.print(" Enter your choice: ");
					choice = scanner.nextInt();
				}while(choice !=1 && choice!=2);
		
				if (choice==1)
					taskList.Greedy();
				else
					taskList.Dynamic();
				
				System.out.print("Continue with others method?(y/n): ");
				continueMethod = scanner.next().charAt(0);
				System.out.println();
			}
			else {
				System.out.println(" Please enter Y/N/y/n only.");
				System.out.print("Continue with others method?(y/n): ");
				continueMethod = scanner.next().charAt(0);
				System.out.println();
			}
		}while(continueMethod != 'n' && continueMethod != 'N'); //if yes repeat
		
	}
	
	
}
