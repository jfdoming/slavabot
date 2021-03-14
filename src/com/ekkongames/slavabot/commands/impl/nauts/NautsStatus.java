package com.ekkongames.slavabot.commands.impl.nauts;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandGroup;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;
import net.dv8tion.jda.api.entities.Member;

import java.util.List;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class NautsStatus extends Command {

    private final Nauts parent;

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
        input.discardToken(0);
        CommandInput inputCopy = new CommandInput(input);
        if (!children.exec(inputCopy, true)) {
            // show everyone's status

            StringBuilder builder = new StringBuilder();
            builder.append("```");
            builder.append("Online Users:\n");

            List<Member> onlineMembers = parent.getOnlinePlayers(input);
            for (Member member : onlineMembers) {
                builder.append(member.getEffectiveName());
                builder.append("\n");
            }
            builder.append("```");
            BotUtils.sendPlainMessage(builder.toString());
        }
    }

}
