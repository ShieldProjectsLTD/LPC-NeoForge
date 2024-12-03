package com.example.examplemod.datagen;

import com.example.examplemod.ExampleMod;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.NotNull;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ExampleMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }

    private void blockWithItem(@NotNull DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(@NotNull DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("shieldextracontent:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(@NotNull DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("shieldextracontent:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
