package com.ekkongames.slavabot.nauts;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandGroup;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;
import net.dv8tion.jda.core.entities.User;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class NautsStatus extends Command {

    private Nauts parent;

    public NautsStatus(Nauts parent) {
        super(
                new CommandInfo.Builder()
                        .names("status")
                        .summary("marks the author with the specified status")
                        .build()
        );

        this.children = new CommandGroup.Builder()
                .add(new NautsStatusLTP(parent))
                .add(new NautsStatusBusy(parent))
                .build();

        this.parent = parent;
    }

    @Override
    public void exec(CommandInput input) {
        // make sure the user specified a target
        if (input.getTokenCount() < 2) {
            // show everyone's status

            StringBuilder builder = new StringBuilder();
            builder.append("```");
            builder.append("Online Users:\n");

            for (User user : parent.onlineMembers) {
                builder.append(user.getName());
                builder.append("\n");
            }
            builder.append("```");
            BotUtils.sendPlainMessage(builder.toString());
        } else {
            input.discardToken(0);
            children.exec(input);
        }
    }

}
