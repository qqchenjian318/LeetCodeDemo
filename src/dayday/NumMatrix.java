package dayday;

public class NumMatrix {
    private int[][] matrix;
    private int[][] sum;

    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        int x = matrix.length;
        int y = matrix[0].length;
        sum = new int[x + 1][y + 1];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                sum[i + 1][j + 1] = sum[i][j + 1] + sum[i + 1][j] - sum[i][j] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        System.out.println("sumRegion sum[row2][col2]=" + sum[row2][col2] +
                ",sum[row2][col1]=" + sum[row2][col1] + ",sum[row1][col2]=" + sum[row1][col2] + ",sum[row1][col1]=" + sum[row1][col1]);
        return sum[row2+1][col2+1] - sum[row2+1][col1] - sum[row1][col2+1] + sum[row1][col1];
    }
}
