import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DaySix extends AbstractDay{
    private final int[] input;
    public DaySix() throws IOException {
        super(6);
        BufferedReader br = new BufferedReader(new FileReader("inputs/input6.txt"));
        input = Arrays.stream(br.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
    }

    @Override
    public Object getOutput1() throws IOException {
        return countFish(80);
    }

    @Override
    public Object getOutput2() throws IOException {
        return countFish(256);
    }

    public long countFish(int days){
        int[] fish = input.clone();
        ArrayList<Long> counters = new ArrayList<>();
        for(int i =  0; i < 9; i++){counters.add(0L);}
        for(int fishy  : fish){
            counters.set(fishy, counters.get(fishy)+1);
        }
        for(int day = 0; day < days; day++){
            Long newFish = counters.get(0);
            counters.add(8, counters.remove(0));
            counters.set(6, counters.get(6) + newFish);
        }

        return counters.stream().mapToLong(a -> a).sum();
    }
}
