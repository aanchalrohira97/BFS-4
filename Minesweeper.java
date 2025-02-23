// BFS Solution 
// tc: O(m*n) sc: O(m*n)

class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int rows = board.length, cols = board[0].length;
        int x = click[0], y = click[1];

        // If the clicked cell is a mine, game over
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }

        // Directions array for 8 possible adjacent positions
        int[][] directions = {
                { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 },
                { 1, -1 }, { 1, 0 }, { 1, 1 }
        };

        // Queue for BFS
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { x, y });

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];

            // Count adjacent mines
            int mineCount = 0;
            for (int[] dir : directions) {
                int nr = r + dir[0], nc = c + dir[1];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && board[nr][nc] == 'M') {
                    mineCount++;
                }
            }

            if (mineCount > 0) {
                board[r][c] = (char) ('0' + mineCount);
            } else {
                board[r][c] = 'B'; // No adjacent mines

                // Add neighbors to the queue if they are still 'E'
                for (int[] dir : directions) {
                    int nr = r + dir[0], nc = c + dir[1];
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && board[nr][nc] == 'E') {
                        board[nr][nc] = 'B'; // Mark visited before adding to queue
                        queue.offer(new int[] { nr, nc });
                    }
                }
            }
        }

        return board;
    }
}
/////////////////////////////

// dfs solution -> same tc and sc

public class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int rows = board.length, cols = board[0].length;
        int x = click[0], y = click[1];

        // If clicked on a mine, game over
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }

        // Directions array for 8 possible moves
        int[][] directions = {
                { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 },
                { 1, -1 }, { 1, 0 }, { 1, 1 }
        };

        dfs(board, x, y, directions, rows, cols);
        return board;
    }

    private void dfs(char[][] board, int r, int c, int[][] directions, int rows, int cols) {
        if (board[r][c] != 'E')
            return;

        // Count adjacent mines
        int mineCount = 0;
        for (int[] dir : directions) {
            int nr = r + dir[0], nc = c + dir[1];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && board[nr][nc] == 'M') {
                mineCount++;
            }
        }

        if (mineCount > 0) {
            board[r][c] = (char) ('0' + mineCount);
        } else {
            board[r][c] = 'B';

            // Recursively reveal neighbors if they are still 'E'
            for (int[] dir : directions) {
                int nr = r + dir[0], nc = c + dir[1];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && board[nr][nc] == 'E') {
                    dfs(board, nr, nc, directions, rows, cols);
                }
            }
        }
    }
}
