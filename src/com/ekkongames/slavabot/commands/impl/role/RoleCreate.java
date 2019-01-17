package com.ekkongames.slavabot.commands.impl.role;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;

/**
 * Created by Dolphish on 2016-10-29.
 */
public class RoleCreate extends Command {

    public RoleCreate() {
        super(new CommandInfo.Builder()
                .names("create", "c")
                .build());
    }

    @Override
    public void exec(CommandInput input) {
        // make sure the user specified a target
        if (input.getTokenCount() < 2) {
            BotUtils.sendMessage("You must specify a role name");
            return;
        }

        // add the role to the target
        BotUtils.makeRole(input.getToken(1));
    }
}
