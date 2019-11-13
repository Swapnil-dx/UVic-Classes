; a2_morse.asm
; CSC 230: Spring 2018
;
; Student name:
; Student ID:
; Date of completed work:
;
; *******************************
; Code provided for Assignment #2
;
; Author: Mike Zastre (2018-Feb-10)
; 
; This skeleton of an assembly-language program is provided to help you
; begin with the programming tasks for A#2. As with A#1, there are 
; "DO NOT TOUCH" sections. You are *not* to modify the lines
; within these sections. The only exceptions are for specific
; changes announced on conneX or in written permission from the course
; instructor. *** Unapproved changes could result in incorrect code
; execution during assignment evaluation, along with an assignment grade
; of zero. ****
;
; I have added for this assignment an additional kind of section
; called "TOUCH CAREFULLY". The intention here is that one or two
; constants can be changed in such a section -- this will be needed
; as you try to test your code on different messages.
;


; =============================================
; ==== BEGINNING OF "DO NOT TOUCH" SECTION ====
; =============================================

.include "m2560def.inc"

.cseg
.equ S_DDRB=0x24
.equ S_PORTB=0x25
.equ S_DDRL=0x10A
.equ S_PORTL=0x10B

	
.org 0
	; Copy test encoding (of SOS) into SRAM
	;
	ldi ZH, high(TESTBUFFER)
	ldi ZL, low(TESTBUFFER)
	ldi r16, 0x30
	st Z+, r16
	ldi r16, 0x37
	st Z+, r16
	ldi r16, 0x30
	st Z+, r16
	clr r16
	st Z, r16

	; initialize run-time stack
	ldi r17, high(0x21ff)
	ldi r16, low(0x21ff)
	out SPH, r17
	out SPL, r16

	; initialize LED ports to output
	ldi r17, 0xff
	sts S_DDRB, r17
	sts S_DDRL, r17

; =======================================
; ==== END OF "DO NOT TOUCH" SECTION ====
; =======================================

; ***************************************************
; **** BEGINNING OF FIRST "STUDENT CODE" SECTION **** 
; ***************************************************

	; If you're not yet ready to execute the
	; encoding and flashing, then leave the
	; rjmp in below. Otherwise delete it or
	; comment it out.

	;rjmp stop

    ; The following seven lines are only for testing of your
    ; code in part B. When you are confident that your part B
    ; is working, you can then delete these seven lines. 
	ldi r17, high(TESTBUFFER)
	ldi r16, low(TESTBUFFER)
	push r17
	push r16
	rcall flash_message
    pop r16
    pop r17
   
; ***************************************************
; **** END OF FIRST "STUDENT CODE" SECTION ********** 
; ***************************************************


; ################################################
; #### BEGINNING OF "TOUCH CAREFULLY" SECTION ####
; ################################################

; The only things you can change in this section is
; the message (i.e., MESSAGE01 or MESSAGE02 or MESSAGE03,
; etc., up to MESSAGE09).
;

	; encode a message
	;
	ldi r17, high(MESSAGE02 << 1)
	ldi r16, low(MESSAGE02 << 1)
	push r17
	push r16
	ldi r17, high(BUFFER01)
	ldi r16, low(BUFFER01)
	push r17
	push r16
	;rcall encode_message
	pop r16
	pop r16
	pop r16
	pop r16

; ##########################################
; #### END OF "TOUCH CAREFULLY" SECTION ####
; ##########################################


; =============================================
; ==== BEGINNING OF "DO NOT TOUCH" SECTION ====
; =============================================
	; display the message three times
	;
	ldi r18, 3
main_loop:
	ldi r17, high(BUFFER01)
	ldi r16, low(BUFFER01)
	push r17
	push r16
	rcall flash_message
	dec r18
	tst r18
	brne main_loop


stop:
	rjmp stop
; =======================================
; ==== END OF "DO NOT TOUCH" SECTION ====
; =======================================


; ****************************************************
; **** BEGINNING OF SECOND "STUDENT CODE" SECTION **** 
; ****************************************************


flash_message:
	push YH
	push YL

	in YL, SPL
	in YH, SPH

	sbiw YH:YL, 3
	
	out SPL, YL
	out SPL, YH	

	std Y+1, r16
	std Y+2, ZH
	std Y+3, ZL

	ldd ZL, Y+9
	ldd ZH, Y+10
	
	mem_loop:
		ld r16, Z+
		tst r16
		breq end
		rcall morse_flash
		rjmp mem_loop
	
	end:
		ldd ZL, Y+3
		ldd ZH, Y+2
		ldd r16, Y+1

		adiw YH:YL, 3
	
		out SPL, YL
		out SPL, YH

		pop YL
		pop YH
	ret

morse_flash:
	
	cpi r16, 0xFF
	breq special_case
	
	mov r17, r16
	swap r16
	ldi r18, 4
	
	shift_loop:
		lsr r17
		lsr r16
		dec r18
		brne shift_loop

	loop1:
		clr r18
		ldi r21, 0b00000001
		and r21, r16
		add r18, r21
		breq case_short

		case_long:
			push r16
			ldi r16, 2
			rcall leds_on
			rcall delay_long
			rcall leds_off
			rcall delay_long
			pop r16
			rjmp continue

		case_short:
			push r16
			ldi r16, 2
			rcall leds_on
			rcall delay_short
			rcall leds_off
			rcall delay_long
			pop r16
		continue:
			lsr r16
			dec r17
			brne loop1
	ret

	special_case:
		rcall leds_off
		rcall delay_long
		rcall delay_long
		rcall delay_long
		ret



leds_on:
	cpi r16, 1
	breq case1
	cpi r16, 2
	breq case2
	cpi r16, 3
	breq case3
	cpi r16, 4
	breq case4
	cpi r16, 5
	breq case5
	cpi r16, 6
	breq case6
	
	case1:
		ldi r19, 0b00000010
		sts S_PORTB, r19
		
		ldi r20, 0b00000000
		sts S_PORTL, r20
		ret
		
	case2:
		ldi r19, 0b00001010
		sts S_PORTB, r19
		
		ldi r20, 0b00000000
		sts S_PORTL, r20
		ret
		
	case3:
		ldi r19, 0b00001010
		sts S_PORTB, r19
		
		ldi r20, 0b00000010
		sts S_PORTL, r20
		ret
		
	case4:
		ldi r19, 0b00001010
		sts S_PORTB, r19
		
		ldi r20, 0b00001010
		sts S_PORTL, r20
		ret
		
	case5:
		ldi r19, 0b0001010
		sts S_PORTB, r19
		
		ldi r20, 0b00101010
		sts S_PORTL, r20
		ret
	
	case6:
		ldi r19, 0b00001010
		sts S_PORTB, r19
		
		ldi r20, 0b10101010
		sts S_PORTL, r20
		ret

leds_off:
	ldi r19, 0x00
	sts S_PORTB, r19
	
	ldi r20, 0x00
	sts S_PORTL, r20
	ret



encode_message:
	push YH
	push YL

	in YL, SPL
	in YL, SPH

	sbiw YH:YL, 7

	out SPL, YL
	out SPH, YH

	std Y+1, r16
	std Y+2, r17
	std Y+3, ZH
	std Y+4, ZL
	std Y+5, XH
	std Y+6, XL
	std Y+7, r18

	ldd XL, Y+13
	ldd XH, Y+14
	ldd ZL, Y+15
	ldd ZH, Y+16

	mov r17, XL
	mov r18, XH

	character_loop:
		lpm r16, Z+
		tst r16
		breq read
		push r16
		rcall letter_to_code
		pop r16
		st X+, r0
		rjmp character_loop
	read:
		push r18
		push r17
		rcall flash_message
		pop r17
		pop r18
	
	ldd r16, Y+1
	ldd r17, Y+2
	ldd ZL, Y+3
	ldd ZH, Y+4
	ldd XL, Y+5
	ldd XH, Y+6
	ldd r18, Y+7

	adiw YH:YL, 7
	
	out SPL, YL
	out SPL, YH

	pop YL
	pop YH
	ret	



letter_to_code:
	push YH
	push YL

	in YL, SPL
	in YH, SPH

	sbiw YH:YL, 3
	
	out SPL, YL
	out SPL, YH
	
	std Y+1, r16
	std Y+2, ZH
	std Y+3, ZL

	ldi ZL, LOW(ITU_MORSE << 1)
	ldi ZH, HIGH(ITU_MORSE << 1)
	
	ldd r16, Y+9
	
	subi r16, 65
	breq encoding

	clr r0
	
	letter_loop:
		ldi r17, 8
		adiw ZH:ZL, 8
		dec r16
		breq encoding
	
	encoding:
		lpm r18, Z+
		cpi r18, '-'
		breq case_dot
		case_dash:
			inc r0
			lsl r0
			rjmp increment
		case_dot:
			lsl r0
		increment:
			swap r0
			inc r0
			swap r0
		lpm r18, Z
		brne encoding

	ldd ZL, Y+3
	ldd ZH, Y+2
	ldd r16, Y+1

	adiw YH:YL, 3
	
	out SPL, YL
	out SPL, YH

	pop YL
	pop YH
	
	ret	 


; **********************************************
; **** END OF SECOND "STUDENT CODE" SECTION **** 
; **********************************************


; =============================================
; ==== BEGINNING OF "DO NOT TOUCH" SECTION ====
; =============================================

delay_long:
	rcall delay
	rcall delay
	rcall delay
	ret

delay_short:
	rcall delay
	ret

; When wanting about a 1/5th of second delay, all other
; code must call this function
;
delay:
	rcall delay_busywait
	ret


; This function is ONLY called from "delay", and
; never directly from other code.
;
delay_busywait:
	push r16
	push r17
	push r18

	ldi r16, 0x08
delay_busywait_loop1:
	dec r16
	breq delay_busywait_exit
	
	ldi r17, 0xff
delay_busywait_loop2:
	dec	r17
	breq delay_busywait_loop1

	ldi r18, 0xff
delay_busywait_loop3:
	dec r18
	breq delay_busywait_loop2
	rjmp delay_busywait_loop3

delay_busywait_exit:
	pop r18
	pop r17
	pop r16
	ret



;.org 0x1000

ITU_MORSE: .db "A", ".-", 0, 0, 0, 0, 0
	.db "B", "-...", 0, 0, 0
	.db "C", "-.-.", 0, 0, 0
	.db "D", "-..", 0, 0, 0, 0
	.db "E", ".", 0, 0, 0, 0, 0, 0
	.db "F", "..-.", 0, 0, 0
	.db "G", "--.", 0, 0, 0, 0
	.db "H", "....", 0, 0, 0
	.db "I", "..", 0, 0, 0, 0, 0
	.db "J", ".---", 0, 0, 0
	.db "K", "-.-.", 0, 0, 0
	.db "L", ".-..", 0, 0, 0
	.db "M", "--", 0, 0, 0, 0, 0
	.db "N", "-.", 0, 0, 0, 0, 0
	.db "O", "---", 0, 0, 0, 0
	.db "P", ".--.", 0, 0, 0
	.db "Q", "--.-", 0, 0, 0
	.db "R", ".-.", 0, 0, 0, 0
	.db "S", "...", 0, 0, 0, 0
	.db "T", "-", 0, 0, 0, 0, 0, 0
	.db "U", "..-", 0, 0, 0, 0
	.db "V", "...-", 0, 0, 0
	.db "W", ".--", 0, 0, 0, 0
	.db "X", "-..-", 0, 0, 0
	.db "Y", "-.--", 0, 0, 0
	.db "Z", "--..", 0, 0, 0
	.db 0, 0, 0, 0, 0, 0, 0, 0

MESSAGE01: .db "A A A", 0
MESSAGE02: .db "SOS", 0
MESSAGE03: .db "A BOX", 0
MESSAGE04: .db "DAIRY QUEEN", 0
MESSAGE05: .db "THE SHAPE OF WATER", 0, 0
MESSAGE06: .db "DARKEST HOUR", 0, 0
MESSAGE07: .db "THREE BILLBOARDS OUTSIDE EBBING MISSOURI", 0, 0
MESSAGE08: .db "OH CANADA OUR OWN AND NATIVE LAND", 0
MESSAGE09: .db "I CAN HAZ CHEEZBURGER", 0

; First message ever sent by Morse code (in 1844)
MESSAGE10: .db "WHAT GOD HATH WROUGHT", 0


.dseg
.org 0x200
BUFFER01: .byte 128
BUFFER02: .byte 128
TESTBUFFER: .byte 4

; =======================================
; ==== END OF "DO NOT TOUCH" SECTION ====
; =======================================
