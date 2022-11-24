package bot.listeners;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.utils.BotConfiguration;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class VoiceChannelEventListener extends ListenerAdapter
{
    // 60 seconds
    public static final int VOICE_CHECK_DELAY = 60 * 1000;

    /**
     * The discord ID of the bot used to check if the bot is alone in the voice channel
     */
    private final String botUserId;

    @Autowired
    public VoiceChannelEventListener(BotConfiguration botConfiguration) {
        super();

        this.botUserId = botConfiguration.getUserId();
    }

    @Override
    public void onGuildVoiceMove(@Nonnull GuildVoiceMoveEvent event) {
        Member movedMember = event.getMember();

        if (!movedMember.getId().equals(botUserId))
        {
            return;
        }

        //set a timer for 1 minute to call leaveVoiceChannel and cancel it if someone comes back
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int membersInVoiceChannel = event.getChannelLeft().getMembers().size();

                if (membersInVoiceChannel == 1) {
                    leaveVoiceChannel(event);
                }
            }
        }, VOICE_CHECK_DELAY);
    }

    @Override
    public void onGuildVoiceLeave(@Nonnull GuildVoiceLeaveEvent event) {
        Member leftMember = event.getMember();
        boolean leftMemberIsBot = leftMember.getId().equals(botUserId);
        List<Member> membersLeft = event.getChannelLeft().getMembers();

        AudioManager audioManager = event.getGuild().getAudioManager();
        VoiceChannel connectedChannel = audioManager.getConnectedChannel();

        // because this event is triggered whenever anything happens in the server need to check to see if the bot was
        // even connected to that voice channel
        if (connectedChannel == null || !connectedChannel.getId().equals(event.getChannelLeft().getId())) {
            return;
        }

        boolean onlyBotsLeft = isOnlyBotsLeft(event.getChannelLeft().getMembers());

        // The bot can be considered alone if it's by itself or if it's alone with another bot
        boolean botAlone = (membersLeft.size() == 1 && !leftMemberIsBot) || onlyBotsLeft;

        if (leftMemberIsBot || membersLeft.isEmpty()) {
            leaveVoiceChannel(event);
        }
        else if (botAlone) {
            //set a timer for 1 minute to call leaveVoiceChannel and cancel it if someone comes back
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    int membersInVoiceChannel = event.getChannelLeft().getMembers().size();
                    boolean onlyBotsLeft = isOnlyBotsLeft(event.getChannelLeft().getMembers());

                    if (membersInVoiceChannel == 1 || onlyBotsLeft)
                    {
                        leaveVoiceChannel(event);
                    }
                }
            }, VOICE_CHECK_DELAY);
        }
    }

    private boolean isOnlyBotsLeft(@Nonnull List<Member> membersInChannel) {
        boolean onlyBotsLeft = true;
        for (Member member : membersInChannel) {
            // If the member is not a bot then set the boolean to false and break the loop
            if (!member.getUser().isBot()) {
                onlyBotsLeft = false;
                break;
            }
        }
        return onlyBotsLeft;
    }

    /**
     * If still connected leave the currently connected voice channel and cleanup
     *
     * @param event The voice event that triggered this handler
     */
    private void leaveVoiceChannel(@Nonnull GenericGuildVoiceEvent event) {
        AudioManager audioManager = event.getGuild().getAudioManager();

        AudioPlayerSendHandler audioPlayerSendHandler = (AudioPlayerSendHandler) audioManager.getSendingHandler();

        if (audioPlayerSendHandler == null) {
            return;
        }

        audioManager.closeAudioConnection();
        audioPlayerSendHandler.getTrackScheduler().getQueue().clear();
        audioPlayerSendHandler.getAudioPlayer().destroy();
    }
}