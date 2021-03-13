package com.savin.matrix.operations;

import com.savin.matrix.Matrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class is implements matrix operations. Currently it can only multiply two matrices
 *
 * @author Mikhail Savin
 */
public class MatrixOperations {

    /**
     * Multiplies two matrices using multiple threads
     *
     * @param A first matrix
     * @param B second matrix
     * @return result of the multiplication
     */
    public Matrix multiply(Matrix A, Matrix B) {
        Matrix resultingMatrix = new Matrix(A.getRows(), B.getColumns());

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < A.getRows(); i++) {
            executorService.execute(new MatrixMultithreadedMultiplication(A, B, resultingMatrix, i));
        }
        executorService.shutdown();

        return resultingMatrix;
    }
}
