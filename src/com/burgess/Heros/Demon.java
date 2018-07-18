package com.burgess.Heros;

import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Demon {
    public Demon() {
    }

    public static void skill1(final Player player) {
        final Hero demon = (Hero)PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!demon.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (demon.skill1Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b爆炎 §7]§c技能 冷却时间还有 " + demon.skill1Delay + "秒");
        } else if (!demon.isSkill1Run) {
            demon.isSkill1Run = true;
            demon.skill1Delay = 30;
            demon.enableSkill1 = true;
            player.sendMessage(PublicData.pluginPrefix + "§d您释放了 §7[ §b爆炎 §7] ");
            PublicData.particleFrontCreate(player.getLocation(), 4.0D, Effect.FLAME);
            double damage = (double)demon.level * 0.5D + 5.0D;
            List<Entity> nearEntity = player.getNearbyEntities(4.0D, 4.0D, 4.0D);
            Iterator var6 = nearEntity.iterator();

            while(var6.hasNext()) {
                Entity entity = (Entity)var6.next();
                if (player.getWorld().getPVP() && entity instanceof Player) {
                    ((Player)entity).damage(damage);
                    entity.sendMessage(PublicData.pluginPrefix + "§b" + player.getName() + " §d释放§7[ §b爆炎 §7]§d,对您造成灼烧，受到 §b" + damage + " §d点伤害");
                }

                if (entity instanceof LivingEntity && !(entity instanceof Player)) {
                    ((LivingEntity)entity).damage(damage);
                }
            }

            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            this.cancel();
                        }

                        PublicData.loadPlayerActionBar(demon.player);
                        if (demon.skill1Delay > 0) {
                            --demon.skill1Delay;
                        } else {
                            demon.skill1Delay = 0;
                            demon.isSkill1Run = false;
                            this.cancel();
                        }
                    } catch (IllegalStateException var2) {
                        ;
                    }

                }
            };
            br.runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("WolfCraftPluginHeros"), 20L, 20L);
        }
    }

    public static void skill2(final Player player) {
        final Hero demon = (Hero)PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!demon.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (demon.skill2Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b弱化 §7]§c技能 冷却时间还有 " + demon.skill2Delay + "秒");
        } else if (!demon.isSkill2Run) {
            demon.isSkill2Run = true;
            demon.skill2Delay = 20;
            demon.enableSkill2 = true;
            player.sendMessage(PublicData.pluginPrefix + "§d您释放了 §7[ §b弱化 §7] §d下一次攻击附带弱化效果");
            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            this.cancel();
                        }

                        PublicData.loadPlayerActionBar(demon.player);
                        if (demon.skill2Delay > 0) {
                            --demon.skill2Delay;
                        } else {
                            demon.skill2Delay = 0;
                            demon.isSkill2Run = false;
                            this.cancel();
                        }
                    } catch (IllegalStateException var2) {
                        ;
                    }

                }
            };
            br.runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("WolfCraftPluginHeros"), 20L, 20L);
        }
    }

    public static void skill3(final Player player) {
        final Hero demon = (Hero)PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!demon.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (demon.skill3Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b极灸 §7]§c技能 冷却时间还有 " + demon.skill3Delay + "秒");
        } else if (!demon.isSkill3Run) {
            demon.isSkill3Run = true;
            demon.skill3Delay = 40;
            demon.enableSkill3 = true;
            player.sendMessage(PublicData.pluginPrefix + "§d您释放了 §7[ §b极灸 §7] ");

            for(int i = 0; i < 15; ++i) {
                SmallFireball sfb = (SmallFireball)demon.player.getWorld().spawn(demon.player.getEyeLocation().add(demon.player.getEyeLocation().getDirection().multiply(2).add(new Vector(Math.random() * 2.0D - 1.0D, 0.0D, 0.0D)).getX(), demon.player.getEyeLocation().getDirection().getY(), demon.player.getEyeLocation().getDirection().multiply(2).add(new Vector(0.0D, 0.0D, Math.random() * 2.0D - 1.0D)).getZ()), SmallFireball.class);
                sfb.setShooter(player);
            }

            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            this.cancel();
                        }

                        PublicData.loadPlayerActionBar(demon.player);
                        if (demon.skill3Delay > 0) {
                            --demon.skill3Delay;
                        } else {
                            demon.skill3Delay = 0;
                            demon.isSkill3Run = false;
                            demon.enableSkill3 = false;
                            this.cancel();
                        }
                    } catch (IllegalStateException var2) {
                        ;
                    }

                }
            };
            br.runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("WolfCraftPluginHeros"), 20L, 20L);
        }
    }

    public static void skill4(final Player player) {
        final Hero demon = (Hero)PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!demon.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (demon.skill4Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b狂化 §7] §c技能 冷却时间还有 " + demon.skill4Delay + "秒");
        } else if (!demon.isSkill4Run) {
            demon.isSkill4Run = true;
            demon.skill4Delay = demon.level / 2 + 33;
            demon.enableSkill4 = true;
            player.sendMessage(PublicData.pluginPrefix + "§d您释放了 §7[ §b狂化 §7]  §b" + (demon.level / 2 + 3) + " §d秒内获得狂化");
            PublicData.particleAroundCreate(player.getLocation(), 2.0D, Effect.COLOURED_DUST);
            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            this.cancel();
                        }

                        PublicData.loadPlayerActionBar(demon.player);
                        if (demon.skill4Delay > 0) {
                            --demon.skill4Delay;
                            if (demon.skill1Delay == 30) {
                                demon.enableSkill4 = false;
                            }
                        } else {
                            demon.skill4Delay = 0;
                            demon.isSkill4Run = false;
                            this.cancel();
                        }
                    } catch (IllegalStateException var2) {
                        ;
                    }

                }
            };
            br.runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("WolfCraftPluginHeros"), 20L, 20L);
        }
    }
}
