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
	
	printf ("\n******Test Smithy******\n");

	G.numActions = 0;
	G.handCount[0] = 5;
	G.hand[0][0] = smithy;
	G.hand[0][1] = copper;
	G.hand[0][2] = copper;
	G.hand[0][3] = copper;
	G.hand[0][4] = copper;
	
	G.deckCount[0] = 4;
	G.deck[0][1] = feast;
	G.deck[0][2] = copper;
	G.deck[0][3] = copper;
	G.deck[0][4] = mine;
	
	temp = cardEffect(smithy, 0, 0, 0, &G, 0, 0);
	assert(temp == 0);
	printf("handCount (should be 7)= %d\n", G.handCount[0]);
	assert(G.handCount[0] == 7);
	printf("\nTEST SUCCESSFULLY COMPLETED\n");
	
	return 0;
} 