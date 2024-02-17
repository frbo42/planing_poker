package planing.poker.second.models

import planing.poker.second.repository.gameStorage

class Player(val name: String, var card: String)

fun vote(userName: String, selection: String) {
    gameStorage[userName] = Player(userName, selection)
}