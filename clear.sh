#!/bin/zsh
for version in "" ".debug";
	do adb shell pm clear "com.martinchamarro.muvis$version";
done;
