package com.burgess.Lisiteners;

import com.burgess.Heros.Ghost;
import com.burgess.Heros.Hero;
import com.burgess.Heros.PublicData;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class EntityDamageByEntity {
    EntityDamageByEntityEvent e;

    public EntityDamageByEntity() {
        this((EntityDamageByEntityEvent)null);
    }

    public EntityDamageByEntity(EntityDamageByEntityEvent ede) {
        this.e = ede;
    }

    public void onEntityDamageByEntity() {
        if (PublicData.enableWorlds.contains(this.e.getEntity().getWorld().getName())) {
            double damageNum;
            Hero hero;
            int lightTime;
            LivingEntity le;
            LivingEntity player;
            if (this.e.getDamager() instanceof Arrow) {
                Arrow a = (Arrow)this.e.getDamager();
                Player d;
                List entityList;
                Entity entity;
                Iterator var9;
                LivingEntity shoushang;
                int slowTime;
                if (this.e.getEntity() instanceof Player) {
                    if (PublicData.onlineHero.containsKey(this.e.getEntity().getName()) && ((Hero)PublicData.onlineHero.get(this.e.getEntity().getName())).hero.equals(Hero.Vampire) && ((Hero)PublicData.onlineHero.get(this.e.getEntity().getName())).enableSkill3 && a.getShooter() instanceof LivingEntity) {
                        player = (LivingEntity)a.getShooter();
                        Location leeRotate = player.getLocation().setDirection(player.getLocation().getDirection().multiply(-1));
                        player.teleport(leeRotate);
                    }

                    if (a.getShooter() instanceof Player) {
                        d = (Player)a.getShooter();
                        if (PublicData.onlineHero.containsKey(d.getName())) {
                            hero = (Hero)PublicData.onlineHero.get(d.getName());
                            if (hero.hero.equals(Hero.Hunter)) {
                                if (hero.enableSkill1 && this.e.getEntity() instanceof LivingEntity) {
                                    lightTime = hero.level * 6 + 160;
                                    PublicData.particleAroundCreate(this.e.getEntity().getLocation(), 2.6D, Effect.FIREWORKS_SPARK);
                                    this.e.getEntity().sendMessage(PublicData.pluginPrefix + "§c您被 §b" + hero.player.getName() + " §c释放的 §7[ §b斥候箭 §7] §c射中，获得 §b" + lightTime / 20 + " §c秒斥候效果");
                                    entityList = ((LivingEntity)this.e.getEntity()).getNearbyEntities(6.0D, 6.0D, 6.0D);
                                    ((LivingEntity)this.e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, lightTime, 1), true);
                                    var9 = entityList.iterator();

                                    while(var9.hasNext()) {
                                        entity = (Entity)var9.next();
                                        if (entity instanceof LivingEntity) {
                                            ((LivingEntity)entity).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, lightTime, 1), true);
                                        }

                                        if (entity instanceof Player) {
                                            ((Player)entity).sendMessage(PublicData.pluginPrefix + "§c您获得了 §b" + hero.player.getName() + " §c释放的 §7[ §b斥候箭 §7] 效果§c，获得 §b" + lightTime / 20 + " §c秒斥候效果");
                                        }
                                    }
                                }

                                if (hero.enableSkill2) {
                                    if (this.e.getEntity() instanceof LivingEntity) {
                                        ((LivingEntity)this.e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 120, 3), true);
                                        this.e.getEntity().sendMessage(PublicData.pluginPrefix + "§c您被 §b" + hero.player.getName() + " §c释放的 §7[ §b寒冰箭 §7] §c射中，获得 §b6 §c秒高额减速效果");
                                        PublicData.particleAroundCreate(this.e.getEntity().getLocation(), 3.0D, Effect.SNOWBALL_BREAK);
                                    }

                                    hero.enableSkill2 = false;
                                }

                                if (hero.enableSkill4) {
                                    Player ss = (Player)this.e.getEntity();
                                    damageNum = ss.getHealth() * 0.3D + (ss.getMaxHealth() - ss.getHealth()) * 0.2D;
                                    this.e.getEntity().sendMessage(PublicData.pluginPrefix + "§c您被 §b" + hero.player.getName() + " §c释放的 §7[ §b龙晏箭 §7] §c射中，获得 §b" + damageNum + " §c点伤害");
                                    PublicData.particleAroundCreate(ss.getLocation(), 3.0D, Effect.CRIT);
                                } else {
                                    damageNum = (double)hero.level * 0.3D + 5.0D;
                                }

                                if (this.e.getEntity().getWorld().getPVP()) {
                                    this.e.setDamage(this.e.getDamage() + damageNum);
                                } else {
                                    this.e.setDamage(0.0D);
                                }

                                hero.enableSkill1 = false;
                                hero.enableSkill4 = false;
                            } else if (hero.hero.equals(Hero.TreeElves)) {
                                shoushang = (LivingEntity)this.e.getEntity();
                                hero.enableSkill2 = false;
                                if (hero.enableSkill1) {
                                    slowTime = hero.level * 2 + 2;
                                    shoushang.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, slowTime, 10));
                                    shoushang.sendMessage(PublicData.pluginPrefix + "§c您被 §b" + hero.player.getName() + " §c释放的 §7[ §b藤条束缚 §7] §c击中，§b" + slowTime / 20 + " §c秒内不能移动");
                                    hero.player.sendMessage(PublicData.pluginPrefix + "§d您的 §7[ §b藤条束缚 §7] §d击中了玩家 §b" + shoushang.getName());
                                    hero.enableSkill1 = false;
                                }

                                damageNum = (double)((int)(shoushang.getMaxHealth() * 0.05D));
                                if (this.e.getEntity().getWorld().getPVP()) {
                                    this.e.setDamage(this.e.getDamage() + damageNum);
                                } else {
                                    this.e.setDamage(0.0D);
                                }
                            }
                        }
                    }

                    if (PublicData.onlineHero.containsKey(this.e.getEntity().getName()) && ((Hero)PublicData.onlineHero.get(this.e.getEntity().getName())).hero.equals(Hero.Demon)) {
                        hero = (Hero)PublicData.onlineHero.get(this.e.getEntity().getName());
                        if (hero.enableSkill4) {
                            this.e.setDamage(this.e.getDamage() * 0.3D);
                        }
                    }
                } else if (a.getShooter() instanceof Player) {
                    d = (Player)a.getShooter();
                    if (PublicData.onlineHero.containsKey(d.getName())) {
                        hero = (Hero)PublicData.onlineHero.get(d.getName());
                        if (!hero.hero.equals(Hero.Hunter)) {
                            if (hero.hero.equals(Hero.TreeElves)) {
                                shoushang = (LivingEntity)this.e.getEntity();
                                hero.enableSkill2 = false;
                                if (hero.enableSkill1) {
                                    slowTime = hero.level * 2 + 2;
                                    shoushang.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, slowTime, 10));
                                    hero.player.sendMessage(PublicData.pluginPrefix + "§d您的 §7[ §b藤条束缚 §7] §d击中了 §b" + shoushang.getName());
                                    hero.enableSkill1 = false;
                                }

                                damageNum = (double)((int)(shoushang.getMaxHealth() * 0.05D));
                                this.e.setDamage(this.e.getDamage() + damageNum);
                            }
                        } else {
                            if (hero.enableSkill1 && this.e.getEntity() instanceof LivingEntity) {
                                lightTime = hero.level * 6 + 160;
                                PublicData.particleAroundCreate(this.e.getEntity().getLocation(), 2.6D, Effect.FIREWORKS_SPARK);
                                entityList = ((LivingEntity)this.e.getEntity()).getNearbyEntities(6.0D, 6.0D, 6.0D);
                                ((LivingEntity)this.e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, lightTime, 1), true);
                                var9 = entityList.iterator();

                                while(var9.hasNext()) {
                                    entity = (Entity)var9.next();
                                    if (entity instanceof LivingEntity) {
                                        ((LivingEntity)entity).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, lightTime, 1), true);
                                    }

                                    if (entity instanceof Player) {
                                        ((Player)entity).sendMessage(PublicData.pluginPrefix + "§c您获得了 §b" + hero.player.getName() + " §c释放的 §7[ §b斥候箭 §7] 效果§c，获得 §b" + lightTime / 20 + " §c秒斥候效果");
                                    }
                                }
                            }

                            if (hero.enableSkill2) {
                                if (this.e.getEntity() instanceof LivingEntity) {
                                    ((LivingEntity)this.e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 120, 3), true);
                                    PublicData.particleAroundCreate(this.e.getEntity().getLocation(), 3.0D, Effect.SNOWBALL_BREAK);
                                }

                                hero.enableSkill2 = false;
                            }

                            if (hero.enableSkill4 && this.e.getEntity() instanceof LivingEntity) {
                                shoushang = (LivingEntity)this.e.getEntity();
                                damageNum = shoushang.getHealth() * 0.3D + (shoushang.getMaxHealth() - shoushang.getHealth()) * 0.2D;
                                PublicData.particleAroundCreate(shoushang.getLocation(), 3.0D, Effect.CRIT);
                            } else {
                                damageNum = (double)hero.level * 0.3D + 5.0D;
                            }

                            this.e.setDamage(this.e.getDamage() + damageNum);
                            hero.enableSkill1 = false;
                            hero.enableSkill4 = false;
                        }
                    }
                }

                if (a.getShooter() instanceof Player && PublicData.onlineHero.containsKey(((Player)a.getShooter()).getName()) && ((Hero)PublicData.onlineHero.get(((Player)a.getShooter()).getName())).hero.equals(Hero.Ghost)) {
                    hero = (Hero)PublicData.onlineHero.get(((Player)a.getShooter()).getName());
                    this.e.setDamage(Ghost.getDistanceDamage(this.e.getDamage(), hero.player, this.e.getEntity()));
                }

                if (PublicData.onlineHero.containsKey(this.e.getEntity().getName()) && this.e.getEntity() instanceof Player && ((Hero)PublicData.onlineHero.get(this.e.getEntity().getName())).hero.equals(Hero.Knight)) {
                    hero = (Hero)PublicData.onlineHero.get(this.e.getEntity().getName());
                    if (hero.enableSkill3 && a.getShooter() instanceof LivingEntity) {
                        shoushang = (LivingEntity)a.getShooter();
                        shoushang.damage(this.e.getFinalDamage());
                    }
                }
            } else {
                if (PublicData.onlineHero.containsKey(this.e.getEntity().getName()) && ((Hero)PublicData.onlineHero.get(this.e.getEntity().getName())).hero.equals(Hero.Vampire) && ((Hero)PublicData.onlineHero.get(this.e.getEntity().getName())).enableSkill3 && this.e.getDamager() instanceof LivingEntity) {
                    le = (LivingEntity)this.e.getDamager();
                    Location leeRotate = le.getLocation().setDirection(le.getLocation().getDirection().multiply(-1));
                    le.teleport(leeRotate);
                }

                if (this.e.getDamager() instanceof Player && PublicData.onlineHero.containsKey(this.e.getDamager().getName())) {
                    hero = (Hero)PublicData.onlineHero.get(this.e.getDamager().getName());
                    if (hero.hero.equals(Hero.TreeElves)) {
                        hero.enableSkill2 = false;
                        player = (LivingEntity)this.e.getEntity();
                        if (hero.enableSkill1) {
                            lightTime = hero.level * 2 + 2;
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, lightTime, 10));
                            player.sendMessage(PublicData.pluginPrefix + "§c您被 §b" + hero.player.getName() + " §c释放的 §7[ §b藤条束缚 §7] §c击中，§b" + lightTime / 20 + "§c秒内不能移动");
                            hero.player.sendMessage(PublicData.pluginPrefix + "§d您的 §7[ §b藤条束缚 §7] §d击中了 §b" + player.getName());
                            hero.enableSkill1 = false;
                        }

                        damageNum = (double)((int)(player.getMaxHealth() * 0.05D));
                        if (this.e.getEntity() instanceof Player) {
                            if (this.e.getEntity().getWorld().getPVP()) {
                                this.e.setDamage(this.e.getDamage() + damageNum);
                            } else {
                                this.e.setDamage(0.0D);
                            }
                        } else {
                            this.e.setDamage(this.e.getDamage() + damageNum);
                        }
                    } else if (hero.hero.equals(Hero.Demon)) {
                        player = (LivingEntity)this.e.getEntity();
                        if (hero.enableSkill1) {
                            lightTime = hero.level * 2 + 80;
                            if (player instanceof Player && PublicData.onlineHero.containsKey(player.getName())) {
                                final Hero chenmoHero = (Hero)PublicData.onlineHero.get(player.getName());
                                chenmoHero.canUseSkill = false;
                                BukkitRunnable br = new BukkitRunnable() {
                                    public void run() {
                                        chenmoHero.canUseSkill = true;
                                    }
                                };
                                br.runTaskLaterAsynchronously(Bukkit.getPluginManager().getPlugin("WolfCraftPluginHeros"), (long)lightTime);
                            }

                            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, lightTime, 1), true);
                            player.sendMessage(PublicData.pluginPrefix + "§c您被 §b" + hero.player.getName() + " §c释放的 §7[ §b弱化 §7] §c击中，§b沉默持续" + lightTime / 20 + "§c秒");
                            hero.player.sendMessage(PublicData.pluginPrefix + "§d您的 §7[ §b弱化 §7] §d击中了 §b" + player.getName());
                            hero.enableSkill1 = false;
                        }
                    }
                }
            }

            if (this.e.getEntity() instanceof LivingEntity) {
                le = (LivingEntity)this.e.getEntity();
                if (this.e.getFinalDamage() >= le.getHealth() && this.e.getDamager() instanceof Player && PublicData.onlineHero.containsKey(this.e.getDamager().getName()) && ((Hero)PublicData.onlineHero.get(this.e.getDamager().getName())).hero.equals(Hero.Ghost)) {
                    hero = (Hero)PublicData.onlineHero.get(this.e.getDamager().getName());
                    if (this.e.getEntity() instanceof Monster) {
                        if (hero.pbNum < 2) {
                            ++hero.pbNum;
                        } else {
                            if (hero.ghostNum < 8) {
                                ++hero.ghostNum;
                            }

                            hero.pbNum = 0;
                        }

                        if (PublicData.onlineHero.containsKey(le.getName())) {
                            lightTime = ((Hero)PublicData.onlineHero.get(le.getName())).level / 10 + 1;
                            hero.player.setHealth(hero.player.getHealth() + (double)lightTime > hero.player.getMaxHealth() ? hero.player.getMaxHealth() : hero.player.getHealth() + (double)lightTime);
                        }
                    } else if (this.e.getEntity() instanceof Player) {
                        if (hero.ghostNum < 8) {
                            ++hero.ghostNum;
                        }

                        if (PublicData.onlineHero.containsKey(le.getName())) {
                            lightTime = ((Hero)PublicData.onlineHero.get(le.getName())).level / 10 + 1;
                            hero.player.setHealth(hero.player.getHealth() + (double)lightTime > hero.player.getMaxHealth() ? hero.player.getMaxHealth() : hero.player.getHealth() + (double)lightTime);
                        }
                    }

                    PublicData.loadPlayerActionBar(hero.player);
                }

                if (this.e.getDamager() instanceof Player && PublicData.onlineHero.containsKey(this.e.getDamager().getName()) && ((Hero)PublicData.onlineHero.get(this.e.getDamager().getName())).hero.equals(Hero.Ghost)) {
                    hero = (Hero)PublicData.onlineHero.get(this.e.getDamager().getName());
                    this.e.setDamage(Ghost.getDistanceDamage(this.e.getDamage(), hero.player, this.e.getEntity()));
                }
            }

            if (this.e.getDamager() instanceof Player && PublicData.onlineHero.containsKey(this.e.getDamager().getName()) && ((Hero)PublicData.onlineHero.get(this.e.getDamager().getName())).hero.equals(Hero.Vampire)) {
                hero = (Hero)PublicData.onlineHero.get(this.e.getDamager().getName());
                double finaldamage = this.e.getFinalDamage();
                double healthdamage = finaldamage * (double)hero.level / 100.0D + 2.0D;
                hero.player.setHealth(hero.player.getHealth() + healthdamage > hero.player.getMaxHealth() ? hero.player.getMaxHealth() : hero.player.getHealth() + healthdamage);
            }

            if (this.e.getEntity() instanceof Player && PublicData.onlineHero.containsKey(this.e.getEntity().getName()) && ((Hero)PublicData.onlineHero.get(this.e.getEntity().getName())).hero.equals(Hero.Knight)) {
                hero = (Hero)PublicData.onlineHero.get(this.e.getEntity().getName());
                if (hero.enableSkill3 && this.e.getDamager() instanceof LivingEntity) {
                    ((LivingEntity)this.e.getDamager()).damage(this.e.getDamage());
                }
            }

        }
    }
}
