@ECHO OFF
"C:\Program Files (x86)\Atmel\AVR Tools\AvrAssembler2\avrasm2.exe" -S "H:\CSC230\Assignment 1\assign1\labels.tmp" -fI -W+ie -C V3 -o "H:\CSC230\Assignment 1\assign1\assign1.hex" -d "H:\CSC230\Assignment 1\assign1\assign1.obj" -e "H:\CSC230\Assignment 1\assign1\assign1.eep" -m "H:\CSC230\Assignment 1\assign1\assign1.map" "H:\CSC230\Assignment 1\assign1\assign1.asm"
