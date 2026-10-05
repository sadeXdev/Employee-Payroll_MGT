import java.time.LocalDate;

/**
 * Represents a single payment made to an employee.
 */
public class Payment {
    private final String employeeId;
    private final LocalDate date;
    private final double grossAmount;
    private final double deductions;
    private final double netAmount;

    public Payment(String employeeId, double grossAmount, double deductions) {
        this.employeeId = employeeId;
        this.date = LocalDate.now();
        this.grossAmount = grossAmount;
        this.deductions = deductions;
        this.netAmount = grossAmount - deductions;
    }

    public String getEmployeeId() { return employeeId; }
    public LocalDate getDate() { return date; }
    public double getGrossAmount() { return grossAmount; }
    public double getDeductions() { return deductions; }
    public double getNetAmount() { return netAmount; }

    @Override
    public String toString() {
        return String.format("[%s] Employee %s | Gross: %.2f | Deductions: %.2f | Net: %.2f",
                date, employeeId, grossAmount, deductions, netAmount);
    }
}