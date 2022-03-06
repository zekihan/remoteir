package com.zekihan.remoteir

class Constants {
    class Intents {
        companion object {
            fun powerStateChange(): String {
                return "powerStateChangeOnClick"
            }

            fun powerStateChangeFromApp(): String {
                return "powerStateChangeFromApp"
            }
        }
    }

    class SharedPreferences {
        companion object {
            fun powerStateWidget(): String {
                return "powerStateWidget"
            }

            fun powerState(): String {
                return "powerState"
            }
        }
    }
}