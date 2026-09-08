package com.lumiscosity.aa4atlas;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.NetworkSyncedItem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class EmptyAtlasItem extends NetworkSyncedItem {
    public EmptyAtlasItem(Item.Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack handStack = user.getStackInHand(hand);
        if (world.isClient) {
            return TypedActionResult.success(handStack);
        } else {
            if (!user.getAbilities().creativeMode) {
                handStack.decrement(1);
            }

            user.incrementStat(Stats.USED.getOrCreateStat(this));
            user.getWorld().playSoundFromEntity((PlayerEntity)null, user, SoundEvents.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, user.getSoundCategory(), 1.0F, 1.0F);
            ItemStack newAtlas = AtlasItem.createAtlas(world);
            if (!handStack.isEmpty()) {
                if (!user.getInventory().insertStack(newAtlas.copy())) {
                    user.dropItem(newAtlas, false);
                }

            }
            return TypedActionResult.consume(newAtlas);
        }
    }
}