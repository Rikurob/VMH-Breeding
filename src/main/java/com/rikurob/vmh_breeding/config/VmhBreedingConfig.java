package com.rikurob.vmh_breeding.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;


public class VmhBreedingConfig {
	public static final ForgeConfigSpec BREEDING;

	public static DoubleValue vmhBreedingTruncationCoefficient;
	public static BooleanValue vmhScaleByBreeding;

	public static BooleanValue vmhDoScaleNoise;

	public static BooleanValue vmhAddEggValues;

	static {
        ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();

		setupBreedingConfig(configBuilder);

		BREEDING= configBuilder.build();
	}

	private static void setupBreedingConfig(ForgeConfigSpec.Builder builder) {

		builder.push("Default Spawn Size/Distribution Values");
		vmhBreedingTruncationCoefficient = builder
				.comment("\nDetermines the coeffecient to multiply the max and min of a parents scale to use as the min/max value of the bred animal.\n",
				"\nNOTE:Currently the parents scales are used as the min and max values of the scaling, not the min and max values of the parents.\n")
				.translation("vmh.config.vmhBreedingTruncationCoefficient")
				.defineInRange("vmhBreedingTruncationCoefficient", 1.01D, 0.01D, 2D);
		builder.pop();

		builder.push("Scale By Breeding");
		vmhScaleByBreeding = builder
				.comment("\nEnables/disables scaling by breeding instead of type.\n")
				.translation("vmh.config.vmhScaleByBreeding")
				.define("vmhScaleByBreeding", true);
		builder.pop();

	}



	//DEFAULT VALUES IF CONFIG FAILS TO LOAD
	public static double breedingCoefficient=1.01D;
	public static boolean isScaleByBreeding=true;


	//SET SPLIT VALUES
	public static void loadForgeConfig() {
		isScaleByBreeding= vmhScaleByBreeding.get();
		breedingCoefficient= vmhBreedingTruncationCoefficient.get();
	}



}

