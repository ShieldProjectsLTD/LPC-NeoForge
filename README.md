
The NeoForge Mod - LuckPerms Chat!
=======
### 1.21.1 Minecraft (NeoForge) version

--------------

# # Instruction
Once you have the project ready, just open the repository in the IDE of your choice.  
It is usually recommended to use the IntelliJ IDEA.

Next, run the `gradle build` command, and you can already start your modding path <3

If at some point it does not find libraries in your IDE or you encounter problems?  
You can run `gradlew --refresh-dependencies` - To update the local cache.  
And also `gradlew clean` - To reset all settings  
P.S. (this will not affect your code)  
And then start the process again.


You can already launch the client with your Mod via the `gradlew runClient`

### # Starting the server
The server can be started, if you want, through this `gradlew runServer` command

When starting the server, you should take into account that you can get the OP through the file ``ops.json``,
Follow the project directory ``run/ops.json``

This `ops.json` file should look like this:
```json
[
    {
        "uuid": "uuid Player",
        "name": "Nickname Player",
        "level": 4,
        "bypassesPlayerLimit": false
    }
]
```
P.S. You can quickly get the UUID of the Player through the file names, look in the directory `run/world/playerdata`, there will be your file with the name that contains the UUID of the Player

--------------

Good **luck**!

Mapping Names:
============
By default, the MDK is configured to use the official mapping names from Mojang for methods and fields
in the Minecraft codebase. These names are covered by a specific license. All modders should be aware of this
license. For the latest license text, refer to the mapping file itself, or the reference copy here:
https://github.com/NeoForged/NeoForm/blob/main/Mojang.md

Recommended Additional Resources:
==========
Community Documentation: https://docs.neoforged.net/  
NeoForged Discord: https://discord.neoforged.net/

--------------
