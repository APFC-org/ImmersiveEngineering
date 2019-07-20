/*
 * BluSunrize
 * Copyright (c) 2017
 *
 * This code is licensed under "Blu's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */

package blusunrize.immersiveengineering.client.render;

import blusunrize.immersiveengineering.common.entities.IEExplosiveEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class EntityRenderIEExplosive extends EntityRenderer<IEExplosiveEntity>
{
	public EntityRenderIEExplosive(EntityRendererManager renderManager)
	{
		super(renderManager);
		this.shadowSize = .5f;
	}

	@Override
	public void doRender(IEExplosiveEntity entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		if(entity.block==null)
			return;
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getInstance().getBlockRendererDispatcher();
		GlStateManager.pushMatrix();
		GlStateManager.translated((float)x, (float)y+0.5F, (float)z);

		if(entity.getFuse()-partialTicks+1 < 10)
		{
			float f = 1.0F-((float)entity.getFuse()-partialTicks+1.0F)/10.0F;
			f = MathHelper.clamp(f, 0.0F, 1.0F);
			f = f*f;
			f = f*f;
			float f1 = 1.0F+f*0.3F;
			GlStateManager.scalef(f1, f1, f1);
		}

		float f2 = (1-(entity.getFuse()-partialTicks+1)/100F)*.8F;
		this.bindEntityTexture(entity);
		GlStateManager.translated(-0.5F, -0.5F, 0.5F);
		blockrendererdispatcher.renderBlockBrightness(entity.block, entity.getBrightness());
		GlStateManager.translated(0.0F, 0.0F, 1.0F);

		if(entity.getFuse()/5%2==0)
		{
			GlStateManager.disableTexture();
			GlStateManager.disableLighting();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(770, 772);
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, f2);
			GlStateManager.polygonOffset(-3.0F, -3.0F);
			GlStateManager.enablePolygonOffset();
			blockrendererdispatcher.renderBlockBrightness(entity.block, 1.0F);
			GlStateManager.polygonOffset(0.0F, 0.0F);
			GlStateManager.disablePolygonOffset();
			GlStateManager.color3f(1, 1, 1);
			GlStateManager.disableBlend();
			GlStateManager.enableLighting();
			GlStateManager.enableTexture();
		}

		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(IEExplosiveEntity entity)
	{
		return AtlasTexture.LOCATION_BLOCKS_TEXTURE;
	}
}