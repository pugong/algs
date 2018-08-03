package org.yunxin.leetCodeDemo;

/**
 * Created by lpug on 2018/7/31.
 * Write a program to solve a Sudoku puzzle by filling the empty cells.

 A sudoku solution must satisfy all of the following rules:

 Each of the digits 1-9 must occur exactly once in each row.
 Each of the digits 1-9 must occur exactly once in each column.
 Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 Empty cells are indicated by the character '.'.


 A sudoku puzzle...


 ...and its solution numbers marked in red.

 Note:

 The given board contain only digits 1-9 and the character '.'.
 You may assume that the given Sudoku puzzle will have a single unique solution.
 The given board size is always 9x9.
 */
public class SudokuSolver {
    private int MAX_ROW = 8;
    private int MAX_COL = 8;
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    boolean[][] checkerBoard = null;
    char[][] solvedBoard = null;

    public void solveSudoku(char[][] board) {
        char[][] resultBoard = sudokuSolve(board);

        if(resultBoard !=null) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if(checkerBoard[i][j])
                        System.out.print(ANSI_RED + resultBoard[i][j]);
                    else
                        System.out.print(ANSI_BLACK + resultBoard[i][j]);
                    System.out.print(" ");
                }
            }
        }
    }

    private char[][] sudokuSolve(char[][] board) {
        solvedBoard = board;

        boolean solved = false;
        if (board == null || board.length < 9 || board[0].length < 9) {
            System.out.println("illegal Sudoku");
            return null;
        }

        checkerBoard = new boolean[solvedBoard.length][solvedBoard[0].length];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(solvedBoard[i][j]);
                System.out.print(" ");
                if (solvedBoard[i][j] == '.')
                    checkerBoard[i][j] = false;
                else
                    checkerBoard[i][j] = true;

            }
        }

        if (bruteSolve(0, 0)) {
            return solvedBoard;
        } else
            return null;
    }

    private boolean bruteSolve(int iRow, int iCol) {
        boolean result = false;
        int newRow = iCol == MAX_COL ? iRow + 1 : iRow;
        int newCol = iCol == MAX_COL ? 0 : iCol + 1;

        if (checkerBoard[iRow][iCol]) {
            return bruteSolve(newRow, newCol);
        } else {
            int value = Character.getNumericValue(solvedBoard[iRow][iCol]);
            if (solvedBoard[iRow][iCol] == '.')
                value = 1;
            else
                value += 1;

            if(value > 9)
                return  false;

            solvedBoard[iRow][iCol] = Character.highSurrogate(value);

            if (validateCol(iRow, iCol) && validateRow(iRow, iCol) && validateSquare(iRow, iCol)) {
                result = bruteSolve(newRow, newCol);
            }
        }
        return result;

    }

    private boolean validateSquare(int iRow, int iCol) {
        boolean result = true;
        // todo: validate the 3*3 square
//        for(int i=0; i<9; i++) {
//            if (i!= iCol && solvedBoard[iRow][i] == solvedBoard[iRow][iCol]) {
//                result = false;
//                break;
//            }
//        }
        return result;
    }

    private boolean validateRow(int iRow, int iCol) {
        boolean result = true;
        for(int i=0; i<9; i++) {
            if (i!= iCol && solvedBoard[iRow][i] == solvedBoard[iRow][iCol]) {
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean validateCol(int iRow, int iCol) {
        boolean result = true;
        for(int i=0; i<9; i++) {
            if (i!= iRow && solvedBoard[i][iCol] == solvedBoard[iRow][iCol]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
