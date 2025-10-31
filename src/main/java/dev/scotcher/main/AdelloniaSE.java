package dev.scotcher.main;

import dev.scotcher.*;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jspecify.annotations.Nullable;

public final class AdelloniaSE extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginConfigurations.registerConfigurations(this);

        PluginWorldGenerators.registerWorldGenerators(this);
        PluginDatapacks.registerDatapacks(this);

        PluginItems.registerItems(this);
        PluginBlocks.registerBlocks(this);
        PluginEntities.registerEntities(this);

        PluginScoreboards.registerScoreboards(this);

        PluginCommands.registerCommands(this);
        PluginEvents.registerEvents(this);

        PluginTasks.registerTasks(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        PluginWorldGenerators.regenerateDefaultWorld();
    }

    @Override
    public @Nullable ChunkGenerator getDefaultWorldGenerator(String worldName, @Nullable String id) {
        return PluginWorldGenerators.getDefaultGenerator();
    }

    @Override
    public @Nullable BiomeProvider getDefaultBiomeProvider(String worldName, @Nullable String id) {
        return PluginWorldGenerators.getDefaultBiomeProvider();
    }
}
