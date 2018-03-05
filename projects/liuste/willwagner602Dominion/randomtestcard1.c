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

int main ()	{
	int k[10] = {adventurer, council_room, feast, village, great_hall, embargo, cutpurse, sea_hag, baron, smithy};
	struct gameState state;
	int i;
	int temp;
	int pre1;
	int pre2;
	int player_count = 0;
	int player = 0;
	srand(time(NULL));
	int seed;
	for (i = 0; i < MAX_TESTS; i++) {
		pre1 = 0;
		pre2 = 0;
		temp = 1;
		seed = rand();		//pick random seed
		player_count = rand() % 3 + 2;
		player = rand() % player_count;
		initializeGame(player_count, k, seed, &state);	//initialize Gamestate
	
		printf ("\n******Random Card Test Village******\n");

		state.whoseTurn = player;
		state.deckCount[player] = rand() % MAX_DECK; //Pick random deck size out of MAX DECK size
		state.discardCount[player] = rand() % MAX_DECK;
		state.handCount[player] = rand() % MAX_HAND;
		state.hand[player][0] = village;
		state.numActions = rand() % 10; 
		pre1 = state.numActions;
		pre2 = state.deckCount[player];
		//printf("pre2: %d\n", pre2);
		temp = cardEffect(village, 0, 0, 0, &state, 0, 0);
		//printf("state.handCount[player] after cardEffect function call: %d\n", state.handCount[player]);
		if (assert(temp == 0))
			printf("\nTEST SUCCESSFULLY COMPLETED\n");
		if (assert(state.deckCount[player] == pre2 - 1))
			printf("\nTEST SUCCESSFULLY COMPLETED\n");
		else
			printf("\nFAILED\n");
		if (assert(state.handCount[0] == 0))
			printf("\nTEST SUCCESSFULLY COMPLETED\n");
		else 
			printf("\nFAILED\n");
		if(assert(state.numActions == pre1 + 2))
			printf("\nTEST SUCCESSFULLY COMPLETED\n");
		else 
			printf("\nFAILED\n");
	}
	return 0;
} 