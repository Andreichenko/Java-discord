package asterisk.server;

import org.asteriskjava.fastagi.DefaultAgiServer;
import org.slf4j.LoggerFactory;

public class BotServer extends DefaultAgiServer {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BotServer.class);


    private static final String streamHost = "127.0.0.1";

    private static final int streamPort = 29000;

    private BotServer() {
        super(new BotAgiScript(streamHost, streamPort));
    }

    public static void main(String[] args) {
        logger.info("started");
        final BotServer botServer = new BotServer();
        final StreamServer streamServer = new StreamServer(streamPort);

        try {


            streamServer.startup();
            logger.info("Stream server started on port {}", streamPort);
            botServer.startup();
        } catch (Exception e) {
            logger.error("connection is not working now, please again", e);
        } finally {
            streamServer.shutdown();
        }

    }
}