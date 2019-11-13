@ECHO OFF
"C:\Program Files (x86)\Atmel\AVR Tools\AvrAssembler2\avrasm2.exe" -S "H:\CSC230\Lab 3\clearled\labels.tmp" -fI -W+ie -C V3 -o "H:\CSC230\Lab 3\clearled\clearled.hex" -d "H:\CSC230\Lab 3\clearled\clearled.obj" -e "H:\CSC230\Lab 3\clearled\clearled.eep" -m "H:\CSC230\Lab 3\clearled\clearled.map" "H:\CSC230\Lab 3\clearled\clearled.asm"
