/**
 * Created by Lyle on 12/22/2016.
 */
public class Matrix {
    int nRows;
    int nCols;
    int[][] matrix;

    Matrix(int nRows, int nCols) {
        this.nRows = nRows;
        this.nCols = nCols;
        matrix = new int[nRows][nCols];
    }

    void setElementAt(int x, int y, int value) {
        if (value == 0 || value == 1) {
            matrix [x][y] = value;
        }
    }

    int getNumRows() {
        return nRows;
    }

    int getNumCols() {
        return nCols;
    }

    int getElementAt(int x, int y) {
        return matrix[x][y];
    }
}
