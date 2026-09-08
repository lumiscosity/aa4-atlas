package com.lumiscosity.aa4atlas;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.lumiscosity.aa4atlas.AA4Atlas.ANTIQUE_ATLAS;

public class AtlasItem extends Item {
    @NotNull
    static final Identifier GUI_PACKET_ID = Objects.requireNonNull(Identifier.of("aa4-atlas", "gui_packet"));

    public AtlasItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) return super.use(world, user, hand);
        ServerPlayNetworking.send((ServerPlayerEntity) user, GUI_PACKET_ID, PacketByteBufs.empty());
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    public static ItemStack createAtlas(World world) {
        ItemStack itemStack = new ItemStack(ANTIQUE_ATLAS);
        // TODO: draw the rest of the owl
        // createAtlasState(itemStack, world, world.getRegistryKey());
        return itemStack;
    }
}
