import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DayTwelve extends AbstractDay{
    private final int out1;
    private final int out2;
    public DayTwelve() throws IOException {
        super(12);
        Map<String, Integer> nameToInt = new HashMap<>();
        ArrayList<Boolean> bigCave = new ArrayList<>();
        ArrayList<ArrayList<Integer>> caves = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("inputs/input12.txt"));
        String text = br.readLine();
        while (text != null){
            String[] path = text.split("-");
            for(String str : path){
                if(!nameToInt.containsKey(str)){
                    nameToInt.put(str, nameToInt.size());
                    caves.add(new ArrayList<>());
                    if(Character.isLowerCase(str.charAt(0)) && !str.equals("end"))
                        bigCave.add(false);
                    else
                        bigCave.add(true);
                }
            }
            caves.get(nameToInt.get(path[0])).add(nameToInt.get(path[1]));
            caves.get(nameToInt.get(path[1])).add(nameToInt.get(path[0]));
            text = br.readLine();
        }
        out1 = traverseCaves(nameToInt.get("start"),nameToInt.get("end"), caves, bigCave, "");
        out2 = traverseCaves(nameToInt.get("start"),nameToInt.get("start"), nameToInt.get("end"), caves, bigCave, new int[caves.size()], false);
    }

    @Override
    public Object getOutput1() throws IOException {
        return out1;
    }

    public Object getOutput2() throws IOException {
        return out2;
    }

    public int traverseCaves(int node, int end,ArrayList<ArrayList<Integer>> caves, ArrayList<Boolean> bigCave, String path){
        if(!bigCave.get(node) && path.contains(" " + node + " "))
            return 0;
        if(node == end)
            return 1;
        int count = 0;
        for(Integer cave : caves.get(node)){
            count += traverseCaves(cave, end, caves, bigCave, path + " "+ node + " ");
        }
        return count;
    }

    public int traverseCaves(int node, int start, int end ,ArrayList<ArrayList<Integer>> caves, ArrayList<Boolean> bigCave, int[] visits, boolean twice){
        boolean flag = twice;
        if(!bigCave.get(node) && visits[node] >= 1) {
            if(twice)
                return 0;
            flag = true;
        }
        if(node == start && visits[node] != 0)
            return 0;
        if(node == end)
            return 1;
        int count = 0;
        for(Integer cave : caves.get(node)){
            int[] list = visits.clone();
            list[node]++;
            count += traverseCaves(cave, start, end, caves, bigCave, list, flag);
        }
        return count;
    }
}
