package PHamBot.parser;

import PHambot.command.ToDoCommand;
import PHambot.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseCommand_returnsCorrectCommand() {
        Parser parser = new Parser();
        assertEquals(parser.parseCommand("todo read book").getClass(), ToDoCommand.class);
    }
}
