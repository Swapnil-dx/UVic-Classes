;
; lab 5
; Spring, 2018

.include "m2560def.inc"

.cseg

	;give symbolic names to registers
	
	;int str_length(String src) in Java

	;address of the source string as a parameter to subroutine str_length
	;srcH:srcL=r1:r0 stores address of the source string
	;note the registers are used to pass the parameter to the subroutine
	.def srcH=r1
	.def srcL=r0 
	 
	;stores the length of the string - return value of subroutine str_length
	;note that n(r17) is used to store the returned value
	.def n=r17 

	.def temp=r18

	;address of the source string msg1 is loaded to srcH:srcL - parameter
	;Note msg1 is stored in the flash memory (program memory)
	;why msg<<1?
	ldi temp, high(msg1<<1)
	mov srcH, temp
	ldi temp, low(msg1<<1)
	mov srcL, temp

	call str_length

	sts LENGTH1, n ;returned value is stored at data memory LENGTH1

	;**************Write your code here*****************
	;** use the example above, call the subroutine str_length again
	;** to get the length of msg2
	;** parameter - address of the source string msg2 is loaded to srcH:srcL
	;** return - register n is used to store the returned value
	;** store the value in register n to data memory at LENGTH2
	ldi temp, high(msg2<<1)
	mov srcH, temp
	ldi temp, low(msg2<<1)
	mov srcL, temp

	call str_length

	sts LENGTH2, n
	;**************Write your code here*****************
	;** use the example above, call the subroutine str_length again
	;** to get the length of msg3
	;** parameter - address of the source string msg3 is loaded to srcH:srcL
	;** return - register n is used to store the returned value
	;** store the value in register n to data memory at LENGTH3
	ldi temp, high(msg3<<1)
	mov srcH, temp
	ldi temp, low(msg3<<1)
	mov srcL, temp

	call str_length

	sts LENGTH3, n

	done: rjmp done


;-------------- subroutine ---------------------------------
	;**************Write your code here*****************
	;** implement subroutine str_length 
	;** calculate the number of characters in source string (pass-by-reference)
	;** parameter - srcH:srcL contains the memory address of the
	;** source string in flash memory 
	;** return - register n is used to store the returned value
	;** c-string format - last byte contains 0
str_length:
	mov ZH, srcH	;store higher address to string in ZH
	mov ZL, srcL	;store lower address to string in ZL

	ldi n, -1		;begin program counter at -1 to account
					;for first increment

	loop1:
		inc n			
		lpm temp, Z+	;Load character from program memory
		tst temp		;test for null 
		brne loop1		;branch
ret

;-------------- string stored in program memory ---------------------------------
msg1: .db "Hello, world!", 0 ; c-string format 13 characters
msg2: .db "", 0 ; c-string format 0 characters
msg3: .db "CSC 230 is fun!", 0 ; c-string format 15 characters

;-------------- data memory ---------------------------------
.dseg
.org 0x200

LENGTH1: .byte 1
LENGTH2: .byte 1
LENGTH3: .byte 1
