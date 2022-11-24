package bot.utils.commands.impl;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.requests.RestAction;
import org.jetbrains.annotations.NotNull;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class BlankRestAction<T> implements RestAction<T> {

    @NotNull
    @Override
    public JDA getJDA() {
        return null;
    }
    @NotNull
    @Override
    public RestAction<T> setCheck(@Nullable BooleanSupplier checks) {
        return null;
    }

    @Override
    public T complete(boolean shouldQueue) throws RateLimitedException {
        return null;
    }

    @NotNull
    @Override
    public CompletableFuture<T> submit(boolean shouldQueue) {
        return null;
    }

    @Override
    public void queue(@Nullable Consumer success, @Nullable Consumer failure) {
        // do nothing
    }
}
