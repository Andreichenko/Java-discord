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

        durationInMilliSeconds = 0;
        queue.clear();

    }
    public void setQueue(List<AudioTrack> queue){

        this.queue = queue;
    }

    public int getQueueSize(){
        return queue.size();
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason){

        LOGGER.info("Track {} ended {}", track.getIdentifier(), endReason.toString());

        historyQueue.add(track);
    }

    @Override
    public void onTrackException(AudioPlayer player, AudioTrack track, FriendlyException exception){

    }

    @Override
    public void onTrackStuck(AudioPlayer player, AudioTrack track, long thresholdMs){

    }

    AudioTrack nextTrack(){

        if (queue.size() > 0){
            AudioTrack audioTrack = queue.get(0);
            durationInMilliSeconds -= audioTrack.getDuration();
            queue.remove(0);
            return audioTrack;
        }
    }

    public AudioTrack getLoopTrack(){

        return loopTrack;
    }

    public void setLoopTrack(AudioTrack loopTrack){

        this.loopTrack = loopTrack;
    }

    public EvictingQueue<AudioTrack> getHistory(){
        return historyQueue;
    }
}
