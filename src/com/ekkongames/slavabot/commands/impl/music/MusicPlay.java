package com.ekkongames.slavabot.commands.impl.music;

import com.ekkongames.jdacbl.audio.GuildVoiceController;
import com.ekkongames.jdacbl.bot.GuildState;
import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;
import net.dv8tion.jda.api.entities.VoiceChannel;

import java.util.Objects;

public class MusicPlay extends Command {

    public MusicPlay() {
        super(
                new CommandInfo.Builder()
                        .names("play")
                        .build()
        );
    }

    @Override
    public void exec(CommandInput input) {
        // make sure the user specified a target
        if (input.getTokenCount() < 2) {
            BotUtils.sendMessage("You must specify an audio source");
            return;
        }

        GuildState guildState = commandGroup.getBot().getGuildState(BotUtils.getGuild().getId());
        GuildVoiceController controller = guildState.getVoiceController();

        // make sure we're in a voice channel before we start playing music
        if (BotUtils.getSelf().getVoiceState().getChannel() == null) {
            // connect to the voice channel, if the author is in the voice channel
            VoiceChannel memberChannel = Objects.requireNonNull(
                    Objects.requireNonNull(
                            BotUtils.getGuild().getMember(BotUtils.getAuthor())
                    ).getVoiceState()
            ).getChannel();
            if (memberChannel == null) {
                BotUtils.sendMessage("You can't play music if you aren't in a voice channel");
                return;
            }
            controller.connectToChannel(memberChannel);
        }
        controller.playTrack(input.getToken(1));
    }

}
