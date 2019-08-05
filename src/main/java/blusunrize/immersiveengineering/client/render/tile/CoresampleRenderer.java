/*
 * BluSunrize
 * Copyright (c) 2017
 *
 * This code is licensed under "Blu's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */

package blusunrize.immersiveengineering.client.render.tile;

import blusunrize.immersiveengineering.client.ClientUtils;
import blusunrize.immersiveengineering.common.blocks.stone.CoresampleTileEntity;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.util.Direction;

public class CoresampleRenderer extends TileEntityRenderer<CoresampleTileEntity>
{
	@Override
	public void render(CoresampleTileEntity tile, double x, double y, double z, float partialTicks, int destroyStage)
	{
		if(!tile.getWorld().isBlockLoaded(tile.getPos())||tile.coresample==null)
			return;

		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.translated(x+.5, y+.54864, z+.52903);
		GlStateManager.rotatef(tile.facing==Direction.NORTH?180: tile.facing==Direction.WEST?-90: tile.facing==Direction.EAST?90: 0, 0, 1, 0);
		GlStateManager.rotatef(-45, 1, 0, 0);
		ClientUtils.mc().getItemRenderer().renderItem(tile.coresample, TransformType.FIXED);
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}
}