package bot.listeners;

import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class VoiceChannelEventListener extends ListenerAdapter {

    public static final int VOICE_CHECK_DELAY = 30 * 1000;

    private String BOT_USER_ID;

    public VoiceChannelEventListener()
    {
        super();

    }

    public void onGuildVoiceMove(@Nonnull GuildVoiceMoveEvent event) {

    }

    private void leaveVoiceChannel(@Nonnull GenericGuildVoiceEvent event) {

    }
}
