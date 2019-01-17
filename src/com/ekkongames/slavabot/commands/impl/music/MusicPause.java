package com.ekkongames.slavabot.commands.impl.music;

import com.ekkongames.jdacbl.audio.GuildVoiceController;
import com.ekkongames.jdacbl.bot.GuildState;
import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;

public class MusicPause extends Command {

    public MusicPause() {
        super(
                new CommandInfo.Builder()
                        .names("pause")
                        .build()
        );
    }

    @Override
    public void exec(CommandInput input) {
        GuildState guildState = commandGroup.getBot().getGuildState(BotUtils.getGuild().getId());
        GuildVoiceController controller = guildState.getVoiceController();

        controller.setPaused(true);
    }

}
