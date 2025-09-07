import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

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

    public CaveExplorer(String filename) {
    try {
        Scanner sc = new Scanner(new File(filename));
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        sc.nextLine(); // consume newline

        cave = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < cols; j++) {
                cave[i][j] = line.charAt(j);
                if (cave[i][j] == 'S') {
                    startRow = i;
                    startCol = j;
                }
            }
        }
        sc.close();
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + filename);
    }
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
    Stack<int[]> stack = new Stack<>();
    stack.push(new int[] {startRow, startCol});

    while (!stack.isEmpty()) {
        int[] current = stack.pop();
        int row = current[0];
        int col = current[1];

        if (!isValid(row, col, visited)) continue;
        visited[row][col] = true;

        if (cave[row][col] == 'M') return true;

        // Push neighbors in reverse priority (so West is processed first)
        stack.push(new int[] {row + 1, col}); // South
        stack.push(new int[] {row - 1, col}); // North
        stack.push(new int[] {row, col + 1}); // East
        stack.push(new int[] {row, col - 1}); // West
    }

    return false;
}


private boolean isValid(int r, int c, boolean[][] visited) {
    return r >= 0 && r < cave.length && c >= 0 && c < cave[0].length &&
           !visited[r][c] && (cave[r][c] == '.' || cave[r][c] == 'M');
}

public String getPath() {
    boolean[][] visited = new boolean[cave.length][cave[0].length];
    int row = startRow;
    int col = startCol;
    StringBuilder path = new StringBuilder();

    while (true) {
        visited[row][col] = true;
        if (cave[row][col] == 'M') return path.toString();

        if (isValid(row, col - 1, visited)) {
            col--;
            path.append('w');
        } else if (isValid(row, col + 1, visited)) {
            col++;
            path.append('e');
        } else if (isValid(row - 1, col, visited)) {
            row--;
            path.append('n');
        } else if (isValid(row + 1, col, visited)) {
            row++;
            path.append('s');
        } else break;
    }

    return "";
}

public static void main(String[] args) {
    CaveExplorer cave1 = new CaveExplorer();
    System.out.println("Cave 1:");
    System.out.println(cave1.toString());
    System.out.println("Solved: " + cave1.solve());
    System.out.println("Path: " + cave1.getPath());

    CaveExplorer cave2 = new CaveExplorer("cave.txt");
    System.out.println("\nCave 2:");
    System.out.println(cave2.toString());
    System.out.println("Solved: " + cave2.solve());
    System.out.println("Path: " + cave2.getPath());
}

}
