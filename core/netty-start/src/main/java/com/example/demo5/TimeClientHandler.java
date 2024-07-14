package com.example.demo5;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/*
One Small Caveat of Socket Buffer

In a stream-based transport such as TCP/IP,
received data is stored into a socket receive buffer.
Unfortunately, the buffer of a stream-based transport is not a queue of packets
but a queue of bytes. It means, even if you sent two messages as two independent packets,
an operating system will not treat them as two messages but as just a bunch of bytes.
Therefore, there is no guarantee that what you read is exactly what your remote peer wrote.
For example, let us assume that the TCP/IP stack of an operating system has received three packets:

abc def ghi

Because of this general property of a stream-based protocol,
there's a high chance of reading them in the following fragmented form in your application:

ab cdefg h i

Therefore, a receiving part, regardless it is server-side or client-side,
should defrag the received data into one or more meaningful frames
that could be easily understood by the application logic.
In the case of the example above, the received data should be framed like the following:

abc def ghi
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    private ByteBuf buf;

    /**
     * Gets called after the {@link ChannelHandler} was added to the actual context and it's ready to handle events.
     */
    //在 channelRead 之前 invoke
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        buf = ctx.alloc().buffer(4);
    }

    /**
     * Gets called after the {@link ChannelHandler} was removed from the actual context and it doesn't handle events
     * anymore.
     */
    //在 channelRead 之后 invoke
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        buf.release();
        buf = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf m = (ByteBuf) msg;
        buf.writeBytes(m);
        m.release();

        if (buf.readableBytes() >= 4) {
            long currentTimeMillis = (buf.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
