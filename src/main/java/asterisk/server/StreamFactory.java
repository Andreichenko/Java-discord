package asterisk.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class StreamFactory {

    private static final Logger logger = LoggerFactory.getLogger(StreamFactory.class);
    private static final StreamFactory instance = new StreamFactory();
    private static final ConcurrentHashMap<String, LinkedBlockingQueue<StreamEvent>> queueMap =
            new ConcurrentHashMap<String, LinkedBlockingQueue<StreamEvent>>();

    private StreamFactory() {}

    public static StreamFactory getInstance() {
        return instance;
    }

    public LinkedBlockingQueue<StreamEvent> createStream(String caller) {
        LinkedBlockingQueue<StreamEvent> queue = new LinkedBlockingQueue<StreamEvent>();
        LinkedBlockingQueue<StreamEvent> oldQueue = queueMap.putIfAbsent(caller, queue);
        if (oldQueue != null) {
            logger.warn("found stale caller stream queue: caller={}", caller);
        }
        return queue;
    }
}
