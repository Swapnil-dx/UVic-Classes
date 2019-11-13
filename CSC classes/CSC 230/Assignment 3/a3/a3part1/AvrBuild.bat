@ECHO OFF
"C:\Program Files (x86)\Atmel\AVR Tools\AvrAssembler2\avrasm2.exe" -S "H:\CSC230\Assignment 3\a3\a3part1\labels.tmp" -fI -W+ie -C V3 -o "H:\CSC230\Assignment 3\a3\a3part1\a3part1.hex" -d "H:\CSC230\Assignment 3\a3\a3part1\a3part1.obj" -e "H:\CSC230\Assignment 3\a3\a3part1\a3part1.eep" -m "H:\CSC230\Assignment 3\a3\a3part1\a3part1.map" "H:\CSC230\Assignment 3\a3\a3part1\a3part1.asm"
