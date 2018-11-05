package com.mrle.ch5.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import org.junit.Assert;
import org.junit.Test;

public class Demo {

    @Test
    public void m1(){
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", CharsetUtil.UTF_8);

        ByteBuf sliced = buf.slice(0, 15);
        System.out.println(sliced.toString(CharsetUtil.UTF_8));
        System.out.println(sliced.getByte(0));
        buf.setByte(0, (byte) 'J');
        System.out.println(buf.getByte(0));
        System.out.println(sliced.getByte(0));
    }

    @Test
    public void m2(){
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", CharsetUtil.UTF_8);
        ByteBuf copy = buf.copy(0, 15);

        System.out.println(copy.toString(CharsetUtil.UTF_8));

        System.out.println(copy.getByte(0));

        buf.setByte(0, (byte) 'J');
        System.out.println(buf.getByte(0) != copy.getByte(0));
        System.out.println(buf.getByte(0));
        System.out.println(copy.getByte(0));
    }

    @Test
    public void m3() {
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", CharsetUtil.UTF_8);

        System.out.println((char)buf.getByte(0));

        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();

        buf.setByte(0, (byte) 'B');

        System.out.println((char) buf.getByte(0));
        System.out.println(readerIndex);
        System.out.println(writerIndex);
        System.out.println(readerIndex == buf.readerIndex());
        System.out.println(writerIndex == buf.writerIndex());
    }

    @Test
    public void m4() {
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", CharsetUtil.UTF_8);
        System.out.println(buf.readerIndex());

        System.out.println((char) buf.readByte());

        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();

        buf.writeByte((byte) '?');

        System.out.println(readerIndex);
        System.out.println(writerIndex);
        System.out.println(buf.writerIndex());
        System.out.println(readerIndex == buf.readerIndex());
        System.out.println(writerIndex != buf.writerIndex());

        System.out.println(buf.toString(CharsetUtil.UTF_8));
    }
}
