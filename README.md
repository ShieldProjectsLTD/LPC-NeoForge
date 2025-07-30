# LuckPerms Chat

**Make your server chat more organized and beautiful!**

LuckPerms Chat is a lightweight and simple server module for NeoForge that enhances your chat by adding prefixes and settings from LuckPerms.

## Links to mod pages:
- #### Modrinth -> [LuckPerms Chat](https://modrinth.com/mod/luckperms-chat)
- #### CurseForge -> [LuckPerms Chat](https://www.curseforge.com/minecraft/mc-mods/luckperms-chat)

## Why LuckPerms Chat?
- **Easily customizable:** Chat formatting settings can be easily configured using the `lpcmod-common.toml` file.
- **Improved communication:** Add prefixes and colors related to specific groups and improve the formatting of messages to make the chat on the server more interesting and structured.

## How to use
1. Install [LuckPerms](https://modrinth.com/plugin/luckperms).
2. Add the LuckPerms Chat mod to the `mods` folder of your server.
3. Configure the LPC mod configuration in `lpcmod-common.toml` according to your server needs.
4. Restart the server and you are ready to go!

## Requirements
- **NeoForge**
- **Required mod:** [LuckPerms](https://modrinth.com/plugin/luckperms)
- **Server-side only**

---

## For developers

This repository contains multiple branches for different Minecraft versions:

- The `main` branch contains the base README and general info.
- Branches like `1.21.1` and `1.21.6` hold the mod sources for specific Minecraft versions.

## Building the mod
Use Gradle to build the mod jar:
```bash
  ./gradlew clean build --refresh-dependencies
```

### Start client
```bash
    ./gradlew runClient
```

### Start server
```bash
    ./gradlew runServer
```

### Refreshing dependencies
If libraries fail to resolve, run:
```bash
    ./gradlew --refresh-dependencies
```