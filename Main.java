import java.util.*;

class Employee {
    private String id;
    private String name;
    private int leaveBalance;

    public Employee(String id, String name, int leaveBalance) {
        this.id = id;
        this.name = name;
        this.leaveBalance = leaveBalance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public boolean requestLeave(int days) {
        if (days <= leaveBalance) {
            leaveBalance -= days;
            return true;
        }
        return false;
    }
}

class LeaveManagementSystem {
    private Map<String, Employee> employees;
    private Scanner scanner;

    public LeaveManagementSystem() {
        employees = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void addEmployee() {
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Leave Balance: ");
        int leaveBalance = scanner.nextInt();
        scanner.nextLine();
        employees.put(id, new Employee(id, name, leaveBalance));
        System.out.println("Employee added successfully!");
    }

    public void applyLeave() {
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();
        if (!employees.containsKey(id)) {
            System.out.println("Employee not found.");
            return;
        }
        System.out.print("Enter Leave Days: ");
        int leaveDays = scanner.nextInt();
        scanner.nextLine();
        
        Employee emp = employees.get(id);
        if (emp.requestLeave(leaveDays)) {
            System.out.println("Leave approved! Remaining balance: " + emp.getLeaveBalance());
        } else {
            System.out.println("Leave request denied due to insufficient balance.");
        }
    }

    public void viewEmployees() {
        for (Employee emp : employees.values()) {
            System.out.println("ID: " + emp.getId() + ", Name: " + emp.getName() + ", Leave Balance: " + emp.getLeaveBalance());
        }
    }

    public void start() {
        while (true) {
            System.out.println("\nEmployee Leave Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Apply for Leave");
            System.out.println("3. View Employees");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    applyLeave();
                    break;
                case 3:
                    viewEmployees();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        LeaveManagementSystem system = new LeaveManagementSystem();
        system.start();
    }
}