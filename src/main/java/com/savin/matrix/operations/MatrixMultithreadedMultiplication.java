package com.savin.matrix.operations;

import com.savin.matrix.Matrix;
import com.savin.matrix.MatrixException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This utility class allows to multiply two matrices using multiple threads.
 * To keep mathematical notation, matrices are denoted as "A" and "B"
 *
 * @author Mikhail Savin
 */
public class MatrixMultithreadedMultiplication implements Runnable {
    private static final Logger LOG = LogManager.getLogger();

    /**
     * First matrix
     */
    private Matrix A;

    /**
     * Second matrix
     */
    private Matrix B;

    /**
     * Current thread number
     */
    private final int threadNumber;

    /**
     * Resulting matrix (it contains result of the multiplication)
     */
    private Matrix resultingMatrix;

    /**
     * Creates MatrixMultithreadedMultiplication instance with the given matrices A, B,
     * resulting matrix and current thread number
     *
     * @param A first matrix
     * @param B second matrix
     * @param resultingMatrix matrix with the result of multiplication
     * @param threadNumber current thread
     */
    public MatrixMultithreadedMultiplication(Matrix A, Matrix B, Matrix resultingMatrix, int threadNumber) {
        this.A = A;
        this.B = B;
        this.resultingMatrix = resultingMatrix;
        this.threadNumber = threadNumber;
    }

    /**
     * @return first matrix
     */
    public Matrix getA() {
        return A;
    }

    /**
     * @param a first matrix to set
     */
    public void setA(Matrix a) {
        A = a;
    }

    /**
     * @return second matrix
     */
    public Matrix getB() {
        return B;
    }

    /**
     * @param b second matrix to set
     */
    public void setB(Matrix b) {
        B = b;
    }

    /**
     * Method for threads to run
     */
    @Override
    public void run() {
        if (A.getColumns() == B.getRows()) {
            for (int i = 0; i < A.getColumns(); i++) {
                resultingMatrix.getMatrixBody()[threadNumber][i] = 0;
                for (int j = 0; j < B.getRows(); j++) {
                    resultingMatrix.getMatrixBody()[threadNumber][i] +=
                            A.getMatrixBody()[threadNumber][j] * B.getMatrixBody()[j][i];
                }
            }
        } else {
            LOG.error(new MatrixException("Cannot multiply because the number of columns in the first matrix " +
                    "doesn't equal the number of rows in the second"));
        }
    }
}
