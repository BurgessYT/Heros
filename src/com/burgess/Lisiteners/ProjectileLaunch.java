package com.burgess.Lisiteners;

import com.burgess.Heros.Hero;
import com.burgess.Heros.PublicData;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ProjectileLaunch {
    ProjectileLaunchEvent projectilelaunch;

    public ProjectileLaunch() {
        this((ProjectileLaunchEvent)null);
    }

    public ProjectileLaunch(ProjectileLaunchEvent projectilelaunch) {
        this.projectilelaunch = projectilelaunch;
    }

    public void onProjectileLaunch() {
        if (this.projectilelaunch.getEntity() instanceof CraftArrow) {
            if (!PublicData.enableWorlds.contains(this.projectilelaunch.getEntity().getWorld().getName())) {
                return;
            }

            final Arrow a = (Arrow)this.projectilelaunch.getEntity();
            if (a.getShooter() instanceof Player) {
                Player hunterPlater = (Player)a.getShooter();
                if (PublicData.onlineHero.containsKey(hunterPlater.getName())) {
                    final Hero hunter = (Hero)PublicData.onlineHero.get(hunterPlater.getName());
                    if (!hunter.hero.equals(Hero.Hunter)) {
                        return;
                    }

                    if (!hunter.enableSkill3) {
                        return;
                    }

                    hunter.enableSkill3 = false;
                    BukkitRunnable br = new BukkitRunnable() {
                        public void run() {
                            try {
                                if (a.getLocation().equals(hunter.arrowLoc)) {
                                    hunter.arrowLoc = null;
                                    this.cancel();
                                } else {
                                    hunter.arrowLoc = a.getLocation();
                                    a.getWorld().playEffect(a.getLocation(), Effect.FOOTSTEP, 0);
                                    a.getWorld().playEffect(a.getLocation().add(0.0D, 2.0D, 0.0D), Effect.FOOTSTEP, 0);
                                    a.getWorld().playEffect(a.getLocation().add(0.0D, -2.0D, 0.0D), Effect.FOOTSTEP, 0);
                                    a.getWorld().playEffect(a.getLocation().add(0.0D, 1.0D, 0.0D), Effect.FOOTSTEP, 0);
                                    a.getWorld().playEffect(a.getLocation().add(2.0D, 0.0D, 0.0D), Effect.FOOTSTEP, 0);
                                    a.getWorld().playEffect(a.getLocation().add(-2.0D, 0.0D, 0.0D), Effect.FOOTSTEP, 0);
                                    a.getWorld().playEffect(a.getLocation().add(0.0D, 0.0D, 2.0D), Effect.FOOTSTEP, 0);
                                    a.getWorld().playEffect(a.getLocation().add(0.0D, 0.0D, -2.0D), Effect.FOOTSTEP, 0);
                                    hunter.player.teleport(a.getLocation().add(0.0D, 1.5D, 0.0D));
                                }
                            } catch (NullPointerException var2) {
                                this.cancel();
                            }

                        }
                    };
                    br.runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("WolfCraftPluginHeros"), 2L, 1L);
                }
            }
        }

    }
}
