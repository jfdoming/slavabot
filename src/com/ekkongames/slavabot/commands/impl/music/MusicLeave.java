package com.ekkongames.slavabot.commands.impl.music;

import com.ekkongames.jdacbl.audio.GuildVoiceController;
import com.ekkongames.jdacbl.bot.GuildState;
import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;

public class MusicLeave extends Command {

    public MusicLeave() {
        super(
                new CommandInfo.Builder()
                        .names("leave")
                        .build()
        );
    }

    @Override
    public void exec(CommandInput input) {
        GuildState guildState = commandGroup.getBot().getGuildState(BotUtils.getGuild().getId());
        GuildVoiceController controller = guildState.getVoiceController();
        controller.disconnectFromChannel();
    }

}
