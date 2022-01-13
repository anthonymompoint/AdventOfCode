import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DayNine extends AbstractDay{
    public DayNine() {
        super(9);
    }

    @Override
    public Object getOutput1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs/input9.txt"));
        String line = br.readLine();
        char[][] field = new char[line.length()][line.length()];
        int z = 0;
        while(line != null && z < field.length){
            field[z] = line.toCharArray();
            z++;
            line = br.readLine();
        }
        int[][] spots = new int[field.length][field[0].length];
        for(int i = 0; i < field.length; i++){
            for(int x = 0; x < field[i].length;x++){
                if(x - 1 > -1 && field[i][x-1] < field[i][x])
                    spots[i][x-1]++;
                if(x + 1 < field.length && field[i][x+1] < field[i][x])
                    spots[i][x+1]++;
                if(i - 1 > -1 && field[i-1][x] < field[i][x])
                    spots[i-1][x]++;
                if(i + 1 < field.length && field[i+1][x] < field[i][x])
                    spots[i+1][x]++;
            }
        }

        int sum = 0;
        for(int i = 0; i < field.length; i++){
            for(int x = 0; x < field[i].length;x++){
                int neighbors = 0;
                if(x - 1 > -1)
                    neighbors++;
                if(x + 1 < field.length)
                    neighbors++;
                if(i - 1 > -1)
                    neighbors++;
                if(i + 1 < field.length )
                    neighbors++;


                if(spots[i][x] == neighbors)
                    sum += 1 + Integer.parseInt(field[i][x]+ "");
            }
        }
        return sum;
    }

    @Override
    public Object getOutput2() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs/input9.txt"));
        String line = br.readLine();
        ArrayList<String> lines = new ArrayList<>();
        while(line != null){
            lines.add(line);
            line = br.readLine();
        }

        char[][] field = new char[lines.size()][lines.get(0).length()];
        for(int i = 0; i < lines.size();i++){
            field[i] = lines.get(i).toCharArray();
        }
        int[][] spots = new int[field.length][field[0].length];
        for(int i = 0; i < field.length; i++){
            for(int x = 0; x < field[i].length;x++){
                if(x - 1 > -1 && field[i][x-1] < field[i][x])
                    spots[i][x-1]++;
                if(x + 1 < field[i].length && field[i][x+1] < field[i][x])
                    spots[i][x+1]++;
                if(i - 1 > -1 && field[i-1][x] < field[i][x])
                    spots[i-1][x]++;
                if(i + 1 < field.length && field[i+1][x] < field[i][x])
                    spots[i+1][x]++;
            }
        }

        ArrayList<Integer> basins = new ArrayList<>();
        for(int i = 0; i < field.length; i++){
            for(int x = 0; x < field[i].length;x++){
                int neighbors = 0;
                if(x - 1 > -1)
                    neighbors++;
                if(x + 1 < field[0].length)
                    neighbors++;
                if(i - 1 > -1)
                    neighbors++;
                if(i + 1 < field.length )
                    neighbors++;

                boolean[][] explored = new boolean[field.length][field[0].length];
                if(spots[i][x] == neighbors)
                    basins.add(findBasinSize(field, explored, i, x));
            }
        }
        int mult = 1;
        for(int i = 0; i < 3; i++){
            Integer max = basins.stream().mapToInt(Integer::intValue).max().orElse(0);
            mult *= max;
            basins.remove(max);
        }
        return mult;
    }

    // COMMENCE THE BASIN TRAWL
    public int findBasinSize(char[][] field, boolean[][] explored, int x, int y){
        if(x < 0 || y < 0 || x == field.length || y == field[0].length || field[x][y] == '9' || explored[x][y])
            return 0;
        explored[x][y] = true;
        return findBasinSize(field, explored, x, y + 1) + findBasinSize(field,explored , x, y - 1) + findBasinSize(field, explored, x-1, y) + findBasinSize(field, explored, x+1, y) + 1;
    }
}
