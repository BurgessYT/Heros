package com.burgess.Heros;

import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class TreeElves {
    public TreeElves() {
    }

    public static void skill1(final Player player) {
        final Hero treeelves = PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!treeelves.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (treeelves.skill1Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b藤条束缚 §7]§c技能 冷却时间还有 " + treeelves.skill1Delay + "秒");
        } else if (!treeelves.isSkill1Run) {
            treeelves.isSkill1Run = true;
            treeelves.skill1Delay = 25;
            treeelves.enableSkill1 = true;
            player.sendMessage(PublicData.pluginPrefix + "§d您释放了 §7[ §b藤条束缚 §7]§d,下一次攻击附带效果");
            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            this.cancel();
                        }

                        PublicData.loadPlayerActionBar(treeelves.player);
                        if (treeelves.skill1Delay > 0) {
                            --treeelves.skill1Delay;
                        } else {
                            treeelves.skill1Delay = 0;
                            treeelves.isSkill1Run = false;
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
        final Hero treeelves = PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!treeelves.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (treeelves.skill2Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b隐匿符咒 §7]§c技能 冷却时间还有 " + treeelves.skill2Delay + "秒");
        } else if (!treeelves.isSkill2Run) {
            treeelves.isSkill2Run = true;
            treeelves.skill2Delay = (int)((double)treeelves.level * 0.3D + 14.0D);
            treeelves.enableSkill2 = true;
            player.sendMessage(PublicData.pluginPrefix + "§d您释放了 §7[ §b隐匿符咒 §7]§d,进入隐身状态增加移速");
            PublicData.particleAroundCreate(player.getLocation(), 2.0D, Effect.FLYING_GLYPH);
            treeelves.player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, treeelves.level * 6 + 14, 0), true);
            treeelves.player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, treeelves.level * 6 + 14, 0), true);
            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            this.cancel();
                        }

                        PublicData.loadPlayerActionBar(treeelves.player);
                        if (!treeelves.enableSkill2) {
                            treeelves.player.removePotionEffect(PotionEffectType.INVISIBILITY);
                            treeelves.player.removePotionEffect(PotionEffectType.SPEED);
                        }

                        if (!treeelves.canUseSkill) {
                            treeelves.player.removePotionEffect(PotionEffectType.INVISIBILITY);
                            treeelves.player.removePotionEffect(PotionEffectType.SPEED);
                        }

                        if (treeelves.skill2Delay > 0) {
                            --treeelves.skill2Delay;
                        } else {
                            treeelves.skill2Delay = 0;
                            treeelves.isSkill2Run = false;
                            treeelves.enableSkill2 = false;
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
        final Hero treeelves = PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!treeelves.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (treeelves.skill3Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b树灵牵引 §7]§c技能 冷却时间还有 " + treeelves.skill3Delay + "秒");
        } else {
            if (treeelves.enableSkill2) {
                treeelves.enableSkill2 = false;
            }

            if (!treeelves.isSkill3Run) {
                treeelves.isSkill3Run = true;
                treeelves.skill3Delay = 40;
                treeelves.skill2Delay = 0;
                treeelves.enableSkill3 = true;
                PublicData.particleAroundCreate(player.getLocation(), 10.0D, Effect.SPELL);
                List<Entity> entityList = player.getNearbyEntities(10.0D, 10.0D, 10.0D);
                int healthBounds = 0;
                Iterator var5 = entityList.iterator();

                while(var5.hasNext()) {
                    Entity entity = (Entity)var5.next();
                    if (entity instanceof LivingEntity) {
                        if (entity instanceof Player && !entity.getWorld().getPVP()) {
                            break;
                        }

                        if (!PublicData.hasObstacle(player.getEyeLocation(), ((LivingEntity)entity).getEyeLocation())) {
                            ++healthBounds;
                            entity.teleport(player);
                            entity.sendMessage(PublicData.pluginPrefix + "§c您被 §b" + player.getName() + " §c释放的 §7[ §b树灵牵引§7] §c拉扯到他的身边，注意自身安全！");
                        }
                    }
                }

                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, healthBounds * 20, 1));
                player.sendMessage(PublicData.pluginPrefix + "§d您释放了 §7[ §b树灵牵引 §7] §d拉扯 §b" + healthBounds + " §d名敌人");
                BukkitRunnable br = new BukkitRunnable() {
                    public void run() {
                        try {
                            if (!player.isOnline()) {
                                this.cancel();
                            }

                            PublicData.loadPlayerActionBar(treeelves.player);
                            if (treeelves.skill3Delay > 0) {
                                --treeelves.skill3Delay;
                            } else {
                                treeelves.skill3Delay = 0;
                                treeelves.isSkill3Run = false;
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

    public static void skill4(Player player) {
    }
}
