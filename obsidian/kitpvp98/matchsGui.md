
paginated gui 6 rows
 
all waiting games as yellow concrete
+empty games as green concrete
+in game as red concrete
+filter for each category as anvil -> on click open gui to sel what type of game to look for, empty, in game, waiting

on click yellow concrete or green concrete: 
	add player to list of players in waiting list for given game.id
	if playerList == 1: set game.status to waiting
	if player list == game.id.maxSize:
		teleport all players to map and give player.selectedKitId to each player
		set game.status as in game
	
on click of anvil:
	show list of games based on selection

missing:
- map list
- how to define respawn points after the match is over, maybe admin command
- messages and sounds on each action
- how to define spawn points (maybe admin command like: /matchs spawn <mapID> <team>)