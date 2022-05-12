package bot.commands.audio.utils;

import bot.commands.audio.UserInfo;
import bot.entities.OptionEntity;
import bot.repositories.OptionEntityRepository;
import com.google.common.collect.EvictingQueue;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioTrack;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.api.entities.TextChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static bot.utils.OptionsCommands.AUTOPLAY_NAME;

public class TrackScheduler extends AudioEventAdapter {
    private static final Logger LOGGER = LogManager.getLogger(TrackScheduler.class);
    private final EvictingQueue<AudioTrack> historyQueue = EvictingQueue.create(20);

    /**
     * The API key needed to call the YouTube API
     */
    private final String youtubeApiKey;

    /**
     * The repository for all option data
     */
    private final OptionEntityRepository optionEntityRepository;

    /**
     * The ID of the guild that this is the track scheduler for
     */
    private final String guildId;

    private List<AudioTrack> queue = new ArrayList<>();

    private AudioTrack loopTrack = null;

    /**
     * boolean to indicate if the bot has been told to leave the voice channel. If the leave message has been called then
     * this is true and related videos shouldn't be found.
     */
    private boolean gotLeaveMessage = false;

    /**
     * The duration of the queue
     */
    private long queueDurationInMilliSeconds = 0;

    public TrackScheduler(String youtubeApiKey, OptionEntityRepository optionEntityRepository, String guildId) {
        this.youtubeApiKey = youtubeApiKey;
        this.optionEntityRepository = optionEntityRepository;
        this.guildId = guildId;
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        LOGGER.info("Track {} ended {}", track.getIdentifier(), endReason);

        if (endReason == AudioTrackEndReason.LOAD_FAILED) {
            // send message to the text channel saying that the loading failed
            UserInfo userInfo = (UserInfo) track.getUserData();
            TextChannel textChannel = userInfo.getJda().getTextChannelById(userInfo.getChannelId());
            if (textChannel != null) {
                textChannel.sendMessage(String.format("Loading failed for %s", userInfo.getSearchQuery())).queue();
                return;
            }

            LOGGER.error("The text channel inside track userInfo is null.");
        }

        historyQueue.add(track);

        if (!endReason.mayStartNext && !endReason.equals(AudioTrackEndReason.STOPPED)) {
            return;
        }

        if (loopTrack != null) {
            player.playTrack(loopTrack.makeClone());
            return;
        }

        if (!queue.isEmpty()) {
            player.playTrack(nextTrack());
            return;
        }

        // should a related video be found
        if (track instanceof YoutubeAudioTrack && !gotLeaveMessage) {
            // get the optionEntityForAutoplay
            OptionEntity optionEntity = optionEntityRepository.findByServerIdAndName(guildId, AUTOPLAY_NAME);
            if (optionEntity != null && !optionEntity.getOption()) {
                return;
            }
            String oldTrackId = track.getInfo().identifier;
            AudioTrack nextTrack = getRelatedVideoRetry(oldTrackId, 0);
            if (nextTrack != null) {
                queueDurationInMilliSeconds += nextTrack.getDuration();
                queue.add(nextTrack);
                player.playTrack(nextTrack());
            }
        }
    }

    public AudioTrack getRelatedVideoRetry(String trackId, int retryAmount) {
        LOGGER.info("finding a related video, tried " + retryAmount + " times");
        if (retryAmount >= 10) {
            LOGGER.error("Tried to find a related video ");
            return null;
        }

        try {
            return YouTubeUtils.getRelatedVideo(trackId, new ArrayList<>(historyQueue), youtubeApiKey);

        } catch(IOException | IllegalArgumentException | FriendlyException e) {
            LOGGER.error("Encountered error when trying to find a related video", e);
        }

        return getRelatedVideoRetry(trackId, retryAmount + 1);
    }

    @Override
    public void onTrackException(AudioPlayer player, AudioTrack track, FriendlyException exception) {
        LOGGER.error("Error when playing track", exception);
    }

    public void queue(AudioTrack track, boolean queueFirst) {
        queueDurationInMilliSeconds += track.getDuration();
        if (queueFirst) {
            queue.add(0, track);
            return;
        }
        queue.add(track);
    }

    public void clearQueue() {
        queueDurationInMilliSeconds = 0;
        queue.clear();
    }

    public int getQueueSize() {
        return queue.size();
    }

    AudioTrack nextTrack() {
        if (!queue.isEmpty()) {
            AudioTrack audioTrack = queue.get(0);
            queueDurationInMilliSeconds -= audioTrack.getDuration();
            queue.remove(0);
            return audioTrack;
        }

        return null;
    }

    public List<AudioTrack> getQueue() {
        return queue;
    }

    public void setQueue(List<AudioTrack> queue) {
        this.queue = queue;
    }

    public long getQueueDurationInMilliSeconds() {
        return queueDurationInMilliSeconds;
    }

    public AudioTrack getLoopTrack() {
        return loopTrack;
    }

    public void setLoopTrack(AudioTrack loopTrack) {
        this.loopTrack = loopTrack;
    }

    public void remove(int trackToRemove) {
        queue.remove(trackToRemove);
    }

    public EvictingQueue<AudioTrack> getHistory() {
        return historyQueue;
    }

    public void setGotLeaveMessage(boolean gotLeaveMessage) {
        this.gotLeaveMessage = gotLeaveMessage;
    }
}