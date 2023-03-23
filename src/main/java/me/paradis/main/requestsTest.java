package me.paradis.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class requestsTest implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // get first arg in command
        String arg = strings[0];

        commandSender.sendMessage(get("http://50.116.47.159/" + arg));


        return false;
    }

    public static String get(String url) {
        String stuff = null;
        try {
            URL url1 = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(url1.openStream()));
            String str = in.readLine();
            in.close();
            if (str != null) {
                stuff = str;
            }
        }
        catch (java.io.IOException e1) {
            stuff = e1.getMessage();
        }
        return stuff;
    }
}
