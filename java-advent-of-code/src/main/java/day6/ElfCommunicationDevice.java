package day6;

import java.util.Arrays;
import java.util.HashSet;

public class ElfCommunicationDevice {

    private int uniqueSequenceLength;

    public ElfCommunicationDevice(int uniqueSequenceLength) {
        this.uniqueSequenceLength = uniqueSequenceLength;
    }

    public int findMarkerFromBuffer(String buffer) {

        Character[] recentCharacters = new Character[uniqueSequenceLength];

        char[] bufferChars = buffer.toCharArray();

        for (int i = 0; i < bufferChars.length; i++) {
            recentCharacters[i % uniqueSequenceLength] = bufferChars[i];

            //Check when we have 4 chars or more
            if (i >= uniqueSequenceLength) {
                if (uniqueSequence(recentCharacters)) {
                    return i + 1;
                }
            }
        }
        return -1;
    }

    private boolean uniqueSequence(Character[] sequence) {
        HashSet<Character> characters = new HashSet<>(Arrays.asList(sequence));
        return characters.size() == uniqueSequenceLength;
    }

}
