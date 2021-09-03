package prgrms.weekly.w3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class W3 {
    // static final int[][] game_board = {{1, 1, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 0, 1}, {1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 1, 0}, {0, 1, 1, 1, 0, 0}};
    // static final int[][] table = {{1, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 1, 1}, {0, 0, 1, 0, 0, 0}, {1, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 0}};

    static final int[][] game_board = {{0,0,0},{1,1,0},{1,1,1}};
    static final int[][] table = {{1,1,1},{1,0,0},{0,0,0}};

    public static void main(String[] args) {
        int answer = new Solution().solution(game_board, table);
        System.out.println(answer);
    }
}

/**
 * 0. Init
 * - int totalHole;
 * - List<Block> holes;
 * - List<Block> blocks;
 * <p>
 * 1.
 */
class Solution {
    private int totalHolePoints = 0;
    private int boardLength;

    private int[][] visited;
    private int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private int minX, minY, maxX, maxY;

    private List<Block> holes = new ArrayList<>();
    private List<Block> blocks = new ArrayList<>();

    public int solution(int[][] game_board, int[][] table) {
        int answer = -1;
        // init
        boardLength = game_board.length;

        // find hole
        visited = new int[boardLength][boardLength];
        findHoles(game_board);

        // find block
        visited = new int[boardLength][boardLength];
        findBlocks(table);

        // matching holes & blocks
        int sumOfMatchingPoint = MatchingAllBlockAndHole();

        // 정리
        answer = sumOfMatchingPoint;

        return answer;
    }

    private int getSumOfPoint() {
        Optional<Integer> sum = holes.stream().map(block -> block.point).reduce(Integer::sum);
        return sum.get();
    }

    public void findHoles(int[][] game_board) {
        int cnt = 1;
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (game_board[i][j] == 0 && visited[i][j] == 0) // hole & not visited?
                {
                    // min x, min y, max x, max y
                    minY = maxY = i;
                    minX = maxX = j;

                    dfs(cnt, i, j, game_board, 0);
                    holes.add(createBlock(cnt, minY, minX));

                    cnt++;
                }
            }
        }
    }

    public Block createBlock(int cnt, int y, int x) {
        int yLength = maxY - minY + 1;
        int xLength = maxX - minX + 1;

        boolean[][] block = new boolean[yLength][xLength];
        int point = 0;

        for (int i = 0; i < yLength; i++) {
            for (int j = 0; j < xLength; j++) {
                if (visited[y + i][x + j] == cnt) {
                    block[i][j] = true;
                    point++;
                }

            }
        }


        return new Block(block, point);
    }

    public void dfs(int cnt, int i, int j, int[][] game_board, int correct) {
        visited[i][j] = cnt;
        if (correct == 0)
            totalHolePoints++;
        minY = Math.min(minY, i);
        minX = Math.min(minX, j);
        maxY = Math.max(maxY, i);
        maxX = Math.max(maxX, j);

        for (int d = 0; d < direction.length; d++) { // 기저 조건
            int nxtI = i + direction[d][0];
            int nxtJ = j + direction[d][1];

            if (nxtI < 0 || nxtI >= boardLength || nxtJ < 0 || nxtJ >= boardLength)
                continue;
            if (game_board[nxtI][nxtJ] != correct) // 벽
                continue;
            if (visited[nxtI][nxtJ] > 0) // 방문함
                continue;
            // visited[nxtI][nxtJ] = cnt;
            dfs(cnt, nxtI, nxtJ, game_board, correct);
        }
    }

    public void findBlocks(int[][] table) {
        int cnt = 1;
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (table[i][j] == 1 && visited[i][j] == 0) // hole & not visited?
                {
                    // min x, min y, max x, max y
                    minY = maxY = i;
                    minX = maxX = j;

                    dfs(cnt, i, j, table, 1);
                    blocks.add(createBlock(cnt, minY, minX));

                    cnt++;
                }
            }
        }
    }

    public int MatchingAllBlockAndHole() {
        int sumOfMatchingPoint = 0;
        boolean[] blockVisited = new boolean[blocks.size()];

        for (int i = 0; i < holes.size(); i++) {

            for (int j = 0; j < blocks.size(); j++) {
                if (blockVisited[j]) continue;

                if (holes.get(i).match(blocks.get(j))) {
                    blockVisited[j] = true;
                    sumOfMatchingPoint += blocks.get(j).point;
                    break;
                }
                // boolean isMatch = false;
                for (int k = 0; k < 3; k++) {
                    blocks.get(j).rotate();
                    if (holes.get(i).match(blocks.get(j))) {
                        blockVisited[j] = true;
                        sumOfMatchingPoint += blocks.get(j).point;
                        break;
                    }
                }
                if (blockVisited[j]) break;

            }
        }
        return sumOfMatchingPoint;
    }

}

class Block {
    public boolean[][] points;
    public final int point;

    public Block(boolean[][] points, int point) {
        this.points = points;
        this.point = point;
    }

    public boolean match(Block other) {
        int low = this.getRow();
        int cols = this.getCols();
        if (low == other.getRow() && cols == other.getCols()) {
            for (int i = 0; i < low; i++) {
                for (int j = 0; j < cols; j++) {
                    if (this.points[i][j] != other.points[i][j])
                        return false;
                }
            }
            return true;
        }

        return false;
    }

    public void rotate() { // 90도 회전
        int newCols = this.getRow();
        int newRow = this.getCols();

        boolean[][] newPoints = new boolean[newRow][newCols];
        for (int i = 0; i < newCols; i++) {
            for (int j = 0; j < newRow; j++) {
                newPoints[j][newCols - i - 1] = this.points[i][j];
            }
        }
        this.points = newPoints;
    }

    public int getRow() {
        return points.length;
    }

    public int getCols() {
        return points[0].length;
    }
}