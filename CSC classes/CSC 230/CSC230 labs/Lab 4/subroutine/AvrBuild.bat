@ECHO OFF
"C:\Program Files (x86)\Atmel\AVR Tools\AvrAssembler2\avrasm2.exe" -S "H:\CSC230\subroutine\labels.tmp" -fI -W+ie -C V3 -o "H:\CSC230\subroutine\subroutine.hex" -d "H:\CSC230\subroutine\subroutine.obj" -e "H:\CSC230\subroutine\subroutine.eep" -m "H:\CSC230\subroutine\subroutine.map" "H:\CSC230\subroutine\subroutine.asm"
