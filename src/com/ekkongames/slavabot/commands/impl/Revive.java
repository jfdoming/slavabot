package com.ekkongames.slavabot.commands.impl;

import com.ekkongames.jdacbl.utils.BotUtils;
import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.PrimitiveUtils;
import com.ekkongames.slavabot.PermissionConstants;
import net.dv8tion.jda.core.entities.User;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class Revive extends Command {

    public static final String REVIVE_VC = "meme speak";

    public Revive() {
        super(new CommandInfo.Builder()
                .names("revive", "unbanish", "unban", "ri")
                .summary("revive a user")
                .description("This command will attempt to switch the voice channel of the "
                        + "target user, which **will** fail if the user is not "
                        + "currently in a voice channel. In this case, the user will "
                        + "still have the role \"Banished,\" removed from their roles."
                )
                .usage("@user")
                .auth(PermissionConstants.OWNER_ROLE)
                .build());
    }

    @Override
    public void exec(CommandInput input) {
        // make sure the user specified a target
        if (input.getTokenCount() < 2) {
            BotUtils.sendMessage("You must specify a user to revive");
            return;
        }

        // make sure the user specified a valid target
        User target = PrimitiveUtils.get(input.getMentionedUsers());
        if (target == null) {
            BotUtils.sendMessage("No user was mentioned, or the mentioned user doesn't exist");
            return;
        }

        // revive the target!
        BotUtils.removeRoleFromUser(target, Banish.BANISH_ROLE);
        BotUtils.moveUserToVoiceChannel(target, REVIVE_VC);
    }

}
