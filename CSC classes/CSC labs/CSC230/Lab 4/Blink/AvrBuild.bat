@ECHO OFF
"C:\Program Files (x86)\Atmel\AVR Tools\AvrAssembler2\avrasm2.exe" -S "H:\CSC230\Lab 4\Blink\labels.tmp" -fI -W+ie -C V3 -o "H:\CSC230\Lab 4\Blink\blink.hex" -d "H:\CSC230\Lab 4\Blink\blink.obj" -e "H:\CSC230\Lab 4\Blink\blink.eep" -m "H:\CSC230\Lab 4\Blink\blink.map" "H:\CSC230\Lab 4\Blink\blink.asm"
