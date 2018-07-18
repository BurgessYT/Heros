package com.burgess.Lisiteners;

import com.burgess.Heros.Hero;
import com.burgess.Heros.PublicData;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerJoin {
    PlayerJoinEvent event;

    public PlayerJoin() {
        this((PlayerJoinEvent)null);
    }

    public PlayerJoin(PlayerJoinEvent event) {
        this.event = event;
    }

    public void onPlayerJoin() {
        Player loginPlayer = this.event.getPlayer();
        if (PublicData.forceSpawnWorld != null) {
            World world = Bukkit.getServer().getWorld(PublicData.forceSpawnWorld);
            Location spawnLoc = world.getSpawnLocation();
            loginPlayer.teleport(spawnLoc);
        }

        if (loginPlayer.getGameMode() != GameMode.SURVIVAL && !loginPlayer.isOp()) {
            loginPlayer.setGameMode(GameMode.SURVIVAL);
        }

        String playername = loginPlayer.getName();
        File playerFile = new File(PublicData.PluginFile, File.separator + "player" + File.separator + playername + ".yml");
        YamlConfiguration playerconfig = null;
        if (!playerFile.exists()) {
            try {
                playerFile.createNewFile();
                new YamlConfiguration();
                playerconfig = YamlConfiguration.loadConfiguration(playerFile);
                playerconfig.set("Hero", "NormalHuman");
                playerconfig.set("Level", 1);
                playerconfig.set("XP", 0);
                playerconfig.save(playerFile);
            } catch (IOException var8) {
                var8.printStackTrace();
            }
        } else {
            new YamlConfiguration();
            playerconfig = YamlConfiguration.loadConfiguration(playerFile);
        }

        Hero oh = new Hero();
        oh.level = playerconfig.getInt("Level");
        oh.hero = playerconfig.getString("Hero");
        oh.xp = playerconfig.getInt("XP");
        oh.player = loginPlayer;
        oh.setHeroMaxHealth();
        PublicData.onlineHero.put(loginPlayer.getName(), oh);
        if (oh.hero.equals(Hero.Hunter) && PublicData.enableWorlds.contains(oh.player.getWorld().getName()) && oh.player.getItemInHand().getTypeId() == 261) {
            oh.player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 0), true);
        }

        if (oh.hero.equals(Hero.TreeElves)) {
            oh.arrowLoc = oh.player.getLocation();
        }

        Iterator var7 = loginPlayer.getServer().getOnlinePlayers().iterator();

        while(var7.hasNext()) {
            Player player = (Player)var7.next();
            player.sendMessage(PublicData.pluginPrefix + PublicData.translateHeroName(oh.hero) + playername + " 来到了狼族服务器");
        }

        PublicData.loadPlayerActionBar(loginPlayer);
        loginPlayer.sendMessage("当前等级为 " + ((Hero)PublicData.onlineHero.get(playername)).level);
    }
}
