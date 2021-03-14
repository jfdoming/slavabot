package com.ekkongames.slavabot.commands.impl.nauts;

import com.ekkongames.jdacbl.commands.Command;
import com.ekkongames.jdacbl.commands.CommandInfo;
import com.ekkongames.jdacbl.commands.CommandInput;
import com.ekkongames.jdacbl.utils.BotUtils;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Julian Dominguez-Schatz <jfdoming at ekkon.dx.am>
 */
public class NautsLeaderboard extends Command {

    public NautsLeaderboard() {
        super(
                new CommandInfo.Builder()
                        .names("leaderboard", "lb")
                        .summary("check the leaderboard position of a player")
                        .build()
        );
    }

    @Override
    public void exec(CommandInput input) {
        if (input.getTokenCount() < 2) {
            BotUtils.sendMessage("You must specify a user to look up");
            return;
        }

        StringBuilder usernameBuilder = new StringBuilder();
        for (int i = 1; i < input.getTokenCount(); i++) {
            if (i > 1) {
                usernameBuilder.append(" ");
            }
            usernameBuilder.append(input.getToken(i));
        }

        final String username = usernameBuilder.toString();

        final MessageChannel sendChannel = BotUtils.getMessageChannel();

        commandGroup.getBot().doInBackground(() -> {
            HttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost("https://orikaru.net/resources/logic/php/ajax/nautsranking.php");

            List<NameValuePair> params = new ArrayList<>(2);
            params.add(new BasicNameValuePair("action", "search"));
            params.add(new BasicNameValuePair("params[settings][sortOrder]", "asc"));
            params.add(new BasicNameValuePair("params[settings][username]", username));
            params.add(new BasicNameValuePair("params[settings][mainNautId]", "32"));
            try {
                post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                HttpResponse response = client.execute(post);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent()))) {
                        // the response should come in as a single line
                        JSONObject obj = new JSONObject(in.readLine());
                        sendChannel.sendMessage(processResponse(obj)).queue();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private String processResponse(JSONObject obj) {
        if (obj.getInt("success") != 1) {
            return "```Leaderboard query failed! Message: " + obj.getString("errorMessage") + "```";
        }

        JSONArray users = obj.getJSONArray("result");

        if (users.length() == 0) {
            return "```No users found for the specified name!```";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("```");

        int count = Math.min(users.length(), 50);
        for (int i = 0; i < count; i++) {
            JSONObject user = users.getJSONObject(i);
            builder.append(user.getInt("rank"));
            builder.append(". ");
            builder.append(user.getString("username"));
            builder.append("\n");
        }
        builder.append("```");
        return builder.toString();
    }

}
