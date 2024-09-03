package PHambot.command;

import PHambot.task.Deadline;
import PHambot.utils.Utilities;

/**
 * Represents a command to add a Deadline task.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private final Deadline deadline;


    public DeadlineCommand(String task, String date, String time) {
        this.deadline = new Deadline(task, date, time);
    }

    public DeadlineCommand(String task, String date) {
        this.deadline = new Deadline(task, date);
    }

    public Deadline getDeadline() {
        return this.deadline;
    }

    @Override
    public boolean executeCommand() {
        Utilities.OutlineMessage("Added: " + deadline);
        return Command.taskList.addTask(deadline);
    }
}
