public class Earth {
    public Cell[][] area;

    public void init(int line, int row, int numOfLiveCells) {
        area = new Cell[line][row];
        for (int i = 0, num = 0; i < line; i++) {
            for (int j = 0; j < row; j++) {
                Cell cell = new Cell();
                cell.setEdge(judgeEdge(i,j));
                if (!cell.isEdge() && num < numOfLiveCells) {
                    cell.revive();
                    num++;
                }
                area[i][j] = cell;
            }
        }

        relationshipInit();
    }

    private void relationshipInit() {
        int line = area.length;
        int row = area[0].length;
        for (int i = 1; i < line-1; i++) {
            for (int j = 1; j < row - 1; j++) {
                Cell[] neighbor = {area[i-1][j-1], area[i-1][j], area[i-1][j+1], area[i][j-1], area[i][j+1], area[i+1][j-1],area[i+1][j], area[i+1][j+1]};
                area[i][j].init(neighbor);
            }
        }
    }

    private boolean judgeEdge(int i, int j) {
        return i == 0 || i == area.length-1 || j == 0 || j == area[0].length-1;
    }

    public void run() {
        int line = area.length;
        int row = area[0].length;
        for (int i = 1; i < line-1; i++) {
            for (int j = 1; j < row - 1; j++) {
               area[i][j].renewal();
            }
        }
    }
}
