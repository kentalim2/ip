import java.util.LinkedList;
public class TaskList {
    private LinkedList<String> list = new LinkedList<>();

    public void addToList(String task) {
        list.add(task);
    }


    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            message.append(String.format("%d. %s", i + 1, list.get(i)));

            if (i < size - 1) {
                message.append("\n");
            }
        }
        return message.toString();
    }
}
