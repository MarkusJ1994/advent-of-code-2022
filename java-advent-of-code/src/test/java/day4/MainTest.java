package day4;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    List<String> testData = List.of(
            "2-4,6-8",
            "2-3,4-5",
            "5-7,7-9",
            "2-8,3-7",
            "6-6,4-6",
            "2-6,4-8"
    );

    @Test
    public void testPart1() {
        List<SectionPair> sectionPairs = Main.sectionPairsFullyContained(testData);
        assertEquals(2, sectionPairs.size());
    }

    @Test
    public void testPart2() {
        assertEquals(4, Main.sectionPairsOverlap(testData).size());

        assertTrue(buildPair("15-20,11-16").overlap());
        assertTrue(buildPair("15-20,11-15").overlap());
        assertFalse(buildPair("15-20,11-14").overlap());

        assertTrue(buildPair("15-20,19-25").overlap());
        assertTrue(buildPair("15-20,20-25").overlap());
        assertFalse(buildPair("15-20,21-25").overlap());

        assertTrue(buildPair("15-20,14-21").overlap());
    }

    private SectionPair buildPair(String row) {
        return Main.rowToPair(row);
    }

}