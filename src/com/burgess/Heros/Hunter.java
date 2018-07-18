package com.burgess.Heros;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Hunter {
    public Hunter() {
    }

    public static void skill1(final Player player) {
        final Hero hunter = (Hero)PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!hunter.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (hunter.skill1Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b斥候箭 §7]§c技能 冷却时间还有 " + hunter.skill1Delay + "秒");
        } else if (!hunter.isSkill1Run) {
            hunter.skill1Delay = 30;
            hunter.isSkill1Run = true;
            hunter.enableSkill1 = true;
            player.sendMessage(PublicData.pluginPrefix + "§d您释放了 §7[ §b斥候箭 §7]§d,下一次射箭附带效果");
            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            this.cancel();
                        }

                        PublicData.loadPlayerActionBar(hunter.player);
                        if (hunter.skill1Delay > 0) {
                            --hunter.skill1Delay;
                        } else {
                            hunter.skill1Delay = 0;
                            hunter.isSkill1Run = false;
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
        final Hero hunter = (Hero)PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!hunter.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (hunter.skill2Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b暴雪箭 §7]§c技能 冷却时间还有 " + hunter.skill2Delay + "秒");
        } else if (!hunter.isSkill2Run) {
            hunter.isSkill2Run = true;
            hunter.skill2Delay = 20;
            hunter.enableSkill2 = true;
            player.sendMessage(PublicData.pluginPrefix + "§d您释放了 §7[ §b暴雪箭 §7]§d,下一次射箭附带效果");
            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            this.cancel();
                        }

                        PublicData.loadPlayerActionBar(hunter.player);
                        if (hunter.skill2Delay > 0) {
                            --hunter.skill2Delay;
                        } else {
                            hunter.skill2Delay = 0;
                            hunter.isSkill2Run = false;
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
        final Hero hunter = (Hero)PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!hunter.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (hunter.skill3Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b跃 §7]§c技能 冷却时间还有 " + hunter.skill3Delay + "秒");
        } else if (!hunter.isSkill3Run) {
            hunter.isSkill3Run = true;
            hunter.skill3Delay = 40;
            hunter.enableSkill3 = true;
            player.sendMessage(PublicData.pluginPrefix + "§d您释放了 §7[ §b跃 §7]§d,下一次射箭附带效果");
            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            this.cancel();
                        }

                        PublicData.loadPlayerActionBar(hunter.player);
                        if (hunter.skill3Delay > 0) {
                            --hunter.skill3Delay;
                        } else {
                            hunter.skill3Delay = 0;
                            hunter.isSkill3Run = false;
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
        final Hero hunter = (Hero)PublicData.onlineHero.get(player.getName());
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.sendMessage(PublicData.pluginPrefix + "§c您处于死亡状态，不可使用技能");
        } else if (!PublicData.enableWorlds.contains(player.getWorld().getName())) {
            player.sendMessage(PublicData.pluginPrefix + "§c当前世界不允许使用技能");
        } else if (!hunter.canUseSkill) {
            player.sendMessage(PublicData.pluginPrefix + "§c您被沉默了，不能使用技能");
        } else if (hunter.skill4Delay > 0) {
            player.sendMessage(PublicData.pluginPrefix + "§7[ §b龙晏箭 §7]§c技能 冷却时间还有 " + hunter.skill4Delay + "秒");
        } else if (!hunter.isSkill4Run) {
            hunter.isSkill4Run = true;
            hunter.skill4Delay = 50;
            hunter.enableSkill4 = true;
            player.sendMessage(PublicData.pluginPrefix + "§d您释放了 §7[ §b龙晏箭 §7]§d,下一次射箭附带效果");
            BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    try {
                        if (!player.isOnline()) {
                            this.cancel();
                        }

                        PublicData.loadPlayerActionBar(hunter.player);
                        if (hunter.skill4Delay > 0) {
                            --hunter.skill4Delay;
                        } else {
                            hunter.skill4Delay = 0;
                            hunter.isSkill4Run = false;
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
