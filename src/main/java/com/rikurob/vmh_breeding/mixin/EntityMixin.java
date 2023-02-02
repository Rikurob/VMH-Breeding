package com.rikurob.vmh_breeding.mixin;

import com.rikurob.vmh_breeding.util.BreedingHandler;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.EggItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Entity.class)
public class EntityMixin {


   // @Inject(method = "spawnAtLocation(Lnet/minecraft/world/item/ItemStack;F)Lnet/minecraft/world/entity/item/ItemEntity;", at = @At("RETURN"), cancellable = true)
    //private void injectedSpawnAtLocation(ItemStack itemStack, float randY, CallbackInfoReturnable<ItemEntity> cir) {
   ///
    //}

    @ModifyVariable(method = "spawnAtLocation(Lnet/minecraft/world/item/ItemStack;F)Lnet/minecraft/world/entity/item/ItemEntity;", at = @At("HEAD"), ordinal = 0)
    private ItemStack onSpawnAtLocation(ItemStack itemStack) {
        // Perform your modification on the itemStack here
        if (((Entity)(Object)this) instanceof Chicken) {
            if (itemStack.getItem() instanceof EggItem) {
                itemStack = BreedingHandler.addSizeValuesToEgg(itemStack, ((Chicken) (Object) this));
            }
        }
        return itemStack;
    }
}

