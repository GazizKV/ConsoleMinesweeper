package minesweeper;

import java.util.Objects;

public class Mine {
    int width;
    int height;
    public Mine() {
    }

    public Mine(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mine)) return false;
        Mine mine = (Mine) o;
        return getWidth() == mine.getWidth()
                && getHeight() == mine.getHeight();
    }
    @Override
    public int hashCode() {
        return Objects.hash(getWidth(), getHeight());
    }
}
