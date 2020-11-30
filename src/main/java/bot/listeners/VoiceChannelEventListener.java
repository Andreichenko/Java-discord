package bot.listeners;

import bot.utils.Injectors;
import bot.utils.SystemEnvironment;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
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
        }, VOICE_CHECK_DELAY;
    }

    private void leaveVoiceChannel(@Nonnull GenericGuildVoiceEvent event) {

    }
}
