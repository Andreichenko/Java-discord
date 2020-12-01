package bot.listeners;

import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.utils.Injectors;
import bot.utils.SystemEnvironment;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class VoiceChannelEventListener extends ListenerAdapter {

    public static final int VOICE_CHECK_DELAY = 30 * 1000;

    @SystemEnvironment("BOT_USER_ID")
    private String BOT_USER_ID;

    public VoiceChannelEventListener()
    {
        super();

        Injectors.injectionFieldEnvValue(this);  // add the environment value
    }

    @Override
    public void onGuildVoiceMove(@Nonnull GuildVoiceMoveEvent event) {

        Member movedMember = event.getMember();

        if (!movedMember.getId().equals(BOT_USER_ID)) {

            return;
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask(){

            @Override
            public void run() {
                int membersInVoiceChannel = event.getChannelLeft().getMembers().size();
                if (membersInVoiceChannel == 1) {
                    leaveVoiceChannel(event);
                }

            }
        }, VOICE_CHECK_DELAY);
    }

    /**
     *
     * @param event this event is triggered whenever something happens on the server, and then should check if the bot was

     */
    @Override
    public void onGuildVoiceLeave(@Nonnull GuildVoiceLeaveEvent event){

        Member leftMember = event.getMember();
        boolean leftMemberIsBot = leftMember.getId().equals(BOT_USER_ID);
        List<Member> membersLeft = event.getChannelLeft().getMembers();

        AudioManager audioManager = event.getGuild().getAudioManager();
        VoiceChannel connectedChannel = audioManager.getConnectedChannel();

        // even if someone connects to this voice channel
        if (connectedChannel == null || !connectedChannel.getId().equals(event.getChannelLeft().getId())){
            return;
        }

        boolean onlyBotsLeft = isOnlyBotsLeft(event.getChannelLeft().getMembers());

        boolean botAlone = (membersLeft.size() == 1 && !leftMemberIsBot) || onlyBotsLeft;

        if (leftMemberIsBot || membersLeft.size() == 0){

            leaveVoiceChannel(event);
        }else if (botAlone){
            Timer timer = new Timer();
            timer.schedule(new TimerTask(){
                @Override
                public void run() {

                }
            }, VOICE_CHECK_DELAY);
        }

    }

    private boolean isOnlyBotsLeft(@Nonnull List<Member> membersInChannel)
    {
        boolean onlyBotsLeft = true;
        for (Member member : membersInChannel)
        {
            // If the member is not a bot then set the boolean to false and break the loop
            if (!member.getUser().isBot())
            {
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

    }
}
