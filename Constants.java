package minesweeper;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Constants {
    public static final int SIZE = 9;
    public static final String CLOSED = ".";
    public static final String STEPPEDMINE = "X";
    public static final String SIGNEDASMINE = "*";
    public static final String OPENEDEMPTY = "/";
    public static Integer minesNumber = 10;
    public static int countMinesSigns = 0;
    public static Mine[] mines;
    public static final Cell[][] cells = new Cell[Constants.SIZE][Constants.SIZE];
    public static boolean PLAY = true;
    public static boolean notInitMine = true;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
}
