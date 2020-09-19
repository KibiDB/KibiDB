# KibiDB
KibiDB is open-source data request and storage software written in Java.
The main function of the software is to help beginner or experienced developers to create applications or libraries where they need to manage data and obtain that data quickly and in a flexible way to read.
KibiDB works in the <K, V> format in strings, something similar to the Redis rulebook.

### Clients

| language | link |
|:-:|:-:|
| Java | https://github.com/KibiDB/KibiDB-Driver-Java |
| PHP | NONE |
| C++ | NONE |
| C# | NONE |
| Python | NONE |
| NodeJS | NONE |

### How use

- Have Java 8 or above installed
- Download KibiDB.jar from the Releases area
- Run command java -cp KibiDB.jar com.kibi.Kibi
- If you are in Linux it is necessary to use sessions to keep the software active. Example tmux.

### How to configure
The software generates a file called kibi.properties, in which you can configure the software to your liking

| config | description |
|:-:|:-:|
| port | It is the port where the software will listen/send the information |
| whitelist | If you activate this option "on" only the IPs that are added will be able to send requests to the software |
| authentication | It is advisable to leave it "on" but if you want to turn it off "off", any person can consult the software without the need for a password |
| password | Client authentication password |

### Console Commands

| command | description |
|:-:|:-:|
| help | Get a help list of all commands |
| stop | Shut down KibiDB properly |
| whitelist <add/remove> <ip> | Add or remove an ip from the whitelist |
| insert <key> <value> | Insert a new record or overwrite the existing one |
| get <key> | Get a record or NULL if it doesn't exist |
| set <key> <value> | Change the value of an existing record |
| remove <key> | Delete a record |
| clear | Delete all records |


