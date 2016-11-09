import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MatrixTest {

    @Test
    public void mulDD(){
        DenseMatrix m1 = new DenseMatrix("D1.txt");
        DenseMatrix m2 = new DenseMatrix("D2.txt");
        DenseMatrix expected = new DenseMatrix("resultDD.txt");
        assertEquals(expected, m1.multDD(m2));
    }

    @Test
    public void mulSpSp(){
        SparseMatrix m1 = new SparseMatrix("S1.txt");
        SparseMatrix m2 = new SparseMatrix("S2.txt");
        SparseMatrix expected = new SparseMatrix("resultSpSp.txt");
        assertEquals(expected, m1.multSpSp(m2));
    }

    @Test
    public void mulDS(){
        DenseMatrix m1 = new DenseMatrix("D1.txt");
        SparseMatrix m2 = new SparseMatrix("S1.txt");
        SparseMatrix expected = new SparseMatrix("resultDS.txt");
        assertEquals(expected, m1.multDS(m2));
    }

    @Test
    public void mulSD(){
        SparseMatrix m1 = new SparseMatrix("S1.txt");
        DenseMatrix m2 = new DenseMatrix("D1.txt");
        SparseMatrix expected = new SparseMatrix("resultSD.txt");
        assertEquals(expected, m1.multSD(m2));
    }
}
