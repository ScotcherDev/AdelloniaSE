package dev.scotcher;

import dev.scotcher.world.VoidGenerator;
import dev.scotcher.world.biomeprovider.ProviderVoidGenerator;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginWorldGenerators {

    public static void registerWorldGenerators(JavaPlugin plugin) {

    }

    public static ChunkGenerator getDefaultGenerator() {
        return new VoidGenerator();
    }

    public static BiomeProvider getDefaultBiomeProvider() {
        return new ProviderVoidGenerator();
    }
}
