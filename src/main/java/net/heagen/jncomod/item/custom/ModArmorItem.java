package net.heagen.jncomod.item.custom;

import com.google.common.collect.ImmutableMap;
import net.heagen.jncomod.effect.ModEffects;
import net.heagen.jncomod.item.ModArmorMaterials;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.level.NoteBlockEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

public class ModArmorItem extends ArmorItem {
    private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(ModArmorMaterials.DARK_STONE_TWIN_CANNON_BUDDHA, new MobEffectInstance(ModEffects.PROSPERITY_EFFECT.get(), 400, 0))
                    .put(ModArmorMaterials.JET_BLACK_TWIN_CANNON_BUDDHA, new MobEffectInstance(ModEffects.PROSPERITY_EFFECT.get(), 400, 0))
                    .build();

    public ModArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    public boolean makesFlameheadNeutral(ItemStack stack, LivingEntity wearer){
        return stack.getItem() instanceof ModArmorItem && ((ModArmorItem) stack.getItem()).getMaterial() ==
                ModArmorMaterials.DARK_STONE_TWIN_CANNON_BUDDHA;
    }


    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(!pLevel.isClientSide()) {
            if (pEntity instanceof Player player) {
                if (hasArmorOn(player)) {
                    evaluateArmorEffects(player);
                }
            }
        }
    }

    private void evaluateArmorEffects(Player player) {
        for(Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffectInstance mapEffect = entry.getValue();

            if (hasPlayerCorrectArmorOn(mapArmorMaterial, player)) {
                addEffectToPlayer(player, mapEffect);
            }
        }
    }

    private void addEffectToPlayer(Player player, MobEffectInstance mapEffect) {
        boolean hasPlayerEffect = player.hasEffect(mapEffect.getEffect());

        if(!hasPlayerEffect) {
            player.addEffect(new MobEffectInstance(mapEffect.getEffect(),
                    mapEffect.getDuration(), mapEffect.getAmplifier()));
        }
    }

    private boolean hasPlayerCorrectArmorOn(ArmorMaterial mapArmorMaterial, Player player) {
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(1).getItem());

        return leggings.getMaterial() == mapArmorMaterial;
    }

    private boolean hasArmorOn(Player player) {
        ItemStack leggings = player.getInventory().getArmor(1);

    return !leggings.isEmpty();
    }
}
