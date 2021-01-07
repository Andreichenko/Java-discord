package bot.commands.audio;


import bot.commands.audio.utils.AudioPlayerSendHandler;
import bot.commands.audio.utils.TrackSchedulers;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static bot.utils.ChannelTextResponses.REMOVE_COMMAND_NOT_A_NUMBER;
import static bot.utils.ChannelTextResponses.REMOVE_COMMAND_NO_ARGUMENT;
import static bot.utils.ChannelTextResponses.REMOVE_COMMAND_NO_TRACK_TO_REMOVE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RemoveCommandTest {

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    public void testRemovesTracksSuccessfully(){

        ArgumentCaptor<String> emoteArgumentCaptor = ArgumentCaptor.forClass(String.class);

        ArgumentCaptor<Integer> intArgumentCaptor = ArgumentCaptor.forClass(Integer.class);

        RestAction mockRestAction = mock(RestAction.class);
        doAnswer(invocation -> null).when(mockRestAction).queue();

        TrackSchedulers mockTrackScheduler = mock(TrackSchedulers.class);
        doAnswer(invocation -> null).when(mockTrackScheduler).remove(intArgumentCaptor.capture());

        AudioPlayer mockAudioPlayer = mock(AudioPlayer.class);
        AudioPlayerSendHandler audioPlayerSendHandler = new AudioPlayerSendHandler(mockAudioPlayer, mockTrackScheduler);

        CommandEvent mockCommandEvent = mock(CommandEvent.class);
        when(mockCommandEvent.getArgs()).thenReturn("3");
        when(mockCommandEvent.getGuild()).thenReturn(mock(Guild.class));
        when(mockCommandEvent.getGuild().getAudioManager()).thenReturn(mock(AudioManager.class));
        when(mockCommandEvent.getGuild().getAudioManager().getSendingHandler()).thenReturn(audioPlayerSendHandler);
    }
}
