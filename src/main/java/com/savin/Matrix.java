package com.savin;

import java.util.Random;

public class Matrix {

    private static final int DEFAULT_DIMENSION = 3;

    private int[][] matrixBody;

    private int rows;

    private int columns;

    public Matrix() {
        matrixBody = new int[DEFAULT_DIMENSION][DEFAULT_DIMENSION];
        columns = DEFAULT_DIMENSION;
        rows = DEFAULT_DIMENSION;
    }

    public Matrix(int n) {
        matrixBody = new int[n][n];
        columns = n;
        rows = n;
    }

    public Matrix(int n, int m) {
        matrixBody = new int[n][m];
        columns = n;
        rows = m;
    }

    public int[][] getMatrixBody() {
        return matrixBody;
    }

    public void setMatrixBody(int[][] matrixBody) {
        this.matrixBody = matrixBody;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void display() {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                System.out.print(matrixBody[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    public void random() {
        Random random = new Random();
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                matrixBody[i][j] = random.nextInt();
            }
        }
    }

    public void random(int border) {
        Random random = new Random();
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                matrixBody[i][j] = random.nextInt(border);
            }
        }
    }

    public Matrix multiply(Matrix matrix) throws MatrixException {
        Matrix resultingMatrix = new Matrix(rows, matrix.getColumns());
        if (columns == matrix.getRows()) {
            for (int i = 0; i < columns; i++) {
                for (int j = 0; j < rows; j++) {
                    for (int k = 0; k < rows; k++) {
                        resultingMatrix.getMatrixBody()[i][j] += matrixBody[i][k] * matrix.getMatrixBody()[k][j];
                    }
                }
            }
        } else {
            throw new MatrixException("Cannot multiply because the number of columns in the first matrix " +
                    "doesn't equal the number of rows in the second");
        }
        return resultingMatrix;
    }
}
