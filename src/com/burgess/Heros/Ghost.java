package com.burgess.Heros;

import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Ghost {
    public Ghost() {
    }

    public static void skill1(final Player player) {
        final Hero ghost = (Hero)PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!ghost.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (ghost.skill1Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b亡灵体态 §7]§c技能 冷却时间还有 " + ghost.skill1Delay + "秒");
        } else if (!ghost.isSkill1Run) {
            ghost.isSkill1Run = true;
            ghost.enableSkill1 = true;
            ghost.skill1Delay = 20;
            player.sendMessage(PublicData.pluginPrefix + "§d您释放了 §7[ §b亡灵体态 §7] §d, §b5 §d秒内免疫伤害");
            PublicData.particleAroundCreate(ghost.player.getLocation(), 2.0D, Effect.PARTICLE_SMOKE);
            PublicData.loadPlayerActionBar(ghost.player);
            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            this.cancel();
                        }

                        PublicData.loadPlayerActionBar(ghost.player);
                        if (ghost.skill1Delay > 0) {
                            if (ghost.skill1Delay < 15) {
                                ghost.enableSkill1 = false;
                            }

                            --ghost.skill1Delay;
                        } else {
                            ghost.skill1Delay = 0;
                            ghost.isSkill1Run = false;
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
        final Hero ghost = (Hero)PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!ghost.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (ghost.skill2Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b不死之躯 §7]§c技能 冷却时间还有 " + ghost.skill2Delay + "秒");
        } else if (!ghost.isSkill2Run) {
            ghost.isSkill2Run = true;
            ghost.skill2Delay = 50;
            int skill2Time = (int)((double)ghost.level * 0.2D + 1.0D);
            ghost.enableSkill2 = true;
            player.sendMessage(PublicData.pluginPrefix + "§d您释放了 §7[ §b不死之躯 §7] §d,效果持续 §b" + skill2Time + " §d秒");
            PublicData.particleAroundMaxCreate(ghost.player.getLocation(), 2.5D, Effect.VOID_FOG);
            PublicData.loadPlayerActionBar(ghost.player);
            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            this.cancel();
                        }

                        PublicData.loadPlayerActionBar(ghost.player);
                        if (ghost.skill2Delay > 0) {
                            if ((double)ghost.skill2Delay < 50.0D - ((double)ghost.level * 0.2D + 1.0D)) {
                                ghost.enableSkill2 = false;
                            }

                            --ghost.skill2Delay;
                        } else {
                            ghost.skill2Delay = 0;
                            ghost.isSkill2Run = false;
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
        final Hero ghost = (Hero)PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!ghost.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (ghost.skill3Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b哀嚎毁灭 §7]§c技能 冷却时间还有 " + ghost.skill3Delay + "秒");
        } else if (!ghost.isSkill3Run) {
            if (ghost.ghostNum >= 3) {
                ghost.ghostNum -= 3;
                ghost.isSkill3Run = true;
                ghost.skill3Delay = 13;
                ghost.enableSkill3 = true;
                PublicData.loadPlayerActionBar(ghost.player);
                player.sendMessage(PublicData.pluginPrefix + "§d您释放了 §7[ §b哀嚎毁灭 §7]§d,下一次攻击附带效果");
                BukkitRunnable var2 = new BukkitRunnable() {
                    public void run() {
                        try {
                            if (!player.isOnline()) {
                                this.cancel();
                            }

                            PublicData.loadPlayerActionBar(ghost.player);
                            if (ghost.skill3Delay > 0) {
                                --ghost.skill3Delay;
                                if (ghost.skill3Delay >= 10) {
                                    PublicData.particleCircleCreate(ghost.player.getEyeLocation(), 3.0D, Effect.PARTICLE_SMOKE);
                                    List<Entity> le = ghost.player.getNearbyEntities(3.0D, 3.0D, 3.0D);
                                    double damage = (double)ghost.level * 0.1D + 8.0D;
                                    Iterator var5 = le.iterator();

                                    while(var5.hasNext()) {
                                        Entity entity = (Entity)var5.next();
                                        if (entity instanceof Monster) {
                                            ((Monster)entity).damage(damage, ghost.player);
                                        } else if (entity instanceof Player) {
                                            ((Player)entity).damage(damage, ghost.player);
                                        }
                                    }
                                }
                            } else {
                                ghost.skill3Delay = 0;
                                ghost.isSkill3Run = false;
                                this.cancel();
                            }
                        } catch (IllegalStateException var6) {
                            this.cancel();
                        }

                    }
                };
                var2.runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("WolfCraftPluginHeros"), 0L, 20L);
            } else {
                player.sendMessage(PublicData.pluginPrefix + "§7[ §d亡灵能量 §7] §c不足3点");
            }
        }
    }

    public static void skill4(Player player) {
    }

    public static double getDistanceDamage(double damage, Entity e1, Entity e2) {
        double distance = Math.abs(e1.getLocation().getX() - e2.getLocation().getX() + (e1.getLocation().getY() - e2.getLocation().getY()) + (e1.getLocation().getZ() - e2.getLocation().getZ()));
        if (distance <= 0.2D) {
            damage *= 2.0D;
        } else if (distance <= 1.0D) {
            damage *= 1.8D;
        } else if (distance <= 2.0D) {
            damage *= 1.6D;
        } else if (distance <= 3.0D) {
            damage *= 1.3D;
        } else {
            if (distance <= 4.0D) {
                return damage;
            }

            if (distance <= 6.0D) {
                damage *= 0.8D;
            } else if (distance <= 8.0D) {
                damage *= 0.7D;
            } else if (distance <= 10.0D) {
                damage *= 0.6D;
            } else if (distance <= 15.0D) {
                damage *= 0.5D;
            } else if (distance < 20.0D) {
                damage *= 0.3D;
            } else if (distance > 20.0D) {
                damage *= 0.2D;
            }
        }

        return damage;
    }
}
