import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

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
        int total = 0;
        while(text != null){
            String[] parts = text.split(" \\| ");
            ArrayList<String> words = new ArrayList<>(Arrays.stream(parts[0].split(" ")).toList());
            words = (ArrayList<String>)words.stream().map(word -> {
                char[] arr = word.toCharArray();
                Arrays.sort(arr);
                return new String(arr);
            }).collect(Collectors.toList());
            String[] numbers = new String[10];
            // Find the easy numbers 1, 7, 4, 8
            for(int i = 0; i < words.size(); i++){
                switch (words.get(i).length()){
                    case 2 -> numbers[1] = words.remove(i);
                    case 3 -> numbers[7] = words.remove(i);
                    case 4 -> numbers[4] = words.remove(i);
                    case 7 -> numbers[8] = words.remove(i);
                    default -> i++;
                }
                i--;
            }

            // Solve for 9 using 4, 0 using 1, and 6 is what's left
            for(int i = 0; i < words.size(); i++){
                if(words.get(i).length() == 6) {
                    if (isSubset(words.get(i), numbers[4]))
                        numbers[9] = words.remove(i);
                    else if (isSubset(words.get(i), numbers[1]))
                        numbers[0] = words.remove(i);
                    else
                        numbers[6] = words.remove(i);
                    i--;
                }
            }

            // Same as above with 5 solved by 6, 3 using 1, and 2 being left
            for(int i = 0; i < words.size(); i++){
                if(words.get(i).length() == 5){
                    if (isSubset(numbers[6], words.get(i)))
                        numbers[5] = words.remove(i);
                    else if (isSubset(words.get(i), numbers[1]))
                        numbers[3] = words.remove(i);
                    else
                        numbers[2] = words.remove(i);
                    i--;
                }
            }

            String[] encryptedText = Arrays.stream(parts[1].split(" ")).map(word -> {
                char[] arr = word.toCharArray();
                Arrays.sort(arr);
                return new String(arr);
            }).toList().toArray(new String[0]);
            int sum = 0;
            for(int i = 0; i < encryptedText.length; i++){
                for(int x = 0; x < numbers.length; x++) {
                    if(numbers[x].equals(encryptedText[i])){
                        sum += (int)Math.pow(10, 3-i) * x;
                    }
                }
            }
            total += sum;
            text= br.readLine();
        }

        return total;
    }

    public boolean isSubset(String source, String str){
        int j = 0;
        for(int i = 0; i < source.length(); i++){
            if(j >= str.length())
                return true;
            if(source.charAt(i) == str.charAt(j)){
                j++;
            }
        }
        return j >= str.length();
    }

}
