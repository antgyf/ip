package PHambot.command;

import PHambot.utils.Utilities;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    public static boolean isRunning = true;

    public static final String COMMAND_WORD = "quit";

    public ExitCommand() {
    }

    @Override
    public boolean executeCommand() {
        Utilities.OutlineMessage("See you bro!");
        isRunning = false;
        return false;
    }
}
