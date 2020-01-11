# Content Engine
##  Purpose
The Content Engine is used as a world building engine for The Twisting Haunt. It provides a UI to add and update components within the game, reducing the chances for bugs based on human error.
## Dependencies
The Content Engine uses the shared library EntityLibrary, which is a library that contains the custom Java objects used within TheTwistingHaunt.

The Content Engine also requires an internet connection, as it connects to the development Mongo database that is used in conjunction with TheTwistingHaunt.
## To Use
Currently (1/11/2020) there is not an active build pipeline for Content Engine. Due to this, the best option is to open Content Engine within a Java IDE.
Future iterations will likely include a runnable `.jar`, accessible via console at `java -jar ./contentengine.jar`
## Current Capabilities
- Can open existing game cells
- Can modify data within cell
## Upcoming Features
- Can save modified information within cell
- Can create new cells
- Can delete cells
## Future Iterations
- Increased state control
- Improve database reliability
- Released runnable `.jar` or web client.
- General bugfixes.