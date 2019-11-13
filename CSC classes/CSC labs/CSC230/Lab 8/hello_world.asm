; An excruciatingly verbose "hello, world!"
; program that uses the Arduino mega2560
; LCD display.
;

; At the start of the code segment, we must
; now worry about the presence of interrupts
; elsewhere in the included code. Therefore
; we place "rjmp start" as vector 0 (i.e.,
; when the Arduino first powers up with this
; program in flash, it will execute the instruction
; at vector zero).
;
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


	; The only other two functions needed
	; in our program are lcd_gotoxy and
	; lcd_putchar.

	; lcd_gotoxy accepts two parameters: the
	; first is the row (0 is the first LCD
	; row, 1 is the second LCD row); the second
	; is the column (0 is the left-most row,
	; while 15 is the rightmost row). Parameters
	; are pushed onto the stack, and we must
	; remember to pop them off the stack after
	; the call.
	;

	;
	ldi r16, 1
	ldi r17, 3
	push r16
	push r17
	rcall lcd_gotoxy
	pop r17
	pop r16


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
	push r16
	rcall lcd_putchar
	pop r16




	; And now spin until the end of the world.
	;
stop:
	rjmp stop
