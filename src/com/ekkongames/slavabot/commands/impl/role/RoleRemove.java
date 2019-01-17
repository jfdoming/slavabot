package com.ekkongames.slavabot.commands.impl.role;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;
import com.ekkongames.jdacbl.utils.PrimitiveUtils;
import net.dv8tion.jda.core.entities.User;

/**
 * Created by Dolphish on 2016-10-29.
 */
public class RoleRemove extends Command {

    public RoleRemove() {
        super(new CommandInfo.Builder()
                .names("remove", "rm", "-")
                .build());
    }

    @Override
    public void exec(CommandInput input) {
        // make sure the user specified a target
        if (input.getTokenCount() < 2) {
            BotUtils.sendMessage("You must specify a user to remove a role from");
            return;
        }

        // make sure the user specified a valid target
        User target = PrimitiveUtils.get(input.getMentionedUsers());
        if (target == null) {
            target = BotUtils.getGuild().getMemberById(input.getToken(1)).getUser();
        }
        System.out.println(target);

        // make sure the user specified a role
        if (input.getTokenCount() < 3) {
            BotUtils.sendMessage("You must specify a role to remove");
            return;
        }

        // remove the role from the target
        BotUtils.removeRoleFromUser(target, input.getToken(2));
    }
}
