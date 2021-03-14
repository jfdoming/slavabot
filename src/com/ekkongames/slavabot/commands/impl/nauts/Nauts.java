package com.ekkongames.slavabot.commands.impl.nauts;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandGroup;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;
import com.ekkongames.slavabot.PermissionConstants;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class Nauts extends Command {

    HashSet<User> onlineMembers;

    public Nauts() {
        super(
                new CommandInfo.Builder()
                    .names("nauts")
                    .summary("perform various Awesomenauts-related actions")
                    .build()
        );

        this.children = new CommandGroup.Builder()
                .add(new NautsTeamgen(this))
                .add(new NautsStatus(this))
                .add(new NautsLeaderboard())
                .build();

        onlineMembers = new HashSet<>();
    }

    @Override
    public void exec(CommandInput input) {
        // make sure the user specified a target
        if (input.getTokenCount() < 2) {
            BotUtils.sendMessage("You must specify an action to perform");
            return;
        }

        input.discardToken(0);
        children.exec(input);
    }
    public List<Member> getOnlinePlayers(CommandInput input) {
        Guild guild = BotUtils.getGuild();
        Role nautsRole = guild.getRoleById(PermissionConstants.AWESOMENAUTS_ROLE_ID);
        Set<Member> mentionedUsers = input.getMentionedUsers().stream().map(guild::getMember).collect(Collectors.toSet());
        Set<String> missingUsers = new HashSet<>();
        for (int i = 0; i < input.getTokenCount(); ++i) {
            List<Member> candidates = guild.getMembersByEffectiveName(input.getToken(i), true);
            mentionedUsers.addAll(candidates);
            if (candidates.isEmpty()) {
                missingUsers.add(input.getToken(i));
            }
        }
        if (!missingUsers.isEmpty()) {
            BotUtils.sendMessage("Failed to locate the following users");
            BotUtils.sendPlainMessage(String.join(", ", missingUsers));
        }
        return guild.getMembersWithRoles(nautsRole)
                .stream()
                .filter(member -> member.getActivities().stream()
                        .anyMatch(activity -> "Awesomenauts".equals(activity.getName()))
                        || mentionedUsers.contains(member)
                        || onlineMembers.contains(member.getUser()))
                .collect(Collectors.toList());
    }

}
