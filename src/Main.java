public class Main {
    public static void main(String[] args) {
        DenseMatrix d1 = new DenseMatrix("D1.txt");
        DenseMatrix d2 = new DenseMatrix("D2.txt");
        SparseMatrix s1 = new SparseMatrix("S1.txt");
        SparseMatrix s2 = new SparseMatrix("S2.txt");
        d1.multDD(d2);
        s1.multSpSp(s2);
        s1.multSD(d1);
        d1.multDS(s1);
    }
}