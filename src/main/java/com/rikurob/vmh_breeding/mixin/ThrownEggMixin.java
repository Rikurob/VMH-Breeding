package com.rikurob.vmh_breeding.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import com.rikurob.vmh.util.ScalingHandler;

@Mixin(ThrownEgg.class)
public class ThrownEggMixin {

    @ModifyVariable(method="onHit",at=@At("STORE"), ordinal=0)
    private Chicken injectedOnHit(Chicken chicken) {
        ItemStack egg = ((ThrownEgg) (Object) this).getItem();

        if(egg.getOrCreateTag().getDouble("vmhScaleMax")>0) {
            chicken = EntityType.CHICKEN.create(((ThrownEgg) (Object) this).level);
            ScalingHandler.ScaleEntity(chicken, egg.getOrCreateTag().getDouble("vmhScaleMin"), egg.getOrCreateTag().getDouble("vmhScaleMax"), egg.getOrCreateTag().getDouble("vmhScaleMedian"), (int) egg.getOrCreateTag().getDouble("vmhScaleDistribution"), ScalingHandler.getScale(egg));
            chicken.getPersistentData().putBoolean("vmhChickenFromEgg", true);
        }
        else{
            ScalingHandler.ScaleEntity(chicken);
        }
        return chicken;
    }

    @ModifyConstant(method = "onHit", constant = @Constant(intValue = 32,ordinal = 0))
    private int onHitPercentChanceSpawnExtra(int value) {
        return Math.round(value* ScalingHandler.getScale(((ThrownEgg)(Object)this)));
    }

    @ModifyConstant(method = "onHit", constant = @Constant(intValue = 4,ordinal = 0))
    private int onHitSpawnExtraValue(int value) {
        return Math.round(value* ScalingHandler.getScale(((ThrownEgg)(Object)this)));
    }
}
