@echo off
mkdir bin 2>nul
javac -cp "lib/*" -d bin src\tpProgr2Ticketek\*.java
pause
