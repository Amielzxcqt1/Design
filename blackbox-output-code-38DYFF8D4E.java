public class SalaryCalculator {
    public static void main(String[] args) {
        String employeeName = "Magdalena Leones";
        double monthlySalary = 35000.0;
        int workingDays = 21; // total working days in the month
        double dailyRate = monthlySalary / workingDays; // calculate daily rate

        int leaveCredits = 3;
        int vacationDays = 5;
        int lateMinutes = 30;

        double sssRate = 0.045;
        double pagIbigContribution = 200.0;
        double withholdingTaxRate = 0.10;

        int diff = vacationDays - leaveCredits;
        int unpaidVacationDays = diff;

        // Avoid negative unpaidVacationDays without if or Math.abs
        while (unpaidVacationDays < 0) {
            unpaidVacationDays = 0;
        }

        int actualWorkedDays = workingDays - unpaidVacationDays;
        double lateDays = lateMinutes / 480.0; // 480 minutes = 1 working day
        double totalWorkedDays = actualWorkedDays - lateDays;

        double grossPay = totalWorkedDays * dailyRate;

        double sssContribution = grossPay * sssRate;
        double withholdingTax = grossPay * withholdingTaxRate;
        double totalDeductions = sssContribution + pagIbigContribution + withholdingTax;

        double netPay = grossPay - totalDeductions;

        // Display payslip with ASCII design
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║              PAYSLIP SUMMARY                  ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║ Employee Name: " + padRight(employeeName, 30) + "║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║ Monthly Salary      : P " + padLeft(formatAmount(monthlySalary), 12) + "       ║");
        System.out.println("║ Working Days        : " + padLeft(Integer.toString(workingDays), 12) + "             ║");
        System.out.println("║ Daily Rate          : P " + padLeft(formatAmount(dailyRate), 12) + "       ║");
        System.out.println("║ Vacation Days       : " + padLeft(Integer.toString(vacationDays), 12) + "             ║");
        System.out.println("║ Leave Credits       : " + padLeft(Integer.toString(leaveCredits), 12) + "             ║");
        System.out.println("║ Unpaid Vacation Days: " + padLeft(Integer.toString(unpaidVacationDays), 12) + "             ║");
        System.out.println("║ Late Minutes        : " + padLeft(Integer.toString(lateMinutes), 12) + "             ║");
        System.out.println("║ Total Worked Days   : " + padLeft(formatAmount(totalWorkedDays), 12) + "             ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║ Gross Pay           : P " + padLeft(formatAmount(grossPay), 12) + "       ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║ Deductions:                                  ║");
        System.out.println("║   SSS Contribution (4.5%)   : P " + padLeft(formatAmount(sssContribution), 12) + "       ║");
        System.out.println("║   Pag-Ibig Contribution      : P " + padLeft(formatAmount(pagIbigContribution), 12) + "       ║");
        System.out.println("║   Withholding Tax (10%)      : P " + padLeft(formatAmount(withholdingTax), 12) + "       ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║ Net Pay             : P " + padLeft(formatAmount(netPay), 12) + "       ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    // Helper method to pad string to the right with spaces
    public static String padRight(String s, int n) {
        if (s.length() >= n) {
            return s.substring(0, n);
        }
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < n) {
            sb.append(' ');
        }
        return sb.toString();
    }

    // Helper method to pad string to the left with spaces
    public static String padLeft(String s, int n) {
        if (s.length() >= n) {
            return s.substring(0, n);
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < n - s.length()) {
            sb.append(' ');
        }
        sb.append(s);
        return sb.toString();
    }

    // Helper method to format double to 2 decimal places as string
    public static String formatAmount(double amount) {
        // Multiply by 100, cast to int, then divide by 100.0 to truncate decimals
        int temp = (int)(amount * 100);
        double truncated = temp / 100.0;
        return Double.toString(truncated);
    }
}