package com.lumiscosity.aa4atlas;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;


public record AtlasOpenPayload() implements CustomPayload {
    public static CustomPayload.Id<AtlasOpenPayload> ID = new CustomPayload.Id<>(AtlasItem.GUI_PACKET_ID);
    public static PacketCodec<PacketByteBuf, AtlasOpenPayload> CODEC = PacketCodec.unit(new AtlasOpenPayload());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
