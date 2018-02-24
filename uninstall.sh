#!/bin/zsh
for version in "" ".debug";
	do adb uninstall "com.martinchamarro.muvis$version";
done;
