package asterisk.server;

public class StreamEvent {

    private StreamEventType eventType = StreamEventType.INVALID;
    private String caller = "";

    protected StreamEvent(StreamEventType type, String caller){
        this.eventType = type;
        this.caller = caller;
    }

    public StreamEventType getEventType() {
        return eventType;
    }

    public String getCaller() {
        return caller;
    }
}
