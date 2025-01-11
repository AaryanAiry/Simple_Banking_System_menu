import java.util.*;

class Banking_System {
    static double balance = 0.0;
    static Map<String, String> userDatabase = new HashMap<>(); 
    static Map<String, Double> accountBalances = new HashMap<>(); 
    static Scanner sc = new Scanner(System.in);
    static String loggedInUser = null; 

    
    Banking_System() {}

   
    public void createAccount() {
        System.out.print("Enter username: ");
        String username = sc.next();

        if (userDatabase.containsKey(username)) {
            System.out.println("Account already exists with this username.");
        } else {
            System.out.print("Enter password: ");
            String password = sc.next();
            userDatabase.put(username, password);
            accountBalances.put(username, 0.0); 
            System.out.println("Account created successfully!");
        }
    }

  
    public boolean login(String username, String password) {
        if (userDatabase.containsKey(username)) {
            if (userDatabase.get(username).equals(password)) {
                loggedInUser = username;
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Incorrect password.");
                return false;
            }
        } else {
            System.out.println("No account found with this username.");
            return false;
        }
    }

    
    public void logout() {
        loggedInUser = null;
        System.out.println("Logged out successfully!");
    }

   
    public void deposit(double amount) {
        if (amount > 0) {
            double newBalance = accountBalances.get(loggedInUser) + amount;
            accountBalances.put(loggedInUser, newBalance);
            System.out.println("Deposited " + amount + " successfully! New balance: " + newBalance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    
    public void withdraw(double amount) {
        double currentBalance = accountBalances.get(loggedInUser);
        if (amount > 0 && amount <= currentBalance) {
            double newBalance = currentBalance - amount;
            accountBalances.put(loggedInUser, newBalance);
            System.out.println("Withdrew " + amount + " successfully! New balance: " + newBalance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

 
    public void check_balance() {
        double currentBalance = accountBalances.get(loggedInUser);
        System.out.println("Your current balance is: " + currentBalance);
    }


    public static void displayMainMenu() {
        System.out.println("\n+----------------------------+");
        System.out.println("|     Welcome to XYZ Bank    |");
        System.out.println("+----------------------------+");
        System.out.println("| How can we help you today? |");
        System.out.println("+----------------------------+");
        System.out.println("| 1. Create Account          |");
        System.out.println("| 2. Login                   |");
        System.out.println("| 3. Exit                    |");
        System.out.println("+----------------------------+");
    }


    public static void displayUserMenu() {
        System.out.println("\n+----------------------------+");
        System.out.println("|      Welcome " + loggedInUser + "      |");
        System.out.println("+----------------------------+");
        System.out.println("| What would you like to do? |");
        System.out.println("+----------------------------+");
        System.out.println("|    1. Check Balance        |");
        System.out.println("|    2. Deposit money        |");
        System.out.println("|    3. Withdraw money       |");
        System.out.println("|    4. Logout               |");
        System.out.println("+----------------------------+");
    }


    public static void main(String[] args) {
        Banking_System obj = new Banking_System();
        
        while (true) {
            if (loggedInUser == null) {
     
                displayMainMenu();
                
                int choice = sc.nextInt();
                String username, password;

                switch (choice) {
                    case 1:
                        obj.createAccount();
                        break;

                    case 2:
                        System.out.print("Enter username: ");
                        username = sc.next();
                        System.out.print("Enter password: ");
                        password = sc.next();
                        if (obj.login(username, password)) {
                            System.out.println("Welcome " + username + "!");
                        } else {
                            System.out.println("Login failed.");
                        }
                        break;

                    case 3:
                        System.out.println("Exiting the application. Goodbye!");
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
      
                displayUserMenu();
                
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        obj.check_balance();
                        break;

                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = sc.nextDouble();
                        obj.deposit(depositAmount);
                        break;

                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = sc.nextDouble();
                        obj.withdraw(withdrawAmount);
                        break;

                    case 4:
                        obj.logout();
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}
