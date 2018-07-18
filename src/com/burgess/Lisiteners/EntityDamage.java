package com.burgess.Lisiteners;

import com.burgess.Heros.Hero;
import com.burgess.Heros.PublicData;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage {
    EntityDamageEvent damage;

    public EntityDamage() {
        this((EntityDamageEvent)null);
    }

    public EntityDamage(EntityDamageEvent damage) {
        this.damage = damage;
    }

    public void onEntityDamage() {
        if (PublicData.enableWorlds.contains(this.damage.getEntity().getWorld().getName())) {
            Player player = null;
            if (!(this.damage.getEntity() instanceof Player)) {
                return;
            }

            player = (Player)this.damage.getEntity();
            Hero h = null;
            if (PublicData.onlineHero.containsKey(player.getName())) {
                h = (Hero)PublicData.onlineHero.get(player.getName());
                if (h.hero.equals(Hero.Angel) && this.damage.getCause().name().equals("FALL")) {
                    this.damage.setCancelled(true);
                }

                if (h.hero.equals(Hero.Vampire) && this.damage.getCause().name().equals("FALL") && h.skill4Delay > 38) {
                    this.damage.setCancelled(true);
                }

                if (h.hero.equals(Hero.Ghost) && h.enableSkill1) {
                    this.damage.setCancelled(true);
                }

                if (h.hero.equals(Hero.Knight) && h.enableSkill1) {
                    h.pbNum = (int)((double)h.pbNum - this.damage.getFinalDamage());
                }
            }
        }

    }
}
