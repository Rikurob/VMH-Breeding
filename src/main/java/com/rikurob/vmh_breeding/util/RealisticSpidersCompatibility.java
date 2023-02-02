package com.rikurob.vmh_breeding.util;

public class RealisticSpidersCompatibility {

    private static final boolean REALISTIC_SPIDERS_LOADED = com.rikurob.vmh.util.ModLoaderUtils.isModLoaded("realistic_spiders");

    public static final RealisticSpidersCompatibility INSTANCE = new RealisticSpidersCompatibility();

    public boolean rsEnabled;

    private RealisticSpidersCompatibility()
    {
        this.rsEnabled = REALISTIC_SPIDERS_LOADED;
    }
}
