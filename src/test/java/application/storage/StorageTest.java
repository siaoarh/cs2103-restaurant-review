package application.storage;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Tests for Storage class.
 */
public class StorageTest {
    /**
     * Tests the instantiation of the Storage class.
     */
    @Test
    public void storage_instantiation_success() {
        Storage storage = new Storage();
        assertNotNull(storage);
    }
}
