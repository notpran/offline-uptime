# Minecraft Offline Uptime Plugin

## ⚠ CAUTION ⚠

this plugin was built for 1.20.4 ONLY! an updated version will be made for 1.21 and lower versions soon 

## Overview

The Minecraft Offline Uptime Plugin is a Plugin for tracking and displaying server uptime in a readable format. With this plugin, you can easily monitor how long your Minecraft server has been running, even across different dates.

## Features

- Records server uptime in hours, minutes, and seconds.
- Displays server uptime for different dates.
- Writes server uptime information to a log file in a user-friendly format.

## Usage

The plugin automatically records server uptime in the `uptime.log` file within the plugin folder. The log file is formatted to display server uptime for each date, with the latest date appearing at the top.

To view the server uptime, open the `uptime.log` file using a text editor.

## Example

Server uptime for 23/12/2023 (14:30:45):<br>

02 hours<br>
15 minutes<br>
23 seconds<br>

Server uptime for 22/12/2023 (20:10:12):<br>
01 hours<br>
45 minutes<br>
37 seconds<br>


## Config

Here's the entire config:


config-version: 1<br>
start-time-after: 0<br>
startTime: 1703419893830<br>

'config-version': This is the version of the config. please DO NOT change this

'start-time-after': Its pretty much how many second should the plugin wait before starting the uptime counter.

'StartTime': This is when the plugin was first ran on your server in millliseconds!

## License

This plugin is licensed under the [GNU General Public Licence](LICENSE).
