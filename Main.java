package minesweeper;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        initCells();
        drawFiled();
        playGame();
    }

    private static void playGame() {
        while (Constants.PLAY) {
            inputCoordinate();
            if (!Constants.PLAY) {
                return;
            }
            drawFiled();
        }
    }

    private static void inputCoordinate() {
        System.out.print("Set/unset mines marks or claim a cell as free: ");
        String inputLine = readLine();
        System.out.print("\n");
        String[] command = inputLine.split(" ");
        if (Constants.notInitMine && command[2].equals("free")) {
            initMines(command);
        }
        switch (command[2]) {
            case "free":
                free(command);
                break;
            case "mine":
                mine(command);
                break;
            default:
                break;
        }
    }

    private static void free(String[] command) {
        int width = Integer.parseInt(command[0]) - 1;
        int height = Integer.parseInt(command[1]) - 1;
        Cell freedCell = Constants.cells[height][width];
        if (freedCell.isMine()) {
            Constants.PLAY = false;
            freedCell.setSign(Constants.STEPPEDMINE);
            System.out.println("You stepped on a mine and failed!");
            drawFiled();
        } else if (freedCell.getNumberOfNearMine() != 0) {
            freedCell.setSign(String.valueOf(freedCell.getNumberOfNearMine()));
        } else if (freedCell.getNumberOfNearMine() == 0) {
            traversalWithoutRecursion(height, width);
            checkOnWin();
        }
    }

    private static void traversalWithoutRecursion(int height, int width) {
        boolean changed = true;
        Set<Cell> setCells = new HashSet<>();
        setCells.add(Constants.cells[height][width]);
        Set<Cell> buffer = new HashSet<>();
        int size;
        int sizeAfter;
        while (changed) {
            changed = false;
            size = setCells.size();
            for (Cell nextCell : setCells) {
                buffer.addAll(getEmptyNeighborsAndSign(nextCell.getHeight(), nextCell.getWidth()));
            }
            setCells.addAll(buffer);
            buffer.clear();
            sizeAfter = setCells.size();
            if (size - sizeAfter != 0) {
                changed = true;
            }
        }
    }

    private static List<Cell> getEmptyNeighborsAndSign(int height, int width) {
        Cell currCell = Constants.cells[height][width];
        Cell cell;
        List<Cell> emptyNeighborhoodsCells = new ArrayList<>();
        if (!currCell.isMine()) {
            if (currCell.getNumberOfNearMine() != 0) {
                currCell.setSign(String.valueOf(currCell.getNumberOfNearMine()));
            } else if (currCell.getNumberOfNearMine() == 0) {
                currCell.setSign(Constants.OPENEDEMPTY);
                if (height >= 1 && width >= 1) {
                    cell = Constants.cells[height - 1][width - 1];
                    if (
                            !cell.isMine()
                                    && cell.getNumberOfNearMine() == 0
                    ) {
                        cell.setSign(Constants.OPENEDEMPTY);
                        emptyNeighborhoodsCells.add(cell);
                    } else if (
                            !cell.isMine()
                                    && cell.getNumberOfNearMine() != 0
                    ) {
                        cell.setSign(String.valueOf(cell.getNumberOfNearMine()));
                    }
                }
                if (height >= 1) {
                    cell = Constants.cells[height - 1][width];
                    if (
                            !cell.isMine()
                                    && cell.getNumberOfNearMine() == 0
                    ) {
                        cell.setSign(Constants.OPENEDEMPTY);
                        emptyNeighborhoodsCells.add(cell);
                    } else if (
                            !cell.isMine()
                                    && cell.getNumberOfNearMine() != 0
                    ) {
                        cell.setSign(String.valueOf(cell.getNumberOfNearMine()));
                    }
                }
                if (height >= 1 && width <= 7) {
                    cell = Constants.cells[height - 1][width + 1];
                    if (
                            !cell.isMine()
                                    && cell.getNumberOfNearMine() == 0
                    ) {
                        cell.setSign(Constants.OPENEDEMPTY);
                        emptyNeighborhoodsCells.add(cell);
                    } else if (
                            !cell.isMine()
                                    && cell.getNumberOfNearMine() != 0
                    ) {
                        cell.setSign(String.valueOf(cell.getNumberOfNearMine()));
                    }
                }
                if (width <= 7) {
                    cell = Constants.cells[height][width + 1];
                    if (
                            !cell.isMine()
                                    && cell.getNumberOfNearMine() == 0
                    ) {
                        cell.setSign(Constants.OPENEDEMPTY);
                        emptyNeighborhoodsCells.add(cell);
                    } else if (
                            !cell.isMine()
                                    && cell.getNumberOfNearMine() != 0
                    ) {
                        cell.setSign(String.valueOf(cell.getNumberOfNearMine()));
                    }
                }
                if (height <= 7 && width <= 7) {
                    cell = Constants.cells[height + 1][width + 1];
                    if (
                            !cell.isMine()
                                    && cell.getNumberOfNearMine() == 0
                    ) {
                        cell.setSign(Constants.OPENEDEMPTY);
                        emptyNeighborhoodsCells.add(cell);
                    } else if (
                            !cell.isMine()
                                    && cell.getNumberOfNearMine() != 0
                    ) {
                        cell.setSign(String.valueOf(cell.getNumberOfNearMine()));
                    }
                }
                if (height <= 7) {
                    cell = Constants.cells[height + 1][width];
                    if (
                            !cell.isMine()
                                    && cell.getNumberOfNearMine() == 0
                    ) {
                        cell.setSign(Constants.OPENEDEMPTY);
                        emptyNeighborhoodsCells.add(cell);
                    } else if (
                            !cell.isMine()
                                    && cell.getNumberOfNearMine() != 0
                    ) {
                        cell.setSign(String.valueOf(cell.getNumberOfNearMine()));
                    }
                }
                if (height <= 7 && width >= 1) {
                    cell = Constants.cells[height + 1][width - 1];
                    if (
                            !cell.isMine()
                                    && cell.getNumberOfNearMine() == 0
                    ) {
                        cell.setSign(Constants.OPENEDEMPTY);
                        emptyNeighborhoodsCells.add(cell);
                    } else if (
                            !cell.isMine()
                                    && cell.getNumberOfNearMine() != 0
                    ) {
                        cell.setSign(String.valueOf(cell.getNumberOfNearMine()));
                    }
                }
                if (width >= 1) {
                    cell = Constants.cells[height][width - 1];
                    if (
                            !cell.isMine()
                                    && cell.getNumberOfNearMine() == 0
                    ) {
                        cell.setSign(Constants.OPENEDEMPTY);
                        emptyNeighborhoodsCells.add(cell);
                    } else if (
                            !cell.isMine()
                                    && cell.getNumberOfNearMine() != 0
                    ) {
                        cell.setSign(String.valueOf(cell.getNumberOfNearMine()));
                    }
                }
            }
        }
        return emptyNeighborhoodsCells;
    }

    private static void mine(String[] command) {
        int height = Integer.parseInt(command[1]) - 1;
        int width = Integer.parseInt(command[0]) - 1;
        Cell cell = Constants.cells[height][width];
        if (cell.getSign().equals(Constants.SIGNEDASMINE)) {
            cell.setSign(Constants.CLOSED);
            Constants.countMinesSigns--;
            checkOnWin();
        } else {
            cell.setSign(Constants.SIGNEDASMINE);
            Constants.countMinesSigns++;
            checkOnWin();
        }
    }

    private static void checkOnWin() {
        int rightSigned = 0;
        int opened = 0;
        for (Cell[] nextCells : Constants.cells) {
            for (Cell cell : nextCells) {
                if (cell.getSign().equals(Constants.SIGNEDASMINE) && cell.isMine()) {
                    rightSigned++;
                }
                if (!cell.isMine() && !cell.getSign().equals(Constants.CLOSED)) {
                    opened++;
                }
            }
        }
        if ((Constants.countMinesSigns == Constants.minesNumber && rightSigned == Constants.minesNumber)
                || opened == (Constants.SIZE * Constants.SIZE - Constants.minesNumber)
        ) {
            drawFiled();
            System.out.println("Congratulations! You found all the mines!");
            Constants.PLAY = false;
        }
    }

    private static void initMines(String[] command) {
        int curFreeHeight = Integer.parseInt(command[1]) - 1;
        int curFreeWidth = Integer.parseInt(command[0]) - 1;
        if (Constants.minesNumber > 50) {
            ArrayList<Mine> minesList = new ArrayList<>();
            int counter = 0;
            Cell cell;
            Mine mine;
            boolean isEven = true;
            int from;
            for (int i = 0; i < Constants.SIZE; i++) {
                from = (i + 1) % 2;
                for (int j = from; j < Constants.SIZE; j += 2) {
                    if (i == curFreeHeight && j == curFreeWidth) {
                        continue;
                    }
                    Constants.cells[i][j].setMine(true);
                    counter++;
                    if (counter == Constants.minesNumber) {
                        i = j = Constants.SIZE;
                    }
                }
            }
            if (counter < Constants.minesNumber) {
                for (int i = 0; i < Constants.SIZE; i++) {
                    from = (i + 2) % 2;
                    for (int j = from; j < Constants.SIZE; j += 2) {
                        if (i == curFreeHeight && j == curFreeWidth) {
                            continue;
                        }
                        Constants.cells[i][j].setMine(true);
                        counter++;
                        if (counter == Constants.minesNumber) {
                            i = j = Constants.SIZE;
                        }
                    }
                }
            }
        } else {
            Constants.mines = new Mine[Constants.minesNumber];
            Random random = new Random();
            int remindTheMines = 0;
            int height;
            int width;
            while (remindTheMines < Constants.minesNumber) {
                height = getRandomExclude(curFreeHeight);
                width = getRandomExclude(curFreeWidth);
                if (!Constants.cells[height][width].isMine()) {
                    Constants.cells[height][width].setMine(true);
                    remindTheMines++;
                }
            }
        }
        Constants.notInitMine = false;
        for (Cell[] row : Constants.cells) {
            for (Cell cell : row) {
                if (!cell.isMine()) {
                    checkAndSetNumberOfNearMines(cell);
                }
            }
        }
    }

    private static int getRandomExclude(int exculde) {
        Random random = new Random();
        int result = random.nextInt(Constants.SIZE);
        while (result == exculde) {
            result = random.nextInt(Constants.SIZE);
        }
        return result;
    }

    private static void initCells() {
        System.out.print("How many mines do you want on the field? ");
        Constants.minesNumber = Integer.parseInt(readLine());
        System.out.print("\n");
        for (int i = 0; i < Constants.SIZE; i++) {
            for (int j = 0; j < Constants.SIZE; j++) {
                Cell cell = new Cell(i, j);
                Constants.cells[i][j] = cell;
            }
        }
    }

    private static void checkAndSetNumberOfNearMines(Cell cell) {
        int count = 0;
        int currHeight = cell.getHeight();
        int currWidth = cell.getWidth();
        if (currHeight >= 1 && currWidth >= 1) {
            if (Constants.cells[currHeight - 1][currWidth - 1].isMine()) {
                count++;
            }
        }
        if (currHeight >= 1) {
            if (Constants.cells[currHeight - 1][currWidth].isMine()) {
                count++;
            }
        }
        if (currHeight >= 1 && currWidth <= 7) {
            if (Constants.cells[currHeight - 1][currWidth + 1].isMine()) {
                count++;
            }
        }
        if (currWidth <= 7) {
            if (Constants.cells[currHeight][currWidth + 1].isMine()) {
                count++;
            }
        }
        if (currHeight <= 7 && currWidth <= 7) {
            if (Constants.cells[currHeight + 1][currWidth + 1].isMine()) {
                count++;
            }
        }
        if (currHeight <= 7) {
            if (Constants.cells[currHeight + 1][currWidth].isMine()) {
                count++;
            }
        }
        if (currHeight <= 7 && currWidth >= 1) {
            if (Constants.cells[currHeight + 1][currWidth - 1].isMine()) {
                count++;
            }
        }
        if (currWidth >= 1) {
            if (Constants.cells[currHeight][currWidth - 1].isMine()) {
                count++;
            }
        }
        if (count != 0) {
            cell.setNumberOfNearMine(count);
        }
    }

    private static void drawFiled() {
        StringBuilder line = new StringBuilder();
        System.out.print(" |");
        for (int i = 1; i <= Constants.SIZE; i++) {
            System.out.print(i);
            line.append("-");
        }
        System.out.println("|");
        System.out.println("-|" + line + "|");
        for (int i = 0; i < Constants.SIZE; i++) {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < Constants.SIZE; j++) {
                System.out.print(Constants.cells[i][j].getSign());
            }
            System.out.println("|");
        }
        System.out.println("-|" + line + "|");
    }

    private static String readLine() {
        try {
            return Constants.reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
