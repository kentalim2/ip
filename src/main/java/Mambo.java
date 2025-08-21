import java.util.Scanner;

public class Mambo {
    private static final String line = "\n────────────────────────────────────────────────────────────────\n";

    private static String response(String message) {
        return line + message + line;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        TaskList list = new TaskList();

        System.out.println(response("hi! I'm Mambo, your personal assistant and chatbot!\n"
                                    + "what can I do for you today? ei, ei mun!"));
        while (isRunning) {
            String input = scanner.nextLine();

            switch (input) {
                case "bye":
                    System.out.println(response("byee, see you again!"));
                    isRunning = false;
                    break;
                case "list":
                    System.out.println(response(list.toString()));
                    break;
                default:
                    list.addToList(input);
                    System.out.println(response("added: " + input));
            }
        }

        scanner.close();
    }
}
