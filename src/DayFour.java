import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DayFour extends AbstractDay{
    public DayFour() {
        super(4);
    }

    @Override
    public Object getOutput1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs/input4.txt"));
        int[] numbers = BingoBoard.getNumbers(br.readLine());
        ArrayList<BingoBoard> boards = new ArrayList<>();
        while(br.readLine() != null){
            int[][] board = new int[5][5];
            for(int i = 0; i < 5; i++){
                String str = br.readLine();
                board[i] = BingoBoard.getRow(str.trim());
            }
            boards.add(new BingoBoard(board));
        }

        for(int num : numbers){
            for(BingoBoard board : boards){
                if(board.markItem(num)){
                    int sum = 0;
                    for(int i = 0; i < 5; i++){
                        for(int x = 0; x < 5; x++){
                            if(!board.boolboard[i][x])
                                sum += board.board[i][x];
                        }
                    }
                    return sum * num;
                }
            }
        }

        return 0;
    }

    @Override
    public Object getOutput2() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs/input4.txt"));
        int[] numbers = BingoBoard.getNumbers(br.readLine());
        ArrayList<BingoBoard> boards = new ArrayList<>();
        while(br.readLine() != null){
            int[][] board = new int[5][5];
            for(int i = 0; i < 5; i++){
                String str = br.readLine();
                board[i] = BingoBoard.getRow(str.trim());
            }
            boards.add(new BingoBoard(board));
        }
        boolean[] winners = new boolean[boards.size()];
        BingoBoard lastWinner = null;
        int lastNumber = 0;
        for(int num : numbers){
            for(int i = 0; i < boards.size(); i++){
                if(boards.get(i).markItem(num) && !winners[i]){
                    lastNumber = num;
                    lastWinner = boards.get(i);
                    winners[i] = true;
                }
            }
            boolean flag = false;
            for(boolean won : winners){
                if(!won){
                    flag = true;
                }
            }
            if(!flag) break;
        }

        int sum = 0;


        for(int i = 0; i < 5; i++){
            for(int x = 0; x < 5; x++){
                if(!lastWinner.boolboard[i][x])
                    sum += lastWinner.board[i][x];
            }
        }

        return sum * lastNumber;
    }


    private static class BingoBoard{
        int[][] board;
        boolean[][] boolboard;

        public BingoBoard(int[][] board){
            this.board = board;
            boolboard = new boolean[board.length][board.length];
        }

        public BingoBoard(int[][] board, boolean[][] boolboard){
            this.board = board.clone();
            this.boolboard =  boolboard.clone();
        }
        public boolean markItem(int item){
            for(int i = 0; i < board.length; i++){
                for(int j = 0; j < board[i].length; j++){
                    if(board[i][j] == item){
                        boolboard[i][j] = true;
                        boolean horizonFlag = true;
                        for(int x = 0; x <  board.length; x++){
                            if (!boolboard[i][x]) {
                                horizonFlag = false;
                                break;
                            }
                        }
                        if(horizonFlag) return true;
                        boolean verticalFlag = true;
                        for(int x = 0; x <  board.length; x++){
                            if (!boolboard[x][j]) {
                                verticalFlag = false;
                                break;
                            }
                        }
                        return verticalFlag;
                    }
                }
            }
            return false;
        }

        public BingoBoard copy(){
            return new BingoBoard(board, boolboard);
        }

        public static int[] getRow(String str){
            return Arrays.stream(str.split("\\s+")).mapToInt(Integer::parseInt).toArray();
        }

        public static int[] getNumbers(String str){
            return Arrays.stream(str.split(",")).mapToInt(Integer::parseInt).toArray();
        }
    }
}
