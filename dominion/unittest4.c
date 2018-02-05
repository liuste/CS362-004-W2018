#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
#include <string.h>
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <assert.h>
	//int main(int argc, char *argv[])
int main (int argc, char** argv)	{
    struct gameState G;
    int a;
	int k[10] = {adventurer, gardens, minion, village, embargo, mine, cutpurse, baron, tribute, smithy};
	
	printf ("\n******Testing Whose Turn******\n");
	
	printf("\nTest 4 turns that should be successful\n");
	int x;
    for (x = 2 ; x < 5 ; ++x)	{
        a = initializeGame(2, k, 5, &G);
        a = whoseTurn(&G);
        assert(a == G.whoseTurn);
		printf("\nTEST SUCCESSFULLY COMPLETED\n");		
	}

	return 0;
}