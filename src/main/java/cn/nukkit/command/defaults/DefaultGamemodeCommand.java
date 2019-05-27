package cn.nukkit.command.defaults;

import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.lang.TranslationContainer;

/**
 * Created on 2015/11/12 by xtypr.
 * Package cn.nukkit.command.defaults in project Nukkit .
 */
public class DefaultGamemodeCommand extends VanillaCommand {

    public DefaultGamemodeCommand(String name) {
        super(name, "%nukkit.command.defaultgamemode.description", "%commands.defaultgamemode.usage");
        this.setPermission("nukkit.command.defaultgamemode");
        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[]{
                new CommandParameter("mode", CommandParameter.ARG_TYPE_INT, false)
        });
        this.commandParameters.put("byString", new CommandParameter[]{
                new CommandParameter("mode", new String[]{"survival", "creative", "s", "c",
                        "adventure", "a", "spectator", "view", "v"})
        });
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!this.testPermission(sender)) {
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(new TranslationContainer("commands.generic.usage", new String[]{this.usageMessage}));
            return false;
        }
        int gameMode = Server.getGamemodeFromString(args[0]);
        if (gameMode != -1) {
            sender.getServer().setPropertyInt("gamemode", gameMode);
            sender.sendMessage(new TranslationContainer("commands.defaultgamemode.success", new String[]{Server.getGamemodeString(gameMode)}));
        } else {
            sender.sendMessage("Unknown game mode"); //
        }
        return true;
    }
}
