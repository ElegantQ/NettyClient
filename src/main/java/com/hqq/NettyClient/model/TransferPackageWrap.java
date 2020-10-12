package com.hqq.NettyClient.model;

import io.netty.buffer.ByteBuf;
import lombok.Data;

/**
 * Created by huqiaoqian on 2020/10/12
 */
@Data
public class TransferPackageWrap {
    private Integer length;
    private byte[] bytes;
}
