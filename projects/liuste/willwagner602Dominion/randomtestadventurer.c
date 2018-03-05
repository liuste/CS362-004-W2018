#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
#include <string.h>
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <assert.h>
#define NUM_TESTS 100
#define CARD_COUNT treasure_map

int main(){
	
	int i, j, player_count, player, seed, hand_count, pre_count, post_count;
	int deck_treasures = 0; 
	int k[10] = {curse,estate,duchy,province,copper,baron, smithy, treasure_map, minion, steward};
	srand(time(NULL));   
	struct gameState G;
	
	printf("******Random Test Adventurer Card*****\n");	
	// Main Loop
	for(i = 0;i < NUM_TESTS; i++){ 
		deck_treasures = 0;
		pre_count = 0; post_count = 0;
  		player_count = rand() % 3 + 2;
  		printf("Number of players: %d\n", player_count);
		seed = rand() % 100;	
		//initialize game
		if(initializeGame(player_count, k, seed, &G) == -1){ 
			printf("Error initializing a random game\n");
		}
		// random player generated
		player = rand() % player_count;
		printf("Which player: %d\n", player); 
		G.whoseTurn = player;
		G.numActions = 1;
		G.handCount[player] = 5;
		G.deckCount[player] = rand() % MAX_HAND;
		printf("Player's deckCount: %d\n", G.deckCount[player]);
		G.discardCount[player] = 0;
		
		//random deck generated
		for(j = 0; j < G.deckCount[player]; j++){
			G.deck[player][j] = rand() % CARD_COUNT;
			if(G.deck[player][j] >= copper && G.deck[player][j] <= gold){ 
				deck_treasures++;
			}
		}
		printf("Player's deck_treasures: %d\n", deck_treasures);
		//random hand generated
		for(j = 0; j < G.handCount[player]; j++){	
			G.hand[player][j] = rand() % CARD_COUNT;
			if(G.hand[player][j] >= copper && G.hand[player][j] <= gold){ 
				pre_count++;	}
		}
	
		G.hand[player][0] = adventurer; 
		hand_count = G.handCount[player];
		playCard(0, 0, 0, 0, &G); //play card
		//2 cards should have been placed in users hand
		
		printf("Number of cards in hand before playing Adventurer Card: %d\n", hand_count);
		printf("Number of cards in hand after playing Adventurer Card: %d\n", G.handCount[player]);
		if(deck_treasures == 0)
		{
			if(hand_count + 0 != G.handCount[player]){
				printf("Error! Should not add any card to player's hand\n");
			}	
		}
		else if(deck_treasures == 1)
		{
			if(hand_count + 1 != G.handCount[player]){
				printf("Error! Should only add 1 card to player's hand\n");
			}
		}
		else if(deck_treasures == 2)
		{
			if(hand_count + 2 != G.handCount[player]){
				printf("Error! Should have added 2 card to player's hand\n");
			}
		}
		else
		{
			if(hand_count + 2 != G.handCount[player]){
				printf("Error! Should have added 2 card to player's hand even if there are more than 2 treasure cards generated in deck\n");
			}	
		}

		for(j = 0; j < G.handCount[player]; j++){
			if(G.hand[player][j] >= copper && G.hand[player][j] <= gold)
					post_count++;
		}
		printf("Number of treasure cards in hand before playing adventurer card: %d\n", pre_count);
		printf("Number of treasure cards in hand after playing adventurer card:: %d\n", post_count);
		
		if(pre_count + 2 != post_count){
			printf("Error! 2 treasure cards were not put into players hand after playing adventurer card and having at least 2 treasure cards in the deck\n");
		}
		printf("Iteration #%d: \n\n", i);
		
	}	
	return 0;
}

