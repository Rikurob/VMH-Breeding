package com.rikurob.vmh_breeding.events;

import com.rikurob.vmh.util.RealisticSpidersCompatibility;
import com.rikurob.vmh_breeding.util.BreedingHandler;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class EntityJoinsLevelEvent {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onEntitySpawned(EntityJoinLevelEvent event) {
        execute(event, event.getEntity());
    }

    public static void execute(Entity entity) {
        execute(null, entity);
    }

    private static void execute(@Nullable Event event, Entity entity) {
        if (entity == null)
            return;
        if(entity instanceof Player)
            return;
        if (entity.getPersistentData().getBoolean("vmhSpawned"))
            return;
        if (RealisticSpidersCompatibility.INSTANCE.rsEnabled && (entity.getPersistentData().getBoolean("rsSpawned")))
            return;
        if(entity.getPersistentData().getBoolean("vmhChickenFromEgg"))
            return;
        //Scale Mob By Breeding Data
        if((entity instanceof AgeableMob _child) && (entity instanceof LivingEntity _livEnt ? _livEnt.isBaby() : true)) {
            if (BreedingHandler.breedingMobData.containsKey(_child)) {
                BreedingHandler.ScaleEntityByParents(_child, BreedingHandler.getParentA(_child), BreedingHandler.getParentB(_child));
                BreedingHandler.removeFromBreedingData(_child);
            }
        }
    }
}