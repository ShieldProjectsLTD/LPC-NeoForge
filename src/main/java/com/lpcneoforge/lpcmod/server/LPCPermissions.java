package com.lpcneoforge.lpcmod.server;

import com.lpcneoforge.lpcmod.LPCNeoForge;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.yggdrasil.ProfileResult;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.neoforged.neoforge.server.permission.PermissionAPI;
import net.neoforged.neoforge.server.permission.events.PermissionGatherEvent;
import net.neoforged.neoforge.server.permission.nodes.PermissionDynamicContextKey;
import net.neoforged.neoforge.server.permission.nodes.PermissionNode;
import net.neoforged.neoforge.server.permission.nodes.PermissionType;
import net.neoforged.neoforge.server.permission.nodes.PermissionTypes;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public final class LPCPermissions {
    private static final Collection<PermissionNode<?>> NODES = new HashSet<>();

    public static final PermissionNode<Boolean> RELOAD = registerPermissionNode("reload", PermissionTypes.BOOLEAN, DefaultPermissions.permissionLevel(2));
    public static final PermissionNode<Boolean> COLOR_CODES = registerPermissionNode("colorcodes", PermissionTypes.BOOLEAN, DefaultPermissions.ALLOW);
    public static final PermissionNode<Boolean> RGB_CODES = registerPermissionNode("rgbcodes", PermissionTypes.BOOLEAN, DefaultPermissions.ALLOW);

    private static <T> PermissionNode<T> registerPermissionNode(String name, PermissionType<T> type, PermissionNode.PermissionResolver<T> defaultResolver, PermissionDynamicContextKey<?>... contextKeys) {
        return registerPermissionNode(ResourceLocation.fromNamespaceAndPath(LPCNeoForge.MOD_ID, name), type, defaultResolver, contextKeys);
    }

    private static <T> PermissionNode<T> registerPermissionNode(ResourceLocation nodeName, PermissionType<T> type, PermissionNode.PermissionResolver<T> defaultResolver, PermissionDynamicContextKey<?>... contextKeys) {
        PermissionNode<T> node = new PermissionNode<>(nodeName, type, defaultResolver, contextKeys);
        NODES.add(node);
        return node;
    }

    public static Boolean hasPermission(ServerPlayer player, PermissionNode<?> node) {
        if (player == null) {
            return false;
        }
        MinecraftServer server = player.getServer();
        if (server == null) {
            return false;
        }

        Optional<?> result = Optional.of(PermissionAPI.getPermission(player, node));

        // Проверяем, если значение присутствует и равно true
        return (Boolean) result.get();
    }

    static void registerPermissionNodes(PermissionGatherEvent.Nodes event) {
        event.addNodes(NODES);
    }

    public static final class DefaultPermissions {
        private DefaultPermissions() {}

        public static final PermissionNode.PermissionResolver<Boolean> ALLOW = staticValue(true);
        public static final PermissionNode.PermissionResolver<Boolean> DENY = staticValue(false);

        public static PermissionNode.PermissionResolver<Boolean> permissionLevel(int level) {
            return (player, playerUUID, context) -> {
                if (player != null) {
                    return player.createCommandSourceStack().hasPermission(level);
                }
                MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
                assert server != null;
                ProfileResult result = server.getSessionService().fetchProfile(playerUUID, true);
                if (result == null) return false;
                GameProfile gameProfile = result.profile();
                return server.getProfilePermissions(gameProfile) >= level;
            };
        }

        public static PermissionNode.PermissionResolver<Boolean> op() {
            return (player, playerUUID, context) -> {
                MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
                assert server != null;
                int level = server.getOperatorUserPermissionLevel();
                if (player != null) {
                    return player.createCommandSourceStack().hasPermission(level);
                }
                ProfileResult result = server.getSessionService().fetchProfile(playerUUID, true);
                if (result == null) return false;
                GameProfile gameProfile = result.profile();
                return server.getProfilePermissions(gameProfile) >= level;
            };
        }

        public static <T> PermissionNode.PermissionResolver<T> staticValue(T value) {
            return (player, playerUUID, context) -> value;
        }
    }
}
