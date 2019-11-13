; button.asm 
; Spring, 2018 lab4
;
; A program that demonstrates reading the buttons
; page number such as p.268 refer to pages in datasheet of ATMEGA 2560

; symbonic names for registers
.def DATAH=r27  ;DATAH:DATAL  store 10 bits data from ADC
.def DATAL=r26
.def BOUNDARY_H=r1  ;hold high byte value of the threshhold for button
.def BOUNDARY_L=r0  ;hold low byte value of the threshhold for button, r1:r0

; registers used: r0, 1, 16, 17, 19, 20, 21, 22, 24, 26, 27
; When a button is pressed, the following LED lights are on
; Definitions for PORTA and PORTL when using
; STS and LDS instructions (ie. memory mapped I/O)
;
.equ DDRB=0x24
.equ PORTB=0x25
.equ DDRL=0x10A
.equ PORTL=0x10B

;
; Definitions for using the Analog to Digital Conversion
.equ ADCSRA=0x7A
.equ ADCSRB=0x7B
.equ ADMUX=0x7C
.equ ADCL=0x78
.equ ADCH=0x79

.equ RIGHT	= 0x032 ; the same for both LCD keypad board
; LCD keypad shield:
; board v1.0 
; .equ UP     = 0x0FA
; .equ DOWN   = 0x1C2
; .equ LEFT   = 0x28A
; .equ SELECT = 0x352

; board v1.1 if the following values don't work properly, uncomment the
; values under v1.0 and comment out the following set
.equ UP	    = 0x0C3
.equ DOWN	= 0x17C
.equ LEFT	= 0x22B
.equ SELECT	= 0x316
; 
; 

.cseg
.org 0

	; initialize the Analog to Digital conversion - ADC

	; enable the ADC & slow down the clock from 16mHz to ~125kHz, 16mHz/128
	ldi r16, 0x87  ;0x87 = 0b10000111
	sts ADCSRA, r16

	ldi r16, 0x00
	sts ADCSRB, r16 ; combine with MUX4:0 in ADMUX to select ADC0 p282

	; bits 7:6(REFS1:0) = 01: AVCC with external capacitor at AREF pin p.281
	; bit  5 ADCLAR(ADC Left Adjust Result) = 0: right adjustment the result
	; bits 4:0 (MUX4:0) = 00000: combine with MUX5 in ADCSRB ->ADC0 channel is used.
	ldi r16, 0x40  ;0x40 = 0b01000000
	sts ADMUX, r16

	; initialize PORTB and PORTL for ouput (LED lights)
	ldi	r16, 0xFF
	sts DDRB,r16
	sts DDRL,r16

	; detect if "RIGHT" button is pressed r1:r0 <- 0x032
	ldi r16, low(RIGHT);
	mov BOUNDARY_L, r16
	ldi r16, high(RIGHT)
	mov BOUNDARY_H, r16

loop:
	ldi r19, 0b00000010  ; turn on the light at the bottom
	sts PORTB, r19

	call check_button
	cpi  r24, 1
	brne loop

	ldi r19, 0x00  ; turn off the light if "RIGHT" is pressed for 1 second
	sts PORTB, r19
	ldi	r20, 0x40
	call delay
	rjmp loop

;
; the function tests to see if the button
; RIGHT has been pressed
;
; on return, r24 is set to be: 0 if not pressed, 1 if pressed
;
; this function uses registers:
;	r16
;	r17
;	r24
;
; This function could be made much better.  Notice that the a2d
; returns a 2 byte value (actually 10 bits).
; 
; if you consider the word:
;	 value = (ADCH << 8) +  ADCL
; then:
;
; value > 0x3E8 - no button pressed
;
; Otherwise:
; value < 0x032 - right button pressed
; value < 0x0C3 - up button pressed
; value < 0x17C - down button pressed
; value < 0x22B - left button pressed
; value < 0x316 - select button pressed
;
; This function 'cheats' because I observed
; that ADCH is 0 when the right or up button is
; pressed, and non-zero otherwise.
; 
check_button:
	; start a2d
	lds	r16, ADCSRA	

	; bit 6 =1 ADSC (ADC Start Conversion bit), remain 1 if conversion not done
	; ADSC changed to 0 if conversion is done
	ori r16, 0x40 ; 0x40 = 0b01000000
	sts	ADCSRA, r16

	; wait for A to D conversion to complete, check for bit 6, the ADSC bit
wait:	lds r16, ADCSRA
		andi r16, 0x40
		brne wait

		; read the value, use XH:XL to store the 10-bit result
		lds DATAL, ADCL
		lds DATAH, ADCH

		clr r24
		; if DATAH:DATAL < BOUNDARY_H:BOUNDARY_L
		;     r24=1  "right" button is pressed
		; else
		;     r24=0
		cp DATAL, BOUNDARY_L
		cpc DATAH, BOUNDARY_H
		brsh skip		
		ldi r24,1
skip:	ret

;
; delay
;
; set r20 before calling this subroutine
; r20 = 0x40 is approximately 1 second delay
;
; this function uses registers:
;
;	r20
;	r21
;	r22
;
;delay:	
;TODO: write the delay function here
delay:	

