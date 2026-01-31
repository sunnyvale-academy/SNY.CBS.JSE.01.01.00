import java.util.ArrayList;
import java.util.List;

/**
 * VULNERABLE CLASS: Payroll
 * This class stores salary records and incorrectly returns its internal list.
 */
public class Payroll {
    private final List<SalaryRecord> records = new ArrayList<>();

    public void addRecord(SalaryRecord record) {
        records.add(record);
    }

    /**
     * VULNERABILITY: Returns the internal reference to the 'records' list.
     * Guideline 6-2 / MUTABLE-2: Create copies of mutable output values.
     */
    public List<SalaryRecord> getRecords() {
        return records;
    }

    public void printPayroll() {
        System.out.println("--- Current Payroll ---");
        for (SalaryRecord r : records) {
            System.out.println(r);
        }
    }
}
