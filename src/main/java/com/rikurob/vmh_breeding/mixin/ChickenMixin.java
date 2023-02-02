package com.rikurob.vmh_breeding.mixin;

import net.minecraft.world.entity.animal.Chicken;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import com.rikurob.vmh.util.ScalingHandler;

@Mixin(Chicken.class)
public class ChickenMixin {

    @ModifyConstant(method = "aiStep", constant = @Constant(intValue = 6000,ordinal = 0))
    private int injectedEggTime(int value) {
        return Math.round(value*ScalingHandler.getScale(((Chicken)(Object)this)));
    }


}
