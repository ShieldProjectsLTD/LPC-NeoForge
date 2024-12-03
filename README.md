
The NeoForge Mod Template - For an easier start!
=======
### 1.21.1 Minecraft Version

--------------

# # EN-US Instruction
This repository is like a Template. You can clone it directly to get started with the new
mod. It's a great and fast start!  
Once you have the project ready, just open the repository in the IDE of your choice.  
It is usually recommended to use the IntelliJ IDEA or Eclipse IDE.

Next, run the `gradle build` command, and you can already start your modding path <3

If at some point it does not find libraries in your IDE or you encounter problems?  
You can run `gradlew --refresh-dependencies` - To update the local cache.  
And also `gradlew clean` - To reset all settings  
P.S. (this will not affect your code)  
And then start the process again.

And also, if you have added some recipes, or something else, do not forget to run the `gradlew runData` command

You can already launch the client with your Mod via the `gradlew runClient`

### # Starting the server (Optional)
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

Good luck! You are the best modder!

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
--------------


# # RU-RU Инструкция
Этот репозиторий является как Шаблон. Можно напрямую клонировать, чтобы начать работу с новым
модом. Это прекрасный и быстрый старт!  
Как только у вас будет готов проект, просто откройте репозиторий в IDE по вашему выбору.  
Обычно рекомендуется использовать IDE IntelliJ IDEA или Eclipse.

Далее выполните команду `gradlew build`, и уже можете начинать свой путь моддинга <3

Если в какой-то момент не находит библиотеки в вашей IDE или вы столкнулись с проблемами?  
Вы можете запустить `gradlew --refresh-dependencies` - Чтобы обновить локальный кэш.  
А также `gradlew clean` - Чтобы сбросить все настройки  
P.S. (это не повлияет на ваш код)
А затем запустите процесс заново.

А также, если вы добавили какие-то рецепты, или другое, не забудьте выполнить команду `gradlew runData`

Запустить клиент с вашим Модом, уже можно через `gradlew runClient`

### # Запуск сервера (Не Обязательно)
Сервер можно запустить, если вы это хотите, через эту команду `gradlew runServer`

При запуске сервера, вы должны учитывать то, что вы можете получить OP через файл ```ops.json```,
Следуйте по директории проекта ```run/ops.json```

Этот `ops.json` файл должен выглядеть так:
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
P.S. Вы можете быстро получить UUID Игрока, через названия файла, гляньте в директорию `run/world/playerdata`, там будет лежать ваш файл с названием который содержит UUID Игрока

--------------

Удачи! Ты самый лучший Моддер!

Маппинг имен:
============
По умолчанию MDK настроен на использование официальных названий от Mojang для методов и полей
в кодовой базе Minecraft.  
На эти имена распространяется специальная лицензия. Все моддеры должны знать об этой
лицензии. Для получения последнего текста лицензии обратитесь к самому файлу сопоставления или к справочной копии здесь:  
https://github.com/NeoForged/NeoForm/blob/main/Mojang.md

Рекомендуемые Дополнительные Ресурсы:
==========
Документация NeoForged: https://docs.neoforged.net/  
NeoForged Дискорд: https://discord.neoforged.net/
