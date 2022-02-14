import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DayFourteen extends AbstractDay{
    String input;
    HashMap<String, String> rules;
    public DayFourteen() throws IOException {
        super(14);
        BufferedReader br = new BufferedReader(new FileReader("inputs/input14.txt"));
        input = br.readLine();
        br.readLine();
        String text = br.readLine();
        rules = new HashMap<>();
        while(text != null){
            String[] ins = text.split(" -> ");
            rules.put(ins[0], ins[0].charAt(0) + ins[1] + ins[0].charAt(1));
            text = br.readLine();
        }
    }

    @Override
    public Object getOutput1() throws IOException {

        for(int steps = 0; steps < 10; steps++) {
            ArrayList<String> pairs = new ArrayList<>();
            for (int i = 0; i < input.length() - 1; i++) {
                String pair = input.substring(i, i + 2);
                pairs.add(pair);
            }
            char last = input.charAt(input.length()-1);
            for (int i = 0; i < pairs.size(); i++) {
                if (rules.containsKey(pairs.get(i))) {
                    pairs.set(i, rules.get(pairs.get(i)));
                }
            }
            StringBuilder sb = new StringBuilder();
            for(String pair : pairs){
                sb.append(pair, 0, pair.length()-1);
            }
            sb.append(last);
            input = sb.toString();
        }
        int[] alph = new int['Z'-'A'];
        for(char c : input.toCharArray()){
            alph[c - 'A']++;
        }
        int max = 0;
        int min = Integer.MAX_VALUE;
        for(int num : alph){
            if(num > max)
                max = num;
            if(min > num && num != 0)
                min = num;
        }
        System.out.println(input);
        System.out.println(Arrays.toString(alph));
        return max - min;
    }

    @Override
    public Object getOutput2() throws IOException {
        ArrayList<String> pairs = new ArrayList<>();
        for (int i = 0; i < input.length() - 1; i++) {
            String pair = input.substring(i, i + 2);
            pairs.add(pair);
        }
        long[] alph = new long['Z'-'A'];
        for(String pair : pairs){
            // This is gonna suck, essentially use a recursive function that expands each one
            // 40 steps and pases back up the long array.
        }
        return null;
    }
}
