package com.ekkongames.slavabot.commands.impl.role;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;
import net.dv8tion.jda.api.entities.User;

/**
 * Created by Dolphish on 2016-10-29.
 */
public class RoleAdd extends Command {

    public RoleAdd() {
        super(new CommandInfo.Builder()
                .names("add", "a", "+")
                .build());
    }

    @Override
    public void exec(CommandInput input) {
        // make sure the user specified a target
        if (input.getTokenCount() < 2) {
            BotUtils.sendMessage("You must specify a user to add a role to");
            return;
        }

        // make sure the user specified a valid target
        User target = BotUtils.getTargetUser(input, 1);
        if (target == null) {
            BotUtils.sendMessage("You must mention a user or specify a valid user ID");
            return;
        }

        // make sure the user specified a role
        if (input.getTokenCount() < 3) {
            BotUtils.sendMessage("You must specify a role to add");
            return;
        }

        // add the role to the target
        BotUtils.addRoleToUser(target, input.getToken(2));
    }
}
