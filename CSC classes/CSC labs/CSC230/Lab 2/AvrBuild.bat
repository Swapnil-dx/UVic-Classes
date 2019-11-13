@ECHO OFF
"C:\Program Files (x86)\Atmel\AVR Tools\AvrAssembler2\avrasm2.exe" -S "H:\CSC230\Lab 2\labels.tmp" -fI -W+ie -C V3 -o "H:\CSC230\Lab 2\lab2.hex" -d "H:\CSC230\Lab 2\lab2.obj" -e "H:\CSC230\Lab 2\lab2.eep" -m "H:\CSC230\Lab 2\lab2.map" -l "H:\CSC230\Lab 2\lab2.lst" "H:\CSC230\Lab 2\lab2.asm"
