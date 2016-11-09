import java.io.*;
import java.util.Scanner;

public abstract class Matrix {


    public int rows;
    public int cols;

    public abstract double getEl(int i, int j);

    public int cRow(String fileName){
        int n = 0;
        try{
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
            while (lineNumberReader.readLine() != null){
                n++;
            }
            lineNumberReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return n;
    }

    public int cCol(String fileName){
        int n = 0;
        try{
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            Scanner sc = new Scanner(fileReader);
            String reader;
            if (sc.hasNextLine()) {
                reader = sc.nextLine();
                n = reader.split(" ").length;
            }
            sc.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return n;
    }

    public void printMatrix(Matrix matrix, String fileName){
        try{
            File file = new File(fileName);
            FileWriter out = new FileWriter(file);
            for (int i = 0; i < matrix.rows; i++){
                for (int j = 0; j < matrix.cols; j++) {
                    out.write(matrix.getEl(i, j) + " ");
                }
                out.write("\n");
            }
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public boolean equals(Object o) {
        Matrix other = (Matrix)o;
        boolean eq = true;
        if (this.rows == other.cols && this.cols == other.cols) {
            for(int i = 0; i < this.rows; i++){
                for(int j = 0; j < this.cols; j++) {
                    if (this.getEl(i, j) != other.getEl(i, j))
                        eq = false;
                }
            }
        }
        else {
            eq = false;
        }
        return  eq;
    }
}
