; a3part3.asm
; CSC 230: Spring 2018
;
; Student name: Swapnil Daxini
; Student ID: V00861672
; Date of completed work: 26/03/18
;
; *******************************
; Code provided for Assignment #3
;
; Author: Mike Zastre (2018-Mar-08)
; 
; This skeleton of an assembly-language program is provided to help you
; begin with the programming tasks for A#3. As with A#2, there are 
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
;
; In this "DO NOT TOUCH" section are:
;
; (1) assembler directives setting up the interrupt-vector table
;
; (2) "includes" for the LCD display
;
; (3) some definitions of constants we can use later in the
;     program
;
; (4) code for initial setup of the Analog Digital Converter (in the
;     same manner in which it was set up for Lab #4)
;     
; (5) code for setting up our three timers (timer1, timer3, timer4)
;
; After all this initial code, your own solution's code may start.
;

.cseg
.org 0
	jmp reset

; location in vector table for TIMER1 COMPA
;
.org 0x22
	jmp timer1

; location in vector table for TIMER4 COMPA
;
.org 0x54
	jmp timer4

.include "m2560def.inc"
.include "lcd_function_defs.inc"
.include "lcd_function_code.asm"

.cseg

; These two constants can help given what is required by the
; assignment.
;
#define MAX_PATTERN_LENGTH 10
#define BAR_LENGTH 6
.equ MAX_POS = 5 ; maximum length of text representation
.equ NUMBER = 32767 ; number to use for testing


; All of these delays are in seconds
;
#define DELAY1 0.5
#define DELAY3 0.1
#define DELAY4 0.01


; The following lines are executed at assembly time -- their
; whole purpose is to compute the counter values that will later
; be stored into the appropriate Output Compare registers during
; timer setup.
;

#define CLOCK 16.0e6 
.equ PRESCALE_DIV=1024  ; implies CS[2:0] is 0b101
.equ TOP1=int(0.5+(CLOCK/PRESCALE_DIV*DELAY1))

.if TOP1>65535
.error "TOP1 is out of range"
.endif

.equ TOP3=int(0.5+(CLOCK/PRESCALE_DIV*DELAY3))
.if TOP3>65535
.error "TOP3 is out of range"
.endif

.equ TOP4=int(0.5+(CLOCK/PRESCALE_DIV*DELAY4))
.if TOP4>65535
.error "TOP4 is out of range"
.endif


reset:
	; initialize the ADC converter (which is neeeded
	; to read buttons on shield). Note that we'll
	; use the interrupt handler for timer4 to
	; read the buttons (i.e., every 10 ms)
	;
	ldi temp, (1 << ADEN) | (1 << ADPS2) | (1 << ADPS1) | (1 << ADPS0)
	sts ADCSRA, temp
	ldi temp, (1 << REFS0)
	sts ADMUX, r16


	; timer1 is for the heartbeat -- i.e., part (1)
	;
    ldi r16, high(TOP1)
    sts OCR1AH, r16
    ldi r16, low(TOP1)
    sts OCR1AL, r16
    ldi r16, 0
    sts TCCR1A, r16
    ldi r16, (1 << WGM12) | (1 << CS12) | (1 << CS10)
    sts TCCR1B, temp
	ldi r16, (1 << OCIE1A)
	sts TIMSK1, r16

	; timer3 is for the LCD display updates -- needed for all parts
	;
    ldi r16, high(TOP3)
    sts OCR3AH, r16
    ldi r16, low(TOP3)
    sts OCR3AL, r16
    ldi r16, 0
    sts TCCR3A, r16
    ldi r16, (1 << WGM32) | (1 << CS32) | (1 << CS30)
    sts TCCR3B, temp

	; timer4 is for reading buttons at 10ms intervals -- i.e., part (2)
    ; and part (3)
	;
    ldi r16, high(TOP4)
    sts OCR4AH, r16
    ldi r16, low(TOP4)
    sts OCR4AL, r16
    ldi r16, 0
    sts TCCR4A, r16
    ldi r16, (1 << WGM42) | (1 << CS42) | (1 << CS40)
    sts TCCR4B, temp
	ldi r16, (1 << OCIE4A)
	sts TIMSK4, r16

    ; flip the switch -- i.e., enable the interrupts
    sei

; =======================================
; ==== END OF "DO NOT TOUCH" SECTION ====
; =======================================


; *********************************************
; **** BEGINNING OF "STUDENT CODE" SECTION **** 
; *********************************************

start:
	rcall lcd_init
	rcall lcd_clr
	ldi temp, 0x00
	sts BUTTON_PREVIOUS, temp		;clear all storage bytes
	sts BUTTON_CURRENT, temp
	sts high(BUTTON_COUNT), temp
	sts low(BUTTON_COUNT), temp
	sts BUTTON_LENGTH, temp
	ldi XH, high(DOTDASH_PATTERN)	
	ldi XL, low(DOTDASH_PATTERN)
	
	ldi r20, 10
	ldi temp, ' '		;place space on the dot-dash pattern
	clear_loop:
		st X+, temp
		dec r20
		brne clear_loop
	
	ldi XH, high(DOTDASH_PATTERN)	;load address to dot-dash pattern on X register
	ldi XL, low(DOTDASH_PATTERN)


	
	ldi temp, 0x01		;set PULSE to show <> first
	sts PULSE, temp
	

	check_timer3:
		in temp, TIFR3		;polling loop
		sbrs temp, OCF3A
		rjmp check_timer3
		
		ldi r16, 0		;go to first row column 14 to show pulse
		ldi r17, 14
		push r16
		push r17
		rcall lcd_gotoxy
		pop r17
		pop r16

		ldi temp, 1<<OCF3A
		out TIFR3, temp		

		lds temp, PULSE		;check current state of PULSE
		cpi temp, 0x01
		breq active
		
		ldi temp, 0x20		;case for no pulse
		push temp
		rcall lcd_putchar
		pop temp
		ldi temp, 0x20
		push temp
		rcall lcd_putchar
		pop temp
		rjmp skip3
		
		active:				;case for pulse
			ldi temp, '<'
			push temp
			rcall lcd_putchar
			pop temp
			ldi temp, '>'
			push temp
			rcall lcd_putchar
			pop temp

skip3:	ldi r16, 1			;go to second row column 11 on lcd screen
		ldi r17, 11
		push r16
		push r17
		rcall lcd_gotoxy
		pop r17
		pop r16
		
		lds r17, high(BUTTON_COUNT)		;Use provided code to convert BUTTON_COUNT to decimal text
		lds r16, low(BUTTON_COUNT)
		push r17
		push r16

		ldi r17, high(DISPLAY_TEXT)
		ldi r16, low(DISPLAY_TEXT)
		push r17
		push r16

		rcall to_decimal_text
		pop temp
		pop temp
		pop temp
		pop temp

		
		ldi r20, 0x05					;Display 5 character on LCD
		ldi ZH, high(DISPLAY_TEXT)
		ldi ZL, low(DISPLAY_TEXT)

		loop1:
			ld temp, Z+
			push temp
			rcall lcd_putchar		;Place count on LCD
			pop temp
			dec r20
			brne loop1

		ldi r16, 1		;go to second row column 0
		ldi r17, 0
		push r16
		push r17
		rcall lcd_gotoxy
		pop r17
		pop r16
		
		lds temp, BUTTON_CURRENT
		cpi temp, 0x00		;check to see if button is being pressed
		breq off

		ldi r20, 0x06
		ldi temp, '*'		;if yes place 6 *'s on LCD
		loop2:
			 push temp
			 rcall lcd_putchar
			 pop temp
			 dec r20
			 brne loop2
		rjmp pattern

off:	ldi r20, 0x06
		ldi temp, ' '		;if no button is being pressed, place space on LCD
		loop3:
			 push temp
			 rcall lcd_putchar
			 pop temp
			 dec r20
			 brne loop3
							
pattern:
		ldi r16, 0		
		ldi r17, 0
		push r16
		push r17
		rcall lcd_gotoxy
		pop r17
		pop r16

		ldi YH, high(DOTDASH_PATTERN)	;load address for dot-dash pattern
		ldi YL, low(DOTDASH_PATTERN)

		ldi r20, 10		;Place 10 characters from pattern on LCD
		
		dot_dashloop:
			ld temp, Y+
			push temp
			rcall lcd_putchar
			pop temp
			dec r20
			brne dot_dashloop
		
		rjmp check_timer3

stop:
    rjmp stop


timer1:		;interrupt used to alternate value of PULSE from 1 to 0
	push r16
	push r17
	lds r16, SREG
	push r16

	lds r16, PULSE
	ldi r17, 0x01
	eor r16, r17
	sts PULSE, r16

	pop r16
	sts SREG, r16
	pop r17
	pop r16

	reti

; Note there is no "timer3" interrupt handler as we must use this
; timer3 in a polling style within our main program.


timer4:
	push r16
	push r17
	lds r16, SREG		;store the current SREG register
	push r16

	lds temp, BUTTON_CURRENT
	sts BUTTON_PREVIOUS, temp	;store current value of the button to BUTTON_PREVIOUS

	rcall check_button			;update the value of BUTTON_CURRENT
	
	lds temp, BUTTON_CURRENT	;check for case when BUTTON_CURRENT is 0
	cpi temp, 0x00
	breq case_x0				;branch to cases when current is 0
	
	lds temp, BUTTON_PREVIOUS	;check for cases when BUTTON_PREVIOUS is 1
	cpi temp, 0x01
	breq case_11				;branch to case when button current and previous are both 1
	
	case_01:	;case for when button was pressed	
		lds YH, high(BUTTON_COUNT)
		lds YL, low(BUTTON_COUNT)
		adiw YH:YL, 1		;increment button count
	
		ldi temp, 0x01
		sts BUTTON_LENGTH, temp		;reset length variable to 1
	
		sts high(BUTTON_COUNT), YH
		sts low(BUTTON_COUNT), YL
		rjmp end4	;skip other cases


	case_x0:
		lds r16, BUTTON_PREVIOUS	;check for when BUTTON_PREVIOUS is 0 
		cpi r16, 0x00
		breq end4		;go to end for when both BUTTON_PREVIOUS and BUTTON_CURRENT are 0 
	
	case_10:	;when the button has stopped being pressed. Check if dot or dash
		lds temp, BUTTON_LENGTH
		cpi temp, 20
		brge dash	; break for dash if longer than 200 ms (BUTTON_LENGTH is in multiples of 10ms)
		ldi temp, '.'
		st X+, temp
		rjmp end4
		dash:
			ldi temp, '-'
			st X+, temp
			rjmp end4		

	case_11:  ;case when button is being held down. Increment BUTTON_LENGTH
		lds temp, BUTTON_LENGTH
		cpi temp, 21		;don't increment if greater than 21 as it is a dash and to prevent overflow
		brge end4
		inc temp
		sts BUTTON_LENGTH, temp


end4:	pop r16
		sts SREG, r16		;restore SREG register
		pop r17
		pop r16
    
		reti
   

;Function that updates the BUTTON_CURRENT state
check_button:
	; start a-to-d
	lds r16, ADCSRA
	ori r16, (1 << ADSC)
	sts ADCSRA, r16

	;wait for ADC unit to finish the read
	wait:
		lds r16, ADCSRA
		sbrc r16, ADSC
		rjmp wait

		;read the value
		lds r16, ADCL
		lds r17, ADCH

		; if r17:16 > 900, then *no* button
		; was pushed...
		cpi r17, high(900)
		brge skip
		push r16
		ldi r16, 0x01
		sts BUTTON_CURRENT, r16			;set BUTTON_CURRENT to 1 if r17:r16 < 900
		pop r16
		ret


	skip:
		push r16
		ldi r16, 0x00
		sts BUTTON_CURRENT, r16			;set BUTTON_CURRENT to 0 if r17:r16 > 900
		pop r16
		ret


; First parameter: 16-bit value for which a
; text representation of its decimal form is to
; be stored.
;
; Second parameter: 16-bit address in data memory
; in which the text representation is to be stored.
;
to_decimal_text:
 	.def countL=r18
 	.def countH=r19
 	.def factorL=r20
 	.def factorH=r21
 	.def multiple=r22
 	.def pos=r23
 	.def zero=r0
 	.def ascii_zero=r16
 	push countH
 	push countL
 	push factorH
	push factorL
 	push multiple
 	push pos
	push zero
 	push ascii_zero
 	push YH
 	push YL
 	push ZH
 	push ZL
 	in YH, SPH
 	in YL, SPL
 	; fetch parameters from stack frame
 	;
 	.set PARAM_OFFSET = 16
 	ldd countH, Y+PARAM_OFFSET+3
 	ldd countL, Y+PARAM_OFFSET+2
 	; this is only designed for positive
 	; signed integers; we force a negative
 	; integer to be positive.
 	;
 	andi countH, 0b01111111
 	clr zero
 	clr pos
 	ldi ascii_zero, '0'
 	; The idea here is to build the text representation
 	; digit by digit, starting from the left-most.
 	; Since we need only concern ourselves with final
 	; text strings having five characters (i.e., our
	; text of the decimal will never be more than
 	; five characters in length), we begin we determining
 	; how many times 10000 fits into countH:countL, and
 	; use that to determine what character (from ’0’ to
 	; ’9’) should appear in the left-most position
 	; of the string.
 	;
 	; Then we do the same thing for 1000, then
 	; for 100, then for 10, and finally for 1.
 	;
 	; Note that for *all* of these cases countH:countL is
 	; modified. We never write these values back onto
 	; that stack. This means the caller of the function
 	; can assume call-by-value semantics for the argument
 	; passed into the function.
 	;
to_decimal_next:
 	clr multiple

to_decimal_10000:
 	cpi pos, 0
 	brne to_decimal_1000
 	ldi factorL, low(10000)
 	ldi factorH, high(10000)
 	rjmp to_decimal_loop

to_decimal_1000:
 	cpi pos, 1
 	brne to_decimal_100
 	ldi factorL, low(1000)
 	ldi factorH, high(1000)
	rjmp to_decimal_loop

to_decimal_100:
 	cpi pos, 2
 	brne to_decimal_10
 	ldi factorL, low(100)
 	ldi factorH, high(100)
 	rjmp to_decimal_loop

to_decimal_10:
 	cpi pos, 3
 	brne to_decimal_1
 	ldi factorL, low(10)
 	ldi factorH, high(10)
 	rjmp to_decimal_loop

to_decimal_1:
 	mov multiple, countL
 	rjmp to_decimal_write

to_decimal_loop:
 	inc multiple
 	sub countL, factorL
 	sbc countH, factorH
 	brpl to_decimal_loop
 	dec multiple
 	add countL, factorL
 	adc countH, factorH

to_decimal_write:
 	ldd ZH, Y+PARAM_OFFSET+1
 	ldd ZL, Y+PARAM_OFFSET+0
 	add ZL, pos
 	adc ZH, zero
 	add multiple, ascii_zero
 	st Z, multiple
 	inc pos
 	cpi pos, MAX_POS
 	breq to_decimal_exit
 	rjmp to_decimal_next

to_decimal_exit:
 	pop ZL
 	pop ZH
 	pop YL
 	pop YH
 	pop ascii_zero
 	pop zero
 	pop pos
 	pop multiple
 	pop factorL
 	pop factorH
 	pop countL
 	pop countH
 	.undef countL
 	.undef countH
 	.undef factorL
 	.undef factorH
 	.undef multiple
 	.undef pos
 	.undef zero
	.undef ascii_zero
	ret

; ***************************************************
; **** END OF FIRST "STUDENT CODE" SECTION ********** 
; ***************************************************


; ################################################
; #### BEGINNING OF "TOUCH CAREFULLY" SECTION ####
; ################################################

; The purpose of these locations in data memory are
; explained in the assignment description.
;

.dseg

PULSE: .byte 1
COUNTER: .byte 2
DISPLAY_TEXT: .byte 16
BUTTON_CURRENT: .byte 1
BUTTON_PREVIOUS: .byte 1
BUTTON_COUNT: .byte 2
BUTTON_LENGTH: .byte 1
DOTDASH_PATTERN: .byte MAX_PATTERN_LENGTH

; ##########################################
; #### END OF "TOUCH CAREFULLY" SECTION ####
; ##########################################
