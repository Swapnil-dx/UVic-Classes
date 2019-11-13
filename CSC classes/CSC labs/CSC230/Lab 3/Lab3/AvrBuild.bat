@ECHO OFF
"C:\Program Files (x86)\Atmel\AVR Tools\AvrAssembler2\avrasm2.exe" -S "H:\CSC230\Lab 3\Lab3\labels.tmp" -fI -W+ie -C V3 -o "H:\CSC230\Lab 3\Lab3\lab3.hex" -d "H:\CSC230\Lab 3\Lab3\lab3.obj" -e "H:\CSC230\Lab 3\Lab3\lab3.eep" -m "H:\CSC230\Lab 3\Lab3\lab3.map" "H:\CSC230\Lab 3\Lab3\lab3.asm"
