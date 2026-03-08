package application.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for CommandType enum.
 */
public class CommandTypeTest {
    /**
     * Tests the getCommandType method with various inputs, including abbreviations.
     */
    @Test
    public void commandType_getCommandType_success() {
        assertEquals(CommandType.EXIT, CommandType.getCommandType("exit"));
        assertEquals(CommandType.EXIT, CommandType.getCommandType("ex"));
        assertEquals(CommandType.ADD, CommandType.getCommandType("add"));
        assertEquals(CommandType.ADD, CommandType.getCommandType("a"));
        assertEquals(CommandType.DELETE, CommandType.getCommandType("delete"));
        assertEquals(CommandType.DELETE, CommandType.getCommandType("del"));
        assertEquals(CommandType.LIST, CommandType.getCommandType("list"));
        assertEquals(CommandType.LIST, CommandType.getCommandType("l"));
        assertEquals(CommandType.UNKNOWN, CommandType.getCommandType("unknown"));
        assertEquals(CommandType.UNKNOWN, CommandType.getCommandType("blah"));
        assertEquals(CommandType.UNKNOWN, CommandType.getCommandType(""));
        assertEquals(CommandType.UNKNOWN, CommandType.getCommandType(null));
    }
}
