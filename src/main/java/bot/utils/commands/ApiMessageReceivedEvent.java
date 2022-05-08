package bot.utils.commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import org.jetbrains.annotations.NotNull;

public class ApiMessageReceivedEvent {
    public ApiMessageReceivedEvent(@NotNull JDA api, long responseNumber, @NotNull Message message){
        super(api, responseNumber, message);
    }
}
