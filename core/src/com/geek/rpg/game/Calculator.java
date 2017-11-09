package com.geek.rpg.game;

public class Calculator {
    private static final int MIN_MELEE_ATTACK_CHANCE = 20;
    private static final int MAX_MELEE_ATTACK_CHANCE = 95;

    public static int getMeleeDamage(AbstractUnit attacker, AbstractUnit target) {
        int dmg = attacker.getStrength() - target.getDefence();



        dmg = (int) (dmg * 0.8f + (float) dmg * Math.random() * 0.4f);
        if (dmg < 1) {
            dmg = 1;
        }
        return dmg;
    }
    public static boolean isCritical(AbstractUnit attacker, AbstractUnit target) {
        int criticalChance =(int)(1.0f+(attacker.level - target.level)*1.0f+(attacker.dexterity-target.dexterity)*0.5f);
        if (criticalChance>=70) {
            criticalChance=70;
        }
        if (Math.random()*100<=criticalChance) {
            return true;
        }
        return false;
    }
    public static int doCritical(int dmg,AbstractUnit attacker, AbstractUnit target) {
        return (int)(dmg * 1.5f + (attacker.strength-target.strength) * 1.0f +(attacker.level-target.level) * 1.0f);
    }

    public static boolean isTargetEvaded(AbstractUnit attacker, AbstractUnit target) {
        int attackChance = (int)(85.0f + (attacker.getDexterity() - target.getDexterity()) * 0.2f + (attacker.getLevel() - target.getLevel()) * 1.0f);
        if (attackChance > MAX_MELEE_ATTACK_CHANCE) attackChance = MAX_MELEE_ATTACK_CHANCE;
        if (attackChance < MIN_MELEE_ATTACK_CHANCE) attackChance = MIN_MELEE_ATTACK_CHANCE;
        return Math.random() * 100 > attackChance;
    }
}
