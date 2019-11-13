@ECHO OFF
"C:\Program Files (x86)\Atmel\AVR Tools\AvrAssembler2\avrasm2.exe" -S "H:\CSC230\Assignment 2\a2_morse\labels.tmp" -fI -W+ie -C V3 -o "H:\CSC230\Assignment 2\a2_morse\a2_morse.hex" -d "H:\CSC230\Assignment 2\a2_morse\a2_morse.obj" -e "H:\CSC230\Assignment 2\a2_morse\a2_morse.eep" -m "H:\CSC230\Assignment 2\a2_morse\a2_morse.map" "H:\CSC230\Assignment 2\a2_morse\a2_morse.asm"
