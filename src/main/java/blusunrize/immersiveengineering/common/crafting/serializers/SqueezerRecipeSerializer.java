/*
 * BluSunrize
 * Copyright (c) 2020
 *
 * This code is licensed under "Blu's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */

package blusunrize.immersiveengineering.common.crafting.serializers;

import blusunrize.immersiveengineering.api.crafting.IERecipeSerializer;
import blusunrize.immersiveengineering.api.crafting.IngredientWithSize;
import blusunrize.immersiveengineering.api.crafting.SqueezerRecipe;
import blusunrize.immersiveengineering.common.blocks.IEBlocks.Multiblocks;
import blusunrize.immersiveengineering.common.util.Utils;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

public class SqueezerRecipeSerializer extends IERecipeSerializer<SqueezerRecipe>
{
	@Override
	public ItemStack getIcon()
	{
		return new ItemStack(Multiblocks.squeezer);
	}

	@Override
	public SqueezerRecipe readFromJson(ResourceLocation recipeId, JsonObject json)
	{
		FluidStack fluidOutput = FluidStack.EMPTY;
		if(json.has("fluid"))
			fluidOutput = Utils.jsonDeserializeFluidStack(JSONUtils.getJsonObject(json, "fluid"));
		ItemStack itemOutput = ItemStack.EMPTY;
		if(json.has("result"))
			itemOutput = readOutput(JSONUtils.getJsonObject(json, "result"));
		IngredientWithSize input = IngredientWithSize.deserialize(JSONUtils.getJsonObject(json, "input"));
		int energy = JSONUtils.getInt(json, "energy");
		return new SqueezerRecipe(recipeId, fluidOutput, itemOutput, input, energy);
	}

	@Nullable
	@Override
	public SqueezerRecipe read(ResourceLocation recipeId, PacketBuffer buffer)
	{
		FluidStack fluidOutput = buffer.readFluidStack();
		ItemStack itemOutput = buffer.readItemStack();
		IngredientWithSize input = IngredientWithSize.read(buffer);
		int energy = buffer.readInt();
		return new SqueezerRecipe(recipeId, fluidOutput, itemOutput, input, energy);
	}

	@Override
	public void write(PacketBuffer buffer, SqueezerRecipe recipe)
	{
		buffer.writeFluidStack(recipe.fluidOutput);
		buffer.writeItemStack(recipe.itemOutput);
		recipe.input.write(buffer);
		buffer.writeInt(recipe.getTotalProcessEnergy());
	}
}
