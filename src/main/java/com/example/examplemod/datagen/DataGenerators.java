package com.example.examplemod.datagen;

import com.example.examplemod.ExampleMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = ExampleMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput out = gen.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        gen.addProvider(event.includeServer(), new LootTableProvider(out, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
        gen.addProvider(event.includeServer(), new ModRecipeProvider(out, lookupProvider));

        BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(out, lookupProvider, existingFileHelper);
        gen.addProvider(event.includeServer(), blockTagsProvider);
        gen.addProvider(event.includeServer(), new ModItemTagProvider(out, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));

        gen.addProvider(event.includeServer(), new ModDataMapProvider(out, lookupProvider));

        gen.addProvider(event.includeClient(), new ModItemModelProvider(out, existingFileHelper));
        gen.addProvider(event.includeClient(), new ModBlockStateProvider(out, existingFileHelper));
    }
}
