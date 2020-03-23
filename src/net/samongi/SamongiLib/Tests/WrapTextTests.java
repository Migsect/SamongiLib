package net.samongi.SamongiLib.Tests;

import net.samongi.SamongiLib.Utilities.TextUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.List;

public class WrapTextTests {

    private static final String testData_0 = "The quick brown fox jumps over the lazy dog";
    private static final String testData_1 = "Thequickbrownfoxjumpsoverthelazydog";

    @Test public void singleLine() {
        List<String> wrappedText = TextUtil.wrapText(testData_1, testData_1.length(), 0);
        assertEquals(1,wrappedText.size());
    }

    @Test public void doubleLine() {
        List<String> wrappedText = TextUtil.wrapText(testData_1, 1 + testData_1.length() / 2, 0);
        assertEquals(2, wrappedText.size());
    }

    @Test public void tripleLine() {
        List<String> wrappedText = TextUtil.wrapText(testData_1, 1 + testData_1.length() / 3, 0);
        assertEquals(3, wrappedText.size());
    }
}
