package com.lumiscosity.aa4atlas.mixin;

import com.bawnorton.mixinsquared.api.MixinCanceller;

import java.util.List;
import java.util.Objects;

public class AtlasMixinCanceller implements MixinCanceller {
    @Override
    public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
        return Objects.equals(mixinClassName, "folk.sisby.antique_atlas.mixin.MixinItemStack");
    }
}