package bot.commands.audio.utils;

import com.google.common.collect.EvictingQueue;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TrackSchedulers extends AudioEventAdapter{

    private List<AudioTrack> queue = new ArrayList<>();
    private final Logger LOGGER = LogManager.getLogger(TrackSchedulers.class);

    private final EvictingQueue<AudioTrack> historyQueue = EvictingQueue.create(40);

    private AudioTrack loopTrack = null;
    private long durationInMilliSeconds = 0;

    public void queue(AudioTrack track, boolean queueFirst){

    }
    public void clearQueue(){

    }
    public void setQueue(List<AudioTrack> queue){

    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason){

    }

    @Override
    public void onTrackException(AudioPlayer player, AudioTrack track, FriendlyException exception){

    }

    @Override
    public void onTrackStuck(AudioPlayer player, AudioTrack track, long thresholdMs){

    }

    public void setLoopTrack(AudioTrack loopTrack){

        this.loopTrack = loopTrack;
    }

    public EvictingQueue<AudioTrack> getHistory(){
        return historyQueue;
    }
}
