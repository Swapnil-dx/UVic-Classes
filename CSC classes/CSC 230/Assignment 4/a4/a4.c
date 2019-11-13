/* a4.c
 * CSC Spring 2018
 * 
 * Student name: Swapnil Daxini
 * Student UVic ID: V00861672
 * Date of completed work: 
 *
 *
 * Code provided for Assignment #4
 *
 * Author: Mike Zastre (2018-Mar-25)
 *
 * This skeleton of a C language program is provided to help you
 * begin the programming tasks for A#4. As with the previous
 * assignments, there are "DO NOT TOUCH" sections. You are *not* to
 * modify the lines within these section.
 *
 * You are also NOT to introduce any new program-or file-scope
 * variables (i.e., ALL of your variables must be local variables).
 * YOU MAY, however, read from and write to the existing program- and
 * file-scope variables. Note: "global" variables are program-
 * and file-scope variables.
 *
 * UNAPPROVED CHANGES to "DO NOT TOUCH" sections could result in
 * either incorrect code execution during assignment evaluation, or
 * perhaps even code that cannot be compiled.  The resulting mark may
 * be zero.
 */


/* =============================================
 * ==== BEGINNING OF "DO NOT TOUCH" SECTION ====
 * =============================================
 */

#define F_CPU 16000000UL
#include <avr/io.h>
#include <avr/interrupt.h>
#include <util/delay.h>

#define DELAY1 0.000001
#define DELAY3 0.01

#define PRESCALE_DIV1 8
#define PRESCALE_DIV3 64
#define TOP1 ((int)(0.5 + (F_CPU/PRESCALE_DIV1*DELAY1))) 
#define TOP3 ((int)(0.5 + (F_CPU/PRESCALE_DIV3*DELAY3)))

#define PWM_PERIOD ((long int)500)

volatile long int count = 0;
volatile long int slow_count = 0;


ISR(TIMER1_COMPA_vect) {
	count++;
}


ISR(TIMER3_COMPA_vect) {
	slow_count += 5;
}

/* =======================================
 * ==== END OF "DO NOT TOUCH" SECTION ====
 * =======================================
 */


/* *********************************************
 * **** BEGINNING OF "STUDENT CODE" SECTION ****
 * *********************************************
 */

/* 
Function that accept a number which represent an LED and changes its 
state depending on the passed value.
@params Number (0-3) signifying LED and either 1 or 0 for the change of state
@return none
*/
void led_state(uint8_t LED, uint8_t state) {
	uint8_t mask =  0;		//initialize mask to be used in switch fuction
	switch(LED){
	case 3:
		mask = 0b00000010;	//dependent on LED number
		if(state == 1){
			PORTL |= mask;	//switches on LED
		} else{
			PORTL &= ~mask;	//switches off LED
		}
		break;
	case 2:
		mask = 0b00001000;
		if(state == 1){
			PORTL |= mask;
		} else{
			PORTL &= ~mask;
		}
		break;
	case 1:
		mask = 0b00100000;
		if(state == 1){
			PORTL |= mask;
		} else{
			PORTL &= ~mask;
		}
		break;
	case 0:
		mask = 0b10000000;
		if(state == 1){
			PORTL |= mask;
		} else{
			PORTL &= ~mask;
		}
		break;
	}
}


/*
Function when called, flashes a SOS signal in morse code. The function
achieves this by reading from two arrays and placing multiple calls
to LED state
*/
void SOS() {
    uint8_t light[] = {
        0x1, 0, 0x1, 0, 0x1, 0,
        0xf, 0, 0xf, 0, 0xf, 0,
        0x1, 0, 0x1, 0, 0x1, 0,
        0x0
    };

    int duration[] = {
        100, 250, 100, 250, 100, 500,
        250, 250, 250, 250, 250, 500,
        100, 250, 100, 250, 100, 250,
        250
    };

	int length = 19;

	for(int i = 0; i < length; i++){	//initial for-loop to loop through all different states
		int curr = light[i];
		int delaytime = duration[i];
		
		for(int j = 0; j < 4; j++){		//Second for loop used to switch on current light state
			uint8_t mask = 0b00000001;
			mask &= curr;
			led_state(j, mask);
			curr >>= 1;
		}

		_delay_ms(delaytime);			//apply delay using duration array
	}
}

/*
This function set the brightness of one LED to certain level (between 0 and 1).
This is done by only turning on the LED for certain length of time in every 500ms
*/
void glow(uint8_t LED, float brightness) {
	int threshold = (int)PWM_PERIOD * brightness;	//number of ms to turn LED on for
	

	for(;;){
		if((count < (int) threshold)){		//turn on LED if below threshold
			led_state(LED, 1);
		} else if((count < PWM_PERIOD)){	//turn off LED once above threshold
			led_state(LED, 0);
		} else {
			count = 0;						//Reset count once it reaches 500 ms
			if((int) brightness != 0){		//if statement used to account for the case
			led_state(LED, 1);				//when LED should not be turned on at all
			}
		}
	
	}
}


/*
Function that is similar to glow(), but changes the threshold in intervals
using timer3 to make LED  pulse.
*/
void pulse_glow(uint8_t LED) {

	int threshold = 0;
	int rate = 1;	//used as a status to either increment or decrement
					//threshold level
	for(;;){
		if (slow_count == 15){	//changes threshold level every 30ms
			if(rate == 1){
				threshold += 1;	//add 1 if rate is 1
			} else {
				threshold -= 1;	//subtract 1 if rate is 0
			}

			if(threshold == 500){	//case when threshold is at 500ms
				rate = 0;			//begin to decrease threshold
			}

			if(threshold == 0){		//case when threshold is at 0ms
				rate = 1;			//begin to increment threshold
			}
			slow_count = 0;
		}

		if((count < threshold)){
			led_state(LED, 1);
		} else if((count < PWM_PERIOD)){
			led_state(LED, 0);
		} else {
			count = 0;
			led_state(LED, 1);
		}
	
	}
}

/*
Function is similar to SOS() function and only changes to the light and
duration arrays.
*/
void light_show() {
	uint8_t light[] = {
        0xf, 0x0, 0xf, 0x0, 0xf, 0x0,
		0x6, 0x0, 0x9, 0x0, 0xf, 0x0, 
		0xf, 0x0 ,0xf, 0x0, 0x9, 0x0, 
		0x6, 0x0, 0x8, 0xc, 0x6, 0x3,
		0x1, 0x3, 0x6, 0xc, 0x8, 0xc,
		0x6, 0x3, 0x1, 0x3, 0x6, 0x0,
		0xf, 0x0, 0xf, 0x0, 0x6, 0x0,
		0x6, 0x0
    };

    int duration[] = {
        200, 200, 200, 200, 200, 200,
		100, 100, 100, 100, 200, 200,
		200, 200, 200, 200, 100, 100,
		100, 100, 75, 75, 75, 75,
		75, 75, 75, 75, 75, 75,
		75, 75, 75, 75, 75, 250,
		200, 200, 200, 100, 100, 100,
		100, 100
    };

	int length = 44;

	for(int i = 0; i < length; i++){	//initial for-loop to loop through all different states
		int curr = light[i];
		int delaytime = duration[i];
		
		for(int j = 0; j < 4; j++){		//Second for loop used to switch on current light state
			uint8_t mask = 0b00000001;
			mask &= curr;
			led_state(j, mask);
			curr >>= 1;
		}

		_delay_ms(delaytime);			//apply delay using duration array
	}
}


/* ***************************************************
 * **** END OF FIRST "STUDENT CODE" SECTION **********
 * ***************************************************
 */


/* =============================================
 * ==== BEGINNING OF "DO NOT TOUCH" SECTION ====
 * =============================================
 */

int main() {
    /* Turn off global interrupts while setting up timers. */

	cli();

	/* Set up timer 1, i.e., an interrupt every 1 microsecond. */
	OCR1A = TOP1;
	TCCR1A = 0;
	TCCR1B = 0;
	TCCR1B |= (1 << WGM12);
    /* Next two lines provide a prescaler value of 8. */
	TCCR1B |= (1 << CS11);
	TCCR1B |= (1 << CS10);
	TIMSK1 |= (1 << OCIE1A);

	/* Set up timer 3, i.e., an interrupt every 10 milliseconds. */
	OCR3A = TOP3;
	TCCR3A = 0;
	TCCR3B = 0;
	TCCR3B |= (1 << WGM32);
    /* Next line provides a prescaler value of 64. */
	TCCR3B |= (1 << CS31);
	TIMSK3 |= (1 << OCIE3A);


	/* Turn on global interrupts */
	sei();

/* =======================================
 * ==== END OF "DO NOT TOUCH" SECTION ====
 * =======================================
 */


/* *********************************************
 * **** BEGINNING OF "STUDENT CODE" SECTION ****
 * *********************************************
 */
	
	DDRL = 0xff;

/* This code could be used to test your work for part A.

	led_state(0, 1);
	_delay_ms(1000);
	led_state(2, 1);
	_delay_ms(1000);
	led_state(1, 1);
	_delay_ms(1000);
	led_state(2, 0);
	_delay_ms(1000);
	led_state(0, 0);
	_delay_ms(1000);
	led_state(1, 0);
	_delay_ms(1000);
 */

// This code could be used to test your work for part B.

//	SOS();
 

// This code could be used to test your work for part C.

//	glow(2, 0.1);
 



// This code could be used to test your work for part D.

	//pulse_glow(3);
 


/* This code could be used to test your work for the bonus part.*/

	light_show();


/* ****************************************************
 * **** END OF SECOND "STUDENT CODE" SECTION **********
 * ****************************************************
 */
}
