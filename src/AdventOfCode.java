public class AdventOfCode {
    public static void main(String[] Args){
        try {
            AbstractDay[] days = new AbstractDay[]{new DayTwo(), new DayThree(), new DayFour(), new DayFive(), new DaySix(),
                    new DaySeven(), new DayEight(), new DayNine(), new DayTen(), new DayEleven(), new DayTwelve(),
                    new DayThirteen(), new DayFourteen()};
            for(AbstractDay day : days){
                System.out.println(day.getOutputs());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
