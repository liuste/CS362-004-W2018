#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
#include <string.h>
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <assert.h>

	//int main()
int main (int argc, char** argv)	{
	struct gameState G;
	
	//added over from unittest.c
	int k[10] = {adventurer, gardens, embargo, village, minion, mine, cutpurse, 
	       sea_hag, tribute, smithy};

	printf ("Starting game.\n");
  
	int r = initializeGame(2, k, 2, &G);

	assert (r == 0);
	//
	
	printf("\n******Test Buy Card******\n");
	
	G.numBuys = 2;
	G.coins = 2;
	printf("Testing do not have enough money to buy that\n");
 	int test = buyCard(2, &G);
		if(test == -1)
			printf("\nTEST SUCCESSFULLY COMPLETED\n");
		else
			printf("\nFailed\n");
	printf("Test a successful purchase\n");
	G.coins = 10;
	test = buyCard(2, &G);
		if(test == 0)
			printf("\nTEST SUCCESSFULLY COMPLETED\n");
		else
			printf("\nFailed\n");
 	printf("Test exact amount\n");
 	test = buyCard(2, &G);
		if(test == 0)
			printf("\nTEST SUCCESSFULLY COMPLETED\n");
		else
			printf("\nFailed\n");
 	printf("Test user does not have any buys left\n");
 	G.coins = 6;
	test = buyCard(2, &G);
		if(test == -1)
			printf("\nTEST SUCCESSFULLY COMPLETED\n");
		else
			printf("\nFailed\n");
		
 	return 0;	
}