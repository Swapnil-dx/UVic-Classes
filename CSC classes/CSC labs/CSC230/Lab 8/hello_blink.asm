; A version of "hello, world!" where the
; exclamation marks blinks.

.cseg
.org 0
	jmp start

.org 0x22
	jmp swap_chars_isr 


; The following files *must* be in the same
; directory as this "hello_world.asm". Writing
; programs made up of multiple assembly files
; is not nearly as easy or straightforward
; as writing Java programs with multiple classes.
; Note the files that are included: all assembly
; programs this term which use the LCD display
; must have these includes.
;
.include "m2560def.inc"
.include "lcd_function_defs.inc"
.include "lcd_function_code.asm"


; The next .cseg is needed because we can never
; assume that an included file ends with code
; in cseg (or even in dseg). Therefore we take
; absolutely not chances and indicate that we
; resume in the code segment. (We do not need
; to specific an origin address; the assembler
; will simply at the code which follows into
; the next available address in the code segment).
;
.cseg


; And so our program begins... and the *very*
; first thing we do is initialize the LCD
; display and all of the associated data
; needed for this display.
;
start:
	rcall lcd_init

	ldi r17, high(7800)
	ldi r16, low(7800)
	sts OCR1AH, r17
	sts OCR1AL, r16

	ldi r16, 0
	sts TCCR1A, r16

	ldi r16, (1 << WGM12) | (1 << CS12) | (1 << CS10)
	sts TCCR1B, r16

	ldi r16, (1 << OCIE1A)
	sts TIMSK1, r16

	sei


	ldi r16, 1
	ldi r17, 3
	push r16
	push r17
	rcall lcd_gotoxy
	pop r17
	pop r16



	; Our timer1 interrupt will swap these
	; two values every interrupt. Our busy
	; loop, however, will simply write what
	; is in CHAR_ONE to the last position
	; on the LCD display
	;
	
	ldi r16, 'H'
	push r16
	rcall lcd_putchar
	pop r16

	ldi r16, 'e'
	push r16
	rcall lcd_putchar
	pop r16

	ldi r16, 'l'
	push r16
	rcall lcd_putchar
	pop r16

	ldi r16, 'l'
	push r16
	rcall lcd_putchar
	pop r16

	ldi r16, 'o'
	push r16
	rcall lcd_putchar
	pop r16

	ldi r16, ','
	push r16
	rcall lcd_putchar
	pop r16

	ldi r16, ' '
	push r16
	rcall lcd_putchar
	pop r16
	
	ldi r16, 'w'
	push r16
	rcall lcd_putchar
	pop r16

	ldi r16, 'o'
	push r16
	rcall lcd_putchar
	pop r16

	ldi r16, 'r'
	push r16
	rcall lcd_putchar
	pop r16
	
	ldi r16, 'l'
	push r16
	rcall lcd_putchar
	pop r16

	ldi r16, 'd'
	push r16
	rcall lcd_putchar
	pop r16
	
	ldi r16, '!'
	sts CHAR_ONE, r16

	ldi r16, ' '
	sts CHAR_TWO, r16


	; Constantly place into the last LCD
	; location the value in CHAR_ONE.
	;
blink_loop:
	ldi r16, 1
	ldi r17, 15
	push r16
	push r17
	rcall lcd_gotoxy
	pop r17
	pop r16
	
	lds r16, CHAR_ONE
	push r16
	rcall lcd_putchar
	pop r16

	rjmp blink_loop


swap_chars_isr:
	push r16
	push r17
	lds r16, SREG
	push r16

	lds r16, CHAR_TWO
	lds r17, CHAR_ONE
	sts CHAR_ONE, r16
	sts CHAR_TWO, r17

	pop r16
	sts SREG, r16
	pop r17
	pop r16
	
	reti


.dseg
CHAR_ONE: .byte 1
CHAR_TWO: .byte 1
