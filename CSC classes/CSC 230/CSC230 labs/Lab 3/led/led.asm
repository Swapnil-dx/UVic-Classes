.equ PORTB = 0x25
.equ DDRB = 0x24
.equ PORTL = 0x10B
.equ DDRL = 0x10A

	ldi r17, 0xFF
	sts DDRB, r17
	sts DDRL, r17

	ldi r18, 0b0101000
	sts PORTL, r18

	ldi r19, 0b00001000
	sts PORTB, r19

	ldi r20, 0x40

loop1:
	nop
	ldi r21, 0xFF

loop2:
	nop
	ldi r22, 0xFF

loop3:
	nop
	dec r22
	brne loop3
	ldi r18, 0b00100010
	sts PORTL, r18

	ldi r19, 0b00000010
	sts PORTB, r19

	dec r21
	brne loop2

	dec r20
	brne loop1

	ldi r20, 0x40

loop1_2:
	nop
	ldi r21, 0xFF

loop2_2:
	nop
	ldi r22, 0xFF

loop3_3:
	nop
	dec r22
	brne loop3

	dec r21
	brne loop2

	dec r20
	brne loop1

	ldi r18, 0b10101010
	sts PORTL, r18

	ldi r19, 0b00001010
	sts PORTB, r19
done: jmp done
