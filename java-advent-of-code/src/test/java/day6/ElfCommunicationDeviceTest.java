package day6;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElfCommunicationDeviceTest {

    // <MarkerIndex, Buffer>
    Map<String, Integer> testDataPart1 = Map.of(
            "bvwbjplbgvbhsrlpgdmjqwftvncz", 5,
            "nppdvjthqldpwncqszvftbrmjlhg", 6,
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 10,
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 11);

    Map<String, Integer> testDataPart2 = Map.of(
            "mjqjpqmgbljsphdztnvjfqwrcgsmlb", 19,
            "bvwbjplbgvbhsrlpgdmjqwftvncz", 23,
            "nppdvjthqldpwncqszvftbrmjlhg", 23,
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 29,
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 26);

    @Test
    public void testMarkerFinderPart1() {
        ElfCommunicationDevice elfCommunicationDevice = new ElfCommunicationDevice(4);
        testDataPart1.entrySet().forEach(entry -> {
            assertEquals(entry.getValue(), elfCommunicationDevice.findMarkerFromBuffer(entry.getKey()));
        });
    }

    @Test
    public void testMarkerFinderPart2() {
        ElfCommunicationDevice elfCommunicationDevice = new ElfCommunicationDevice(14);
        testDataPart2.entrySet().forEach(entry -> {
            assertEquals(entry.getValue(), elfCommunicationDevice.findMarkerFromBuffer(entry.getKey()));
        });
    }


}