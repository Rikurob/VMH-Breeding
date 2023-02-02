package com.rikurob.vmh_breeding.events;

import com.rikurob.vmh_breeding.util.BreedingHandler;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class BabyEntitySpawnsEvent {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBabyEntitySpawned(BabyEntitySpawnEvent event) {
        execute(event, event.getChild(),event.getParentA(),event.getParentB());
    }

    public static void execute(AgeableMob child, Mob parentA, Mob parentB) {
        execute(null, child, parentA, parentB);
    }

    private static void execute(@Nullable Event event, AgeableMob child, Mob parentA, Mob parentB) {
        if (child == null)
            return;
        if(child.getPersistentData().getBoolean("vmhChickenFromEgg"))
            return;
        //Set Breeding Data to HashMap
        BreedingHandler.addToBreedingData(parentA,parentB,child);
    }
}