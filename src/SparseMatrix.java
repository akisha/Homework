import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SparseMatrix extends Matrix {

    String inF;
    Map<Point, Double> matrix = new HashMap<Point, Double>();

    SparseMatrix(String fileName) {
        inF = fileName;
        rows = cRow(fileName);
        cols = cCol(fileName);
        try {
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    double val = sc.nextDouble();
                    Point place = new Point(i, j);
                    matrix.put(place, val);
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    SparseMatrix(int m, int n) {
        rows = m;
        cols = n;
    }

    public double getEl(int i, int j) {
        double val = 0;
        Point place = new Point(i, j);
        if (matrix.containsKey(place)) val = matrix.get(place);
        return val;
    }

    public SparseMatrix multSpSp(SparseMatrix other) {
        if (cols != other.rows) throw new IllegalArgumentException("Dimensions disagree");
        SparseMatrix result = new SparseMatrix(rows, other.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                double res = 0;
                for (int k = 0; k < this.cols; k++) {
                    Point place = new Point(i, j);
                    res += this.getEl(i, k) * other.getEl(k, j);
                    result.matrix.put(place, res);
                }
            }
        }
        printMatrix(result, "mulSpSp" + inF.substring(0, inF.length()- 4) + other.inF);
        return result;
    }

    public SparseMatrix multSD(DenseMatrix other) {
        if (cols != other.rows) throw new IllegalArgumentException("Dimensions disagree");
        SparseMatrix result = new SparseMatrix(rows, other.cols);
        for(int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                double res = 0;
                for (int k = 0; k < this.cols; k++) {
                    Point place = new Point(i, j);
                    res += this.getEl(i, k) * other.getEl(k, j);
                    result.matrix.put(place, res);
                }
            }
        }
        printMatrix(result, "mulSD" + inF.substring(0, inF.length()- 4) + other.inF);
        return result;
    }
}