/**
 * Handles the functional requirement: "processing their payments".
 * Calculates gross pay (base + overtime), applies deductions,
 * and records the resulting payment against the employee.
 */
public class PayrollProcessor {

    private static final double STANDARD_HOURS = 160.0;   // hours in a normal pay period
    private static final double TAX_RATE = 0.15;           // flat 15% deduction, for demonstration

    /**
     * Calculates gross salary for the period based on hours worked.
     * Hours beyond STANDARD_HOURS are paid at the employee's overtime rate.
     */
    public double calculateGrossPay(Employee employee) {
        double hours = employee.getHoursWorked();
        if (hours <= STANDARD_HOURS) {
            // Prorate base salary if fewer hours were worked than standard.
            return employee.getBaseSalary() * (hours / STANDARD_HOURS);
        }
        double overtimeHours = hours - STANDARD_HOURS;
        double overtimePay = overtimeHours * employee.getHourlyOvertimeRate();
        return employee.getBaseSalary() + overtimePay;
    }

    public double calculateDeductions(double grossPay) {
        return grossPay * TAX_RATE;
    }

    /**
     * Processes payment for a single employee: computes gross pay,
     * deductions and net pay, then records it in the employee's history.
     */
    public Payment processPayment(Employee employee) {
        if (employee.getHoursWorked() <= 0) {
            throw new IllegalStateException(
                    "Cannot process payment: no hours recorded for employee " + employee.getId());
        }
        double gross = calculateGrossPay(employee);
        double deductions = calculateDeductions(gross);
        Payment payment = new Payment(employee.getId(), gross, deductions);
        employee.addPayment(payment);
        return payment;
    }
}