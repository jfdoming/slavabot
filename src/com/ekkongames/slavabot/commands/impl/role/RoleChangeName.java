package com.ekkongames.slavabot.commands.impl.role;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class RoleChangeName extends Command {

    public RoleChangeName() {
        super(new CommandInfo.Builder()
                .names("name")
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
            BotUtils.sendMessage("You must specify a new name");
            return;
        }

        // modify the role
        BotUtils.setRoleName(input.getToken(1), input.getToken(2));
    }

}
