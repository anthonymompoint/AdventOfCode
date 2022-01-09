import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class DaySeven extends AbstractDay{
    public DaySeven() {
        super(7);
    }

    @Override
    public Object getOutput1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs/input7.txt"));
        int[] values = Arrays.stream(br.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int[] distance = new int[values.length];

        for(int i = 0; i < values.length; i++){
            for (int value : values) {
                distance[i] += Math.abs(i - value);
            }
        }
        return Arrays.stream(distance).min().orElse(0);
    }

    @Override
    public Object getOutput2() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("inputs/input7.txt"));
        int[] values = Arrays.stream(br.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int[] distance = new int[values.length];

        for(int i = 0; i < values.length; i++){
            for (int value : values) {
                int dist = Math.abs(i - value);
                distance[i] += (dist * (dist + 1)) / 2;
            }
        }
        return Arrays.stream(distance).min().orElse(0);
    }
}
