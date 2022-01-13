import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DayTen extends  AbstractDay{
    public DayTen() {
        super(10);
    }

    @Override
    public Object getOutput1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs/input10.txt"));
        String line = br.readLine();
        int score = 0;
        while(line != null){
            Stack<Character> stack = new Stack<>();
            for(char c : line.toCharArray()){
                if(isClosingCharacter(c)){
                    if(stack.peek() != c){
                        switch (c){
                            case ')' -> score += 3;
                            case ']' -> score += 57;
                            case '}' -> score += 1197;
                            case '>' -> score += 25137;
                            default -> score += 0;
                        }
                        break;
                    }
                    else{
                        stack.pop();
                    }
                }
                else{
                    switch (c){
                        case '(' -> stack.push(')');
                        case '[' -> stack.push(']');
                        case '{' -> stack.push('}');
                        case '<' -> stack.push('>');
                    }
                }
            }
            line = br.readLine();
        }
        return score;
    }

    @Override
    public Object getOutput2() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs/input10.txt"));
        String line = br.readLine();

        ArrayList<Long> scores = new ArrayList<>();
        while(line != null){
            Stack<Character> stack = new Stack<>();
            boolean flag = false;
            for(char c : line.toCharArray()){
                if(isClosingCharacter(c)){
                    if(stack.peek() != c){
                        flag = true;
                        break;
                    }
                    else{
                        stack.pop();
                    }
                }
                else{
                    switch (c){
                        case '(' -> stack.push(')');
                        case '[' -> stack.push(']');
                        case '{' -> stack.push('}');
                        case '<' -> stack.push('>');
                    }
                }
            }
            line = br.readLine();
            if(flag || stack.empty()) continue;
            long sum = 0;

            while(!stack.empty())
            {
                sum *= 5;
                switch (stack.pop()){
                    case ')' -> sum += 1;
                    case ']' -> sum += 2;
                    case '}' -> sum += 3;
                    case '>' -> sum += 4;
                }
            }
            scores.add(sum);
        }
        long[] arr = scores.stream().mapToLong(Long::longValue).toArray();
        Arrays.sort(arr);
        return arr[arr.length / 2];
    }

    public boolean isClosingCharacter(char c){
        return c == ']' || c == '}' || c == '>' || c == ')';
    }
}
