package day5;

public record Crate(String content) {

    @Override
    public String toString() {
        return content;
    }
}
