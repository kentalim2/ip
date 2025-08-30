package Mambo.task;

import java.util.LinkedList;

/**
 * Representation of the list of tasks that is stored by the chatbot
 * Supports functionality such as adding and removing tasks
 *
 * @author kentalim2
 */
public class TaskList {
    private LinkedList<Task> list = new LinkedList<>();


    /**
     * Adds a task to the task list
     *
     * @param task Mambo.Mambo.Mambo.Task.Task to be added to the task list
     * @return Message confirming that task has been added to list.
     */
    public String addToList(Task task) {
        list.add(task);
        return String.format("I've added the task to your list:\n   %s\nyou have %d tasks in your list!",
                task, list.size());
    }

    /**
     * Returns the given task at the index specified
     *
     * @param index Index of task that user is trying to obtain
     * @return Mambo.Mambo.Mambo.Task.Task at specified index
     */
    public Task getTask(int index) {
        return list.get(index - 1);
    }

    /**
     * Returns the size of the task list
     *
     * @return Size of task list as an integer
     */
    public int listSize() {
        return list.size();
    }

    /**
     * Marks the task at index as done.
     * Will send an error message if task is already marked as done.
     *
     * @param index of task to be marked done
     * @return chatbot message stating that the operation is done
     */
    public String markTask(int index) {
        Task target = list.get(index - 1);
        if (target.isMarked()) {
            return "thats strange, your task is already marked as completed!";
        } else {
            target.mark();
            return String.format("nice job! this task is done:\n%s", target);
        }
    }

    /**
     * Unmarks the task at index.
     * Will send error message if task is already set as unmarked.
     *
     * @param index of task to be unmarked
     * @return chatbot message stating that the operation is done
     */
    public String unmarkTask(int index) {
        Task target = list.get(index - 1);
        if (!target.isMarked()) {
            return "thats strange, your task is already unmarked!";
        } else {
            target.unmark();
            return String.format("I've marked this task as not done:\n%s", target);
        }
    }

    /**
     * Deletes the task at index
     * @param index
     * @return chatbot message stating that the task has been removed
     */
    public String deleteTask(int index) {
        Task target = list.remove(index - 1);
        return String.format("okay! I've removed this task from your list:\n    %s\n"
                + "you now have %d tasks remaining!", target, list.size());

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
