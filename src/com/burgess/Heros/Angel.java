package com.burgess.Heros;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Angel {
    public Angel() {
    }

    public static void skill1(final Player player) {
        final Hero angel = (Hero)PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!angel.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (angel.skill1Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b羽翼 §7]§c技能 冷却时间还有 " + angel.skill1Delay + "秒");
        } else if (!angel.isSkill1Run) {
            angel.isSkill1Run = true;
            int se = (int)((double)angel.level * 0.3D + 5.0D);
            PublicData.particleFrontCreate(player.getLocation(), 3.0D, Effect.POTION_SWIRL_TRANSPARENT);
            player.setAllowFlight(true);
            player.setFlying(true);
            player.sendMessage(PublicData.pluginPrefix + "§d您使用了§7[ §b羽翼 §7]§d - 获得了 §b" + se + "§d 秒飞行时间");
            angel.skill1Delay = se + 15;
            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            this.cancel();
                        }

                        PublicData.loadPlayerActionBar(angel.player);
                        if (angel.skill1Delay > 0) {
                            --angel.skill1Delay;
                            if (angel.skill1Delay == 15) {
                                angel.player.setAllowFlight(false);
                                angel.player.setFlying(false);
                            }
                        } else {
                            angel.skill1Delay = 0;
                            angel.isSkill1Run = false;
                            this.cancel();
                        }
                    } catch (IllegalStateException var2) {
                        this.cancel();
                    }

                }
            };
            br.runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("WolfCraftPluginHeros"), 20L, 20L);
        }
    }

    public static void skill2(final Player player) {
        final Hero angel = (Hero)PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!angel.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (angel.skill2Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b守护 §7]§c技能 冷却时间还有 " + angel.skill2Delay + "秒");
        } else if (!angel.isSkill2Run) {
            angel.isSkill2Run = true;
            List<Entity> nearEntity = player.getNearbyEntities(4.0D, 4.0D, 4.0D);
            List<Player> nearPlayer = new ArrayList<>();
            nearPlayer.add(player);

            for (Object entity : nearEntity) {
                if (entity instanceof Player) {
                    nearPlayer.add((Player) entity);
                }
            }

            angel.player.getWorld().playSound(angel.player.getLocation(), Sound.RECORD_WARD, 1.0F, 1.0F);
            if (nearPlayer.size() == 1) {
                angel.targetPlayer = player;
            } else {
                for(int i = 0; i < nearPlayer.size() - 1; ++i) {
                    if (nearPlayer.get(i).getHealth() < nearPlayer.get(i + 1).getHealth()) {
                        angel.targetPlayer = nearPlayer.get(i);
                    } else {
                        angel.targetPlayer = nearPlayer.get(i + 1);
                    }
                }
            }

            angel.skill2Delay = 15;
            final double addOnceHealth = ((double)angel.level * 0.3D + 5.0D) / 10.0D;
            PublicData.particleAroundCreate(player.getLocation(), 4.0D, Effect.HEART);
            if (angel.targetPlayer.getName().equals(player.getName())) {
                player.sendMessage(PublicData.pluginPrefix + "§d您为§b自己§d开启了§7[ §b守护 §7]§d技能 ,每秒恢复 §b" + (int)addOnceHealth + " §d血");
            } else {
                player.sendMessage(PublicData.pluginPrefix + "§d您为队友§b" + angel.targetPlayer.getName() + "§d开启了§7[ §b守护 §7]§d技能 ,每秒恢复 §b" + (int)addOnceHealth + " §d血");
            }

            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            this.cancel();
                        }

                        PublicData.loadPlayerActionBar(angel.player);
                        if (angel.skill2Delay > 0) {
                            --angel.skill2Delay;
                            if (angel.skill2Delay >= 5) {
                                if (angel.targetPlayer.getHealth() + addOnceHealth > angel.targetPlayer.getMaxHealth()) {
                                    angel.targetPlayer.setHealth(angel.targetPlayer.getMaxHealth());
                                } else {
                                    angel.targetPlayer.setHealth(angel.targetPlayer.getHealth() + addOnceHealth);
                                }
                            }
                        } else {
                            angel.skill2Delay = 0;
                            angel.isSkill2Run = false;
                            this.cancel();
                        }
                    } catch (IllegalStateException var2) {
                        this.cancel();
                    }

                }
            };
            br.runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("WolfCraftPluginHeros"), 20L, 20L);
        }
    }

    public static void skill3(final Player player) {
        final Hero angel = (Hero)PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!angel.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (angel.skill3Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b圣光 §7]§c技能 冷却时间还有 " + angel.skill3Delay + "秒");
        } else if (!angel.isSkill3Run) {
            angel.isSkill3Run = true;
            angel.skill3Delay = 15;
            double damage = (double)angel.level * 0.2D + 6.0D;
            List<Entity> nearEntity = player.getNearbyEntities(6.0D, 6.0D, 6.0D);
            Iterator var6 = nearEntity.iterator();

            while(var6.hasNext()) {
                Entity entity = (Entity)var6.next();
                if (player.getWorld().getPVP() && entity instanceof Player) {
                    ((Player)entity).damage(damage, angel.player);
                    entity.sendMessage(PublicData.pluginPrefix + "§b" + player.getName() + " §d释放§7[ §b圣光 §7] §c,对您造成灼烧，受到 §b" + damage + " §c点伤害");
                }

                if (entity instanceof LivingEntity && !(entity instanceof Player)) {
                    ((LivingEntity)entity).damage(damage, angel.player);
                }
            }

            player.sendMessage(PublicData.pluginPrefix + "§d您释放了 §7[ §b圣光 §7]§d,圣光灼烧附近敌人造成 §c" + damage + " §d点伤害");
            PublicData.particleAroundCreate(player.getLocation(), 6.0D, Effect.COLOURED_DUST);
            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            this.cancel();
                        }

                        PublicData.loadPlayerActionBar(angel.player);
                        if (angel.skill3Delay > 0) {
                            --angel.skill3Delay;
                        } else {
                            angel.skill3Delay = 0;
                            angel.isSkill3Run = false;
                            this.cancel();
                        }
                    } catch (IllegalStateException var2) {
                        this.cancel();
                    }

                }
            };
            br.runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("WolfCraftPluginHeros"), 20L, 20L);
        }
    }

    public static void skill4(final Player player) {
        final Hero angel = (Hero)PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!angel.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (angel.skill4Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b重生 §7]§c技能 冷却时间还有 " + angel.skill4Delay + "秒");
        } else if (!angel.isSkill4Run) {
            angel.isSkill4Run = true;
            angel.skill4Delay = 70;
            int respawnBounds = 0;
            List<Entity> nearEntity = player.getNearbyEntities(8.0D, 8.0D, 8.0D);
            PublicData.particleCircleCreate(player.getLocation(), 8.0D, Effect.HAPPY_VILLAGER);
            angel.player.getWorld().playSound(angel.player.getLocation(), Sound.RECORD_CHIRP, 1.0F, 1.0F);
            Iterator var5 = nearEntity.iterator();

            while(var5.hasNext()) {
                Entity entity = (Entity)var5.next();
                if (entity instanceof Player && PublicData.onlineHero.containsKey(entity.getName())) {
                    Hero hero = PublicData.onlineHero.get(entity.getName());
                    if (((Player)entity).getGameMode() == GameMode.SPECTATOR) {
                        hero.respawnLoc = entity.getLocation();
                        entity.sendMessage(PublicData.pluginPrefix + "§d您被 §b" + player.getName() + " §d复活了!");
                        ++respawnBounds;
                    }
                }
            }

            player.sendMessage(PublicData.pluginPrefix + "§d您释放了 §7[ §b重生 §7]§d,复活了§b" + respawnBounds + " §d个人");
            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            this.cancel();
                        }

                        PublicData.loadPlayerActionBar(angel.player);
                        if (angel.skill4Delay > 0) {
                            --angel.skill4Delay;
                        } else {
                            angel.skill4Delay = 0;
                            angel.isSkill4Run = false;
                            this.cancel();
                        }
                    } catch (IllegalStateException var2) {
                        this.cancel();
                    }

                }
            };
            br.runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("WolfCraftPluginHeros"), 20L, 20L);
        }
    }
}
