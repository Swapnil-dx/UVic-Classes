@ECHO OFF
"C:\Program Files (x86)\Atmel\AVR Tools\AvrAssembler2\avrasm2.exe" -S "H:\CSC230\Lab 3\led\labels.tmp" -fI -W+ie -C V3 -o "H:\CSC230\Lab 3\led\led.hex" -d "H:\CSC230\Lab 3\led\led.obj" -e "H:\CSC230\Lab 3\led\led.eep" -m "H:\CSC230\Lab 3\led\led.map" "H:\CSC230\Lab 3\led\led.asm"
