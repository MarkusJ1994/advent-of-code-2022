package day4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public record SectionPair(SectionInterval si1, SectionInterval si2) {

    public boolean fullyContains() {
        boolean si1ContainsSi2 = Math.min(si1.lowerBound(), si2.lowerBound()) == si1.lowerBound() && Math.max(si1.upperBound(), si2.upperBound()) == si1.upperBound();
        boolean si2ContainsSi1 = Math.min(si1.lowerBound(), si2.lowerBound()) == si2.lowerBound() && Math.max(si1.upperBound(), si2.upperBound()) == si2.upperBound();
        return si1ContainsSi2 || si2ContainsSi1;
    }

    public boolean overlap() {
        List<Integer> si1Range = IntStream.range(si1.lowerBound(), si1.upperBound() + 1).boxed().toList();
        List<Integer> si2Range = IntStream.range(si2.lowerBound(), si2.upperBound() + 1).boxed().toList();

        List<Integer> sharedSections = new ArrayList<>(si1Range);
        sharedSections.retainAll(si2Range);

        return sharedSections.size() > 0;
    }

}
