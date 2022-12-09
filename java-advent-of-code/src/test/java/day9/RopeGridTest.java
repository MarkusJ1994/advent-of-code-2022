package day9;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RopeGridTest {

    List<String> testCommands = List.of(
            "R 4",
            "U 4",
            "L 3",
            "D 1",
            "R 4",
            "D 1",
            "L 5",
            "R 2"
    );
    List<String> testDataPart2 = List.of(
            "R 5",
            "U 8",
            "L 8",
            "D 3",
            "R 17",
            "D 10",
            "L 25",
            "U 20"
    );

    /**
     * 0,0  0,1  0,2
     * 1,0  1,1  1,2
     * 2,0  2,1  2,2
     */

    @Test
    void calculateDistanceBetweenPoints() {

        assertTrue((RopeGrid.distanceBetweenPointsIsOneStepOrLess(
                new Coordinate(0, 1),
                new Coordinate(0, 0)
        )));

        assertTrue((RopeGrid.distanceBetweenPointsIsOneStepOrLess(
                new Coordinate(1, 1),
                new Coordinate(2, 2)
        )));

        assertTrue((RopeGrid.distanceBetweenPointsIsOneStepOrLess(
                new Coordinate(0, 0),
                new Coordinate(0, 0)
        )));

        assertTrue((RopeGrid.distanceBetweenPointsIsOneStepOrLess(
                new Coordinate(1, 1),
                new Coordinate(1, 2)
        )));

        assertFalse((RopeGrid.distanceBetweenPointsIsOneStepOrLess(
                new Coordinate(0, 0),
                new Coordinate(2, 2)
        )));

        assertFalse((RopeGrid.distanceBetweenPointsIsOneStepOrLess(
                new Coordinate(0, 0),
                new Coordinate(0, 2)
        )));

    }

    @Test
    void testRopeGridPart1() {
        RopeGrid ropeGrid = new RopeGrid(testCommands, 1);

        ropeGrid.traverseCommands();

        ropeGrid.printSnake();
        ropeGrid.printTail();

        assertEquals(13, ropeGrid.getNumberOfCoordinatesVisitedByTail());

    }

    @Test
    void testRopeGridPart2() {
        RopeGrid ropeGrid = new RopeGrid(List.of("R 5"), 9);

        ropeGrid.traverseCommands();

        ropeGrid.printSnake();
        ropeGrid.printTail();

        assertEquals(0, ropeGrid.getNumberOfCoordinatesVisitedByTail());

        ropeGrid = new RopeGrid(List.of("R 5", "U 8"), 9);

        ropeGrid.traverseCommands();

        ropeGrid.printSnake();
        ropeGrid.printTail();

        assertEquals(0, ropeGrid.getNumberOfCoordinatesVisitedByTail());

        ropeGrid = new RopeGrid(List.of("R 5", "U 8", "L 8"), 9);

        ropeGrid.traverseCommands();

        ropeGrid.printSnake();
        ropeGrid.printTail();

        assertEquals(0, ropeGrid.getNumberOfCoordinatesVisitedByTail());

//        assertEquals(36, ropeGrid.getNumberOfCoordinatesVisitedByTail());
    }

}