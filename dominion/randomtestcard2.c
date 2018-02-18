#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
#include <string.h>
#include <stdio.h>
#include <math.h>
#include <stdlib.h>

#define MAX_TESTS 100000

int assert(int number)
{
	if(number == 0)
		return 0;
	else
		return 1;
}

int main (int argc, char** argv)	{
	int k[10] = {adventurer, council_room, feast, village, great_hall, embargo, cutpurse, sea_hag, baron, smithy};
	struct gameState state;
	int i;
	int temp;
	int pre1;
	int player_count = 0;
	int player = 0;
	int seed;

	for (i = 0; i < MAX_TESTS; i++) {	
		printf ("\n******Test Smithy******\n");

		seed = rand();		//pick random seed
		player_count = rand() % 3 + 2;
		player = rand() % player_count;
		initializeGame(player_count, k, seed, &state);	//initialize Gamestate
		state.whoseTurn = player;
		state.deckCount[player] = rand() % MAX_DECK; //Pick random deck size out of MAX DECK size
		state.discardCount[player] = rand() % MAX_DECK;
		state.handCount[player] = rand() % MAX_HAND;
		pre1 = state.handCount[player]+2;
		state.hand[player][0] = smithy;
		state.numActions = rand() % 10; 
		cardEffect(smithy, 0, 0, 0, &state, 0, 0);
		printf("pre1: %d\n", pre1);
		printf("state.handCount[player] after cardEffect called: %d\n", state.handCount[player]);
		if(assert(state.handCount[player] == pre1))
			printf("\nTEST SUCCESSFULLY COMPLETED\n");
		else
			printf("\nFAILED\n");
	}
	return 0;
} 