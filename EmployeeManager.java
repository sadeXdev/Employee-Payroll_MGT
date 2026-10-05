import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Handles the functional requirement: "managing its employees"
 * (add, update, remove, search, list employees).
 */
public class EmployeeManager {
    private final Map<String, Employee> employees = new LinkedHashMap<>();
    private int nextId = 1;

    public Employee addEmployee(String name, String position, double baseSalary) {
        String id = "EMP" + String.format("%03d", nextId++);
        Employee employee = new Employee(id, name, position, baseSalary);
        employees.put(id, employee);
        return employee;
    }

    public boolean removeEmployee(String id) {
        return employees.remove(id) != null;
    }

    public Employee findEmployee(String id) {
        return employees.get(id);
    }

    public boolean updateEmployee(String id, String name, String position, Double baseSalary) {
        Employee e = employees.get(id);
        if (e == null) return false;
        if (name != null && !name.isBlank()) e.setName(name);
        if (position != null && !position.isBlank()) e.setPosition(position);
        if (baseSalary != null) e.setBaseSalary(baseSalary);
        return true;
    }

    public Map<String, Employee> getAllEmployees() {
        return employees;
    }

    public boolean isEmpty() {
        return employees.isEmpty();
    }
}