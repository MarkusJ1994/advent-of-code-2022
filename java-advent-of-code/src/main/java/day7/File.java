package day7;

public record File(String name, int size) {

    @Override
    public String toString() {
        return String.format("- %s (file, size=%d)", name, size);
    }
}
