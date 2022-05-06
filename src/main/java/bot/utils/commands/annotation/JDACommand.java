package bot.utils.commands.annotation;


import bot.utils.commands.AnnotatedModuleCompiler;
import bot.utils.commands.Command;
import bot.utils.commands.CommandBuilder;
import bot.utils.commands.CommandClientBuilder;
import net.dv8tion.jda.api.Permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JDACommand {

    String[] name() default {"null"};

    String help() default "no help available";

    boolean guildOnly() default true;

    String requiredRole() default "";

    boolean ownerCommand() default false;

    String arguments() default "";

    com.jagrosh.jdautilities.command.annotation.JDACommand.Cooldown cooldown() default @com.jagrosh.jdautilities.command.annotation.JDACommand.Cooldown(0);


}
