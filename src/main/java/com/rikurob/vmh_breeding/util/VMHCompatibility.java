package com.rikurob.vmh_breeding.util;

public class VMHCompatibility {

    private static final boolean VMH_LOADED = com.rikurob.vmh.util.ModLoaderUtils.isModLoaded("vmh");

    public static final VMHCompatibility INSTANCE = new VMHCompatibility();

    public boolean vmhEnabled;

    private VMHCompatibility()
    {
        this.vmhEnabled = VMH_LOADED;
    }
}
