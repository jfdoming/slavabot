package com.ekkongames.slavabot.commands.impl.role;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class RoleChangeMentionable extends Command {

    public RoleChangeMentionable() {
        super(new CommandInfo.Builder()
                .names("mentionable")
                .build());
    }

    @Override
    public void exec(CommandInput input) {
        // make sure the user specified a target role
        if (input.getTokenCount() < 2) {
            BotUtils.sendMessage("You must specify a role to modify");
            return;
        }

        // make sure the user specified a boolean value
        if (input.getTokenCount() < 3) {
            BotUtils.sendMessage("You must specify a boolean value");
            return;
        }

        // modify the role
        BotUtils.setRoleMentionable(input.getToken(1), Boolean.parseBoolean(input.getToken(2)));
    }

}
