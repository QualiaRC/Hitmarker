#Hitmarker
[![GitHub version](https://badge.fury.io/gh/boennemann%2Fbadges.svg)](http://badge.fury.io/gh/boennemann%2Fbadges)

## About
A simple Java application using JavaFX and [JNativeHook](https://github.com/kwhat/jnativehook "JNativeHook repo") to turn your user input into hitmarkers. Every mouse click or keypress will display the hitmarker image at the location of your cursor, and play the hitmarker sound.

## To Compile
Import as a Maven Project, then run goal: 
`clean compile assembly:single`

The jar will be in the `/target` folder.

## To quit
The quickest way to stop the program from running is to press the escape key.