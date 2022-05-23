package ru.theone_ss.vanilla_claws;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import ru.theone_ss.vanilla_claws.compat.WinterlyIntegration;
import ru.theone_ss.vanilla_claws.registry.VanillaClawsItems;

public class VanillaClaws implements ModInitializer {



    @Override
    public void onInitialize() {

        VanillaClawsItems.init();
        if(FabricLoader.getInstance().isModLoaded("winterly")) {
            WinterlyIntegration.init();
        }

    }
}
