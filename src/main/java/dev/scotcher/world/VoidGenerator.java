package dev.scotcher.world;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.bukkit.util.noise.SimplexOctaveGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class VoidGenerator extends ChunkGenerator {

    @Override
    public void generateNoise(@NotNull WorldInfo worldInfo, @NotNull Random r, int chunkX, int chunkZ, @NotNull ChunkGenerator.ChunkData chunkData) {
        Random random = new Random(worldInfo.getSeed());

        SimplexOctaveGenerator landscapeGenerator = new SimplexOctaveGenerator(random, 3);
        landscapeGenerator.setScale(0.008);

        SimplexOctaveGenerator abyssGenerator = new SimplexOctaveGenerator(random, 3);
        abyssGenerator.setScale(0.02);

        SimplexOctaveGenerator subIslandLandscapeGenerator = new SimplexOctaveGenerator(random, 3);
        subIslandLandscapeGenerator.setScale(0.008);

        SimplexOctaveGenerator biomeGenerator = new SimplexOctaveGenerator(random, 3);
        biomeGenerator.setScale(0.008);

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = chunkX * 16 + x;
                int worldZ = chunkZ * 16 + z;

                double landscapeNoise = landscapeGenerator.noise(worldX, worldZ, 1, 0.2, true);
                double abyssNoise = abyssGenerator.noise(worldX, worldZ, 1, 2, true);
                double subIslandLandscapeNoise = subIslandLandscapeGenerator.noise(worldX, worldZ, 4, 3, true);
                double biomeNoise = biomeGenerator.noise(worldX, worldZ, 1, 2, true);

                boolean generateSolid = abyssNoise > 0.2;

                int landscapeHeight = (int) (landscapeNoise * 40 + 60);
                if (landscapeHeight > chunkData.getMaxHeight()) landscapeHeight = chunkData.getMaxHeight();

                int subIslandLandscapeHeight = (int) (subIslandLandscapeNoise * 30 + 30);
                if (subIslandLandscapeHeight > chunkData.getMaxHeight()) subIslandLandscapeHeight = chunkData.getMaxHeight();

                if (!generateSolid) continue;

                for (int y = subIslandLandscapeHeight; y < landscapeHeight; y++) {
                    chunkData.setBlock(x, y, z, Material.CYAN_CONCRETE);
                }

                chunkData.setBlock(x, landscapeHeight, z, Material.LIME_CONCRETE);
                chunkData.setBlock(x, landscapeHeight - 1, z, Material.BROWN_CONCRETE);
            }
        }
    }

    @Override
    public void generateBedrock(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkGenerator.ChunkData chunkData) {

    }

    @Override
    public void generateCaves(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkGenerator.ChunkData chunkData) {

    }

    @Override
    public void generateSurface(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkGenerator.ChunkData chunkData) {

    }

    @Override
    public boolean shouldGenerateSurface() {
        return false;
    }

    @Override
    public boolean shouldGenerateStructures() {
        return false;
    }

    @Override
    public boolean shouldGenerateCaves() {
        return false;
    }

    @Override
    public boolean shouldGenerateMobs() {
        return false;
    }

    @Override
    public boolean shouldGenerateDecorations() {
        return false;
    }
}
