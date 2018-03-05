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
	
	printf ("\n******Test Adventurer******\n");

	G.numActions = 0;
	G.handCount[0] = 5;
	G.hand[0][0] = adventurer;
	G.hand[0][1] = copper;
	G.hand[0][2] = copper;
	G.hand[0][3] = copper;
	G.hand[0][4] = copper;
	
	G.deckCount[0] = 4;
	G.deck[0][1] = feast;
	G.deck[0][2] = copper;
	G.deck[0][3] = copper;
	G.deck[0][4] = mine;
	
	temp = cardEffect(adventurer, 0, 0, 0, &G, 0, 0);
	if (assert(temp == 0))
		printf("deckCount= %d\n", G.deckCount[0]);
	if (assert(G.deckCount[0] == 2))
		printf("\nTEST SUCCESSFULLY COMPLETED\n");
	else
		printf("\nFAILED\n");
	return 0;
} 