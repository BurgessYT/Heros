package com.burgess.Lisiteners;

import com.burgess.Heros.Angel;
import com.burgess.Heros.Demon;
import com.burgess.Heros.Ghost;
import com.burgess.Heros.Hero;
import com.burgess.Heros.Hunter;
import com.burgess.Heros.Knight;
import com.burgess.Heros.PublicData;
import com.burgess.Heros.TreeElves;
import com.burgess.Heros.Vampire;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerCommand {
    public PlayerCommand() {
    }

    public boolean command(CommandSender sender, Command cmd, String label, String[] args) {
        String playerName;
        if (args[0].equalsIgnoreCase("set")) {
            label70: {
                playerName = args[1];
                String heroName = args[2];
                if (playerName != null && heroName != null) {
                    if (!PublicData.onlineHero.containsKey(playerName)) {
                        break label70;
                    }

                    Hero hero = PublicData.onlineHero.get(playerName);
                    if (!heroName.equals(Hero.Angel) && !heroName.equals(Hero.Demon) && !heroName.equals(Hero.Ghost) && !heroName.equals(Hero.Hunter) && !heroName.equals(Hero.Knight) && !heroName.equals(Hero.NormalHuman) && !heroName.equals(Hero.TreeElves) && !heroName.equals(Hero.Vampire)) {
                        sender.sendMessage("没有这个种族，种族名称为 Angel Demon Ghost Hunter Knight NormalHuman TreeElves Vampire");
                        break label70;
                    }

                    hero.player.sendMessage("你切换成 " + PublicData.translateHeroName(heroName) + "种族了");
                    hero.hero = heroName;
                    hero.skill1Delay = 0;
                    hero.skill2Delay = 0;
                    hero.skill3Delay = 0;
                    hero.skill4Delay = 0;
                    hero.stopHealthRunnable();
                    hero.silenceTime = 0;
                    hero.pbNum = 0;
                    hero.setHeroMaxHealth();
                    PublicData.loadPlayerActionBar(hero.player);
                    hero.save();
                    return true;
                }

                return false;
            }
        }

        if (!(sender instanceof Player)) {
            sender.getServer().getLogger().info(PublicData.pluginPrefix + "控制台不可输入技能命令");
            return true;
        } else {
            switch((playerName = args[0]).hashCode()) {
                case 49:
                    if (playerName.equals("1")) {
                        this.skill(sender.getName(), 1);
                        return true;
                    }
                    break;
                case 50:
                    if (playerName.equals("2")) {
                        this.skill(sender.getName(), 2);
                        return true;
                    }
                    break;
                case 51:
                    if (playerName.equals("3")) {
                        this.skill(sender.getName(), 3);
                        return true;
                    }
                    break;
                case 52:
                    if (playerName.equals("4")) {
                        this.skill(sender.getName(), 4);
                        return true;
                    }
            }

            return true;
        }
    }

    private void skill(String playername, int skillIndex) {
        Hero h = PublicData.onlineHero.get(playername);
        if (h.hero.equals(Hero.Angel)) {
            if (skillIndex == 1) {
                Angel.skill1(h.player);
            } else if (skillIndex == 2) {
                Angel.skill2(h.player);
            } else if (skillIndex == 3) {
                Angel.skill3(h.player);
            } else if (skillIndex == 4) {
                Angel.skill4(h.player);
            }
        } else if (h.hero.equals(Hero.Demon)) {
            if (skillIndex == 1) {
                Demon.skill1(h.player);
            } else if (skillIndex == 2) {
                Demon.skill2(h.player);
            } else if (skillIndex == 3) {
                Demon.skill3(h.player);
            } else if (skillIndex == 4) {
                Demon.skill4(h.player);
            }
        } else if (h.hero.equals(Hero.Ghost)) {
            if (skillIndex == 1) {
                Ghost.skill1(h.player);
            } else if (skillIndex == 2) {
                Ghost.skill2(h.player);
            } else if (skillIndex == 3) {
                Ghost.skill3(h.player);
            } else if (skillIndex == 4) {
                Ghost.skill4(h.player);
            }
        } else if (h.hero.equals(Hero.Hunter)) {
            if (skillIndex == 1) {
                Hunter.skill1(h.player);
            } else if (skillIndex == 2) {
                Hunter.skill2(h.player);
            } else if (skillIndex == 3) {
                Hunter.skill3(h.player);
            } else if (skillIndex == 4) {
                Hunter.skill4(h.player);
            }
        } else if (h.hero.equals(Hero.Knight)) {
            if (skillIndex == 1) {
                Knight.skill1(h.player);
            } else if (skillIndex == 2) {
                Knight.skill2(h.player);
            } else if (skillIndex == 3) {
                Knight.skill3(h.player);
            } else if (skillIndex == 4) {
                Knight.skill4(h.player);
            }
        } else if (h.hero.equals(Hero.TreeElves)) {
            if (skillIndex == 1) {
                TreeElves.skill1(h.player);
            } else if (skillIndex == 2) {
                TreeElves.skill2(h.player);
            } else if (skillIndex == 3) {
                TreeElves.skill3(h.player);
            } else if (skillIndex == 4) {
                TreeElves.skill4(h.player);
            }
        } else if (h.hero.equals(Hero.Vampire)) {
            if (skillIndex == 1) {
                Vampire.skill1(h.player);
            } else if (skillIndex == 2) {
                Vampire.skill2(h.player);
            } else if (skillIndex == 3) {
                Vampire.skill3(h.player);
            } else if (skillIndex == 4) {
                Vampire.skill4(h.player);
            }
        } else if (h.hero.equals(Hero.NormalHuman)) {
            h.player.sendMessage(PublicData.pluginPrefix + "您还没有转职，不能使用技能");
        }

    }

    public boolean commandSet() {
        return false;
    }
}
