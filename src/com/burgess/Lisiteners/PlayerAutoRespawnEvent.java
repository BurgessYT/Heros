package com.burgess.Lisiteners;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class PlayerAutoRespawnEvent extends Event {
    private Player p;
    private Location deathLoc;
    private Location respawnLoc;
    private int respawnDelayTicks = 0;
    private static final HandlerList handlers = new HandlerList();

    PlayerAutoRespawnEvent(Player p, Location deathLoc, Location respawnLoc) {
        this.p = p;
        this.deathLoc = deathLoc;
        this.respawnLoc = respawnLoc;
    }

    public Player getPlayer() {
        return this.p;
    }

    public Location getDeathLocation() {
        return this.deathLoc;
    }

    public Location getRespawnLocation() {
        return this.respawnLoc;
    }

    public DamageCause getDeathCause() {
        return this.p.getLastDamageCause().getCause();
    }

    /** @deprecated */
    @Deprecated
    public int getRespawnDelayTicks() {
        return this.respawnDelayTicks;
    }

    /** @deprecated */
    @Deprecated
    public double getRespawnDelaySeconds() {
        return Double.parseDouble(this.respawnDelayTicks + ".0") / 20.0D;
    }

    public boolean killedByPlayer() {
        if (this.p.getLastDamageCause().getEntity() instanceof Player) {
            return true;
        } else if (this.p.getLastDamageCause().getEntity() instanceof Arrow) {
            Arrow a = (Arrow)this.p.getLastDamageCause().getEntity();
            return a.getShooter() instanceof Player;
        } else if (this.p.getLastDamageCause().getEntity() instanceof Snowball) {
            Snowball a = (Snowball)this.p.getLastDamageCause().getEntity();
            return a.getShooter() instanceof Player;
        } else {
            return false;
        }
    }

    public Player getKiller() {
        return this.p.getKiller();
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
