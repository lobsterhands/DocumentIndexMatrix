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
        matrix = new int [nRows][nCols];
    }
}
