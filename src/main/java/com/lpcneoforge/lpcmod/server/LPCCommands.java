package com.lpcneoforge.lpcmod.server;

import com.lpcneoforge.lpcmod.server.commands.LPCReloadCommand;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public final class LPCCommands {
    static void registerCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal("lpc").requires(p -> p.hasPermission(4));
//        LPCReloadCommand.register(builder);
        dispatcher.register(builder);
    }
}
