package day8;

public class Tree {

    private final int height;
    private boolean visible = false;

    // Mainly for debugging
    private boolean checked = false;

    public Tree(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
