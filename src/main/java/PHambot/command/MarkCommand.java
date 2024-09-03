package PHambot.command;

import PHambot.utils.Utilities;

/**
 * Represents a command to mark a task as completed.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean executeCommand() {
        Command.taskList.markTask(index - 1 );
        Utilities.OutlineMessage("Marked " + index + " as done.\n"
                + Command.taskList.getTask(index - 1));
        return true;
    }
}
