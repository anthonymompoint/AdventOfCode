import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DayTwo extends AbstractDay {
    public DayTwo(){
        super(2);
    }

    @Override
    public Object getOutput1() throws IOException {
        int depth = 0;
        int forward = 0;
        BufferedReader br = new BufferedReader(new FileReader("inputs/input2-1.txt"));
        String text = br.readLine();
        while(text != null){
            String direction = text.substring(0, text.indexOf(' '));
            int distance = Integer.parseInt(text.substring(text.indexOf(' ') + 1));
            switch (direction) {
                case "forward" -> forward += distance;
                case "down" -> depth += distance;
                case "up" -> depth -= distance;
            }
            text = br.readLine();
        }
        br.close();
        return depth * forward;
    }

    @Override
    public Object getOutput2() throws IOException{
        int depth = 0;
        int forward = 0;
        int aim = 0;
        BufferedReader br = new BufferedReader(new FileReader("inputs/input2-1.txt"));
        String text = br.readLine();
        while(text != null){
            String direction = text.substring(0, text.indexOf(' '));
            int distance = Integer.parseInt(text.substring(text.indexOf(' ') + 1));
            switch (direction) {
                case "forward" -> {
                    depth += distance  * aim;
                    forward += distance;
                }
                case "down" -> aim += distance;
                case "up" -> aim -= distance;
            }
            text = br.readLine();
        }
        br.close();
        return depth * forward;
    }


}
