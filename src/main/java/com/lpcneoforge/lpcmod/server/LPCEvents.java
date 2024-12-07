package com.lpcneoforge.lpcmod.server;

import com.lpcneoforge.lpcmod.Config;
import com.lpcneoforge.lpcmod.LPCNeoForge;
import com.mojang.datafixers.util.Pair;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.ServerChatEvent;

import java.util.stream.Collectors;

import static com.lpcneoforge.lpcmod.server.ChatUtils.*;

public class LPCEvents {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void ChatMessage(ServerChatEvent event) {
        ServerPlayer serverPlayer = event.getPlayer();
        Component originalMessage = event.getMessage();
        MutableComponent fullMessage = Component.literal("<" + serverPlayer.getName().getString() + "> ").append(originalMessage);


        Pair<Boolean, Component> pair = onServerChat(serverPlayer, fullMessage);
        if (pair != null) {
            if(pair.getFirst()) {
                MutableComponent newMessage = pair.getSecond().copy();
                if (fullMessage != newMessage) {
                    event.setCanceled(true);

                    serverPlayer.server.execute(() -> {
                        LPCNeoForge.LOGGER.info(newMessage.getString());
                        broadcastMessage(serverPlayer.level(), newMessage);
                    });
                }
            }
        }
    }

    public static Pair<Boolean, Component> onServerChat(ServerPlayer player, Component messageComponent) {
        if (LPCNeoForge.getLuckperms() != null) {
            final CachedMetaData metaData = LPCNeoForge.getLuckperms()
                    .getPlayerAdapter(ServerPlayer.class).getMetaData(player);

            String user = player.getName().getString();
            String message = messageComponent.getString();
            String prefix = metaData.getPrefix() != null ? metaData.getPrefix() : "";
            String suffix = metaData.getSuffix() != null ? metaData.getSuffix() : "";
            String prefixes = metaData.getPrefixes().keySet().stream().map(key -> metaData.getPrefixes().get(key)).collect(Collectors.joining());
            String suffixes = metaData.getSuffixes().keySet().stream().map(key -> metaData.getSuffixes().get(key)).collect(Collectors.joining());
            String world = player.level().toString();
            String displayName = player.getGameProfile().getName();
            String usernameColor = metaData.getMetaValue("username-color") != null ? metaData.getMetaValue("username-color") : "";
            String messageColor = metaData.getMetaValue("message-color") != null ? metaData.getMetaValue("message-color") : "";
            if(!message.contains(user)) {
                return null;
            }

            if (message.contains("> ")) {
                message = message.substring(message.split("> ")[0].length() + 2);
            }

            MutableComponent output = Component.literal("");
            String raw_outputstring = Config.CHAT_FORMAT.get();
            for (String word : raw_outputstring.split("%")) {
                String toAppend = word;
                MutableComponent wordComponent;

                if (word.equalsIgnoreCase("prefix")) {
                    toAppend = prefix;
                }
                else if (word.equalsIgnoreCase("suffix")) {
                    toAppend = suffix;
                }
                else if (word.equalsIgnoreCase("prefixes")) {
                    toAppend = prefixes;
                }
                else if (word.equalsIgnoreCase("suffixes")) {
                    toAppend = suffixes;
                }
                else if (word.equalsIgnoreCase("world")) {
                    toAppend = world;
                }
                else if (word.equalsIgnoreCase("username")) {
                    toAppend = user;
                    assert usernameColor != null;
                    wordComponent = applyColorToLiteral(toAppend, usernameColor);
                    output.append(wordComponent);
                    continue;
                }
                else if (word.equalsIgnoreCase("displayname")) {
                    toAppend = displayName;
                }
                else if (word.equalsIgnoreCase("username-color")) {
                    toAppend = usernameColor;
                }
                else if (word.equalsIgnoreCase("message-color")) {
                    toAppend = messageColor;
                }
                else if (word.equalsIgnoreCase("chatmessage")) {
                    toAppend = message;
                    assert messageColor != null;
                    wordComponent = applyColorToLiteral(toAppend, messageColor);
                    output.append(wordComponent);
                    continue;
                }

                assert toAppend != null;
                MutableComponent wordcomponent = parseFormattedText(toAppend);
                output.append(wordcomponent);
            }

            return new Pair<>(true, output);
        }
        return null;
    }


    public static void broadcastMessage(Level world, MutableComponent message) {
        MinecraftServer server = world.getServer();
        if (server == null) {
            return;
        }

        for (Player player : server.getPlayerList().getPlayers()) {
            sendMessage(player, message, false);
        }
    }

    public static void sendMessage(Player player, MutableComponent message, boolean emptyLine) {
        if (player.level().isClientSide) {
            return;
        }

        ServerPlayer serverPlayer = (ServerPlayer)player;
        if (emptyLine) {
            serverPlayer.sendSystemMessage(Component.literal(""));
        }

        serverPlayer.sendSystemMessage(message);
    }
}
