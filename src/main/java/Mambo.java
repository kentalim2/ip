import java.util.Scanner;

public class Mambo {
    public static void main(String[] args) {
        final String line = "\n────────────────────────────────────────────────────────────────\n";

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println(line + "Hi! I'm Mambo, your personal assistant and chatbot!\n"
                                + "What can I do for you today? ei, ei mun!" + line);
        while (isRunning) {
            String input = scanner.nextLine();

            switch (input) {
                case "bye":
                    System.out.println(line + "Byee, see you again!" + line);
                    isRunning = false;
                    break;
                default:
                    System.out.println(line + input + line);
            }
        }

        scanner.close();
    }
}
