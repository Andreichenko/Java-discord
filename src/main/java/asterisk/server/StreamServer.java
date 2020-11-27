package asterisk.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StreamServer {
    private static final Logger logger = LoggerFactory.getLogger(StreamServer.class);

    private final int port;

    private Channel listenChannel = null;

    private EventLoopGroup bossGroup = null;
    private EventLoopGroup workerGroup = null;

    public StreamServer(int port) {
        this.port = port;
    }

    public void startup() throws Exception {
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline();
                    }
                });
        listenChannel = bootstrap.bind(port).sync().channel();
    }

    public void shutdown() {
        try {
            if (listenChannel == null) {
                return;
            }

            listenChannel.close().sync();
        } catch (Exception e) {
            logger.error("shutdown stream server got exception", e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
