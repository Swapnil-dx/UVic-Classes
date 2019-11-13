.cseg   ;define code segment
.org 0  ;start at address 0 

.def number1 = r16   ;register 16 holds a number
	
	ldi number1, 0x0A   ;load hex number 0x0A to register
	andi number1, 0b00000001   ;perform and op to clear all bit except LSB

.def number2 = r17    	
	ldi number2, 0x08   ;load number
	lsr number2    ;logical shift right
	

.def number3 = r18
	ldi number3, 0x14      ;load number
	add number3, number2   ;note: add operation works like number3 = number2 + number3

.def number4 = r19
	ldi number4, 0b00010011  ;load binary number (note: in 2s complement)
	ror number4              ;Rotate right
	done: jmp done
