package minesweeper;

public class Cell {
    Integer height;
    Integer width;
    String sign = Constants.CLOSED;
    boolean mine = false;
    int numberOfNearMine;


    public Cell() {
    }

    public Cell(String sign) {
        this.sign = sign;
    }

    public Cell(Integer height, Integer width) {
        this.height = height;
        this.width = width;
    }

    public Cell(Integer height, Integer width, boolean mine) {
        this.height = height;
        this.width = width;
        this.mine = mine;
    }

    public int getNumberOfNearMine() {
        return numberOfNearMine;
    }

    public void setNumberOfNearMine(int numberOfNearMine) {
        this.numberOfNearMine = numberOfNearMine;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
