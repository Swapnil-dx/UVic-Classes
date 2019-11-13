.equ PORTB = 0x25
.equ DDRB = 0x24
.equ PORTL = 0x10B
.equ DDRL = 0x10A

	ldi r17, 0xFF
	sts DDRB, r17
	sts DDRL, r17
	ldi r19, 0b00000100
	sts PORTL, r19
	sts PORTB, r19
done: jmp done
