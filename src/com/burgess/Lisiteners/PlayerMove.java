package com.burgess.Lisiteners;

import com.burgess.Heros.Hero;
import com.burgess.Heros.PublicData;
import java.util.Iterator;
import java.util.Map.Entry;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove {
    PlayerMoveEvent move;

    public PlayerMove() {
        this((PlayerMoveEvent)null);
    }

    public PlayerMove(PlayerMoveEvent move) {
        this.move = move;
    }

    public void onPlayerMove() {
        try {
            if (this.move.getPlayer().getGameMode() == GameMode.SPECTATOR) {
                if (this.move.getFrom().getBlockX() != this.move.getTo().getBlockX() || this.move.getFrom().getBlockY() != this.move.getTo().getBlockY() || this.move.getFrom().getBlockZ() != this.move.getTo().getBlockZ()) {
                    this.move.setCancelled(true);
                }

                if (PublicData.onlineHero.containsKey(this.move.getPlayer().getName())) {
                    Hero hero = (Hero)PublicData.onlineHero.get(this.move.getPlayer().getName());
                    if (hero.hero.equals(Hero.TreeElves) || hero.hero.equals(Hero.Knight) || hero.hero.equals(Hero.Demon)) {
                        hero.stopHealthRunnable();
                    }
                }

            } else {
                Location to = this.move.getTo();
                if (!PublicData.boom.isEmpty()) {
                    Iterator var3 = PublicData.boom.entrySet().iterator();

                    while(var3.hasNext()) {
                        Entry<Hero, Location> boomL = (Entry)var3.next();
                        double distance = (double)Math.abs(to.getBlockX() - ((Location)boomL.getValue()).getBlockX() + (to.getBlockY() - ((Location)boomL.getValue()).getBlockY()) + (to.getBlockZ() - ((Location)boomL.getValue()).getBlockZ()));
                        if (distance <= 1.0D && PublicData.onlineHero.containsKey(this.move.getPlayer().getName())) {
                            Hero moveh = (Hero)PublicData.onlineHero.get(this.move.getPlayer().getName());
                            if (!moveh.player.getName().equals(((Hero)boomL.getKey()).player.getName())) {
                                double damage = (double)((Hero)boomL.getKey()).level * 0.3D + 5.0D;
                                this.move.getPlayer().damage(damage, ((Hero)boomL.getKey()).player);
                                PublicData.particleAroundCreate((Location)boomL.getValue(), 1.5D, Effect.LAVA_POP);
                                ((Hero)boomL.getKey()).enableSkill2 = false;
                                ((Hero)boomL.getKey()).player.sendMessage(PublicData.pluginPrefix + "§d您的 §7[ §b血花 §7] §d已被 §b" + this.move.getPlayer().getName() + " §d引爆");
                                PublicData.loadPlayerActionBar(((Hero)boomL.getKey()).player);
                                PublicData.boom.remove(boomL.getKey());
                            }
                        }
                    }
                }

                if (PublicData.onlineHero.containsKey(this.move.getPlayer().getName())) {
                    Hero hero = (Hero)PublicData.onlineHero.get(this.move.getPlayer().getName());
                    Material material;
                    if (hero.hero.equals(Hero.TreeElves)) {
                        if (to.getBlockX() == hero.arrowLoc.getBlockX() && to.getBlockY() == hero.arrowLoc.getBlockY() && to.getBlockZ() == hero.arrowLoc.getBlockZ()) {
                            material = hero.player.getLocation().add(0.0D, -1.0D, 0.0D).getBlock().getType();
                            if (!material.isBlock() || !material.equals(Material.GRASS) && !material.equals(Material.DIRT) && !material.equals(Material.WOOD) && !material.equals(Material.LOG) && !material.equals(Material.LOG_2) && !material.equals(Material.LEAVES) && !material.equals(Material.LEAVES_2) && !material.equals(Material.MOSSY_COBBLESTONE) && !material.equals(Material.SNOW) && !material.equals(Material.SNOW_BLOCK) && !material.equals(Material.PUMPKIN) && !material.equals(Material.RED_MUSHROOM) && !material.equals(Material.BROWN_MUSHROOM) && !material.equals(Material.MELON_BLOCK) && !material.equals(Material.MYCEL) && !material.equals(Material.HAY_BLOCK)) {
                                hero.stopHealthRunnable();
                                hero.pbNum = 0;
                                PublicData.loadPlayerActionBar(hero.player);
                            } else {
                                hero.startHealthRunnable(20, 20);
                                hero.pbNum = 1;
                                PublicData.loadPlayerActionBar(hero.player);
                            }
                        }

                        hero.arrowLoc = to;
                    } else if (!hero.hero.equals(Hero.Knight) && hero.hero.equals(Hero.Demon)) {
                        material = hero.player.getLocation().getBlock().getType();
                        if (!material.equals(Material.LAVA) && !material.equals(Material.STATIONARY_LAVA)) {
                            hero.stopHealthRunnable();
                        } else {
                            hero.startHealthRunnable(10, 10);
                        }
                    }
                }

            }
        } catch (Exception var9) {
            ;
        }
    }
}
