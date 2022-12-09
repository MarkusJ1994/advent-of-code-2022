package day8;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TreeGridTest {

    List<String> testInput = List.of(
            "30373",
            "25512",
            "65332",
            "33549",
            "35390"
    );

    List<String> testInputDiffHeightAndLength = List.of(
            "30373",
            "25512",
            "65332",
            "33549"
    );

    @Test
    void constructTreeGrid() {

        TreeGrid treeGrid = TreeGrid.constructTreeGrid(testInput);

        assertEquals(5, treeGrid.getWidth());
        assertEquals(5, treeGrid.getHeight());

        TreeGrid treeGridDiffHeightAndLength = TreeGrid.constructTreeGrid(testInputDiffHeightAndLength);

        assertEquals(5, treeGridDiffHeightAndLength.getWidth());
        assertEquals(4, treeGridDiffHeightAndLength.getHeight());
    }

    @Test
    void testXDirectionVisibility() {
        TreeGrid treeGrid = TreeGrid.constructTreeGrid(List.of("30373"));

        boolean zeroElem = treeGrid.checkAllDirections(0, 1);

        assertFalse(zeroElem);

        boolean sevenElem = treeGrid.checkAllDirections(0, 3);

        assertTrue(sevenElem);
    }

    @Test
    void testYDirectionVisibility() {
        TreeGrid treeGrid = TreeGrid.constructTreeGrid(List.of("3", "0", "3", "7", "3"));

        boolean zeroElem = treeGrid.checkYDirection(1, 0);

        assertFalse(zeroElem);

        boolean sevenElem = treeGrid.checkYDirection(3, 0);

        assertTrue(sevenElem);
    }

    @Test
    void testVisibleTrees() {

        TreeGrid treeGrid = TreeGrid.constructTreeGrid(testInput);

        treeGrid.applyVisibility();

        treeGrid.printVisibilityMap();

        assertEquals(21, treeGrid.calculateVisibleTrees());
    }

    @Test
    void testScenicScore() {
        TreeGrid treeGrid = TreeGrid.constructTreeGrid(testInput);

        treeGrid.applyVisibility();

        assertEquals(8, treeGrid.calculateMaxScenicScore());
    }

}