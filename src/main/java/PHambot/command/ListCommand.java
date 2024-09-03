package PHambot.command;

import PHambot.utils.Utilities;

import java.util.List;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public ListCommand() {
    }

    @Override
    public boolean executeCommand() {
        if (Command.taskList.taskCount() == 0 || Command.taskList == null) {
            Utilities.OutlineMessage("You're free! There aren't any tasks currently.");
            return true;
        }
        Utilities.OutlineMessage("These are your current tasks!\n" + Command.taskList);
        return true;
    }
}
