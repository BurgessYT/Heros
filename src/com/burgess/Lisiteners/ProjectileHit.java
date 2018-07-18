package com.burgess.Lisiteners;

import com.burgess.Heros.Hero;
import com.burgess.Heros.PublicData;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Effect;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ProjectileHit {
    ProjectileHitEvent projectile;

    public ProjectileHit() {
        this((ProjectileHitEvent)null);
    }

    public ProjectileHit(ProjectileHitEvent projectile) {
        this.projectile = projectile;
    }

    public void onProjectileHit() {
        if (this.projectile.getEntity() instanceof CraftArrow) {
            Arrow a = (Arrow)this.projectile.getEntity();
            if (a.getShooter() instanceof Player && PublicData.onlineHero.containsKey(((Player)a.getShooter()).getName())) {
                Hero hunter = (Hero)PublicData.onlineHero.get(((Player)a.getShooter()).getName());
                if (hunter.hero.equals(Hero.Hunter) && hunter.enableSkill1) {
                    PublicData.particleCircleCreate(a.getLocation(), 2.6D, Effect.FIREWORKS_SPARK);
                    int lightTime = hunter.level * 6 + 160;
                    List<Entity> entityList = a.getNearbyEntities(6.0D, 6.0D, 6.0D);
                    Iterator var6 = entityList.iterator();

                    while(var6.hasNext()) {
                        Entity entity = (Entity)var6.next();
                        if (entity instanceof LivingEntity) {
                            ((LivingEntity)entity).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, lightTime, 1), true);
                        }

                        if (entity instanceof Player) {
                            ((Player)entity).sendMessage(PublicData.pluginPrefix + "§c您获得了 §b" + hunter.player.getName() + " §c释放的 §7[ §b斥候箭 §7] 效果§c，获得 §b" + lightTime / 20 + " §c秒斥候效果");
                        }
                    }

                    hunter.enableSkill1 = false;
                }
            }
        }

    }
}
