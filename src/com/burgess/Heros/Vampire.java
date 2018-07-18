package com.burgess.Heros;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Vampire {
    public Vampire() {
    }

    public static void skill1(final Player player) {
        final Hero vampire = (Hero)PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!vampire.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (vampire.skill1Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b黑蝙蝠 §7]§c技能 冷却时间还有 " + vampire.skill1Delay + "秒");
        } else if (!vampire.isSkill1Run) {
            vampire.isSkill1Run = true;
            vampire.skill1Delay = 20;
            player.sendMessage(PublicData.pluginPrefix + "§d您使用了 §7[ §b黑蝙蝠 §7] ");
            final Entity bat1 = player.getWorld().spawnEntity(player.getEyeLocation().add(2.0D, 0.0D, 0.0D), EntityType.BAT);
            final Entity bat2 = player.getWorld().spawnEntity(player.getEyeLocation().add(-2.0D, 0.0D, 0.0D), EntityType.BAT);
            final Entity bat3 = player.getWorld().spawnEntity(player.getEyeLocation().add(0.0D, 2.0D, 0.0D), EntityType.BAT);
            final Entity bat4 = player.getWorld().spawnEntity(player.getEyeLocation().add(0.0D, 0.0D, 2.0D), EntityType.BAT);
            final Entity bat5 = player.getWorld().spawnEntity(player.getEyeLocation().add(0.0D, 0.0D, -2.0D), EntityType.BAT);
            final Entity bat6 = player.getWorld().spawnEntity(player.getEyeLocation().add(2.0D, 0.0D, 2.0D), EntityType.BAT);
            final Entity bat7 = player.getWorld().spawnEntity(player.getEyeLocation().add(-2.0D, 0.0D, -2.0D), EntityType.BAT);
            final Entity bat8 = player.getWorld().spawnEntity(player.getEyeLocation().add(-2.0D, 0.0D, 2.0D), EntityType.BAT);
            final Entity bat9 = player.getWorld().spawnEntity(player.getEyeLocation().add(2.0D, 0.0D, -2.0D), EntityType.BAT);
            List<Entity> le = player.getNearbyEntities(3.0D, 3.0D, 3.0D);
            int nessTime = 60;
            Iterator var14 = le.iterator();

            while(var14.hasNext()) {
                Entity entity = (Entity)var14.next();
                if (entity instanceof Monster) {
                    ((Monster)entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, nessTime, 0));
                } else if (entity instanceof Player) {
                    ((Player)entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, nessTime, 0));
                    entity.sendMessage(PublicData.pluginPrefix + "§b" + player.getName() + " §d释放§7[ §b黑蝙蝠§7] §c,您获得失明效果");
                }
            }

            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            ((LivingEntity)bat1).setHealth(0.0D);
                            ((LivingEntity)bat2).setHealth(0.0D);
                            ((LivingEntity)bat3).setHealth(0.0D);
                            ((LivingEntity)bat4).setHealth(0.0D);
                            ((LivingEntity)bat5).setHealth(0.0D);
                            ((LivingEntity)bat6).setHealth(0.0D);
                            ((LivingEntity)bat7).setHealth(0.0D);
                            ((LivingEntity)bat8).setHealth(0.0D);
                            ((LivingEntity)bat9).setHealth(0.0D);
                            this.cancel();
                        }

                        PublicData.loadPlayerActionBar(vampire.player);
                        if (vampire.skill1Delay > 0) {
                            --vampire.skill1Delay;
                            if (vampire.skill1Delay == 17) {
                                ((LivingEntity)bat1).setHealth(0.0D);
                                ((LivingEntity)bat2).setHealth(0.0D);
                                ((LivingEntity)bat3).setHealth(0.0D);
                                ((LivingEntity)bat4).setHealth(0.0D);
                                ((LivingEntity)bat5).setHealth(0.0D);
                                ((LivingEntity)bat6).setHealth(0.0D);
                                ((LivingEntity)bat7).setHealth(0.0D);
                                ((LivingEntity)bat8).setHealth(0.0D);
                                ((LivingEntity)bat9).setHealth(0.0D);
                            }
                        } else {
                            vampire.skill1Delay = 0;
                            vampire.isSkill1Run = false;
                            this.cancel();
                        }
                    } catch (IllegalStateException var2) {
                        ((LivingEntity)bat1).setHealth(0.0D);
                        ((LivingEntity)bat2).setHealth(0.0D);
                        ((LivingEntity)bat3).setHealth(0.0D);
                        ((LivingEntity)bat4).setHealth(0.0D);
                        ((LivingEntity)bat5).setHealth(0.0D);
                        ((LivingEntity)bat6).setHealth(0.0D);
                        ((LivingEntity)bat7).setHealth(0.0D);
                        ((LivingEntity)bat8).setHealth(0.0D);
                        ((LivingEntity)bat9).setHealth(0.0D);
                        this.cancel();
                    }

                }
            };
            br.runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("WolfCraftPluginHeros"), 20L, 20L);
        }
    }

    public static void skill2(final Player player) {
        final Hero vampire = (Hero)PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!vampire.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (vampire.skill2Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b血花 §7]§c技能 冷却时间还有 " + vampire.skill2Delay + "秒");
        } else if (!vampire.isSkill2Run) {
            if (PublicData.boom.containsKey(vampire)) {
                PublicData.boom.remove(vampire);
            }

            vampire.isSkill2Run = true;
            vampire.skill2Delay = 30;
            vampire.enableSkill2 = true;
            PublicData.loadPlayerActionBar(vampire.player);
            player.sendMessage(PublicData.pluginPrefix + "§d您放置了 §7[ §b血花 §7] §d在脚下,三秒后生效");
            player.getWorld().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 0);
            player.getWorld().playEffect(player.getLocation().add(0.0D, 0.0D, 1.0D), Effect.MOBSPAWNER_FLAMES, 0);
            player.getWorld().playEffect(player.getLocation().add(0.0D, 1.0D, 0.0D), Effect.MOBSPAWNER_FLAMES, 0);
            player.getWorld().playEffect(player.getLocation().add(0.0D, 1.0D, 1.0D), Effect.MOBSPAWNER_FLAMES, 0);
            player.getWorld().playEffect(player.getLocation().add(1.0D, 0.0D, 0.0D), Effect.MOBSPAWNER_FLAMES, 0);
            player.getWorld().playEffect(player.getLocation().add(1.0D, 1.0D, 0.0D), Effect.MOBSPAWNER_FLAMES, 0);
            player.getWorld().playEffect(player.getLocation().add(1.0D, 1.0D, 1.0D), Effect.MOBSPAWNER_FLAMES, 0);
            player.getWorld().playEffect(player.getLocation().add(0.0D, 0.0D, -1.0D), Effect.MOBSPAWNER_FLAMES, 0);
            player.getWorld().playEffect(player.getLocation().add(0.0D, -1.0D, 0.0D), Effect.MOBSPAWNER_FLAMES, 0);
            player.getWorld().playEffect(player.getLocation().add(0.0D, -1.0D, -1.0D), Effect.MOBSPAWNER_FLAMES, 0);
            player.getWorld().playEffect(player.getLocation().add(-1.0D, 0.0D, 0.0D), Effect.MOBSPAWNER_FLAMES, 0);
            player.getWorld().playEffect(player.getLocation().add(-1.0D, -1.0D, 0.0D), Effect.MOBSPAWNER_FLAMES, 0);
            player.getWorld().playEffect(player.getLocation().add(-1.0D, -1.0D, -1.0D), Effect.MOBSPAWNER_FLAMES, 0);
            final Location boomLoc = new Location(player.getWorld(), (double)player.getLocation().getBlockX(), (double)player.getLocation().getBlockY(), (double)player.getLocation().getBlockZ());
            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            this.cancel();
                        }

                        PublicData.loadPlayerActionBar(vampire.player);
                        if (vampire.skill2Delay == 27) {
                            PublicData.boom.put(vampire, boomLoc);
                        }

                        if (vampire.skill2Delay > 0) {
                            --vampire.skill2Delay;
                        } else {
                            vampire.skill2Delay = 0;
                            vampire.isSkill2Run = false;
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
        final Hero vampire = (Hero)PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!vampire.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (vampire.skill3Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b迷惑 §7]§c技能 冷却时间还有 " + vampire.skill3Delay + "秒");
        } else if (!vampire.isSkill3Run) {
            vampire.isSkill3Run = true;
            vampire.enableSkill3 = true;
            vampire.skill3Delay = 50;
            player.sendMessage(PublicData.pluginPrefix + "§d您使用了 §7[ §b迷惑 §7] §d持续 §b 10 §d秒");
            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            this.cancel();
                        }

                        PublicData.loadPlayerActionBar(vampire.player);
                        if (vampire.skill3Delay == 40) {
                            vampire.enableSkill3 = false;
                        }

                        if (vampire.skill3Delay > 0) {
                            --vampire.skill3Delay;
                        } else {
                            vampire.skill3Delay = 0;
                            vampire.isSkill3Run = false;
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
        final Hero vampire = (Hero)PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!vampire.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (vampire.skill4Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b影 §7]§c技能 冷却时间还有 " + vampire.skill4Delay + "秒");
        } else if (!vampire.isSkill4Run) {
            vampire.isSkill4Run = true;
            vampire.enableSkill4 = true;
            if (vampire.ghostNum == 0) {
                vampire.ghostNum = 1;
            } else if (vampire.ghostNum == 1) {
                vampire.skill4Delay = 40;
                vampire.ghostNum = 0;
            }

            player.sendMessage(PublicData.pluginPrefix + "§d您使用了 §7[ §b影 §7] ");
            Vector v = player.getLocation().getDirection();
            Vector c = new Vector(v.getX() * 3.5D, v.getY() * 2.0D, v.getZ() * 3.5D);
            player.setVelocity(c);
            if (vampire.ghostNum == 0) {
                BukkitRunnable br = new BukkitRunnable() {
                    public void run() {
                        try {
                            if (!player.isOnline()) {
                                this.cancel();
                            }

                            PublicData.loadPlayerActionBar(vampire.player);
                            if (vampire.skill4Delay > 0) {
                                --vampire.skill4Delay;
                            } else {
                                vampire.skill4Delay = 0;
                                vampire.isSkill4Run = false;
                                this.cancel();
                            }
                        } catch (IllegalStateException var2) {
                            this.cancel();
                        }

                    }
                };
                br.runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("WolfCraftPluginHeros"), 20L, 20L);
                BukkitRunnable br1 = new BukkitRunnable() {
                    public void run() {
                        try {
                            if (!player.isOnline()) {
                                this.cancel();
                            }

                            if (vampire.enableSkill4) {
                                PublicData.particleAroundCreate(player.getLocation(), 2.0D, Effect.VOID_FOG);
                                PublicData.particleAroundCreate(player.getLocation().add(0.0D, -0.8D, 0.0D), 2.0D, Effect.VOID_FOG);
                                PublicData.particleAroundCreate(player.getLocation().add(0.0D, 0.8D, 0.0D), 2.0D, Effect.VOID_FOG);
                                vampire.enableSkill4 = false;
                                Collection<Entity> s = vampire.player.getNearbyEntities(2.0D, 2.0D, 2.0D);
                                Iterator it = s.iterator();

                                while(it.hasNext()) {
                                    Entity entity = (Entity)it.next();
                                    if (!entity.isDead() && entity instanceof LivingEntity) {
                                        ((LivingEntity)entity).damage((double)((int)((double)vampire.level * 0.2D) + 8), vampire.player);
                                        entity.sendMessage(PublicData.pluginPrefix + "§b" + vampire.player.getName() + " §d释放§7[ §b影 §7] §c,对您造成冲击，受到 §b" + ((int)((double)vampire.level * 0.2D) + 8) + " §c点伤害");
                                    }
                                }
                            }
                        } catch (IllegalStateException var4) {
                            this.cancel();
                        }

                    }
                };
                br1.runTaskLater(Bukkit.getPluginManager().getPlugin("WolfCraftPluginHeros"), 10L);
            } else if (vampire.ghostNum == 1) {
                vampire.isSkill4Run = false;
            }

        }
    }
}
