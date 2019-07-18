package com.thechiefmeat.freetusky.components.instancemute.interfaces

interface InstanceActionListener {
    fun mute(mute: Boolean, instance: String, position: Int)
}