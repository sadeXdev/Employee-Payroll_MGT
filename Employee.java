import java.util.ArrayList;
import java.util.List;

/**
 * Represents an employee of the organization.
 */
public class Employee {
    private final String id;
    private String name;
    private String position;
    private double baseSalary;
    private double hoursWorked;      // hours worked in the current pay period
    private double hourlyOvertimeRate;
    private final List<Payment> paymentHistory = new ArrayList<>();

    public Employee(String id, String name, String position, double baseSalary) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.baseSalary = baseSalary;
        this.hoursWorked = 0;
        this.hourlyOvertimeRate = (baseSalary / 160.0) * 1.5; // simple default: 1.5x normal hourly rate
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public double getBaseSalary() { return baseSalary; }
    public void setBaseSalary(double baseSalary) { this.baseSalary = baseSalary; }
    public double getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(double hoursWorked) { this.hoursWorked = hoursWorked; }
    public double getHourlyOvertimeRate() { return hourlyOvertimeRate; }
    public List<Payment> getPaymentHistory() { return paymentHistory; }

    public void addPayment(Payment payment) {
        paymentHistory.add(payment);
    }

    @Override
    public String toString() {
        return String.format("ID: %-6s Name: %-20s Position: %-15s Base Salary: %.2f",
                id, name, position, baseSalary);
    }
}