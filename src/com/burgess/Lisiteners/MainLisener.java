package com.burgess.Lisiteners;

import com.burgess.Heros.Hero;
import com.burgess.Heros.PublicData;
import java.util.Iterator;
import java.util.List;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class MainLisener implements Listener {
    public MainLisener() {
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent join) {
        PlayerJoin pj = new PlayerJoin(join);
        pj.onPlayerJoin();
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent leave) {
        PlayerLeave pl = new PlayerLeave(leave);
        pl.onPlayerLeave();
    }

    @EventHandler
    public void onEntityDeath(PlayerDeathEvent death) {
        PlayerDeath ed = new PlayerDeath(death);
        ed.onPlayerDeath();
    }

    @EventHandler
    public void onPlayerBlockDamage(EntityDamageEvent damage) {
        EntityDamage ed = new EntityDamage(damage);
        ed.onEntityDamage();
    }

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent held) {
        PlayerItemHeld pih = new PlayerItemHeld(held);
        pih.onPlaerItemHeld();
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        EntityDamageByEntity ph = new EntityDamageByEntity(event);
        ph.onEntityDamageByEntity();
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent projectilelaunch) {
        ProjectileLaunch pl = new ProjectileLaunch(projectilelaunch);
        pl.onProjectileLaunch();
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent projectile) {
        ProjectileHit pih = new ProjectileHit(projectile);
        pih.onProjectileHit();
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent move) {
        PlayerMove pm = new PlayerMove(move);
        pm.onPlayerMove();
    }

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent ignite) {
        if (ignite.getCause() == IgniteCause.FIREBALL) {
            ignite.setCancelled(true);
        }

    }

    @EventHandler
    public void onEntityDeathX(EntityDeathEvent e) {
        if (e.getEntity() instanceof Horse) {
            Horse horse = (Horse)e.getEntity();
            if (horse.getCustomName().startsWith("[骑士]")) {
                e.setDroppedExp(0);
                List<ItemStack> drop = e.getDrops();
                Iterator var5 = drop.iterator();

                while(var5.hasNext()) {
                    ItemStack itemStack = (ItemStack)var5.next();
                    itemStack.setTypeId(0);
                }
            }
        } else if (e.getEntity() instanceof Bat) {
            e.setDroppedExp(0);
        }

    }

    @EventHandler
    public void onInteractInventory(InventoryClickEvent e) {
        if (e.getCurrentItem() != null) {
            ItemStack is = e.getCurrentItem();
            if (is.getItemMeta().getDisplayName() == null) {
                return;
            }

            if (is.getItemMeta().getDisplayName().equals("骑士的马鞍") || is.getItemMeta().getDisplayName().equals("骑士的马凯")) {
                e.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void onMoveItemInventory(InventoryMoveItemEvent e) {
        if (e.getItem() != null) {
            ItemStack is = e.getItem();
            if (is.getItemMeta().getDisplayName().equals("骑士的马鞍") || is.getItemMeta().getDisplayName().equals("骑士的马凯")) {
                e.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {
        System.out.println(e.getReason());
        if (e.getReason().equals("Flying is not enabled on this server")) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent block) {
        Entity e = block.getEntity();
        if (e instanceof Player && PublicData.onlineHero.containsKey(e.getName())) {
            Hero hero = (Hero)PublicData.onlineHero.get(e.getName());
            if (hero.hero.equals(Hero.Demon) && (block.getCause() == DamageCause.LAVA || block.getCause() == DamageCause.FIRE_TICK || block.getCause() == DamageCause.FIRE)) {
                hero.player.setFireTicks(0);
                block.setCancelled(true);
            }
        }

    }
}
