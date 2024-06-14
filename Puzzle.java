import java.util.Scanner;

public class Puzzle {
    private static final int SIZE = 4;
    private static final int[][] BOARD = new int[SIZE][SIZE];
    private static final int EMPTY_TILE = 0;

    public static void main(String[] args) {
        initializeBoard();
        shuffleBoard();
        displayBoard();

        Scanner scanner = new Scanner(System.in);
        while (!isSolved()) {
            moveTile(scanner);
            displayBoard();
        }

        System.out.println("Congratulations! You solved the puzzle!");
        scanner.close();
    }

    private static void initializeBoard() {
        int tile = 1;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                BOARD[row][col] = tile++;
            }
        }
        BOARD[SIZE - 1][SIZE - 1] = EMPTY_TILE;
    }

    private static void shuffleBoard() {
        for (int i = 0; i < SIZE * SIZE * 100; i++) {
            int randomRow = (int) (Math.random() * SIZE);
            int randomCol = (int) (Math.random() * SIZE);
            int emptyRow = findEmptyTileRow();
            int emptyCol = findEmptyTileCol();

            if ((Math.abs(randomRow - emptyRow) == 1 && randomCol == emptyCol) ||
                    (Math.abs(randomCol - emptyCol) == 1 && randomRow == emptyRow)) {
                swapTiles(randomRow, randomCol, emptyRow, emptyCol);
            }
        }
    }

    private static void moveTile(Scanner scanner) {
        System.out.print("Enter the tile number you want to move (1-15): ");
        int tileNumber = scanner.nextInt();

        int tileRow = findTileRow(tileNumber);
        int tileCol = findTileCol(tileNumber);
        int emptyRow = findEmptyTileRow();
        int emptyCol = findEmptyTileCol();

        if ((Math.abs(tileRow - emptyRow) == 1 && tileCol == emptyCol) ||
                (Math.abs(tileCol - emptyCol) == 1 && tileRow == emptyRow)) {
            swapTiles(tileRow, tileCol, emptyRow, emptyCol);
        } else {
            System.out.println("Invalid move! Please try again.");
        }
    }

    private static void swapTiles(int row1, int col1, int row2, int col2) {
        int temp = BOARD[row1][col1];
        BOARD[row1][col1] = BOARD[row2][col2];
        BOARD[row2][col2] = temp;
    }

    private static boolean isSolved() {
        int tile = 1;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (tile == SIZE * SIZE) {
                    tile = EMPTY_TILE;
                }
                if (BOARD[row][col] != tile++) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void displayBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (BOARD[row][col] == EMPTY_TILE) {
                    System.out.print("   ");
                } else {
                    System.out.print(BOARD[row][col] + "  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int findTileRow(int tileNumber) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (BOARD[row][col] == tileNumber) {
                    return row;
                }
            }
        }
        return -1;
    }

    private static int findTileCol(int tileNumber) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (BOARD[row][col] == tileNumber) {
                    return col;
                }
            }
        }
        return -1;
    }

    private static int findEmptyTileRow() {
        return findTileRow(EMPTY_TILE);
    }

    private static int findEmptyTileCol() {
        return findTileCol(EMPTY_TILE);
    }
}
