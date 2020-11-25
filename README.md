# discord-bot


[![Build Status](https://travis-ci.com/Andreichenko/discord-bot.svg?branch=master)](https://travis-ci.com/Andreichenko/discord-bot) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) <a alt="Java">
        <img src="https://img.shields.io/badge/Java-v1.8-orange.svg" />
    </a>    <a alt="Docker">
        <img src="https://img.shields.io/badge/Docker-v19-yellowgreen.svg" />
    </a>  <a alt="Bootstrap">
        <img src="https://img.shields.io/badge/Bootstrap-v4.0.0-yellowgreen.svg">
    </a>
    [![BCH compliance](https://bettercodehub.com/edge/badge/Andreichenko/discord-bot?branch=master)](https://bettercodehub.com/)



version 0.1

This bot is deployed in a separate container and connected to your platform ID. This allows you to broadcast an audio stream, and it is also possible to store commands in a separate database based on mongoDB. Spring Session provides an API and implementations for managing a user’s session information.

Spring Session makes it trivial to support clustered sessions without being tied to an application container specific solution. It also provides transparent integration with:

HttpSession - allows replacing the HttpSession in an application container (i.e. Tomcat) neutral way, with support for providing session IDs in headers to work with RESTful APIs

WebSocket - provides the ability to keep the HttpSession alive when receiving WebSocket messages

WebSession - allows replacing the Spring WebFlux’s WebSession in an application container neutral way

The main variables

DISCORD_BOT_KEY	== The discord API key
