package com.burgess.Heros;

import me.winterguardian.easyscoreboards.ScoreboardUtil;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PublicData {
    public static String pluginPrefix = "§f[ §dB娘 §f] §b> §r";
    public static String forceSpawnWorld = null;
    static File playerDataFile = null;
    public static File PluginFile = null;
    public static List<String> enableWorlds = new ArrayList<>();
    public static Map<String, Hero> onlineHero = new TreeMap<>();
    public static Map<Hero, Location> boom = new TreeMap<>();

    public PublicData() {
    }

    public static String translateHeroName(String str) {
        String rstr;
        switch(str.hashCode()) {
            case -2122224736:
                if (str.equals("Hunter")) {
                    rstr = "§b猎手";
                    return rstr + "§r ";
                }
                break;
            case -2042963283:
                if (str.equals("Knight")) {
                    rstr = "§6骑士";
                    return rstr + "§r ";
                }
                break;
            case 63408097:
                if (str.equals("Angel")) {
                    rstr = "§e天使";
                    return rstr + "§r ";
                }
                break;
            case 65916619:
                if (str.equals("Demon")) {
                    rstr = "§c恶魔";
                    return rstr + "§r ";
                }
                break;
            case 68778607:
                if (str.equals("Ghost")) {
                    rstr = "§1亡灵";
                    return rstr + "§r ";
                }
                break;
            case 1385518182:
                if (str.equals("NormalHuman")) {
                    rstr = "§f人类";
                    return rstr + "§r ";
                }
                break;
            case 1897037806:
                if (str.equals("Vampire")) {
                    rstr = "§d吸血鬼";
                    return rstr + "§r ";
                }
                break;
            case 2011722879:
                if (str.equals("TreeElves")) {
                    rstr = "§a树灵";
                    return rstr + "§r ";
                }
        }

        rstr = "§f人类";
        return rstr + "§r ";
    }

    static void particleFrontCreate(Location Loc, double Radii, Effect Type) {
        for(double i = 1.5D; i <= Radii; i += 2.0D) {
            for(double o = 90.0D; o <= 270.0D; ++o) {
                double x = i * Math.cos(o / 180.0D * 3.141592653589793D) * -Math.cos((double)((90.0F - Loc.clone().getPitch() + 90.0F) / 180.0F) * 3.141592653589793D) * Math.sin((double)(Loc.clone().getYaw() / 180.0F) * 3.141592653589793D) + i * Math.sin(o / 180.0D * 3.141592653589793D) * -Math.sin((double)((Loc.clone().getYaw() - 90.0F) / 180.0F) * 3.141592653589793D);
                double y = 0.8D + i * Math.cos(o / 180.0D * 3.141592653589793D) * Math.sin((double)((90.0F - Loc.clone().getPitch() + 90.0F) / 180.0F) * 3.141592653589793D);
                double z = i * (Math.cos(o / 180.0D * 3.141592653589793D) * Math.cos((double)((90.0F - Loc.clone().getPitch() + 90.0F) / 180.0F) * 3.141592653589793D) * Math.cos((double)(Loc.clone().getYaw() / 180.0F) * 3.141592653589793D) + Math.sin(o / 180.0D * 3.141592653589793D) * Math.cos((double)((Loc.clone().getYaw() - 90.0F) / 180.0F) * 3.141592653589793D));
                Location Location = Loc.clone().add(x, y, z);
                Loc.clone().getWorld().playEffect(Location, Type, 0);
            }
        }

    }

    public static void particleAroundCreate(Location Loc, double Radii, Effect Type) {
        for(double i = 1.5D; i <= Radii; i += 2.0D) {
            for(double o = 0.0D; o <= 360.0D; ++o) {
                double x = i * Math.cos(o / 180.0D * 3.141592653589793D) * -Math.cos((double)((90.0F - Loc.clone().getPitch() + 90.0F) / 180.0F) * 3.141592653589793D) * Math.sin((double)(Loc.clone().getYaw() / 180.0F) * 3.141592653589793D) + i * Math.sin(o / 180.0D * 3.141592653589793D) * -Math.sin((double)((Loc.clone().getYaw() - 90.0F) / 180.0F) * 3.141592653589793D);
                double y = 0.8D + i * Math.cos(o / 180.0D * 3.141592653589793D) * Math.sin((double)((90.0F - Loc.clone().getPitch() + 90.0F) / 180.0F) * 3.141592653589793D);
                double z = i * (Math.cos(o / 180.0D * 3.141592653589793D) * Math.cos((double)((90.0F - Loc.clone().getPitch() + 90.0F) / 180.0F) * 3.141592653589793D) * Math.cos((double)(Loc.clone().getYaw() / 180.0F) * 3.141592653589793D) + Math.sin(o / 180.0D * 3.141592653589793D) * Math.cos((double)((Loc.clone().getYaw() - 90.0F) / 180.0F) * 3.141592653589793D));
                Location Location = Loc.clone().add(x, y, z);
                Loc.clone().getWorld().playEffect(Location, Type, 0);
            }
        }

    }

    static void particleAroundMaxCreate(Location Loc, double Radii, Effect Type) {
        for(double i = 1.5D; i <= Radii; ++i) {
            for(double o = 0.0D; o <= 360.0D; ++o) {
                double x = i * Math.cos(o / 180.0D * 3.141592653589793D) * -Math.cos((double)((90.0F - Loc.clone().getPitch() + 90.0F) / 180.0F) * 3.141592653589793D) * Math.sin((double)(Loc.clone().getYaw() / 180.0F) * 3.141592653589793D) + i * Math.sin(o / 180.0D * 3.141592653589793D) * -Math.sin((double)((Loc.clone().getYaw() - 90.0F) / 180.0F) * 3.141592653589793D);
                double y = 0.8D + i * Math.cos(o / 180.0D * 3.141592653589793D) * Math.sin((double)((90.0F - Loc.clone().getPitch() + 90.0F) / 180.0F) * 3.141592653589793D);
                double z = i * (Math.cos(o / 180.0D * 3.141592653589793D) * Math.cos((double)((90.0F - Loc.clone().getPitch() + 90.0F) / 180.0F) * 3.141592653589793D) * Math.cos((double)(Loc.clone().getYaw() / 180.0F) * 3.141592653589793D) + Math.sin(o / 180.0D * 3.141592653589793D) * Math.cos((double)((Loc.clone().getYaw() - 90.0F) / 180.0F) * 3.141592653589793D));
                Location Location = Loc.clone().add(x, y, z);
                Loc.clone().getWorld().playEffect(Location, Type, 0);
            }
        }

    }

    public static void particleCircleCreate(Location Loc, double Radii, Effect Type) {
        double i;
        double o;
        double x;
        double y;
        double z;
        Location Location;
        for(i = 1.5D; i <= Radii / 2.0D; ++i) {
            for(o = 0.0D; o <= 360.0D; ++o) {
                x = i * Math.cos(o / 180.0D * 3.141592653589793D) * -Math.cos((double)((90.0F - Loc.clone().getPitch() + 90.0F) / 180.0F) * 3.141592653589793D) * Math.sin((double)(Loc.clone().getYaw() / 180.0F) * 3.141592653589793D) + i * Math.sin(o / 180.0D * 3.141592653589793D) * -Math.sin((double)((Loc.clone().getYaw() - 90.0F) / 180.0F) * 3.141592653589793D);
                y = 0.8D + i * Math.cos(o / 180.0D * 3.141592653589793D) * Math.sin((double)((90.0F - Loc.clone().getPitch() + 90.0F) / 180.0F) * 3.141592653589793D);
                z = i * (Math.cos(o / 180.0D * 3.141592653589793D) * Math.cos((double)((90.0F - Loc.clone().getPitch() + 90.0F) / 180.0F) * 3.141592653589793D) * Math.cos((double)(Loc.clone().getYaw() / 180.0F) * 3.141592653589793D) + Math.sin(o / 180.0D * 3.141592653589793D) * Math.cos((double)((Loc.clone().getYaw() - 90.0F) / 180.0F) * 3.141592653589793D));
                Location = Loc.clone().add(x, y + Radii, z);
                Loc.clone().getWorld().playEffect(Location, Type, 0);
            }
        }

        for(i = 1.5D; i <= Radii / 2.0D; ++i) {
            for(o = 0.0D; o <= 360.0D; ++o) {
                x = i * Math.cos(o / 180.0D * 3.141592653589793D) * -Math.cos((double)((90.0F - Loc.clone().getPitch() + 90.0F) / 180.0F) * 3.141592653589793D) * Math.sin((double)(Loc.clone().getYaw() / 180.0F) * 3.141592653589793D) + i * Math.sin(o / 180.0D * 3.141592653589793D) * -Math.sin((double)((Loc.clone().getYaw() - 90.0F) / 180.0F) * 3.141592653589793D);
                y = 0.8D + i * Math.cos(o / 180.0D * 3.141592653589793D) * Math.sin((double)((90.0F - Loc.clone().getPitch() + 90.0F) / 180.0F) * 3.141592653589793D);
                z = i * (Math.cos(o / 180.0D * 3.141592653589793D) * Math.cos((double)((90.0F - Loc.clone().getPitch() + 90.0F) / 180.0F) * 3.141592653589793D) * Math.cos((double)(Loc.clone().getYaw() / 180.0F) * 3.141592653589793D) + Math.sin(o / 180.0D * 3.141592653589793D) * Math.cos((double)((Loc.clone().getYaw() - 90.0F) / 180.0F) * 3.141592653589793D));
                Location = Loc.clone().add(x, y - Radii, z);
                Loc.clone().getWorld().playEffect(Location, Type, 0);
            }
        }

        for(i = 1.5D; i <= Radii; ++i) {
            for(o = 0.0D; o <= 360.0D; ++o) {
                x = i * Math.cos(o / 180.0D * 3.141592653589793D) * -Math.cos((double)((90.0F - Loc.clone().getPitch() + 90.0F) / 180.0F) * 3.141592653589793D) * Math.sin((double)(Loc.clone().getYaw() / 180.0F) * 3.141592653589793D) + i * Math.sin(o / 180.0D * 3.141592653589793D) * -Math.sin((double)((Loc.clone().getYaw() - 90.0F) / 180.0F) * 3.141592653589793D);
                y = 0.8D + i * Math.cos(o / 180.0D * 3.141592653589793D) * Math.sin((double)((90.0F - Loc.clone().getPitch() + 90.0F) / 180.0F) * 3.141592653589793D);
                z = i * (Math.cos(o / 180.0D * 3.141592653589793D) * Math.cos((double)((90.0F - Loc.clone().getPitch() + 90.0F) / 180.0F) * 3.141592653589793D) * Math.cos((double)(Loc.clone().getYaw() / 180.0F) * 3.141592653589793D) + Math.sin(o / 180.0D * 3.141592653589793D) * Math.cos((double)((Loc.clone().getYaw() - 90.0F) / 180.0F) * 3.141592653589793D));
                Location = Loc.clone().add(x, y, z);
                Loc.clone().getWorld().playEffect(Location, Type, 0);
            }
        }

    }

    static boolean hasObstacle(Location mainLoc, Location othLoc) {
        int mainX = mainLoc.getBlockX();
        int mainY = mainLoc.getBlockY();
        int mainZ = mainLoc.getBlockZ();
        int othX = othLoc.getBlockX();
        int othY = othLoc.getBlockY();
        int othZ = othLoc.getBlockZ();
        Location newLoc = othLoc;
        int bounds = 0;
        if (Math.abs(othX - mainX) > Math.abs(othY - othY)) {
            if (Math.abs(othX - mainX) > Math.abs(othZ - othZ)) {
                bounds = bounds + Math.abs(othX - mainX);
            } else {
                bounds = bounds + Math.abs(othZ - mainZ);
            }
        } else if (Math.abs(othY - mainY) > Math.abs(othZ - othZ)) {
            bounds = bounds + Math.abs(othY - mainY);
        } else {
            bounds = bounds + Math.abs(othZ - mainZ);
        }

        for(int i = 0; i < bounds; ++i) {
            if (othX > mainX) {
                newLoc.add(-1.0D, 0.0D, 0.0D);
                --othX;
            } else if (othX != mainX) {
                newLoc.add(1.0D, 0.0D, 0.0D);
                ++othX;
            }

            if (othY > mainY) {
                newLoc.add(0.0D, -1.0D, 0.0D);
                --othY;
            } else if (othY != mainY) {
                newLoc.add(0.0D, 1.0D, 0.0D);
                ++othY;
            }

            if (othZ > mainZ) {
                newLoc.add(0.0D, 0.0D, -1.0D);
                --othZ;
            } else if (othZ != mainZ) {
                newLoc.add(0.0D, 0.0D, 1.0D);
                ++othZ;
            }

            if (!newLoc.getBlock().getType().equals(Material.AIR) && !newLoc.getBlock().getType().equals(Material.YELLOW_FLOWER) && !newLoc.getBlock().getType().equals(Material.CHORUS_FLOWER) && !newLoc.getBlock().getType().equals(Material.LONG_GRASS) && !newLoc.getBlock().getType().equals(Material.GRASS)) {
                return true;
            }
        }

        return false;
    }

    public static void loadPlayerActionBar(Player player) {
        if (onlineHero.containsKey(player.getName())) {
            Hero hero = onlineHero.get(player.getName());
            if (hero.player.isOnline()) {
                String heroName = hero.hero;
                String[] dis;
                if (heroName.equals(Hero.Angel)) {
                    dis = new String[]{"§c[种族] " + translateHeroName(heroName), "§c[等级] §a" + hero.level, "§c[经验] §a" + hero.xp, "§e技能冷却", "§7[§b羽翼§7] §a" + hero.skill1Delay, "§7[§b守护§7] §a" + hero.skill2Delay, "§7[§b圣光§7] §a" + hero.skill3Delay, "§7[§b复活§7] §a" + hero.skill4Delay};
                    ScoreboardUtil.unrankedSidebarDisplay(player, dis);
                } else if (heroName.equals(Hero.Demon)) {
                    dis = new String[]{"§c[种族] " + translateHeroName(heroName), "§c[等级] §a" + hero.level, "§c[经验] §a" + hero.xp, "§e技能冷却", "§7[§b爆炎§7] §a" + hero.skill1Delay, "§7[§b弱化§7] §a" + hero.skill2Delay, "§7[§b极灸§7] §a" + hero.skill3Delay, "§7[§b狂化§7] §a" + hero.skill4Delay};
                    ScoreboardUtil.unrankedSidebarDisplay(player, dis);
                } else if (heroName.equals(Hero.Ghost)) {
                    dis = new String[]{"§c[种族] " + translateHeroName(heroName), "§c[等级] §a" + hero.level, "§c[经验] §a" + hero.xp, "§e技能冷却", "§7[§b亡灵体态§7] §a" + hero.skill1Delay, "§7[§b不死之躯§7] §a" + hero.skill2Delay, "§7[§b哀嚎毁灭§7] §a" + hero.skill3Delay, "§7[§d亡灵能量§7] §a" + hero.ghostNum};
                    ScoreboardUtil.unrankedSidebarDisplay(player, dis);
                } else if (heroName.equals(Hero.Hunter)) {
                    dis = new String[]{"§c[种族] " + translateHeroName(heroName), "§c[等级] §a" + hero.level, "§c[经验] §a" + hero.xp, "§e技能冷却", "§7[§b斥候箭§7] §a" + hero.skill1Delay, "§7[§b暴雪箭§7] §a" + hero.skill2Delay, "§7[§b 跃 §7] §a" + hero.skill3Delay, "§7[§d龙晏箭§7] §a" + hero.skill4Delay};
                    ScoreboardUtil.unrankedSidebarDisplay(player, dis);
                } else if (heroName.equals(Hero.Knight)) {
                    dis = new String[]{"§c[种族] " + translateHeroName(heroName), "§c[等级] §a" + hero.level, "§c[经验] §a" + hero.xp, "§e技能冷却", "§7[§b誓约之盾§7] §a" + hero.skill1Delay, "§7[§b正义审判§7] §a" + hero.skill2Delay, "§7[§b荆棘之铠§7] §a" + hero.skill3Delay, "§7[§d骑士之义§7] §a" + hero.skill4Delay};
                    ScoreboardUtil.unrankedSidebarDisplay(player, dis);
                } else if (heroName.equals(Hero.Vampire)) {
                    dis = new String[]{"§c[种族] " + translateHeroName(heroName), "§c[等级] §a" + hero.level, "§c[经验] §a" + hero.xp, "§e技能冷却", "§7[§b黑蝙蝠§7] §a" + hero.skill1Delay, "§7[§b血花§7] " + (hero.enableSkill2 ? "§a已放置" : "§c未放置"), "§7[§b迷惑§7] §a" + hero.skill3Delay, "§7[§d 影 §7] §a" + hero.skill4Delay};
                    ScoreboardUtil.unrankedSidebarDisplay(player, dis);
                } else if (heroName.equals(Hero.TreeElves)) {
                    dis = new String[]{"§c[种族] " + translateHeroName(heroName), "§c[等级] §a" + hero.level, "§c[经验] §a" + hero.xp, "§e技能冷却", "§7[§b藤条束缚§7] §a" + hero.skill1Delay, "§7[§b隐匿符咒§7] §a" + hero.skill2Delay, "§7[§b树灵牵引§7] §a" + hero.skill3Delay, "§7[§d树灵祝福§7] §a" + (hero.pbNum == 1 ? "§a开启" : "§c关闭")};
                    ScoreboardUtil.unrankedSidebarDisplay(player, dis);
                } else {
                    dis = new String[]{"§c[种族] " + translateHeroName(heroName), "§c[等级] §a无", "§c[经验] §a无"};
                    ScoreboardUtil.unrankedSidebarDisplay(player, dis);
                }

            }
        }
    }
}
