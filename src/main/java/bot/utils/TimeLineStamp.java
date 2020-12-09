package bot.utils;

public class TimeLineStamp {
    public static String timeStamp(long audioLengthInSeconds){

        long seconds = 0;
        long minutes = 0;
        long hours = 0;

        return null;
    }

    public static String timeString(long videoLengthInSeconds){
        long seconds = videoLengthInSeconds%60;
        long minutes = videoLengthInSeconds / 60;
        long hours = minutes / 60;

        if (hours != 0){
            return String.format("%d:%02d:%02d", hours, minutes, seconds);
        }
        return String.format("%02d:%02d", minutes, seconds);
    }

}
