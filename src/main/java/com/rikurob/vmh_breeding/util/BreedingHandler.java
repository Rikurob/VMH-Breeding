package com.rikurob.vmh_breeding.util;

import com.rikurob.vmh_breeding.config.VmhBreedingConfig;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import com.rikurob.vmh.util.ScalingHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BreedingHandler {

    public static HashMap<AgeableMob,ArrayList<Mob>> breedingMobData=new HashMap<>();

    public static void addToBreedingData(Mob parentA, Mob parentB, AgeableMob child){
        breedingMobData.put(child,new ArrayList<>(Arrays.asList(parentA,parentB)));
    }
    public static Mob getParentA(AgeableMob child){
        if(breedingMobData.containsKey(child))
            return breedingMobData.get(child).get(0);
        return null;
    }

    public static Mob getParentB(AgeableMob child){
        if(breedingMobData.containsKey(child))
            return breedingMobData.get(child).get(1);
        return null;
    }

    public static void removeFromBreedingData(AgeableMob child){
        breedingMobData.remove(child);
    }


    //Scale Entity by Parent Mobs
    public static void ScaleEntityByParents(AgeableMob child, Mob parentA, Mob parentB){
        Object[]entityDataParentA= ScalingHandler.getEntityDataFromNBTData(parentA);
        double minA = (double) entityDataParentA[0],maxA = (double) entityDataParentA[1], medianA = (double) entityDataParentA[2];

        int distA = (int) entityDataParentA[3];

        float scaleA=ScalingHandler.getScale(parentA);


        Object[]entityDataParentB= ScalingHandler.getEntityDataFromNBTData(parentB);
        double minB = (double) entityDataParentB[0],maxB = (double) entityDataParentB[1], medianB = (double) entityDataParentB[2];

        int distB = (int) entityDataParentB[3];

        float scaleB=ScalingHandler.getScale(parentB);


        double minC=Math.min(minA,minB), maxC=Math.max(maxA,maxB), medianC=(medianA+medianB)/2;

        int distC=Math.max(distA,distB);
        if(Math.random()>0.50)
            distC=distA;
        else
            distC=distB;

        float scaleMin=Math.max(Math.max(scaleA,scaleB)*(float)(VmhBreedingConfig.breedingCoefficient),(float)maxC);
        float scaleMax=Math.min(Math.min(scaleA,scaleB)*(float)(1/ VmhBreedingConfig.breedingCoefficient),(float)minC);

        float scale=ScalingHandler.setScalingValue(scaleMin,scaleMax,medianC,distC);


        ScalingHandler.ScaleEntity(child,minC,maxC,medianC,distC,scale);
    }

    public static ItemStack addSizeValuesToEgg(ItemStack egg, Entity chicken){
        ItemStack eggCopy=egg.copy();
        double min=chicken.getPersistentData().getDouble("vmhScaleMin");
        double max=chicken.getPersistentData().getDouble("vmhScaleMax");;
        double mean=chicken.getPersistentData().getDouble("vmhScaleMean");;
        double median=chicken.getPersistentData().getDouble("vmhScaleMedian");;
        int dist=(int)chicken.getPersistentData().getDouble("vmhScaleDistribution");;
        String biome=chicken.getPersistentData().getString("vmhBiomeSpawnedIn");;
        double scaleA=chicken.getPersistentData().getDouble("vmhScale");;
        float scaleMin=(float)Math.max(scaleA* VmhBreedingConfig.breedingCoefficient,scaleA);
        float scaleMax=(float)Math.min(scaleA/ VmhBreedingConfig.breedingCoefficient,scaleA);
        float scale = ScalingHandler.setScalingValue(scaleMin,scaleMax,median,dist);

        eggCopy.getOrCreateTag().putDouble("vmhScale", scale);
        eggCopy.getOrCreateTag().putDouble("vmhScaleMin", min);
        eggCopy.getOrCreateTag().putDouble("vmhScaleMax", max);
        eggCopy.getOrCreateTag().putDouble("vmhScaleMean", mean);
        eggCopy.getOrCreateTag().putDouble("vmhScaleMedian", median);
        eggCopy.getOrCreateTag().putDouble("vmhScaleDistribution", dist);
        eggCopy.getOrCreateTag().putString("vmhBiomeSpawnedIn", biome);

        return eggCopy;
    }
}
