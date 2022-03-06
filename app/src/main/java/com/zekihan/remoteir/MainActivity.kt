package com.zekihan.remoteir

import android.content.Context
import android.hardware.ConsumerIrManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.zekihan.remoteir.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val manager: ConsumerIrManager =
            getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager
        if (!manager.hasIrEmitter()) {
            Snackbar.make(
                binding.root.rootView,
                "Cannot found IR Emitter on the device",
                Snackbar.LENGTH_LONG
            )
                .setAction("Action", null).show()
        }
        val carrierFrequency = 38400

        binding.buttonLightUp?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.LIGHT_UP.getSignal())
        }

        binding.buttonLightDown?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.LIGHT_DOWN.getSignal())
        }

        binding.buttonOff?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.OFF.getSignal())

            val settings =
                it.context.getSharedPreferences(Constants.SharedPreferences.powerStateWidget(), 0)
            val editor = settings.edit()
            editor.putInt(Constants.SharedPreferences.powerState(), PowerStateEnum.OFF.value)
            editor.apply()

            val intent = PowerOff.getSelfIntent(it.context, Constants.Intents.powerStateChangeFromApp())
            it.context.sendBroadcast(intent)
        }

        binding.buttonOn?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.ON.getSignal())

            val settings =
                it.context.getSharedPreferences(Constants.SharedPreferences.powerStateWidget(), 0)
            val editor = settings.edit()
            editor.putInt(Constants.SharedPreferences.powerState(), PowerStateEnum.ON.value)
            editor.apply()

            val intent = PowerOff.getSelfIntent(it.context, Constants.Intents.powerStateChangeFromApp())
            it.context.sendBroadcast(intent)
        }

        binding.buttonR1?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.R1.getSignal())
        }

        binding.buttonG1?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.G1.getSignal())
        }

        binding.buttonB1?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.B1.getSignal())
        }

        binding.buttonWarm?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.WARM.getSignal())
        }

        binding.buttonR2?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.R2.getSignal())
        }

        binding.buttonG2?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.G2.getSignal())
        }

        binding.buttonB2?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.B2.getSignal())
        }

        binding.buttonCold?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.COLD.getSignal())
        }

        binding.buttonR3?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.R3.getSignal())
        }

        binding.buttonG3?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.G3.getSignal())
        }

        binding.buttonB3?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.B3.getSignal())
        }

        binding.buttonFlash?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.FLASH.getSignal())
        }

        binding.buttonR4?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.R4.getSignal())
        }

        binding.buttonG4?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.G4.getSignal())
        }

        binding.buttonB4?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.B4.getSignal())
        }

        binding.buttonStrobe?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.STROBE.getSignal())
        }

        binding.buttonR5?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.R5.getSignal())
        }

        binding.buttonG5?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.G5.getSignal())
        }

        binding.buttonB5?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.B5.getSignal())
        }

        binding.buttonSmooth?.setOnClickListener {
            manager.transmit(carrierFrequency, Osram.SMOOTH.getSignal())
        }

    }
}