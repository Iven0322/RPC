package com.iven.ivenrpc.fault.tolerant;

import com.iven.ivenrpc.spi.SpiLoader;

public class TolerantStartegyFactory {
    static{
        SpiLoader.load(TolerantStrategy.class);
    }

    private static final TolerantStrategy DEFAULT_TOLERANT_STRATEGY = new FailFastTolerantStrategy();

    public static TolerantStrategy getInstance(String key){
        return SpiLoader.getInstance(TolerantStrategy.class,key);
    }
}
