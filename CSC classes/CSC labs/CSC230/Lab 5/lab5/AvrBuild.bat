@ECHO OFF
"C:\Program Files (x86)\Atmel\AVR Tools\AvrAssembler2\avrasm2.exe" -S "H:\CSC230\Lab 5\lab5\labels.tmp" -fI -W+ie -C V3 -o "H:\CSC230\Lab 5\lab5\lab5.hex" -d "H:\CSC230\Lab 5\lab5\lab5.obj" -e "H:\CSC230\Lab 5\lab5\lab5.eep" -m "H:\CSC230\Lab 5\lab5\lab5.map" "H:\CSC230\Lab 5\lab5\lab5.asm"
