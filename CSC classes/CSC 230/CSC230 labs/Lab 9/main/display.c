/*
 * Lab 9  - display.c
 * Name: Swapnil Daxini
 * Student Number: V00861672
 */
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

#include "main.h"
#include "lcd_drv.h"

// These are included by the LCD driver code, so 
// we don't need to include them here.
// #include <avr/io.h>
// #include <util/delay.h>
int main( void )
{
	lcd_init();

	/*TO DO: show a string on the first line, then show the number of characters of that string on the second line. Let them blink for 0.5 second. 
	*/
	for (;;){
	
	lcd_xy(0,0);

	char msg[100] = "University of Vi";

	lcd_puts(msg);

	lcd_xy(0,1);
	int n = strlen(msg);
	char len[5];
	itoa(n, len, 10); //Convert int to string
	lcd_puts(len);

	_delay_ms(500);
	char blank[17] = "                ";	//Blank message to make blink

	lcd_xy(0,0);
	lcd_puts(blank);

	lcd_xy(0,1);
	lcd_puts(blank);
	_delay_ms(500);

	}
}
