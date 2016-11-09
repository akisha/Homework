import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DenseMatrix extends Matrix{

    public String inF;
    public int rows;
    public int cols;
    double[][] matrix = new double[rows][cols];

    DenseMatrix (String fileName){
        inF = fileName;
        rows = cRow(fileName);
        cols = cCol(fileName);
        matrix = new double[rows][cols];
        try{
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            for (int i = 0; i < rows; i++){
                for (int j = 0; j < cols; j++){
                    matrix[i][j] = sc.nextDouble();
                }
            }
            sc.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    DenseMatrix (int m, int n){
        rows = m;
        cols = n;
        matrix = new double[rows][cols];
    }

    public double getEl(int i, int j){
        double el = matrix[i][j];
        return el;
    }

    public DenseMatrix multDD(DenseMatrix other){
        if (cols != other.rows) throw new IllegalArgumentException("Dimensions disagree");
        DenseMatrix result = new DenseMatrix(rows, other.cols);
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < other.cols; j++){
                for (int k = 0; k < cols; k++){
                    result.matrix[i][j] += matrix[i][k] * other.matrix[k][j];
                }
            }
        }
        //printMatrix(result, "mulDD" + inF.substring(0, inF.length()- 4) + other.inF);
        try{
            File file = new File("mulDD" + inF.substring(0, inF.length()- 4) + other.inF);
            FileWriter out = new FileWriter(file);
            for (int i = 0; i < result.rows; i++){
                for (int j = 0; j < result.cols; j++) {
                    out.write(result.getEl(i, j) + " ");
                }
                out.write("\n");
            }
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return result;
    }

    /*public SparseMatrix test(DenseMatrix other){
        SparseMatrix t = new SparseMatrix(2, 2);
        double d = 0;
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++){
                Point p = new Point(i,j);
                d = d + 1;
                t.matrix.put(p, d);
        }
        printMatrix(t, "FUCKU.txt");
        return t;
    }*/

    public SparseMatrix multDS(SparseMatrix other) {
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
        printMatrix(result, "mulDS" + inF.substring(0, inF.length()- 4) + other.inF);
        return result;
    }
}
