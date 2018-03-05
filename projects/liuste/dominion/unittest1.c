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
	
	G.coins = 0;
	
	printf("\n******Test Update Coins******\n");
	
	printf("\nTest copper coins\n");
	G.hand[0][0] = copper;
	G.hand[0][1] = copper;
	G.hand[0][2] = copper;
	G.hand[0][3] = copper;
	G.hand[0][4] = copper;
	G.hand[0][5] = copper;
	G.hand[0][6] = copper;
	G.hand[0][7] = copper;
	G.hand[0][8] = copper;
	G.hand[0][9] = copper;
	G.handCount[0] = 10;
	updateCoins(0,&G, 0);
	printf("\nDisplay 10 copper coins: %d\n", G.coins);
	if(G.coins == 10)
		printf("\nTEST SUCCESSFULLY COMPLETED\n");
	else
		printf("\nFailed\n");
	
	printf("\nTest silver coins\n");
	G.hand[0][0] = silver;
	G.hand[0][1] = silver;
	G.hand[0][2] = silver;
	G.hand[0][3] = silver;
	G.hand[0][4] = silver;
	G.hand[0][5] = silver;
	G.hand[0][6] = silver;
	G.hand[0][7] = silver;
	G.hand[0][8] = silver;
	G.hand[0][9] = silver;
	G.handCount[0] = 10;
	updateCoins(0,&G, 0);
	printf("\nDisplay 10 silver coins: %d\n", G.coins-10);
	if(G.coins == 20)
		printf("\nTEST SUCCESSFULLY COMPLETED\n");
	else
		printf("\nFailed\n");
	
	printf("\nTest gold coins\n");
	G.hand[0][0] = gold;
	G.hand[0][1] = gold;
	G.hand[0][2] = gold;
	G.hand[0][3] = gold;
	G.hand[0][4] = gold;
	G.hand[0][5] = gold;
	G.hand[0][6] = gold;
	G.hand[0][7] = gold;
	G.hand[0][8] = gold;
	G.hand[0][9] = gold;
	G.handCount[0] = 10;
	updateCoins(0,&G, 0);
	printf("\nDisplay 10 gold coins: %d\n", G.coins-20);
	if(G.coins == 30)
		printf("\nTEST SUCCESSFULLY COMPLETED\n");
	else
		printf("\nFailed\n");
	
	printf("\nCan we get no coins?\n");
	G.hand[0][0] = adventurer;
	G.hand[0][1] = adventurer;
	G.hand[0][2] = adventurer;
	G.hand[0][3] = adventurer;
	G.hand[0][4] = adventurer;
	G.hand[0][5] = adventurer;
	G.hand[0][6] = adventurer;
	G.hand[0][7] = adventurer;
	G.hand[0][8] = adventurer;
	G.hand[0][9] = adventurer;
	updateCoins(0,&G, 0);
	printf("\nDisplay no coins: %d\n", G.coins);
	if(G.coins == 0)
		printf("\nTEST SUCCESSFULLY COMPLETED");
	else
		printf("\nFailed");
	
	return 0;
}