import java.util.Scanner;

/**
 * Console entry point for Organization X's software:
 * manages employees, processes their payments, and produces reports.
 */
public class Main {
    private static final EmployeeManager manager = new EmployeeManager();
    private static final PayrollProcessor payroll = new PayrollProcessor();
    private static final ReportGenerator reports = new ReportGenerator();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> addEmployee();
                case "2" -> updateEmployee();
                case "3" -> removeEmployee();
                case "4" -> reports.printEmployeeReport(manager);
                case "5" -> recordHoursAndPay();
                case "6" -> reports.printPayrollReport(manager);
                case "0" -> running = false;
                default -> System.out.println("Invalid option, try again.");
            }
        }
        System.out.println("Goodbye.");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n===== ORGANIZATION X - EMPLOYEE & PAYROLL SYSTEM =====");
        System.out.println("1. Add employee");
        System.out.println("2. Update employee");
        System.out.println("3. Remove employee");
        System.out.println("4. View employee report");
        System.out.println("5. Record hours worked & process payment");
        System.out.println("6. View payroll report");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addEmployee() {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Position: ");
        String position = scanner.nextLine().trim();
        double salary = readDouble("Base salary: ");
        if (name.isEmpty() || position.isEmpty() || salary < 0) {
            System.out.println("Invalid input. Employee not added.");
            return;
        }
        Employee e = manager.addEmployee(name, position, salary);
        System.out.println("Employee added: " + e);
    }

    private static void updateEmployee() {
        System.out.print("Employee ID to update: ");
        String id = scanner.nextLine().trim();
        if (manager.findEmployee(id) == null) {
            System.out.println("Employee not found.");
            return;
        }
        System.out.print("New name (leave blank to keep current): ");
        String name = scanner.nextLine().trim();
        System.out.print("New position (leave blank to keep current): ");
        String position = scanner.nextLine().trim();
        System.out.print("New base salary (leave blank to keep current): ");
        String salaryInput = scanner.nextLine().trim();
        Double salary = salaryInput.isEmpty() ? null : parseDoubleOrNull(salaryInput);
        boolean updated = manager.updateEmployee(id, name, position, salary);
        System.out.println(updated ? "Employee updated." : "Update failed.");
    }

    private static void removeEmployee() {
        System.out.print("Employee ID to remove: ");
        String id = scanner.nextLine().trim();
        boolean removed = manager.removeEmployee(id);
        System.out.println(removed ? "Employee removed." : "Employee not found.");
    }

    private static void recordHoursAndPay() {
        System.out.print("Employee ID: ");
        String id = scanner.nextLine().trim();
        Employee e = manager.findEmployee(id);
        if (e == null) {
            System.out.println("Employee not found.");
            return;
        }
        double hours = readDouble("Hours worked this period: ");
        if (hours < 0) {
            System.out.println("Invalid hours.");
            return;
        }
        e.setHoursWorked(hours);
        try {
            Payment payment = payroll.processPayment(e);
            System.out.println("Payment processed: " + payment);
        } catch (IllegalStateException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static double readDouble(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        Double value = parseDoubleOrNull(input);
        return value == null ? -1 : value;
    }

    private static Double parseDoubleOrNull(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}