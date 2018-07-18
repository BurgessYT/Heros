package com.burgess.Heros;

import com.burgess.Lisiteners.MainLisener;
import com.burgess.Lisiteners.PlayerCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MainClass extends JavaPlugin {
    public MainClass() {
    }

    public void onEnable() {
        this.getServer().getLogger().info(PublicData.pluginPrefix + "已运行");
        this.getServer().getPluginManager().registerEvents(new MainLisener(), this);
        this.loadConfig();
    }

    public void onDisable() {
        this.getServer().getLogger().info(PublicData.pluginPrefix + "已关闭");
    }

    private void loadConfig() {
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdirs();
        }

        PublicData.PluginFile = this.getDataFolder();
        PublicData.playerDataFile = new File(this.getDataFolder(), "player");
        if (!PublicData.playerDataFile.exists()) {
            PublicData.playerDataFile.mkdirs();
        }

        File configfile = new File(this.getDataFolder(), "config.yml");
        if (!configfile.exists()) {
            this.saveDefaultConfig();
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(configfile);
        PublicData.enableWorlds = config.getStringList("EnableWorlds");
        boolean forcespawn = config.getBoolean("ForceLoginSpawn");
        if (forcespawn) {
            PublicData.forceSpawnWorld = config.getString("ForceSpawnWorld");
        }

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return (new PlayerCommand()).command(sender, cmd, label, args);
    }
}
