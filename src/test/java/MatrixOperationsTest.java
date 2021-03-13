import com.savin.matrix.Matrix;
import com.savin.matrix.MatrixException;
import com.savin.matrix.operations.MatrixOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A class to test Matrix operations
 *
 * @author Mikhail Savin
 */
public class MatrixOperationsTest {
    private static final Logger LOG = LogManager.getLogger();

    /**
     * Tests matrix multiplication using single and multiple threads
     *
     * @throws MatrixException if a critical error occurred during matrix operations
     */
    @Test
    public void matrixMultiplication() throws MatrixException {
        Matrix A = new Matrix(2000);
        Matrix B = new Matrix(2000);
        A.random(10);
        B.random(10);

        long startTime = System.nanoTime();

        Matrix C = A.multiply(B);

        long endTime = System.nanoTime();
        LOG.info("Multiplication took " + (endTime - startTime) / 1000000 + " milliseconds");

        System.out.print("\n");

        MatrixOperations matrixOperations = new MatrixOperations();
        startTime = System.nanoTime();

        Matrix D = matrixOperations.multiply(A, B);

        endTime = System.nanoTime();
        LOG.info("Multithreaded Multiplication took " + (endTime - startTime) / 1000000 + " milliseconds");

        C.display();
        System.out.println("\n");
        D.display();
        assertArrayEquals(C.getMatrixBody(), D.getMatrixBody());
    }
}
