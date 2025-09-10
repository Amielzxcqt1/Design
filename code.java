import java.util.Scanner;

public class SalaryCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Employee details (fixed as per the problem)
        String employeeName = "Magdalena Leones";
        double monthlySalary = 35000.0;
        double dailyRate = 1666.67;
        int workingDays = 21;
        int leaveCredits = 3;

        // Deductions rates and amounts
        double sssRate = 0.045; // 4.5%
        double pagIbigContribution = 200.0;
        double withholdingTaxRate = 0.10; // 10%

        // Inputs from user for this month
        System.out.println("Enter number of vacation days taken (week-long vacation assumed 5 days): ");
        int vacationDays = scanner.nextInt();

        System.out.println("Enter total minutes late this month: ");
        int minutesLate = scanner.nextInt();

        System.out.println("Enter amount of late Pag-Ibig contribution adjustment for past 2 months: ");
        double pagIbigAdjustment = scanner.nextDouble();

        // Calculate deductions and pays
        // Calculate leave deduction (if leave credits are used, no deduction for those days)
        int unpaidLeaveDays = vacationDays - leaveCredits;
        if (unpaidLeaveDays < 0) {
            unpaidLeaveDays = 0; // no unpaid leave if leave credits cover vacation
        }

        // Calculate gross pay adjusted for unpaid leave
        double grossPay = monthlySalary - (unpaidLeaveDays * dailyRate);

        // Calculate deduction for being late (30 minutes late = half day deduction)
        // Assuming 1 day = 8 hours = 480 minutes, so 30 minutes late is 30/480 = 0.0625 day
        double lateDeduction = (minutesLate / 480.0) * dailyRate;

        // Calculate SSS contribution
        double sssContribution = grossPay * sssRate;

        // Total Pag-Ibig contribution includes adjustment
        double totalPagIbig = pagIbigContribution + pagIbigAdjustment;

        // Calculate withholding tax based on gross pay minus SSS and Pag-Ibig
        double taxableIncome = grossPay - sssContribution - totalPagIbig;
        double withholdingTax = taxableIncome * withholdingTaxRate;

        // Total deductions
        double totalDeductions = sssContribution + totalPagIbig + withholdingTax + lateDeduction;

        // Net pay
        double netPay = grossPay - totalDeductions;

        // Display payslip
        System.out.println("\n--- Payslip ---");
        System.out.println("Employee Name: " + employeeName);
        System.out.printf("Gross Pay: P%.2f\n", grossPay);
        System.out.println("Deductions:");
        System.out.printf("  SSS Contribution (4.5%%): P%.2f\n", sssContribution);
        System.out.printf("  Pag-Ibig Contribution + Adjustment: P%.2f\n", totalPagIbig);
        System.out.printf("  Withholding Tax (10%%): P%.2f\n", withholdingTax);
        System.out.printf("  Late Deduction (%.0f minutes): P%.2f\n", (double)minutesLate, lateDeduction);
        System.out.printf("Total Deductions: P%.2f\n", totalDeductions);
        System.out.printf("Net Pay: P%.2f\n", netPay);

        scanner.close();
    }
}
