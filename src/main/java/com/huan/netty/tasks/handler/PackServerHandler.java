package com.huan.netty.tasks.handler;

import com.huan.netty.tasks.pack.Pack;
import com.huan.netty.tasks.provider.ProviderManager;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务器获取到具体的报文消息，交给具体的业务类去进行处理
 * 
 * @描述
 * @作者 huan
 * @时间 2017年12月26日 - 下午8:31:33
 */
@Slf4j
@Sharable
public class PackServerHandler extends SimpleChannelInboundHandler<Pack> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Pack msg) throws Exception {
		ProviderManager.invoked(ctx, msg);
	}

	/** 当发生了异常时，此方法调用 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.error("error:", cause);
		ctx.close();
	}

}
