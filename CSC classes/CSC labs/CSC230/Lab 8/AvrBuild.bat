@ECHO OFF
"C:\Program Files (x86)\Atmel\AVR Tools\AvrAssembler2\avrasm2.exe" -S "H:\CSC230\Lab 8\labels.tmp" -fI -W+ie -C V3 -o "H:\CSC230\Lab 8\hello_blink.hex" -d "H:\CSC230\Lab 8\hello_blink.obj" -e "H:\CSC230\Lab 8\hello_blink.eep" -m "H:\CSC230\Lab 8\hello_blink.map" "H:\CSC230\Lab 8\hello_blink.asm"
