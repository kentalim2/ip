import java.util.LinkedList;

/**
 * Representation of the list of tasks that is stored by the chatbot
 * Supports functionality such as adding and removing tasks
 *
 * @author kentalim2
 */
public class TaskList {
    private LinkedList<Task> list = new LinkedList<>();


    public String addToList(Task task) {
        list.add(task);
        return String.format("I've added the task to your list:\n   %s\nyou have %d tasks in your list!",
                task, list.size());
    }

    /**
     * Marks the task at index as done
     *
     * @param index of task to be marked done
     * @return chatbot message stating that the operation is done
     */
    public String markTask(int index) {
        Task target = list.get(index - 1);
        target.mark();
        return String.format("nice job! this task is done:\n%s", target);
    }

    /**
     * Unmarks the task at index
     *
     * @param index of task to be unmarked
     * @return chatbot message stating that the operation is done
     */
    public String unmarkTask(int index) {
        Task target = list.get(index - 1);
        target.unmark();
        return String.format("I've marked this task as not done:\n%s", target);
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
