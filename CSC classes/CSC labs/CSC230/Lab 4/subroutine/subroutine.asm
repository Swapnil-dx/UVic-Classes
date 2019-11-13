.cseg
.org 0

	ldi r16, 0
	call addOne
	inc r16

done: jmp done

addOne:
	inc r16
	ret
