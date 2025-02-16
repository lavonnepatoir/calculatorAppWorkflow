import java.util.Scanner;

public class calculatorApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        double num1 = scanner.nextDouble();

        System.out.print("Enter a math operator: (+) (-) (*) (/) ");
        char operator = scanner.next().charAt(0);

        System.out.print("Enter a second number: ");
        double num2 = scanner.nextDouble();

        double answer;

        switch (operator) {
            case '+':
                answer = add(num1, num2);
                break;
            case '-':
                answer = subtract(num1, num2);
                break;
            case '*':
                answer = multiply(num1, num2);
                break;
            case '/':
                answer = divide(num1, num2);
                break;
            default:
                System.out.println("Invalid.");
                return;
        }

        System.out.println(num1 + " + " + num2 + " = " + answer);
        scanner.close();
    }

    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Can't divide by zero.");
            return Double.NaN;
        }
        return a / b;
    }
}
