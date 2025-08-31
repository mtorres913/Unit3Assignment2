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
}
