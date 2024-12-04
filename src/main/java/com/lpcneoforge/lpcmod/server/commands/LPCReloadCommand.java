package com.lpcneoforge.lpcmod.server.commands;

import com.lpcneoforge.lpcmod.LPCNeoForge;
import com.lpcneoforge.lpcmod.server.LPCPermissions;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.server.permission.PermissionAPI;

import java.util.Collection;
import java.util.List;

public class LPCReloadCommand {

//    public static void register(LiteralArgumentBuilder<CommandSourceStack> builder) {
//        builder.then(Commands.literal("reload")
//                .requires(source -> {
//                    if (source.getEntity() != null) {
//                        return source.hasPermission(4);
//                    }
//                    if (source.getEntity() instanceof ServerPlayer player) {
//                        return isPermissionAvailable(player);
//                    }
//                    return false;
//                })
//                .executes(LPCReloadCommand::reloadMod));
//    }
//
//    private static boolean isPermissionAvailable(ServerPlayer player) {
//        try {
//            return PermissionAPI.getPermission(player, LPCPermissions.CAN_EXECUTE_LPC_RELOAD_COMMAND);
//        } catch (IllegalStateException e) {
//            player.sendSystemMessage(Component.literal("Permissions system is not initialized yet!"));
//            return false;
//        }
//    }
//
//    private static int reloadMod(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
//        return reloadMod(List.of(ctx.getSource().getPlayerOrException()), ctx);
//    }
//
//    private static int reloadMod(Collection<ServerPlayer> players, CommandContext<CommandSourceStack> ctx) {
//        if (reloadLPCMod()) {
//            for (ServerPlayer player : players) {
//                player.sendSystemMessage(Component.literal("LPCMod reloaded successfully!"));
//            }
//            ctx.getSource().sendSuccess(() -> Component.literal("LPCMod reloaded for all players!"), true);
//            return players.size();
//        } else {
//            ctx.getSource().sendFailure(Component.literal("Failed to reload LPCMod. Check server logs for details."));
//            return 0;
//        }
//    }
//
//    private static boolean reloadLPCMod() {
//        try {
//            LPCNeoForge.getInstance().reload();
//            return true;
//        } catch (Exception e) {
//            LPCNeoForge.LOGGER.error("Error during LPCMod reload", e);
//            return false;
//        }
//    }
}
