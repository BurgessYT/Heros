package com.burgess.Heros;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Hero implements Comparable<Hero> {
    public static String Angel = "Angel";
    public static String Demon = "Demon";
    public static String Ghost = "Ghost";
    public static String Hunter = "Hunter";
    public static String Knight = "Knight";
    public static String NormalHuman = "NormalHuman";
    public static String TreeElves = "TreeElves";
    public static String Vampire = "Vampire";
    private BukkitRunnable healthRunnable = null;
    public Horse horse = null;
    public Player player;
    public Player targetPlayer = null;
    public Location respawnLoc = null;
    public Location arrowLoc = null;
    public String hero = "NormalHuman";
    public String teamName = null;
    public int level = 1;
    public int xp = 0;
    public int silenceTime = 0;
    public int pbNum = 0;
    public int ghostNum = 0;
    public int deathTime = 10;
    public int skill1Delay = 0;
    public int skill2Delay = 0;
    public int skill3Delay = 0;
    public int skill4Delay = 0;
    public boolean isSkill1Run = false;
    public boolean isSkill2Run = false;
    public boolean isSkill3Run = false;
    public boolean isSkill4Run = false;
    public boolean enableSkill1 = false;
    public boolean enableSkill2 = false;
    public boolean enableSkill3 = false;
    public boolean enableSkill4 = false;
    public boolean canUseSkill = true;
    public boolean hasTeam = false;

    public Hero() {
    }

    public int compareTo(Hero h) {
        return this.level + this.xp - h.level + this.xp;
    }

    public String toString() {
        return "ID: " + this.player.getName() + "\r\n" + "英雄: " + PublicData.translateHeroName(this.hero) + "\r\n" + "等级: " + this.level + "\r\n" + "经验: " + this.xp;
    }

    public void addXP() {
    }

    public void addLevel() {
    }

    public void startHealthRunnable(int startTime, int delayTime) {
        if (this.healthRunnable == null) {
            this.healthRunnable = new BukkitRunnable() {
                public void run() {
                    if (Hero.this.player.getHealth() + 1.0D <= Hero.this.player.getMaxHealth()) {
                        Hero.this.player.setHealth(Hero.this.player.getHealth() + 1.0D);
                    }

                }
            };
            this.healthRunnable.runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("WolfCraftPluginHeros"), (long)startTime, (long)delayTime);
        }
    }

    public void stopHealthRunnable() {
        if (this.healthRunnable != null) {
            this.healthRunnable.cancel();
            this.healthRunnable = null;
        }
    }

    public void silence(int time) {
        this.silenceTime = time;
        this.canUseSkill = false;
        BukkitRunnable silencebr = new BukkitRunnable() {
            public void run() {
                if (!Hero.this.player.isOnline()) {
                    this.cancel();
                }

                if (Hero.this.silenceTime > 0) {
                    --Hero.this.silenceTime;
                } else {
                    Hero.this.canUseSkill = true;
                    Hero.this.player.sendMessage(PublicData.pluginPrefix + "§7[ §b正义审判 §7] §d(沉默)效果结束");
                    Hero.this.silenceTime = 0;
                    this.cancel();
                }

            }
        };
        silencebr.runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("WolfCraftPluginHeros"), 20L, 20L);
    }

    public void save() {
        String playername = this.player.getName();
        File playerFile = new File(PublicData.playerDataFile, playername + ".yml");
        new YamlConfiguration();
        YamlConfiguration playerconfig = YamlConfiguration.loadConfiguration(playerFile);
        Hero h = (Hero)PublicData.onlineHero.get(playername);
        playerconfig.set("Hero", h.hero);
        playerconfig.set("Level", h.level);
        playerconfig.set("XP", h.xp);

        try {
            playerconfig.save(playerFile);
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    public void setHeroMaxHealth() {
        double heroMaxHealth = 20.0D;
        String var3 = this.hero;
        switch(this.hero.hashCode()) {
            case -2122224736:
                if (var3.equals("Hunter")) {
                    heroMaxHealth += (double)((this.level - this.level % 4) / 4);
                }
                break;
            case -2042963283:
                if (var3.equals("Knight")) {
                    heroMaxHealth += (double)this.level;
                }
                break;
            case 63408097:
                if (var3.equals("Angel")) {
                    heroMaxHealth += (double)((this.level - this.level % 3) / 3);
                }
                break;
            case 65916619:
                if (var3.equals("Demon")) {
                    heroMaxHealth += (double)((this.level - this.level % 5) / 5 * 3);
                }
                break;
            case 68778607:
                if (var3.equals("Ghost")) {
                    heroMaxHealth += (double)((this.level - this.level % 2) / 2);
                }
                break;
            case 1385518182:
                if (!var3.equals("NormalHuman")) {
                    ;
                }
                break;
            case 1897037806:
                if (var3.equals("Vampire")) {
                    heroMaxHealth += (double)((this.level - this.level % 5) / 5 * 2);
                }
                break;
            case 2011722879:
                if (var3.equals("TreeElves")) {
                    heroMaxHealth += (double)((this.level - this.level % 3) / 3 * 2);
                }
        }

        this.player.setMaxHealth(heroMaxHealth);
    }
}
