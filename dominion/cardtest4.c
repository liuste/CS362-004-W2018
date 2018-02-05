#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
#include <string.h>
#include <stdio.h>
#include <math.h>
#include <stdlib.h>

int assert(int number)
{
	if(number == 0)
		return 0;
	else
		return 1;
}

int main (int argc, char** argv)	{
	struct gameState G;
	int temp;
	
	printf ("\n******Test Village******\n");

	G.numActions = 0;
	G.handCount[0] = 1;
	G.hand[0][0] = village;
	temp = cardEffect(village, 0, 0, 0, &G, 0, 0);
	if (assert(temp == 0))
		printf("\nTEST SUCCESSFULLY COMPLETED\n");
	if (assert(G.handCount[0] == 0))
		printf("\nTEST SUCCESSFULLY COMPLETED\n");
	else 
		printf("\nFAILED\n");
	if(assert(G.numActions == 2))
		printf("\nTEST SUCCESSFULLY COMPLETED\n");
	else 
		printf("\nFAILED\n");
	return 0;
} 