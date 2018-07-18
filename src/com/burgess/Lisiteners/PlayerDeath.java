package com.burgess.Lisiteners;

import com.burgess.Heros.Hero;
import com.burgess.Heros.PublicData;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerDeath {
    PlayerDeathEvent death;

    public PlayerDeath() {
        this((PlayerDeathEvent)null);
    }

    public PlayerDeath(PlayerDeathEvent death) {
        this.death = death;
    }

    public void onPlayerDeath() {
        final Location deathLoc = this.death.getEntity().getLocation();
        final Player player = this.death.getEntity();
        PlayerPreAutoRespawnEvent ppare = new PlayerPreAutoRespawnEvent(player, deathLoc);
        Bukkit.getPluginManager().callEvent(ppare);
        if (!ppare.isCancelled()) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("WolfCraftPluginHeros"), new Runnable() {
                public void run() {
                    player.spigot().respawn();
                    Location respawnLoc = player.getLocation();
                    player.teleport(deathLoc);
                    player.setGameMode(GameMode.SPECTATOR);
                    final Hero h = (Hero)PublicData.onlineHero.get(player.getName());
                    if (h.hero.equals(Hero.Ghost) && h.enableSkill2) {
                        h.respawnLoc = h.player.getLocation();
                        h.player.sendMessage(PublicData.pluginPrefix + "§7[ §b不死之躯 §7] §d效果生效");
                        h.enableSkill2 = false;
                    }

                    BukkitRunnable br = new BukkitRunnable() {
                        public void run() {
                            if (h.deathTime == 1) {
                                player.setGameMode(GameMode.SURVIVAL);
                                if (h.respawnLoc == null) {
                                    player.teleport(player.getWorld().getSpawnLocation());
                                    h.deathTime = 10;
                                    this.cancel();
                                } else {
                                    player.teleport(deathLoc);
                                    h.deathTime = 10;
                                    h.respawnLoc = null;
                                    this.cancel();
                                }
                            } else if (h.respawnLoc == null) {
                                --h.deathTime;
                                player.sendMessage("距离复活还有 " + h.deathTime + "秒");
                            } else {
                                player.setGameMode(GameMode.SURVIVAL);
                                player.teleport(deathLoc);
                                h.deathTime = 10;
                                h.respawnLoc = null;
                                this.cancel();
                            }

                        }
                    };
                    br.runTaskTimer(Bukkit.getPluginManager().getPlugin("WolfCraftPluginHeros"), 0L, 20L);
                    Bukkit.getPluginManager().callEvent(new PlayerAutoRespawnEvent(PlayerDeath.this.death.getEntity(), deathLoc, respawnLoc));
                }
            }, 0L);
        }
    }
}
