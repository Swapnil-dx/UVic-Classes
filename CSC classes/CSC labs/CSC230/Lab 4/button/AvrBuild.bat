@ECHO OFF
"C:\Program Files (x86)\Atmel\AVR Tools\AvrAssembler2\avrasm2.exe" -S "H:\CSC230\Lab 4\button\labels.tmp" -fI -W+ie -C V3 -o "H:\CSC230\Lab 4\button\button.hex" -d "H:\CSC230\Lab 4\button\button.obj" -e "H:\CSC230\Lab 4\button\button.eep" -m "H:\CSC230\Lab 4\button\button.map" "H:\CSC230\Lab 4\button\button.asm"
