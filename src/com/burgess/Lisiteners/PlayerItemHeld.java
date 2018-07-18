package com.burgess.Lisiteners;

import com.burgess.Heros.Hero;
import com.burgess.Heros.PublicData;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerItemHeld {
    PlayerItemHeldEvent held;

    public PlayerItemHeld() {
        this((PlayerItemHeldEvent)null);
    }

    public PlayerItemHeld(PlayerItemHeldEvent held) {
        this.held = held;
    }

    public void onPlaerItemHeld() {
        Player player = this.held.getPlayer();
        if (PublicData.enableWorlds.contains(player.getWorld().getName())) {
            if (player.getInventory().getItem(this.held.getNewSlot()) == null) {
                player.removePotionEffect(PotionEffectType.SPEED);
            } else {
                if (PublicData.onlineHero.containsKey(player.getName()) && ((Hero)PublicData.onlineHero.get(player.getName())).hero.equals(Hero.Hunter)) {
                    if (player.getInventory().getItem(this.held.getNewSlot()).getTypeId() == 261) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 0), true);
                    } else {
                        player.removePotionEffect(PotionEffectType.SPEED);
                    }
                }

            }
        }
    }
}
