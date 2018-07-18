package com.burgess.Heros;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.Iterator;
import java.util.List;

public class Knight {
    public Knight() {
    }

    public static void skill1(final Player player) {
        final Hero knight = PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!knight.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (knight.skill1Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b誓约之盾 §7]§c技能 冷却时间还有 " + knight.skill1Delay + "秒");
        } else if (!knight.isSkill1Run) {
            knight.isSkill1Run = true;
            knight.enableSkill1 = true;
            knight.skill1Delay = 30;
            knight.pbNum = knight.level / 2 + 10;
            player.sendMessage(PublicData.pluginPrefix + "§d您使用了 §7[ §b誓约之盾 §7] §b 10 §d秒内获得§b " + knight.pbNum + " §d护盾");
            PublicData.particleAroundMaxCreate(player.getLocation(), 2.0D, Effect.HAPPY_VILLAGER);
            player.setMaxHealth(player.getMaxHealth() + (double)knight.pbNum);
            player.setHealth(player.getHealth() + (double)knight.pbNum);
            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            this.cancel();
                        }

                        PublicData.loadPlayerActionBar(knight.player);
                        if (knight.skill1Delay == 20) {
                            if (knight.pbNum > 0) {
                                knight.player.setHealth(knight.player.getHealth() - (double)knight.pbNum);
                            }

                            knight.setHeroMaxHealth();
                            knight.pbNum = 0;
                            knight.enableSkill1 = false;
                        }

                        if (knight.skill1Delay > 0) {
                            --knight.skill1Delay;
                        } else {
                            knight.skill1Delay = 0;
                            knight.isSkill1Run = false;
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
        final Hero knight = PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!knight.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (knight.skill2Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b正义审判 §7]§c技能 冷却时间还有 " + knight.skill2Delay + "秒");
        } else if (!knight.isSkill2Run) {
            knight.isSkill2Run = true;
            knight.enableSkill2 = true;
            knight.skill2Delay = 38;
            PublicData.particleAroundCreate(player.getLocation(), 6.0D, Effect.SPELL);
            List<Entity> le = player.getNearbyEntities(6.0D, 6.0D, 6.0D);
            int silenceBounds = 0;
            int silenceTime = (int)((double)knight.level * 0.2D + 5.0D);
            Iterator var6 = le.iterator();

            while(var6.hasNext()) {
                Entity entity = (Entity)var6.next();
                if (entity instanceof Player && PublicData.onlineHero.containsKey(entity.getName())) {
                    Hero silenceHero = PublicData.onlineHero.get(entity.getName());
                    if (Math.random() <= 0.5D || !silenceHero.canUseSkill) {
                        continue;
                    }

                    silenceHero.silence(silenceTime);
                    ++silenceBounds;
                    entity.sendMessage(PublicData.pluginPrefix + "§b" + player.getName() + " §d对您释放了 §7[ §b正义审判 §7] §c,您被沉默 §b" + silenceTime + " §d秒");
                }
            }

            player.sendMessage(PublicData.pluginPrefix + "§d您使用了 §7[ §b正义审判 §7] §d沉默了 §b" + silenceBounds + " §d名英雄");
            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            this.cancel();
                        }

                        PublicData.loadPlayerActionBar(knight.player);
                        if (knight.skill2Delay > 0) {
                            --knight.skill2Delay;
                        } else {
                            knight.skill2Delay = 0;
                            knight.isSkill2Run = false;
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
        final Hero knight = PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!knight.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (knight.skill3Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b荆棘之铠 §7]§c技能 冷却时间还有 " + knight.skill3Delay + "秒");
        } else if (!knight.isSkill3Run) {
            knight.isSkill3Run = true;
            knight.enableSkill3 = true;
            knight.skill3Delay = 50;
            player.getWorld().playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 0);
            player.getWorld().playEffect(player.getLocation().add(0.0D, 2.0D, 0.0D), Effect.ENDER_SIGNAL, 0);
            player.getWorld().playEffect(player.getLocation().add(0.0D, -2.0D, 0.0D), Effect.ENDER_SIGNAL, 0);
            player.getWorld().playEffect(player.getLocation().add(0.0D, 1.0D, 0.0D), Effect.ENDER_SIGNAL, 0);
            player.getWorld().playEffect(player.getLocation().add(2.0D, 0.0D, 0.0D), Effect.ENDER_SIGNAL, 0);
            player.getWorld().playEffect(player.getLocation().add(-2.0D, 0.0D, 0.0D), Effect.ENDER_SIGNAL, 0);
            player.getWorld().playEffect(player.getLocation().add(0.0D, 0.0D, 2.0D), Effect.ENDER_SIGNAL, 0);
            player.getWorld().playEffect(player.getLocation().add(0.0D, 0.0D, -2.0D), Effect.ENDER_SIGNAL, 0);
            player.sendMessage(PublicData.pluginPrefix + "§d您使用了 §7[ §b荆棘之铠 §7] §d持续 §b 10 §d秒");
            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            this.cancel();
                        }

                        PublicData.loadPlayerActionBar(knight.player);
                        if (knight.skill3Delay == 40) {
                            knight.enableSkill3 = false;
                        }

                        if (knight.skill3Delay > 0) {
                            --knight.skill3Delay;
                        } else {
                            knight.skill3Delay = 0;
                            knight.isSkill3Run = false;
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
        final Hero knight = PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!knight.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (knight.skill4Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b骑士之义 §7]§c技能 冷却时间还有 " + knight.skill4Delay + "秒");
        } else if (!knight.isSkill4Run) {
            knight.isSkill4Run = true;
            knight.enableSkill4 = true;
            knight.skill4Delay = 60;
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b骑士之义 §7] §d召唤圣骑持续 §b " + (int)((double)knight.level * 0.1D + 13.0D) + "§d 秒");
            knight.horse = (Horse)player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
            knight.horse.setCustomNameVisible(true);
            knight.horse.setCustomName("[骑士]" + player.getName() + "的马");
            knight.horse.setAdult();
            knight.horse.setTamed(true);
            knight.horse.setOwner(player);
            knight.horse.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, (int)((double)knight.level * 0.1D + 13.0D), 0));
            ItemStack saddle = new ItemStack(Material.SADDLE);
            ItemMeta saddleMeta = saddle.getItemMeta();
            saddleMeta.setDisplayName("骑士的马鞍");
            saddle.setItemMeta(saddleMeta);
            ItemStack horseArmour = new ItemStack(418);
            ItemMeta horseArmourMeta = horseArmour.getItemMeta();
            horseArmourMeta.setDisplayName("骑士的马凯");
            horseArmour.setItemMeta(horseArmourMeta);
            knight.horse.getInventory().addItem(saddle);
            knight.horse.getInventory().addItem(horseArmour);
            knight.horse.addPassenger(player);
            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            if (knight.horse != null) {
                                knight.horse.getInventory().clear();
                                knight.horse.setHealth(0.0D);
                                knight.horse = null;
                            }

                            this.cancel();
                        }

                        if (knight.skill4Delay == 50 - (int)((double)knight.level * 0.1D + 13.0D) && knight.horse != null) {
                            knight.horse.getInventory().clear();
                            knight.horse.setHealth(0.0D);
                            knight.horse = null;
                        }

                        PublicData.loadPlayerActionBar(knight.player);
                        if (knight.skill4Delay > 0) {
                            --knight.skill4Delay;
                        } else {
                            knight.skill4Delay = 0;
                            knight.isSkill4Run = false;
                            this.cancel();
                        }
                    } catch (IllegalStateException var2) {
                        PublicData.loadPlayerActionBar(knight.player);
                        --knight.skill4Delay;
                        knight.horse.getInventory().clear();
                        knight.horse.setHealth(0.0D);
                        knight.horse = null;
                    }

                }
            };
            br.runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("WolfCraftPluginHeros"), 20L, 20L);
        }
    }
}
