package application.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.exception.InvalidArgumentException;
import application.exception.MissingArgumentException;
import application.review.Rating;

/**
 * Tests for ArgumentParser class.
 */
public class ArgumentParserTest {
    private String testString;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        testString = "";
    }

    /**
     * Cleans up the test environment after each test.
     */
    @AfterEach
    public void tearDown() {
        testString = null;
    }

    /**
     * Tests splitting a string into a pair with a valid delimiter.
     */
    @Test
    public void splitIntoPair_validInput_success() {
        testString = "add name /rating 5";
        String[] result = ArgumentParser.splitIntoPair(testString, " ");
        assertEquals("add", result[0]);
        assertEquals("name /rating 5", result[1]);
    }

    /**
     * Tests splitting a string that does not contain the delimiter.
     */
    @Test
    public void splitIntoPair_noDelimiter_success() {
        testString = "exit";
        String[] result = ArgumentParser.splitIntoPair(testString, " ");
        assertEquals("exit", result[0]);
        assertEquals("", result[1]);
    }

    /**
     * Tests the string validation utility for null, empty, and whitespace strings.
     */
    @Test
    public void isInvalidString_nullOrEmpty_success() {
        assertTrue(ArgumentParser.isInvalidString(null));
        assertTrue(ArgumentParser.isInvalidString(""));
        assertTrue(ArgumentParser.isInvalidString("   "));
        assertFalse(ArgumentParser.isInvalidString("valid"));
    }

    /**
     * Tests integer parsing with a valid numeric string.
     * @throws Exception if an unexpected error occurs
     */
    @Test
    public void toInt_validNumber_success() throws Exception {
        testString = "5";
        assertEquals(5, ArgumentParser.toInt(testString));
    }

    /**
     * Tests integer parsing with an invalid non-numeric string.
     */
    @Test
    public void toInt_invalidNumber_throwsException() {
        testString = "abc";
        assertThrows(InvalidArgumentException.class, () -> ArgumentParser.toInt(testString));
    }

    /**
     * Tests integer parsing with an empty string.
     */
    @Test
    public void toInt_empty_throwsException() {
        testString = "";
        assertThrows(MissingArgumentException.class, () -> ArgumentParser.toInt(testString));
    }

    /**
     * Tests integer parsing with multiple numbers in the string.
     */
    @Test
    public void toInt_multipleNumbers_throwsException() {
        testString = "1 2";
        assertThrows(InvalidArgumentException.class, () -> ArgumentParser.toInt(testString));
    }

    /**
     * Tests rating parsing with a valid float string.
     * @throws Exception if an unexpected error occurs
     */
    @Test
    public void toRating_validRating_success() throws Exception {
        testString = "4.5";
        Rating rating = ArgumentParser.toRating(testString);
        assertEquals(4.5f, rating.getRatingValue());
    }

    /**
     * Tests rating parsing with an invalid non-numeric format.
     */
    @Test
    public void toRating_invalidFormat_throwsException() {
        testString = "abc";
        assertThrows(InvalidArgumentException.class, () -> ArgumentParser.toRating(testString));
    }

    /**
     * Tests rating parsing with an empty string.
     */
    @Test
    public void toRating_empty_throwsException() {
        testString = "";
        assertThrows(MissingArgumentException.class, () -> ArgumentParser.toRating(testString));
    }

    /**
     * Tests rating parsing with values outside the valid range [0, 5].
     */
    @Test
    public void toRating_invalidRange_throwsException() {
        assertThrows(InvalidArgumentException.class, () -> ArgumentParser.toRating("6.0"));
        assertThrows(InvalidArgumentException.class, () -> ArgumentParser.toRating("-1.0"));
    }

    /**
     * Tests date parsing with a valid ISO-formatted date string.
     * @throws Exception if an unexpected error occurs
     */
    @Test
    public void parseDate_validDate_success() throws Exception {
        testString = "2023-10-27";
        LocalDate date = ArgumentParser.parseDate(testString);
        assertEquals(LocalDate.of(2023, 10, 27), date);
    }

    /**
     * Tests date parsing with an invalid date format.
     */
    @Test
    public void parseDate_invalidFormat_throwsException() {
        testString = "27-10-2023";
        assertThrows(InvalidArgumentException.class, () -> ArgumentParser.parseDate(testString));
    }

    /**
     * Tests date parsing with an empty string.
     */
    @Test
    public void parseDate_empty_throwsException() {
        testString = "";
        assertThrows(MissingArgumentException.class, () -> ArgumentParser.parseDate(testString));
    }

    /**
     * Tests date formatting from a LocalDate object to a string.
     */
    @Test
    public void formatDate_validDate_success() {
        LocalDate date = LocalDate.of(2023, 10, 27);
        assertEquals("Fri, 27 Oct 2023", ArgumentParser.formatDate(date));
    }
}
