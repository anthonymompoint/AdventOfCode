public class AdventOfCode {
    public static void main(String[] Args){
        try {
            System.out.print((new DayTwo()).getOutputs());
            System.out.print((new DayThree()).getOutputs());
            System.out.println((new DayFour()).getOutputs());
            System.out.println((new DayFive()).getOutputs());
            System.out.println((new DaySix()).getOutputs());
            System.out.println((new DaySeven()).getOutputs());
            System.out.println((new DayEight()).getOutputs());
            System.out.println((new DayNine()).getOutputs());
            System.out.println((new DayTen()).getOutputs());
            System.out.println((new DayEleven()).getOutputs());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
