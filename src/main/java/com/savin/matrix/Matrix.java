package com.savin.matrix;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

/**
 * This class is a matrix implementation
 *
 * @author Mikhail Savin
 */
public class Matrix {
    private static final Logger LOG = LogManager.getLogger();

    /**
     * Default matrix dimension
     */
    private static final int DEFAULT_DIMENSION = 3;

    /**
     * Matrix body with the data
     */
    private int[][] matrixBody;

    /**
     * Number of matrix rows
     */
    private int rows;

    /**
     * Number of matrix columns
     */
    private int columns;

    /**
     * Creates a new matrix with the default dimension
     */
    public Matrix() {
        matrixBody = new int[DEFAULT_DIMENSION][DEFAULT_DIMENSION];
        columns = DEFAULT_DIMENSION;
        rows = DEFAULT_DIMENSION;
    }

    /**
     * Creates a new matrix with the n x n dimension
     *
     * @param n given number of rows and columns
     */
    public Matrix(int n) {
        matrixBody = new int[n][n];
        columns = n;
        rows = n;
    }

    /**
     * Creates a new matrix with the given dimension (m x n)
     *
     * @param n given number of columns
     * @param m given number of rows
     */
    public Matrix(int n, int m) {
        matrixBody = new int[n][m];
        columns = n;
        rows = m;
    }

    /**
     * @return body of the matrix
     */
    public int[][] getMatrixBody() {
        return matrixBody;
    }

    /**
     * @param matrixBody matrix body to set
     */
    public void setMatrixBody(int[][] matrixBody) {
        this.matrixBody = matrixBody;
    }

    /**
     * @return number of rows in a matrix
     */
    public int getRows() {
        return rows;
    }

    /**
     * @param rows the number of rows in a matrix to set
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * @return number of columns in a matrix
     */
    public int getColumns() {
        return columns;
    }

    /**
     * @param columns the number of columns in a matrix to set
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }

    /**
     * Displays the matrix in a convenient form
     */
    public void display() {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                System.out.print(matrixBody[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    /**
     * Fills the matrix with random numbers
     */
    public void random() {
        Random random = new Random();
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                matrixBody[i][j] = random.nextInt();
            }
        }
    }

    /**
     * Fills the matrix with random numbers that are less than the given bound number
     *
     * @param border upper bound for generated numbers
     */
    public void random(int border) {
        Random random = new Random();
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                matrixBody[i][j] = random.nextInt(border);
            }
        }
    }

    /**
     * Multiplies current matrix on the given one.
     * It is one-threaded multiplication
     *
     * @param matrix matrix to multiply with
     * @return multiplication result as a new matrix
     * @throws MatrixException if a critical error occurred during matrix operations
     */
    public Matrix multiply(Matrix matrix) throws MatrixException {
        Matrix resultingMatrix = new Matrix(rows, matrix.getColumns());
        long startTime = System.nanoTime();

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
        long endTime = System.nanoTime();
        LOG.info("Multiplication took " + (endTime - startTime) / 1000000 + " milliseconds");

        return resultingMatrix;
    }
}
