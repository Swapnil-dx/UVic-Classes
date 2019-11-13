@ECHO OFF
"C:\Program Files (x86)\Atmel\AVR Tools\AvrAssembler2\avrasm2.exe" -S "H:\CSC230\Assignment 3\a3\a3part3\labels.tmp" -fI -W+ie -C V2E -o "H:\CSC230\Assignment 3\a3\a3part3\a3part3.hex" -d "H:\CSC230\Assignment 3\a3\a3part3\a3part3.obj" -e "H:\CSC230\Assignment 3\a3\a3part3\a3part3.eep" -m "H:\CSC230\Assignment 3\a3\a3part3\a3part3.map" "H:\CSC230\Assignment 3\a3\a3part3\a3part3.asm"
