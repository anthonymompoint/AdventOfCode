import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DaySix extends AbstractDay{
    public DaySix(){
        super(6);
    }

    @Override
    public Object getOutput1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs/input6.txt"));
        int[] fish = Arrays.stream(br.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int days = 80;
        ArrayList<Integer> counters = new ArrayList<>(Collections.nCopies(9, 0));
        for(int fishy  : fish){
            counters.set(fishy, counters.get(fishy)+1);
        }
        for(int day = 0; day < days; day++){
            int newFish = counters.get(0);
            counters.add(8, counters.remove(0));
            counters.set(6, counters.get(6) + newFish);
        }

        int counter = 0;
        for(int num : counters){
            counter += num;
        }
        return counter;
    }

    @Override
    public Object getOutput2() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs/input6.txt"));
        int[] fish = Arrays.stream(br.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int days = 256;
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

        long counter = 0;
        for(Long num : counters){
            counter += num;
        }
        return counter;
    }
}
