package com.ekkongames.slavabot.commands.impl;

import com.ekkongames.jdacbl.bot.BotInfo;
import com.ekkongames.jdacbl.utils.BotUtils;
import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.slavabot.PermissionConstants;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class Toggle extends Command {

    public Toggle() {
        super(new CommandInfo.Builder()
                .names("toggle", "t")
                .summary("toggle a property")
                .description("Toggleable properties include:\n"
                        + "- swears: whether or not to correct poor language\n"
                        + "- smartFiltering: whether to use smart filtering for swears "
                        + "(only works if the swears property is also active)"
                )
                .usage("swears|smartFiltering")
                .auth(PermissionConstants.OWNER_ROLE)
                .build());
    }

    @Override
    public void exec(CommandInput input) {
        BotInfo info = commandGroup.getBot().getInfo();

        if (input.getTokenCount() < 2) {
            BotUtils.sendMessage("Listing toggles");

            String toggles = "```";
//            toggles += ("- Swears: " + (info.isAllowSwears() ? "" : "dis") + "allowed\n");
//            toggles += ("- Smart Filtering: " + (info.isSmartFiltering() ? "en" : "dis") + "abled");

            BotUtils.sendPlainMessage(toggles + "```");
            return;
        }

        switch (input.getToken(1)) {
//            case "swears":
//                info.setAllowSwears(!info.isAllowSwears());
//                BotUtils.sendMessage("Swears have been " + (info.isAllowSwears() ? "" : "dis") + "allowed");
//                break;
//            case "smartFiltering":
//                info.setSmartFiltering(!info.isSmartFiltering());
//                BotUtils.sendMessage("Smart filtering has been " + (info.isSmartFiltering() ? "en" : "dis") + "abled");
//                break;
            default:
                BotUtils.sendMessage("Unknown property");
        }
    }

}
