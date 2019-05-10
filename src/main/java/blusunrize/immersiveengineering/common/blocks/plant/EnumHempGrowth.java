/*
 * BluSunrize
 * Copyright (c) 2017
 *
 * This code is licensed under "Blu's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */

package blusunrize.immersiveengineering.common.blocks.plant;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum EnumHempGrowth implements IStringSerializable
{
	BOTTOM0,
	BOTTOM1,
	BOTTOM2,
	BOTTOM3,
	BOTTOM4,
	TOP0;

	@Override
	public String getName()
	{
		return this.toString().toLowerCase(Locale.ENGLISH);
	}

	public EnumHempGrowth next()
	{
		switch(this)
		{
			case BOTTOM0:
				return BOTTOM1;
			case BOTTOM1:
				return BOTTOM2;
			case BOTTOM2:
				return BOTTOM3;
			case BOTTOM3:
				return BOTTOM4;
			case BOTTOM4:
			case TOP0:
			default:
				return this;
		}
	}
}