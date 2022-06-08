package ru.theone_ss.vanilla_claws.item;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Language;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;

public class IntegratedClawsItem extends ClawsItem {
    public String requiredMod;
    public boolean isRequiredModInstalled;

    public IntegratedClawsItem(String requiredMod, ToolMaterial material, int damage, float attackSpeed, Settings settings) {
        super(material, damage, attackSpeed, settings);
        this.requiredMod = requiredMod;
        this.isRequiredModInstalled = FabricLoader.getInstance().isModLoaded(requiredMod);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if(!isRequiredModInstalled) {
            String text = Language.getInstance().get("tooltip.vanilla_claws.requires_mod");
            text = text.replaceAll("%", requiredMod.toUpperCase(Locale.ROOT));
            tooltip.add(new LiteralText(text).formatted(Formatting.GRAY));
        }
    }

}
