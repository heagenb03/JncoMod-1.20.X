package net.heagen.jncomod.block.custom;

import net.heagen.jncomod.block.ModBlocks;
import net.heagen.jncomod.worldgen.dimension.ModDimensions;
import net.heagen.jncomod.worldgen.portal.JNCOTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class JNCOPortalBlock extends Block {
    public JNCOPortalBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pEntity.canChangeDimensions()) {
            handleJNCOPortal(pEntity, pPos);
        }
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        for(int i = 0; i < 4; ++i) {
            double d0 = (double)pPos.getX() + pRandom.nextDouble();
            double d1 = (double)pPos.getY() + pRandom.nextDouble();
            double d2 = (double)pPos.getZ() + pRandom.nextDouble();
            double d3 = ((double)pRandom.nextFloat() - 0.5D) * 0.5D;
            double d4 = ((double)pRandom.nextFloat() - 0.5D) * 0.5D;
            double d5 = ((double)pRandom.nextFloat() - 0.5D) * 0.5D;
            int j = pRandom.nextInt(2) * 2 - 1;
            if (!pLevel.getBlockState(pPos.west()).is(this) && !pLevel.getBlockState(pPos.east()).is(this)) {
                d0 = (double)pPos.getX() + 0.5D + 0.25D * (double)j;
                d3 = (double)(pRandom.nextFloat() * 2.0F * (float)j);
            } else {
                d2 = (double)pPos.getZ() + 0.5D + 0.25D * (double)j;
                d5 = (double)(pRandom.nextFloat() * 2.0F * (float)j);
            }
        }
    }

    private void handleJNCOPortal(Entity pPlayer, BlockPos pPos) {
        if (pPlayer.level() instanceof ServerLevel serverLevel) {
            MinecraftServer minecraftServer = serverLevel.getServer();
            ResourceKey<Level> resourcekey = pPlayer.level().dimension() == ModDimensions.JNCODIM_LEVEL_KEY ?
                    Level.OVERWORLD : ModDimensions.JNCODIM_LEVEL_KEY;
            
            ServerLevel portalDimension = minecraftServer.getLevel(resourcekey);
            if (portalDimension != null && !pPlayer.isPassenger()) {
                if (resourcekey == ModDimensions.JNCODIM_LEVEL_KEY) {
                    pPlayer.changeDimension(portalDimension, new JNCOTeleporter(pPos, true));
                } else {
                    pPlayer.changeDimension(portalDimension, new JNCOTeleporter(pPos, false));
                }
            }
        }
    }
}
