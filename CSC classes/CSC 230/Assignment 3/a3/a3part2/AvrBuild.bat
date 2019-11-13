@ECHO OFF
"C:\Program Files (x86)\Atmel\AVR Tools\AvrAssembler2\avrasm2.exe" -S "H:\CSC230\Assignment 3\a3\a3part2\labels.tmp" -fI -W+ie -C V3 -o "H:\CSC230\Assignment 3\a3\a3part2\a3part2.hex" -d "H:\CSC230\Assignment 3\a3\a3part2\a3part2.obj" -e "H:\CSC230\Assignment 3\a3\a3part2\a3part2.eep" -m "H:\CSC230\Assignment 3\a3\a3part2\a3part2.map" "H:\CSC230\Assignment 3\a3\a3part2\a3part2.asm"
