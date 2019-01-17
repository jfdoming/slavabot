package com.ekkongames.slavabot.commands.impl.role;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandGroup;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class Role extends Command {

    public Role() {
        super(
                new CommandInfo.Builder()
                    .names("role")
                    .visible(false)
                    .build(),
                new CommandGroup.Builder()
                    .add(new RoleAdd())
                    .add(new RoleRemove())
                    .add(new RoleChange())
                    .add(new RoleCreate())
                    .build()
        );
    }

    @Override
    public void exec(CommandInput input) {
        if (!BotUtils.isUsingStaticGuild()) {
            BotUtils.sendMessage("Couldn't understand that");
            return;
        }

        // make sure the user specified a target
        if (input.getTokenCount() < 2) {
            BotUtils.sendMessage("You must specify an action to perform");
            return;
        }

        input.discardToken(0);
        children.exec(input);
    }

}
