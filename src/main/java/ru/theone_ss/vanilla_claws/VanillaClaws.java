package ru.theone_ss.vanilla_claws;

import net.fabricmc.api.ModInitializer;
import ru.theone_ss.vanilla_claws.registry.VanillaClawsItems;

public class VanillaClaws implements ModInitializer {



    @Override
    public void onInitialize() {

        VanillaClawsItems.init();

    }
}
