import java.awt.*;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DayThirteen extends AbstractDay{
    Integer out1 = -1;
    StringBuilder out2 = new StringBuilder();
    public DayThirteen() throws IOException {
        super(13);
        BufferedReader br = new BufferedReader(new FileReader("inputs/input13.txt"));
        String text = br.readLine();
        ArrayList<Point> points = new ArrayList<>();
        int max = 0;
        while(text.length() > 0){
            int[] point = Arrays.stream(text.split(",")).mapToInt(Integer::parseInt).toArray();
            if(point[0] > max)
                max = point[0];
            if(point[1] > max)
                max = point[1];
            points.add(new Point(point[0], point[1]));
            text = br.readLine();
        }
        text = br.readLine();
        ArrayList<Pair<Character, Integer>> folds = new ArrayList<>();
        while(text != null){
            char axis = text.charAt(text.indexOf('=')-1);
            int divider = Integer.parseInt(text.substring(text.indexOf('=')+1));
            folds.add(new Pair<>(axis, divider));
            text = br.readLine();
        }
        for(Pair<Character, Integer> fold : folds){
            for(int i = 0; i < points.size(); i++){
                Point point  = points.get(i);
                if(fold.getFirst() == 'y' && (int)points.get(i).getY() >= fold.getSecond()) {
                    int folded = (int) (fold.getSecond() - (points.get(i).getY() - fold.getSecond()));
                    if(!points.contains(new Point((int)point.getX(), folded)))
                        point.setLocation(point.getX(), folded);
                    else {
                        i--;
                        points.remove(point);
                    }
                }
                if(fold.getFirst() == 'x' && (int)point.getX() >= fold.getSecond() ) {
                    int folded = (int) (fold.getSecond() - (point.getX() - fold.getSecond()));
                    if(!points.contains(new Point(folded, (int)point.getY()))) {
                        point.setLocation(folded, point.getY());
                    }
                    else {
                        i--;
                        points.remove(point);
                    }
                }
            }
            if(out1 == -1)
                out1 = points.size();
        }
        int maxX = 0;
        int maxY = 0;
        for(Point2D point : points){
            if(point.getX() > maxX)
                maxX = (int) point.getX();
            if(point.getY() > maxY)
                maxY = (int) point.getY();
        }
        char[][] map = new char[maxY+1][maxX+1];
        for(int i = 0; i < map.length; i++)
            for(int x = 0; x < map[0].length; x++)
                map[i][x] = ' ';
        for(Point2D point : points) {
            map[(int) point.getY()][(int) point.getX()] = '#';
        }
        for(char[] row : map){
            for(char let : row)
                out2.append(let).append(' ');
            out2.append("\n");
        }
    }

    @Override
    public Object getOutput1() throws IOException {
        return out1;
    }

    @Override
    public Object getOutput2() throws IOException {
        return out2.toString();
    }
}
