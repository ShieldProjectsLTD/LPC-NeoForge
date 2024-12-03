package com.example.examplemod.items;

import com.example.examplemod.ExampleMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExampleMod.MODID);


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
