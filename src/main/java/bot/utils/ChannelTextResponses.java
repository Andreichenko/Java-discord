package bot.utils;

public class ChannelTextResponses {

    public static final String NOT_CONNECTED_TO_VOICE_MESSAGE = "**You need to be in a voice channel.**";
    public static final String NO_ARGUMENT_PROVIDED_TO_PLAY_COMMAND = "**Need to provide something to play**";
    public static final String ERROR_LOADING_VIDEO = "**There was a problem loading that video.**";
    public static final String DONT_HAVE_PERMISSION_TO_JOIN_VOICE_CHANNEL = "**Do not have permission to join your voice " +
            "channel**";

    public static final String REMOVE_COMMAND_NO_ARGUMENT = "**No song was provided to remove**";
    public static final String REMOVE_COMMAND_NOT_A_NUMBER = "**%s is not a number**";
    public static final String REMOVE_COMMAND_NO_TRACK_TO_REMOVE = "**There is not a track at position %d on the queue**";

    public static final String NOTHING_CURRENTLY_PLAYING = "**Nothing is currently playing**";

    public static final String LOOP_ENABLED = "**Loop enabled!**";
    public static final String LOOP_DISABLED = "**Loop disabled!**";

    public static final String BOT_NOT_CONNECTED_TO_VOICE = "**Not currently connected to the voice channel**";

    public static final String TRYING_TO_RESUME_PLAYING_SONG = "**The song is not paused.**";
    public static final String TRYING_TO_PAUSE_PAUSED_SONG = "**Song is already paused.**";

    public static final String HOW_TO_MAKE_ALIAS = "`-aliascreate ALIAS_NAME <Command to run when ALIAS_NAME is called>`";
    public static final String NEED_MORE_ARGUMENTS_TO_CREATE_AN_ALIAS = "**Need more arguments to create an alias" +
            ".**\n**Create an alias with**" + HOW_TO_MAKE_ALIAS;
    public static final String ALIAS_NAME_ALREADY_IN_USE_AS_COMMAND = "**%s is already used as the name of a command**";
    public static final String ALIAS_CREATED = "**Alias created with name `%s` that executes command `%s` with arguments " +
            "`%s`**";
    public static final String ERROR_OCCURRED_CREATING_ALIAS = "**Error when creating alias with name `%s`**";
    public static final String ALIAS_CANT_BE_CREATED_COMMAND_NOT_FOUND = "**%s is not a command**";
    public static final String NO_ALIASES_SET = "**There are currently no aliases set**";

    public static final String ALIAS_REMOVED = "**Alias with name `%s` has been removed**";
    public static final String ALIAS_DELETE_ERROR_OCCURRED = "**Something went wrong whilst removing that alias**";
    public static final String ALIAS_DELETE_NONE_PROVIDED = "**You need to provide an alias to delete**";
    public static final String ALIAS_DELETE_ALIAS_DOES_NOT_EXIST = "**Alias with name `%s` doesn't exist**";
    public static final String ALIAS_TOO_LONG = "**That Alias is too long. It needs to be less than 2000 characters**";

    public static final String ECHO_COMMAND_NO_ARGS = "**You need to provide some text to echo.**";

    public static final String NO_HISTORY_TO_SHOW = "**There is no history to show.**";
    public static final String CANT_DISPLAY_QUEUE_PAGE = "**Cannot display that page**";
}
