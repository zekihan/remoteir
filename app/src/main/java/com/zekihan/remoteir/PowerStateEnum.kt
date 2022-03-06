package com.zekihan.remoteir

enum class PowerStateEnum(var value: Int) {
    OFF(0),
    ON(1);

    fun getPowerStateText(): String {
        return when (value) {
            OFF.value -> "OFF"
            ON.value -> "ON"
            else -> ""
        }
    }

    fun switch(): PowerStateEnum {
        return when (value) {
            OFF.value -> ON
            ON.value -> OFF
            else -> OFF
        }
    }

    companion object {
        fun getState(value: Int): PowerStateEnum {
            return when (value) {
                OFF.value -> OFF
                ON.value -> ON
                else -> OFF
            }
        }
    }
}