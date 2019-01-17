package com.ekkongames.slavabot;

import com.ekkongames.jdacbl.bot.BotInfo;
import com.ekkongames.jdacbl.bot.jar.EntryPoint;
import com.ekkongames.jdacbl.commands.CommandGroup;
import com.ekkongames.slavabot.commands.impl.Banish;
import com.ekkongames.slavabot.commands.impl.Nickname;
import com.ekkongames.slavabot.commands.impl.Revive;
import com.ekkongames.slavabot.commands.impl.role.Role;
import com.ekkongames.slavabot.commands.impl.Say;
import com.ekkongames.slavabot.commands.impl.Toggle;
import com.ekkongames.slavabot.commands.impl.Togle;
import com.ekkongames.slavabot.commands.impl.Warn;
import com.ekkongames.slavabot.commands.impl.Warns;
import com.ekkongames.slavabot.commands.impl.music.Music;
import com.ekkongames.slavabot.commands.impl.music.MusicPlay;
import com.ekkongames.slavabot.commands.impl.sch.Schedule;
import com.ekkongames.slavabot.nauts.Nauts;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class SlavaBot implements EntryPoint {

    // the string used to trigger command parsing
    public static final String COMMAND_PREFIX = "$";

    // the game our bot is "playing"
    public static final String GAME = "$help for commands";

    @Override
    public BotInfo run() {
        CommandGroup commands = new CommandGroup.Builder()
                .add(new Toggle())
                .add(new Warn())
                .add(new Warns())
                .add(new Banish())
                .add(new Revive())
                .add(new Say())
                .add(new Togle())
                .add(new Role())
                .add(new Nickname())
                .add(new Schedule())
                .add(new Nauts())
                .add(new Music())
                .add(new MusicPlay()) // I'm deliberately adding this here as well, for convenience.
                .build();
        return new BotInfo.Builder()
                .setCommandPrefix(COMMAND_PREFIX)
                .setGame(GAME)
                .setCommandGroup(commands)
                .build(APIConstants.API_TOKEN);
    }

}