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
	
	printf ("\n******Test Great Hall******\n");

	G.numActions = 0;
	G.handCount[0] = 1;
	G.hand[0][0] = great_hall;
	temp = cardEffect(great_hall, 0, 0, 0, &G, 0, 0);
	if (assert(temp == 0))
		printf("\nTEST SUCCESSFULLY COMPLETED\n");
	if (assert(G.handCount[0] == 0))
		printf("\nTEST SUCCESSFULLY COMPLETED\n");
	else
		printf("\nFAILED\n");
	if (assert(G.numActions == 1))
		printf("\nTEST SUCCESSFULLY COMPLETED\n");
	else 
		printf("\nFAILED\n");
	return 0;
}