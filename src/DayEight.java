import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayEight extends AbstractDay{
    public DayEight() {
        super(8);
    }

    @Override
    public Object getOutput1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs/input8.txt"));
        String text = br.readLine();
        int[] numberLengths = {0, 2, 0, 0, 4, 0, 0, 3, 7, 0};
        int matches = 0;
        while(text != null){
            String[] parts = text.split(" \\| ");
            String[] words = new String[numberLengths.length];
            for(String scrambled : parts[0].split(" ")){
                for(int i = 0; i < numberLengths.length; i++){
                    if(scrambled.length() == numberLengths[i]) {
                        words[i] = scrambled;
                        break;
                    }
                }
            }
            for(String word : parts[1].split(" ")){
                for (String s : words) {
                    if (s != null && word.length() == s.length()) {
                        matches++;
                        break;
                    }
                }
            }
            text= br.readLine();
        }

        return matches;
    }

    @Override
    public Object getOutput2() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs/input8.txt"));
        String text = br.readLine();
        int[] numberLengths = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
        return 0;
    }

}
