import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskListFile {
    private static final Path DATA_DIRECTORY = Paths.get("data");
    private static final Path FILE_PATH = DATA_DIRECTORY.resolve("tasklist.txt");

    public void initializeFile() {
        try {
            if (!Files.exists(DATA_DIRECTORY)) {
                Files.createDirectories(DATA_DIRECTORY);
                System.out.println("Created data directory: " + DATA_DIRECTORY);
            }

            if (!Files.exists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
                System.out.println("Created data file: " + FILE_PATH);
            }
        } catch (IOException e) {
            System.out.println("Error initializing file: " + e.getMessage());
        }
    }

    public TaskList loadFile() {
        TaskList tasks = new TaskList();

        try {
            initializeFile();
            Scanner scanner = new Scanner(FILE_PATH);
            while (scanner.hasNext()) {
                String nextTask = scanner.nextLine();
                tasks.addToList(Parser.parseFile(nextTask));
            }

        } catch (MamboException e) {
            System.out.println("Error loading list: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error loading list: " + e.getMessage());
        }

        return tasks;
    }

    public void saveFile(TaskList list) {
        try {
            List<String> tempList = new ArrayList<>();
            for (int i = 1; i < list.listSize() + 1; i++) {
                Task task = list.getTask(i);
                tempList.add(task.convertToFileFormat());
            }
            Files.write(FILE_PATH, tempList);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
