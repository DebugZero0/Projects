// package core;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Account{
	private List<String> transHistory = new ArrayList<>();

	private String name;
	private double amount;
	private int password;
	private static int uniqueId=0;
	Account(String name,int password){
//		this.amount=amount; 
		this.uniqueId=++uniqueId;
		this.name=name; 
		this.setPass(password);
	}
	public double getBal() {
		return this.amount;
	}
	public int getId() {
		return this.uniqueId; 
	}
	public String getName() {
		return this.name;
	}
	public int getPass() {
		return this.password;
	}
	public int setPass(int password) {
		return this.password=password;
	}
	public double setBal(double amount) {
		return this.amount=amount;
	}
	public List<String> getTransHistory() {
		return transHistory;
	}
	public void addTransaction(String type, double amount, double balance) {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String time = LocalDateTime.now().format(dtf);
	    transHistory.add(type + "," + amount + "," + balance + "," + time);
	}


}

public class Bank {
	static List<Account> accounts=new ArrayList<>();
	

	public static void main(String[] args) {
		System.out.println("==== Welcome to the banking system ==== \n");
	
		Scanner sc=new Scanner(System.in); 
		int choice;
		while(true) {
			System.out.println("\n1.Create Account\n2.Login to Account\n3.Exist");
			System.out.print("Enter your choice::");
			choice=sc.nextInt(); 
			switch(choice) {
			case 1:
				addAccount(sc);
				break;
			case 2:
				sc.nextLine();
				System.out.println("\nLogin to your account \nEnter name:"); 
				String name=sc.nextLine(); 
				System.out.println("Enter password");
				int password=sc.nextInt();
				Account temp=authentication(name,password);
				if(temp!=null) {
					System.out.println("*Login Successfull*");
					runAccMenu(temp,sc);
				}
				else System.out.println("No account found with the given credentials");
				break;
			case 3:
				System.out.println("Hope you have a nice day");
				return;
				
			default: System.out.println("Try again !!");	
				
			}
		}
	}

	private static void addAccount(Scanner sc) {
		sc.nextLine();
		System.out.println("\nEnter name:");
		String name=sc.nextLine();
		System.out.println("Enter password");
		int password=sc.nextInt();
		Account account=new Account(name,password);
		accounts.add(account);
		
		System.out.println("Your account is created successfully");		
	}
	private static Account authentication(String name,int password) {
		
		for(Account ele: accounts) { 
			if(ele.getName().equalsIgnoreCase(name) && ele.getPass()==password) { 
				return ele;
			}
		}
		return null;
	}

	private static void runAccMenu(Account acc,Scanner sc) {
		System.out.println("\n---Account Menu---");	
		while(true) {
			System.out.println("\n1.Check Balance\n2.Deposite\n3.Withdraw\n4.Transaction History\n5.Exit...\n");
			System.out.println("Enter choice::"); 
			int choice=sc.nextInt();
			switch(choice){
			case 1:
				checkBal(acc);
				break;
			case 2:
				Deposit(acc,sc);
				break;
			case 3:
				withdraw(acc,sc);
				break;
			case 4:
				tranHistory(acc);
				break;
			case 5:
				return;
			default: System.out.println("Try again !!");	
			}
						
		}
	}
	private static void tranHistory(Account acc) {
		List<String> history = acc.getTransHistory();
		
		if (history.isEmpty()) {
			System.out.println("No transaction history available.");
			return;
		}

		System.out.print("--------------------------------------------------------------");
		System.out.printf("\n%-12s | %-10s | %-10s | %-20s\n", "Transaction", "Amount", "Balance", "Date & Time");
		System.out.println("-------------|------------|------------|----------------------");

		for(String entry : history) {
			String[] parts = entry.split(",");
			if (parts.length == 4) {
				System.out.printf("%-12s | $%-9.2f | $%-9.2f | %-20s\n",
					parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), parts[3]);
			}
		}
		System.out.println("---------------------------------------------------------------");
		System.out.printf("Current balance: $%.2f\n", acc.getBal());
	}




	private static void withdraw(Account acc, Scanner sc) {
		System.out.println("Enter amount");
		double amount=sc.nextDouble();
		if(acc.getBal()>amount) {
			System.out.println("*Withdrawl Done* balance="+acc.setBal(acc.getBal()-amount));
			acc.addTransaction("Withdrawal", amount, acc.getBal());
		}
		else {
			System.out.println("Insufficient balance");
		}
	}

	private static void Deposit(Account acc,Scanner sc) {
		System.out.println("Enter amount");
		double amount=sc.nextDouble();	
		if (amount <= 0) {
		    System.out.println("Amount must be greater than 0.");
		    return;
		}
		System.out.printf("*Deposited successfully* Balance = $%.2f\n", acc.setBal(acc.getBal() + amount));
		acc.addTransaction("Deposit", amount, acc.getBal());				
	}

	private static void checkBal(Account acc) {
		
		System.out.println(acc.getName()+" balance= "+acc.getBal());		
	}
}

/*

==== Welcome to the banking system ==== 


1.Create Account
2.Login to Account
3.Exist
Enter your choice::1

Enter name:
Ankan Nandi
Enter password
1234
Your account is created successfully

1.Create Account
2.Login to Account
3.Exist
Enter your choice::1

Enter name:
souvik karmakar
Enter password
4321
Your account is created successfully

1.Create Account
2.Login to Account
3.Exist
Enter your choice::2

Login to your account 
Enter name:
Ankan Nandi
Enter password
1234
*Login Successfull*

---Account Menu---

1.Check Balance
2.Deposite
3.Withdraw
4.Transaction History
5.Exit...

Enter choice::
2
Enter amount
5000
*Deposited successfully* Balance = $5000.00

1.Check Balance
2.Deposite
3.Withdraw
4.Transaction History
5.Exit...

Enter choice::
2
Enter amount
600
*Deposited successfully* Balance = $5600.00

1.Check Balance
2.Deposite
3.Withdraw
4.Transaction History
5.Exit...

Enter choice::
3
Enter amount
900
*Withdrawl Done* balance=4700.0

1.Check Balance
2.Deposite
3.Withdraw
4.Transaction History
5.Exit...

Enter choice::
5

1.Create Account
2.Login to Account
3.Exist
Enter your choice::2

Login to your account 
Enter name:
souvik karmakar
Enter password
4321
*Login Successfull*

---Account Menu---

1.Check Balance
2.Deposite
3.Withdraw
4.Transaction History
5.Exit...

Enter choice::
2
Enter amount
10000
*Deposited successfully* Balance = $10000.00

1.Check Balance
2.Deposite
3.Withdraw
4.Transaction History
5.Exit...

Enter choice::
3
Enter amount
987
*Withdrawl Done* balance=9013.0

1.Check Balance
2.Deposite
3.Withdraw
4.Transaction History
5.Exit...

Enter choice::
5

1.Create Account
2.Login to Account
3.Exist
Enter your choice::2

Login to your account 
Enter name:
Ankan Nandi
Enter password
1234
*Login Successfull*

---Account Menu---

1.Check Balance
2.Deposite
3.Withdraw
4.Transaction History
5.Exit...

Enter choice::
4
--------------------------------------------------------------
Transaction  | Amount     | Balance    | Date & Time         
-------------|------------|------------|----------------------
Deposit      | $5000.00   | $5000.00   | 01-08-2025 22:05:37 
Deposit      | $600.00    | $5600.00   | 01-08-2025 22:05:59 
Withdrawal   | $900.00    | $4700.00   | 01-08-2025 22:06:11 
---------------------------------------------------------------
Current balance: $4700.00

1.Check Balance
2.Deposite
3.Withdraw
4.Transaction History
5.Exit...

Enter choice::
5

1.Create Account
2.Login to Account
3.Exist
Enter your choice::2

Login to your account 
Enter name:
souvik karmakar
Enter password
4321
*Login Successfull*

---Account Menu---

1.Check Balance
2.Deposite
3.Withdraw
4.Transaction History
5.Exit...

Enter choice::
4
--------------------------------------------------------------
Transaction  | Amount     | Balance    | Date & Time         
-------------|------------|------------|----------------------
Deposit      | $10000.00  | $10000.00  | 01-08-2025 22:06:46 
Withdrawal   | $987.00    | $9013.00   | 01-08-2025 22:06:54 
---------------------------------------------------------------
Current balance: $9013.00

1.Check Balance
2.Deposite
3.Withdraw
4.Transaction History
5.Exit...

Enter choice::
5

1.Create Account
2.Login to Account
3.Exist
Enter your choice::3
Hope you have a nice day

 */