import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DayEleven extends AbstractDay{
    public DayEleven() {
        super(11);
    }

    @Override
    public Object getOutput1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs/input11.txt"));
        String line = br.readLine();
        int[][] field = new int[line.length()][line.length()];
        for(int i = 0; i < field.length; i++){
            char[] cArray = line.toCharArray();
            for(int x = 0; x < field.length; x++){
                field[i][x] = Integer.parseInt(cArray[x] + "");
            }
            line = br.readLine();
        }

        int count = 0;
        for(int i = 0; i < 100; i++){

            boolean[][] popped = new boolean[field.length][field.length];
            for(int x = 0; x < field.length; x++){
                for(int y = 0; y < field.length; y++){
                   count += increment(field, popped, x, y);
                }
            }

            for(int x = 0; x < field.length; x++){
                for(int y= 0; y < field.length; y++){
                    if(popped[x][y]) field[x][y] = 0;
                }
            }
        }

        return count;
    }

    @Override
    public Object getOutput2() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs/input11.txt"));
        String line = br.readLine();
        int[][] field = new int[line.length()][line.length()];
        for(int i = 0; i < field.length; i++){
            char[] cArray = line.toCharArray();
            for(int x = 0; x < field.length; x++){
                field[i][x] = Integer.parseInt(cArray[x] + "");
            }
            line = br.readLine();
        }

        long i = 0;
        while(true){
            int count = 0;
            boolean[][] popped = new boolean[field.length][field.length];
            for(int x = 0; x < field.length; x++){
                for(int y = 0; y < field.length; y++){
                    increment(field, popped, x, y);
                }
            }

            for(int x = 0; x < field.length; x++){
                for(int y= 0; y < field.length; y++){
                    if(popped[x][y]) {
                        field[x][y] = 0;
                        count++;
                    }
                }
            }
            if(count == field.length * field.length){
                return i +1;
            }
            i++;
        }

    }

    public int increment(int[][] field, boolean[][] popped, int x, int y){
        if(x  < 0 || y < 0 || x >= field.length || y >= field.length) return 0;

        int count = 0;
        field[x][y]++;
        if(field[x][y] > 9 && !popped[x][y]){
            popped[x][y] = true;
            count++;
            for(int i = -1; i < 2; i++){
                for(int j = -1; j < 2; j++){
                    if(!(i == 0 && j == 0)){
                        count += increment(field, popped, x + i, y + j);
                    }
                }
            }
        }
        return count;
    }
}
