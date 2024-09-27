package com.rebotted

enum class ServerState(sqlEnabled: Boolean, freeSpawning: Boolean) {
    PUBLIC(true, false),
    DEBUG(false, true)
}