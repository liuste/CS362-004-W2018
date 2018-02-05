#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
#include <string.h>
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <assert.h>

int main (int argc, char** argv)	{
	struct gameState G;
	int temp;
	
	printf ("\n******Test Village******\n");

	G.numActions = 0;
	G.handCount[0] = 1;
	G.hand[0][0] = village;
	temp = cardEffect(village, 0, 0, 0, &G, 0, 0);
	assert(temp == 0);
	assert(G.handCount[0] == 0);
	assert(G.numActions == 2);
	printf("\nTEST SUCCESSFULLY COMPLETED\n");
	
	return 0;
} 