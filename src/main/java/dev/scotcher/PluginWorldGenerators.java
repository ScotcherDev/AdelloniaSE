package dev.scotcher;

import dev.scotcher.world.VoidGenerator;
import dev.scotcher.world.biomeprovider.ProviderVoidGenerator;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.codehaus.plexus.util.FileUtils;

import java.io.IOException;
import java.util.List;

public class PluginWorldGenerators {

    public static void registerWorldGenerators(JavaPlugin plugin) {

    }

    public static void regenerateDefaultWorld() {
        List<World> worlds = Bukkit.getWorlds();
        World world = worlds.getFirst();
        if (world == null) return;

        Bukkit.unloadWorld(world, false);

        try {
            FileUtils.deleteDirectory(world.getWorldFolder());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ChunkGenerator getDefaultGenerator() {
        try {
            return new VoidGenerator();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static BiomeProvider getDefaultBiomeProvider() {
        return new ProviderVoidGenerator();
    }
}
