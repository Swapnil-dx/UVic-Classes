/*
 * Lab 9  - display.c
 * Name: Swapnil Daxini
 * Student Number: V00861672
 */
#define F_CPU 16000000UL

#include <avr/io.h>
#include <util/delay.h>

/*
 * Our 6 LED strip occupies ardruino pins 42, 44, 46, 48, 50, 52
 * and Gnd (ground)
 * Pin 42 Port L: bit 7 (PL7)
 * Pin 44 Port L: bit 5 (PL5)
 * Pin 46 Port L: bit 3 (PL3)
 * Pin 48 Port L: bit 1 (PL1)
 * Pin 50 Port B: bit 3 (PB3)
 * Pin 52 Port B: bit 1 (PB1)
*/
int main (void)
{
  /* set PORTL and PORTB for output*/
  DDRL = 0xFF;
  DDRB = 0xFF;
  
  /*
		write your code here according to the following pseudo-code:
		
		turn the first led on;
		1 second delay;
		for (; ;)
		{
			turn the current led off, turn the next led on; //wrap around with around 1 second delay;
			
		}
  */
	PORTL = 0x80;
	PORTB = 0x00;
	_delay_ms(1000);

	for(;;){
		PORTL = 0x20;
		PORTB = 0x00;
		_delay_ms(1000);

		PORTL = 0x08;
		PORTB = 0x00;
		_delay_ms(1000);

		PORTL = 0x02;
		PORTB = 0x00;
		_delay_ms(1000);

		PORTL = 0x00;
		PORTB = 0x08;
		_delay_ms(1000);

		PORTL = 0x00;
		PORTB = 0x02;
		_delay_ms(1000);

		PORTL = 0x80;
		PORTB = 0x00;
		_delay_ms(1000);
	
	}

  return 1;
}
