public class Pair<I, V> {
    public I first;
    public V second;
    public Pair(I first, V second){
        this.first = first;
        this.second = second;
    }

    public I getFirst(){
        return first;
    }
    public V getSecond(){
        return second;
    }

    @Override
    public String toString() {
        return "[" + first.toString() + "," + second.toString() +"]";
    }
}
