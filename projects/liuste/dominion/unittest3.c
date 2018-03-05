#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
#include <string.h>
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <assert.h>

int main(int argc, char** argv)
{
	struct gameState state;
	int k[10] = { adventurer, gardens, embargo, village, minion, mine, cutpurse, sea_hag, tribute, smithy };
	int a = initializeGame(2, k, 3, &state);
	assert(a == 0);

	printf("\n******Test Is Game Over******\n");
	state.supplyCount[province] = 0;
	int j = isGameOver(&state);
	printf("j= %d\n", j);
	assert(j == 1);
	printf("\nTEST SUCCESSFULLY COMPLETED\n");
	state.supplyCount[province] = 8;
	state.supplyCount[village] = 0;
	state.supplyCount[embargo] = 0;
	state.supplyCount[minion] = 0;
	j = isGameOver(&state);
	printf("j= %d\n", j);
	assert(j == 1);
	printf("\nTEST SUCCESSFULLY COMPLETED\n");

	return 0;
} 