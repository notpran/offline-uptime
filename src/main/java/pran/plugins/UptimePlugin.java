package pran.plugins;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UptimePlugin extends JavaPlugin {
    private long startTime;
    private int startTimeAfterSeconds;

    @Override
    public void onEnable() {
        loadConfig();
        if (getConfig().contains("startTime")) {
            startTime = getConfig().getLong("startTime");
        } else {
            startTime = System.currentTimeMillis() + (startTimeAfterSeconds * 1000); // Add delay if specified
            getConfig().set("startTime", startTime);
            saveConfig();
        }

        if (previousLogFileExists()) {
            long previousUptime = readPreviousUptime();
            sendUptimeMessage(previousUptime);
        }
        System.out.println("Starting Uptime record");
    }

    @Override
    public void onDisable() {
        System.out.println("Stoping Uptime record");
        System.out.println("Writing to Log file");

        long uptime = System.currentTimeMillis() - startTime;
        writeUptimeToLogFile(uptime);
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();

        // Load the configuration values
        startTimeAfterSeconds = getConfig().getInt("start-time-after", 0);
    }

    private boolean previousLogFileExists() {
        File folder = getDataFolder();
        File logFile = new File(folder, "uptime.log");
        return logFile.exists();
    }

    private long readPreviousUptime() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(getDataFolder(), "uptime.log")));
            String uptimeString = reader.readLine();
            reader.close();

            return Long.parseLong(uptimeString);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void writeUptimeToLogFile(long uptime) {
        try {
            File folder = getDataFolder();
            if (!folder.exists()) {
                folder.mkdir();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(folder, "uptime.log")));
            writer.write(formatUptime(uptime));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendUptimeMessage(long uptime) {
        getLogger().info("Server uptime of " + formatDate() + ":\n"
                + "             " + (uptime / 3600000) + " hours\n"
                + "             " + ((uptime % 3600000) / 60000) + " minutes");
    }

    private String formatDate() {
        return new java.text.SimpleDateFormat("MM/dd/yy").format(new java.util.Date());
    }

    private String formatUptime(long uptime) {
        return formatDate() + ":\n" +
                "             " + (uptime / 3600000) + " hours\n" +
                "             " + ((uptime % 3600000) / 60000) + " minutes";
    }
}
