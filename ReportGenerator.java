import java.util.Map;

/**
 * Handles the functional requirement: "reporting".
 * Produces an employee list report and a payroll report.
 */
public class ReportGenerator {

    public void printEmployeeReport(EmployeeManager manager) {
        System.out.println("\n===== EMPLOYEE REPORT =====");
        Map<String, Employee> employees = manager.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees registered.");
            return;
        }
        for (Employee e : employees.values()) {
            System.out.println(e);
        }
        System.out.println("Total employees: " + employees.size());
    }

    public void printPayrollReport(EmployeeManager manager) {
        System.out.println("\n===== PAYROLL REPORT =====");
        Map<String, Employee> employees = manager.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees registered.");
            return;
        }
        double totalPaid = 0;
        for (Employee e : employees.values()) {
            System.out.println("Employee: " + e.getName() + " (" + e.getId() + ")");
            if (e.getPaymentHistory().isEmpty()) {
                System.out.println("   No payments processed yet.");
                continue;
            }
            for (Payment p : e.getPaymentHistory()) {
                System.out.println("   " + p);
                totalPaid += p.getNetAmount();
            }
        }
        System.out.printf("Total net amount paid across all employees: %.2f%n", totalPaid);
    }
}