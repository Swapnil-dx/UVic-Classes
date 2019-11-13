; reverse.asm
; CSC 230: Spring 2018
;
; Code provided for Assignment #1
;
; Mike Zastre (2018-Jan-21)

; This skeleton of an assembly-language program is provided to help you
; begin with the programming task for A#1, part (b). In this and other
; files provided through the semester, you will see lines of code
; indicating "DO NOT TOUCH" sections. You are *not* to modify the
; lines within these sections. The only exceptions are for specific
; changes announced on conneX or in written permission from the course
; instructor. *** Unapproved changes could result in incorrect code
; execution during assignment evaluation, along with an assignment grade
; of zero. ****
;
; In a more positive vein, you are expected to place your code with the
; area marked "STUDENT CODE" sections.

; ==== BEGINNING OF "DO NOT TOUCH" SECTION ====
; Your task: To reverse the bits in the word IN1:IN2 and to store the
; result in OUT1:OUT2. For example, if the word stored in IN1:IN2 is
; 0xA174, then reversing the bits will yield the value 0x2E85 to be
; stored in OUT1:OUT2.

    .cseg
    .org 0

; ==== END OF "DO NOT TOUCH" SECTION ==========

; **** BEGINNING OF "STUDENT CODE" SECTION **** 
    ; These first lines store a word into IN1:IN2. You may
    ; change the value of the word as part of your coding and
    ; testing.
    ;
    ldi R16, 0xA1
    sts IN1, R16
    ldi R16, 0x74
    sts IN2, R16
    
	ldi R20, 7
	
	lds R17, IN1
	lds R18, IN2
	ldi R23, 0x00
	ldi R24, 0x00
    loop1:
		ldi R21, 0b00000000
		add R21, R17
		andi R21, 0b00000001
		add R23, R21
		
		lsr R17
		lsl R23
		
		ldi R22, 0b00000000
		add R22, R18
		andi R22, 0b00000001
		add R24, R22
		
		lsr R18
		lsl R24
		
		dec R20
		brne loop1

	ldi R21, 0b00000000
	add R21, R17
	andi R21, 0b00000001
	add R23, R21
	
	ldi R22, 0b00000000
	add R22, R18
	andi R22, 0b00000001
	add R24, R22	

	sts OUT2, R23
	sts OUT1, R24

; **** END OF "STUDENT CODE" SECTION ********** 



; ==== BEGINNING OF "DO NOT TOUCH" SECTION ====
stop:
.    rjmp stop

    .dseg
    .org 0x200
IN1:	.byte 1
IN2:	.byte 1
OUT1:	.byte 1
OUT2:	.byte 1
; ==== END OF "DO NOT TOUCH" SECTION ==========
