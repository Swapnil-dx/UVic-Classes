@ECHO OFF
"C:\Program Files (x86)\Atmel\AVR Tools\AvrAssembler2\avrasm2.exe" -S "H:\CSC230\Lab 7\timers\labels.tmp" -fI -W+ie -C V3 -o "H:\CSC230\Lab 7\timers\timers.hex" -d "H:\CSC230\Lab 7\timers\timers.obj" -e "H:\CSC230\Lab 7\timers\timers.eep" -m "H:\CSC230\Lab 7\timers\timers.map" "H:\CSC230\Lab 7\timers\timers.asm"
