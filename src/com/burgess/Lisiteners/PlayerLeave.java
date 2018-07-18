package com.burgess.Lisiteners;

import com.burgess.Heros.Hero;
import com.burgess.Heros.PublicData;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave {
    PlayerQuitEvent event;

    public PlayerLeave() {
        this((PlayerQuitEvent)null);
    }

    public PlayerLeave(PlayerQuitEvent event) {
        this.event = event;
    }

    public void onPlayerLeave() {
        if (PublicData.onlineHero.containsKey(this.event.getPlayer().getName())) {
            Hero hero = (Hero)PublicData.onlineHero.get(this.event.getPlayer().getName());
            if (hero.hero.equals(Hero.Vampire)) {
                if (PublicData.boom.containsKey(hero)) {
                    PublicData.boom.remove(hero);
                }
            } else if (hero.hero.equals(Hero.Knight)) {
                if (hero.pbNum > 0) {
                    hero.player.setHealth(hero.player.getHealth() - (double)hero.pbNum);
                }

                if (hero.horse != null) {
                    hero.horse.getInventory().clear();
                    hero.horse.setHealth(0.0D);
                    hero.horse = null;
                }
            }

            hero.save();
            PublicData.onlineHero.remove(this.event.getPlayer().getName());
        }

        System.out.println("当前还有 " + PublicData.onlineHero.size() + "名英雄在线");
    }
}
