package bot.controllers;

import bot.services.BotService;
import com.jagrosh.jdautilities.command.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageActivity;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.internal.entities.AbstractMessage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/command")
@RestController
public class SendCommandController {

    private final BotService botService;

    @Autowired
    public SendCommandController(BotService botService){
        this.botService = botService;
    }

    @PostMapping()
    public ResponseEntity<String> sendCommand(@RequestParam String commandName, @RequestParam String commandArgs,
                                              @RequestParam String guildId, @RequestParam String textChannelId,
                                              @RequestParam String userId) {

       // Command command = botService.getCommandFromName(commandName);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    class EndpointMessage extends AbstractMessage{
        public EndpointMessage(String content, String nonce, boolean isTTS, MessageChannel messageChannel, Member author){
            super(content, nonce, isTTS);
        }

        @NotNull
        @Override
        public MessageChannel getChannel(){
            return super.getChannel();
        }

        @Override
        protected void unsupported(){

        }

        @Nullable
        @Override
        public MessageActivity getActivity(){
            return null;
        }

        @Override
        public long getIdLong(){
            return 0;
        }
    }

}
