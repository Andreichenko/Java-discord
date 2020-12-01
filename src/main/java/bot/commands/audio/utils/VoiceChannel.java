package bot.commands.audio.utils;

import bot.utils.ChannelTextResponses;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.managers.AudioManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;

public class VoiceChannel {

    private static final Logger LOGGER = LogManager.getLogger(VoiceChannel.class);

    public static void joinVoiceChannel(Member member,
                                        Guild guild,
                                        AudioPlayerManager playerManager)
            throws IllegalArgumentException, InsufficientPermissionException {

        GuildVoiceState memberVoiceState = member.getVoiceState();

        AudioManager audioManager = guild.getAudioManager();

        AudioPlayer player = playerManager.createPlayer();

        if (memberVoiceState == null || !memberVoiceState.inVoiceChannel()) {

            throw new IllegalArgumentException("Unable to join the voice channel");
        }

        TrackSchedulers trackScheduler = new TrackSchedulers();
        player.addListener(trackScheduler);

        audioManager.setSendingHandler(new AudioPlayerSendHandler(player, trackScheduler));
        audioManager.openAudioConnection(memberVoiceState.getChannel());
    }

    public static void SearchAndPlaySong(JDA jda, String argument, String guildId, String textChannelId,
                                         String memberId, boolean playTop, AudioPlayerManager playerManager) {

        if (guildId == null || guildId.equals("")){
            throw new IllegalArgumentException("Guild ID is NULL");
        }
        if (memberId == null || memberId.equals("")){
            throw new IllegalArgumentException("member ID is NULL");
        }

        if (textChannelId == null || textChannelId.equals("")){
            throw new IllegalArgumentException("message channel ID is NULL");
        }

        Guild guild = jda.getGuildById(guildId);
        if (guild == null){
            throw new IllegalArgumentException("Guild is NULL is the ID correct?");
        }
        MessageChannel channel = guild.getTextChannelById(textChannelId);
        if (channel == null){
            throw new IllegalArgumentException("Channel is NULL is the ID correct?");
        }
        Member member = guild.getMemberById(memberId);
        if (member == null){
            throw new IllegalArgumentException("Member is NULL is the ID correct?");
        }
        if (argument.isEmpty()){
            channel.sendMessage(ChannelTextResponses.NO_ARGUMENT_PROVIDED_TO_PLAY_COMMAND).queue();
            return;
        }

        AudioManager audioManager = guild.getAudioManager();
        if (!audioManager.isConnected()){

            try {
                VoiceChannel.joinVoiceChannel(member, guild, playerManager);

            }catch (InsufficientPermissionException e){
                channel.sendMessage(ChannelTextResponses.DONT_HAVE_PERMISSION_TO_JOIN_VOICE_CHANNEL).queue();
                return;
            }catch (IllegalArgumentException e){
                channel.sendMessage(ChannelTextResponses.NOT_CONNECTED_TO_VOICE_MESSAGE).queue();
                return;
            }
        }

        if (audioManager.getConnectedChannel() != null && !audioManager.getConnectedChannel().getMembers().contains(member)){
            channel.sendMessage(ChannelTextResponses.NOT_CONNECTED_TO_VOICE_MESSAGE).queue();
            return;
        }

        channel.sendMessage("Searching for `").append(argument).append("`").queue();
        channel.sendTyping().queue();

        AudioPlayerSendHandler audioPlayerSendHandler = (AudioPlayerSendHandler) audioManager.getSendingHandler();
        TrackSchedulers trackScheduler = audioPlayerSendHandler.getTrackScheduler();

        if (!isValidURL(argument)){
            LOGGER.info("argument is not a url so prepending ytsearch");
            argument = "ytsearch: ".concat(argument);
        }

    }

    private static boolean isValidURL(String urlString){

        try {
            URL url = new URL(urlString);
            url.toURI();
            return true;
        }catch (Exception exception){
            return false;
        }
    }
    public static void setPauseStatusOnAudioPlayer(Guild guild, MessageChannel channel,
                                                   Member member, boolean pauseStatus) {

        AudioManager audioManager = guild.getAudioManager();

    }

    public static AudioPlayerSendHandler getAudioPlayerSendHandler(JDA jda, String guildId){
        return null;
    }

}

