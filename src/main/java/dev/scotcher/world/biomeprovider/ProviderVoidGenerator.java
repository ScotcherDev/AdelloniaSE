package dev.scotcher.world.biomeprovider;

import com.google.common.collect.Lists;
import org.bukkit.block.Biome;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.WorldInfo;
import org.bukkit.util.noise.SimplexOctaveGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class ProviderVoidGenerator extends BiomeProvider {

    @Override
    public @NotNull Biome getBiome(@NotNull WorldInfo worldInfo, int x, int y, int z) {
        Random random = new Random(worldInfo.getSeed());

        SimplexOctaveGenerator gen = new SimplexOctaveGenerator(random, 6);
        gen.setScale(0.01);

        double noise = gen.noise(x, z, 1, 1, true);

        return noise < 0 ? Biome.SNOWY_TAIGA : Biome.FROZEN_PEAKS;
    }

    @Override
    public @NotNull List<Biome> getBiomes(@NotNull WorldInfo worldInfo) {
        return Lists.newArrayList(
                Biome.FROZEN_PEAKS,
                Biome.ICE_SPIKES,
                Biome.SNOWY_PLAINS,
                Biome.SNOWY_SLOPES,
                Biome.SNOWY_TAIGA
        );
    }
}
