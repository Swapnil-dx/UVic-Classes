.org 0

.def count = r17
	ldi count, 0

loop:
	inc count
	cpi count, 0x04
	breq done
	rjmp loop
done: jmp done
