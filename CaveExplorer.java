public class CaveExplorer {
    private char[][] cave;
    private int startRow, startCol;

    public CaveExplorer() {
        cave = new char[][] {
            {'R','R','R','R','R','R'},
            {'R','.','.','S','R','R'},
            {'R','.','R','R','R','R'},
            {'R','.','M','R','R','R'},
            {'R','R','R','R','R','R'}
        };
        startRow = 1;
        startCol = 3;
    }

    public String toString() {
    StringBuilder sb = new StringBuilder();
    for (char[] row : cave) {
        for (char cell : row) {
            sb.append(cell);
        }
        sb.append('\n');
    }
    return sb.toString();
}
public boolean solve() {
    boolean[][] visited = new boolean[cave.length][cave[0].length];
    int row = startRow;
    int col = startCol;

    while (true) {
        visited[row][col] = true;
        if (cave[row][col] == 'M') return true;

        // Try moving in one of four directions (only one will work)
        if (isValid(row, col - 1, visited)) col--; // West
        else if (isValid(row, col + 1, visited)) col++; // East
        else if (isValid(row - 1, col, visited)) row--; // North
        else if (isValid(row + 1, col, visited)) row++; // South
        else break; // No valid moves
    }

    return false;
}

private boolean isValid(int r, int c, boolean[][] visited) {
    return r >= 0 && r < cave.length && c >= 0 && c < cave[0].length &&
           !visited[r][c] && (cave[r][c] == '.' || cave[r][c] == 'M');
}

}
