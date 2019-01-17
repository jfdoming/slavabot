package com.ekkongames.slavabot.commands.impl.role;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;

import java.awt.Color;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class RoleChangeColour extends Command {

    public RoleChangeColour() {
        super(new CommandInfo.Builder()
                .names("colour")
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
        if (input.getTokenCount() < 5) {
            BotUtils.sendMessage("You must specify 3 integer values for a colour");
            return;
        }

        int r, g, b;
        try {
            r = Integer.parseInt(input.getToken(2));
            g = Integer.parseInt(input.getToken(3));
            b = Integer.parseInt(input.getToken(4));
        } catch (NumberFormatException e) {
            BotUtils.sendMessage("You must specify 3 integer values for a colour");
            return;
        }

        // modify the role
        BotUtils.setRoleColour(input.getToken(1), new Color(r, g, b));
    }

}
