package com.ekkongames.slavabot.commands.impl.music;

import com.ekkongames.jdacbl.audio.GuildVoiceController;
import com.ekkongames.jdacbl.bot.GuildState;
import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class MusicQueue extends Command {

    public MusicQueue() {
        super(
                new CommandInfo.Builder()
                        .names("queue", "q")
                        .build()
        );
    }

    @Override
    public void exec(CommandInput input) {
        GuildState guildState = commandGroup.getBot().getGuildState(BotUtils.getGuild().getId());
        GuildVoiceController controller = guildState.getVoiceController();

        //controller.something
    }

}
