import static spark.Spark.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.io.IOException;

public class calculatorApp {

    private static final Logger logger = Logger.getLogger(calculatorApp.class.getName());
    private static final String REGION = "us-west"; // or "us-east"

    static {
        try {
            FileHandler fileHandler = new FileHandler("calculator.log", true); // append mode
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(true);
        } catch (IOException e) {
            System.err.println("Failed to set up file logger: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        port(3000); //Check port usage!!

        get("/calculate", (req, res) -> {
            try {
                double num1 = Double.parseDouble(req.queryParams("a"));
                double num2 = Double.parseDouble(req.queryParams("b"));
                char operator = req.queryParams("op").charAt(0);

                if (num1 == 999 && num2 == 999) {
                    throw new RuntimeException("Simulated crash in the works!");
                }

                double result;

                switch (operator) {
                    case '+': result = add(num1, num2); break;
                    case '-': result = subtract(num1, num2); break;
                    case '*': result = multiply(num1, num2); break;
                    case '/': result = divide(num1, num2); break;
                    default: return "Invalid operator.";
                }

                String userId = req.queryParams("user") != null ? req.queryParams("user") : "guest";
                String ip = req.ip();
                String timestamp = java.time.Instant.now().toString();

                logger.log(Level.INFO, String.format(
                        "region=%s userId=%s ip=%s timestamp=%s operation=%s a=%.2f b=%.2f result=%.2f",
                        REGION, userId, ip, timestamp, operator, num1, num2, result
                ));

                return num1 + " " + operator + " " + num2 + " = " + result;
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error occurred: " + e.getMessage(), e);
                return "Error: " + e.getMessage();
            }

        });

        get("/", (req, res) -> {
            res.type("text/html");
            return """
        <html>
        <head><title>Calculator</title></head>
        <body>
            <h2>Simple Calculator</h2>
            <input type="text" id="a" placeholder="Enter first number"><br><br>
            <input type="text" id="op" placeholder="Enter operator (+ - * /)"><br><br>
            <input type="text" id="b" placeholder="Enter second number"><br><br>
            <button onclick="calculate()">=</button>
            <p id="result"></p>
            <script> function calculate() { const a = document.getElementById('a').value; const b = document.getElementById('b').value; const op = document.getElementById('op').value; const url = `/calculate?a=${encodeURIComponent(a)}&b=${encodeURIComponent(b)}&op=${encodeURIComponent(op)}`; fetch(url) .then(response => response.text()) .then(data => { document.getElementById('result').innerText = data; }) .catch(error => { document.getElementById('result').innerText = "Error: " + error; }); } </script>
        </body>
        </html>
    """;
        });

    }

    public static double add(double a, double b) { return a + b; }
    public static double subtract(double a, double b) { return a - b; }
    public static double multiply(double a, double b) { return a * b; }
    public static double divide(double a, double b) { return b == 0 ? Double.NaN : a / b; }
}
