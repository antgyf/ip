package Johnson.command;

import Johnson.utils.Utilities;

/**
 * Represents a command to mark a task as incomplete.
 */
public class UnmarkCommand extends IndexedCommand {

    public static final String COMMAND_WORD = "unmark";

    private static final String COMMAND_MSG = "Damn, must've missed. Watch out for this one:\n";

    public UnmarkCommand(int index) {
        super(index);
        assert index > 0 : "Index should be greater than 0";
    }

    @Override
    public String executeCommand() {
        String checkIndex = checkIndex();
        if (checkIndex != null) {
            return checkIndex;
        }

        Command.taskList.unmarkTask(index - 1);

        assert !taskList.getTask(index - 1).getTaskStatus() : "Task should be marked as incomplete";

        Utilities.OutlineMessage(COMMAND_MSG +
                index + ":  " + Command.taskList.getTask(index - 1));

        return COMMAND_MSG + index + ":  " + Command.taskList.getTask(index - 1);
    }
}
