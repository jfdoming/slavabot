package com.ekkongames.slavabot.nauts;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;
import com.ekkongames.slavabot.PermissionConstants;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class NautsTeamgen extends Command {

    private Nauts parent;

    public NautsTeamgen(Nauts parent) {
        super(
                new CommandInfo.Builder()
                        .names("teamgen")
                        .summary("randomly generate Awesomenauts teams")
                        .description("These teams are based on the online users with the Awesomenauts role, " +
                                "and additional members can be included by mentioning them as parameters.")
                        .usage("[@user1 @user2 ...]")
                        .build()
        );

        this.parent = parent;
    }

    @Override
    public void exec(CommandInput input) {
        Guild guild = BotUtils.getGuild();
        Role nautsRole = guild.getRoleById(PermissionConstants.AWESOMENAUTS_ROLE_ID);
        final List<Member> awesomenautsPlayers = guild.getMembersWithRoles(nautsRole);

        List<Member> playersToRemove = awesomenautsPlayers
                .stream()
                .filter(member -> member.getGame() == null || !member.getGame().getName().equals("Awesomenauts"))
                .filter(member -> !input.getMentionedUsers().contains(member.getUser()))
                .filter(member -> !parent.onlineMembers.contains(member.getUser()))
                .collect(Collectors.toList());
        awesomenautsPlayers.removeAll(playersToRemove);

        int playerCount = awesomenautsPlayers.size();

        if (awesomenautsPlayers.size() < 4) {
            BotUtils.sendMessage("There are only enough members online to create a single team");
            return;
        }

        Collections.shuffle(awesomenautsPlayers);

        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Team 1 is");

        for (int i = 0; i < playerCount / 2; i++) {
            if (i == (playerCount / 2) - 1) {
                messageBuilder.append(" and");
            } else if (i > 0) {
                messageBuilder.append(",");
            }
            messageBuilder.append(" **");
            messageBuilder.append(awesomenautsPlayers.get(i).getEffectiveName());
            messageBuilder.append("**");
        }

        messageBuilder.append(", and team 2 is");

        for (int i = playerCount / 2; i < playerCount; i++) {
            if (i == awesomenautsPlayers.size() - 1) {
                messageBuilder.append(" and");
            } else if (i > playerCount / 2) {
                messageBuilder.append(",");
            }
            messageBuilder.append(" **");
            messageBuilder.append(awesomenautsPlayers.get(i).getEffectiveName());
            messageBuilder.append("**");
        }

        messageBuilder.append(".");
        BotUtils.sendPlainMessage(messageBuilder.toString());
    }

}
