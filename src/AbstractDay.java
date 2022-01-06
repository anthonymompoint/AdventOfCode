import java.io.IOException;

public abstract class AbstractDay {
    final int day;
    public AbstractDay(int day){
        this.day = day;
    }
    public abstract Object getOutput1() throws IOException;
    public abstract Object getOutput2() throws IOException;
    public String getOutputs() throws IOException {
        String out = "Day " + day +": \n";
        out += "==============================\n";
        out += getOutput1() + "\n";
        out += getOutput2() + "\n";
        return out;
    }
}
