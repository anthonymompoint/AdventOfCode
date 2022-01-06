import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DayThree extends AbstractDay{
    public DayThree(){
        super(3);
    }

    @Override
    public Object getOutput1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs/input3.txt"));
        String text = br.readLine();
        int[] zeroOccurrences = new int[text.length()];
        int[] oneOccurrences = new int[text.length()];

        while(text != null) {
            for (int i = text.length() - 1; i > -1; i--) {
                if (text.charAt(i) == '1')
                    oneOccurrences[i]++;
                else if(text.charAt(i) == '0')
                    zeroOccurrences[i]++;
            }
            text = br.readLine();
        }
        StringBuilder gamma = new StringBuilder();
        for(int i = oneOccurrences.length-1; i > -1; i--){
            gamma.append(oneOccurrences[i] > zeroOccurrences[i] ? 1 : 0);
        }
        int gammaNum = convertBinary(gamma.toString());
        br.close();
        return gammaNum * (gammaNum ^ (int)Math.pow(2, oneOccurrences.length)-1);
    }

    @Override
    public Object getOutput2() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs/input3.txt"));
        ArrayList<String> oxygen = new ArrayList<>();
        ArrayList<String> CO2 = new ArrayList<>();
        String text = br.readLine();
        while(text != null){
            oxygen.add(text);
            CO2.add(text);
            text = br.readLine();
        }

        int place = 0;
        while(oxygen.size() > 1 && place < oxygen.get(0).length()){
            int zero = 0;
            int one = 0;
            for(String line : oxygen){
                if(line.charAt(place) == '1')
                    one++;
                else
                    zero++;
            }
            char commonChar = '0';
            if(one >= zero)
                commonChar = '1';
            for(int i = 0; i < oxygen.size(); i++){
                if(oxygen.get(i).charAt(place) != commonChar) {
                    oxygen.remove(i);
                    i--;
                }
            }
            place++;
        }

        place = 0;
        while(CO2.size() > 1 && place < oxygen.get(0).length()){
            int zero = 0;
            int one = 0;
            for(String line : CO2){
                if(line.charAt(place) == '1')
                    one++;
                else
                    zero++;
            }
            char commonChar = '0';
            if(one >= zero)
                commonChar = '1';
            for(int i = 0; i < CO2.size(); i++){
                if(CO2.get(i).charAt(place) == commonChar){
                    CO2.remove(i);
                    i--;
                }
            }
            place++;
        }
        br.close();
        return convertBinary(flip(oxygen.get(0))) * convertBinary(flip(CO2.get(0)));
    }

    public String flip(String string){
        StringBuilder out = new StringBuilder();
        for(char c  : string.toCharArray()){
            out.insert(0, c);
        }
        return out.toString();
    }

    public int convertBinary(String str){
        int out = 0;
        for(int i = 0 ; i < str.length(); i++){
            out += str.charAt(i) == '1' ? Math.pow(2, i) : 0;
        }
        return out;
    }
}
