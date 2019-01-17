package com.ekkongames.slavabot.commands.impl.music;

import com.ekkongames.jdacbl.audio.GuildVoiceController;
import com.ekkongames.jdacbl.bot.GuildState;
import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;

public class MusicSkip extends Command {

    public MusicSkip() {
        super(
                new CommandInfo.Builder()
                        .names("skip")
                        .build()
        );
    }

    @Override
    public void exec(CommandInput input) {
        GuildState guildState = commandGroup.getBot().getGuildState(BotUtils.getGuild().getId());
        GuildVoiceController controller = guildState.getVoiceController();

        // make sure we're in a voice channel before we start playing music
        if (BotUtils.getSelf().getVoiceState().getChannel() == null) {
            // connect to the voice channel, if the user is in the voice channel
            controller.connectToChannel(BotUtils.getEvent().getMember().getVoiceState().getChannel());
        }

        controller.skipTrack();
    }

}
