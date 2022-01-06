import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class DayFive extends AbstractDay{

    public DayFive(){
        super(5);
    }
    @Override
    public Object getOutput1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs/input5.txt"));
        String line = br.readLine();
        int[][] field = new int[1000][1000];
        while(line != null){
            String[] pairs = line.split(" -> ");
            int[] l1 = Arrays.stream(pairs[0].split(",")).mapToInt(Integer::parseInt).toArray();
            int[] l2 = Arrays.stream(pairs[1].split(",")).mapToInt(Integer::parseInt).toArray();
            if(l1[0] > l2[0] || l1[1] > l2[1]){
                int[] temp = {l1[0], l1[1]};
                l1 = new int[]{l2[0], l2[1]};
                l2 = temp;
            }

            line = br.readLine();
            if(!(l1[0] == l2[0] || l2[1] == l1[1])) {continue;}

            for(int  x = l1[0]; x <= l2[0]; x++){
                for(int y = l1[1]; y <= l2[1]; y++){
                    field[x][y]++;
                }
            }
        }

        int overlaps = 0;

        for(int[] row : field){
            for(int spot : row){
                if(spot > 1){
                    overlaps++;
                }
            }
        }
        return overlaps;
    }

    @Override
    public Object getOutput2() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs/input5.txt"));
        String line = br.readLine();
        int[][] field = new int[1000][1000];
        while(line != null){
            String[] pairs = line.split(" -> ");
            int[] l1 = Arrays.stream(pairs[0].split(",")).mapToInt(Integer::parseInt).toArray();
            int[] l2 = Arrays.stream(pairs[1].split(",")).mapToInt(Integer::parseInt).toArray();
            if(l1[0] > l2[0]  || (l1[0] == l2[0] && l1[1] > l2[1])){
                int[] temp = {l1[0], l1[1]};
                l1 = new int[]{l2[0], l2[1]};
                l2 = temp;
            }

            double slope;

            if(l1[0]-l2[0] == 0)
                slope = Integer.MAX_VALUE;
            else
                slope = ((double)(l1[1]- l2[1]))/(l1[0]-l2[0]);
            double yInt = l1[1] - slope * l1[0];
            line = br.readLine();

            if(!(Math.abs(slope) == 1 || slope == 0 || slope == Integer.MAX_VALUE)) continue;
            if(slope == Integer.MAX_VALUE){
                for(int i = l1[1]; i <= l2[1]; i++){
                    field[l1[0]][i]++;
                }
                continue;
            }

            for(int i = l1[0]; i <= l2[0]; i++){
                field[i][(int)(slope * i + yInt)]++;
            }
        }

        int overlaps = 0;

        for(int[] row : field){
            for(int spot : row){
                if(spot > 1){
                    overlaps++;
                }
            }
        }
        return overlaps;
    }
}
