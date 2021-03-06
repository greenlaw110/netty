/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.example.socksproxy;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundByteHandlerAdapter;


public final class DirectClientHandler extends ChannelInboundByteHandlerAdapter {
    private static final String name = "DIRECT_CLIENT_HANDLER";

    public static String getName() {
        return name;
    }
    private final CallbackNotifier cb;

    public DirectClientHandler(CallbackNotifier cb) {
        this.cb = cb;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.pipeline().remove(this);
        cb.onSuccess(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable) throws Exception {
        cb.onFailure(ctx, throwable);
    }

    @Override
    public void inboundBufferUpdated(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
    }
}
