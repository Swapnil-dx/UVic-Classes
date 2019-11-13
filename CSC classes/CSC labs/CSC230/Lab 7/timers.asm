;Program lab7 - LED Blinker

;Programmer Dr. Mike Zastre
;Debug platform: AVR Simulator 2
;Device: ATmega2560
;March 1, 2018
;

.include "m2560def.inc"
.cseg
.org 0

; Purpose of this program is to blink three LEDs
; at different rates, and to implement this 
; blinking by using (a) timers and (b) toggling
; port bits.
;
; To make this a bit more tractable, we'll only
; choose LEDS which are controlled via the same
; port register. These three bits will be:
; 
; * pin 42 (Port L, bit 7) -- blink 0.5 seconds;
;   this will run on timer 3. However, we'll call
;   this LED3 below.
; * pin 44 (Port L, bit 5) -- blink 1.5 seconds
;   this will run on timer 4. However, we'll call
;   this LED4 below.
; * pin 46 (Port L, bit 3) -- blink 3.0 seconds
;   this will run on timer 5. However, we'll call
;   this LED5 below.
;
; (Note that we aren't using the names LED1 and
; LED2 -- we're trying to keep the names of delays,
; LEDs, etc. match the names of their timers.)
;
; IMPORTANT NOTE: This program polls the timers
; as part of its code. We can also implement the
; same behavior via interrupts -- which might eventually
; seem easier, but only once we understand the
; asynchronous nature of interrupts.
;


.equ S_DDRL=0x10A
.equ S_PORTL=0x10B

#define DELAY3 0.5
#define DELAY4 1.5
#define DELAY5 3.0

#define LED3 0b10000000
#define LED4 0b00100000
#define LED5 0b00001000

.def temp=r16
.def templow=r16
.def temphigh=r17
.def leds=r18

	; Set up the stack. We should *always* set up
	; the stack.
	;
	; Have I yet said that ...
	; ... we should set up the stack?
	;
	ldi templow, low(RAMEND)
	out SPL, templow
	ldi temphigh, high(RAMEND)
	out SPH, temphigh


	; Let's set the Data Direction Register for 
	; the LEDs port register, and while we're at
	; it, ensure the LEDs are *all* off.
    ;
	ldi temp, 0xFF
	sts S_DDRL, temp
	
	ldi temp, 0
	sts S_PORTL, temp



	; What follows is a bit of assembler arithmetic.
	; All of the quantities below are calculated at
	; assembly time.
	;
	; The PRESCALE value is set to 1024 -- that is,
	; because base system clock runs at 16MHz and it is too
    ; fast for our purposes (i.e., a 16-bit counter would
    ; overflow in no time).  The PRESCALE value permits us
    ; to use a "version" of the clock that runs slower but
    ; without needing a separate and slower clock.
	;

#define CLOCK 16.0e6
.equ PRESCALE_DIV=1024  ; implies CS[2:0] is 0b101

.equ TOP3=int(0.5+(CLOCK/PRESCALE_DIV*DELAY3))
.if TOP3>65535
.error "TOP3 is out of range"
.endif

	
	; We'll now set up the three timers. For each timer we
	; must:
	;
	; (1) Set its Output Compare Register to the proper
	;     TOP value for that register. Note carefully how
	;     the high byte is output first, then the low
	;     byte. THIS IS ABSOLUTELY NECESSARY!

	; (2) Set its Timer Counter Control Registers to
	;     the correct configuration (which for us really
	;     means only setting values in the TCCR B register).
	;     But just to be safe, we'll clear all the bits in
	;     the TCCR A register as well.
	;
	ldi temphigh, high(TOP3)
	ldi templow, low(TOP3)
	sts OCR3AH, temphigh
	sts OCR3AL, templow

	ldi temp, 0
	sts TCCR3A, temp
	
	ldi temp, (1 << WGM32) | (1 << CS32) | (1 << CS30)
	sts TCCR3B, temp
	
	clr leds

main_loop:
	check_timer_3:
		in temp, TIFR3
		sbrs temp, OCFA3
		rjmp set_leds
		
		ldi temp, 1<<OCFA3
		out TIFR3, temp
		
		ldi temp, LED3
		eor leds, temp

	set leds:
		sts S_PORTL, leds
	skip_overflow:
		rjmp main_loop
	
	stop:
		rjmp stop


