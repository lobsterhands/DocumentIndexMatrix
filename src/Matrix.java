/**
 * Created by Lyle on 12/22/2016.
 */
public class Matrix {
    int nRows;
    int nCols;
    boolean[][] matrix;

    Matrix(int nRows, int nCols) {
        this.nRows = nRows;
        this.nCols = nCols;
        matrix = new boolean[nRows][nCols];
    }

    void setElementAt(int x, int y, boolean value) {
        matrix [x][y] = value;
    }

    int getColNumber() {
        return nCols;
    }

    boolean getElementAt(int x, int y) {
        return matrix[x][y];
    }
}
