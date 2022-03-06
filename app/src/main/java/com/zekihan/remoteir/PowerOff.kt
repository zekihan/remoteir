package com.zekihan.remoteir

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.hardware.ConsumerIrManager
import android.widget.RemoteViews

open class PowerOff : AppWidgetProvider() {

    private var state = PowerStateEnum.OFF

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray?
    ) {
        val remoteViews = RemoteViews(context.packageName, R.layout.power_off)
        val powerStateWidget = ComponentName(context, PowerOff::class.java)
        remoteViews.setOnClickPendingIntent(
            R.id.widgetButtonOff,
            getPendingSelfIntent(context, Constants.Intents.powerStateChange())
        )
        appWidgetManager.updateAppWidget(powerStateWidget, remoteViews)
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (Constants.Intents.powerStateChange() == intent.action) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val remoteViews = RemoteViews(context.packageName, R.layout.power_off)
            val powerStateWidget = ComponentName(context, PowerOff::class.java)

            val settings =
                context.getSharedPreferences(Constants.SharedPreferences.powerStateWidget(), 0)
            val stateValue =
                settings.getInt(Constants.SharedPreferences.powerState(), PowerStateEnum.OFF.value)
            state = PowerStateEnum.getState(stateValue)
            state = state.switch()

            irAction(context, state)

            val editor = settings.edit()
            editor.putInt(Constants.SharedPreferences.powerState(), state.value)
            editor.apply()

            remoteViews.setTextViewText(R.id.widgetButtonOff, state.getPowerStateText())
            appWidgetManager.updateAppWidget(powerStateWidget, remoteViews)
        }

        if (Constants.Intents.powerStateChangeFromApp() == intent.action) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val remoteViews = RemoteViews(context.packageName, R.layout.power_off)
            val powerStateWidget = ComponentName(context, PowerOff::class.java)

            val settings =
                context.getSharedPreferences(Constants.SharedPreferences.powerStateWidget(), 0)
            val stateValue =
                settings.getInt(Constants.SharedPreferences.powerState(), PowerStateEnum.OFF.value)
            state = PowerStateEnum.getState(stateValue)
            remoteViews.setTextViewText(R.id.widgetButtonOff, state.getPowerStateText())
            appWidgetManager.updateAppWidget(powerStateWidget, remoteViews)
        }
    }

    companion object {
        fun getPendingSelfIntent(context: Context?, action: String?): PendingIntent? {
            val intent = getSelfIntent(context, action)
            return PendingIntent.getBroadcast(context, 0, intent, 0)
        }

        fun getSelfIntent(context: Context?, action: String?): Intent {
            val intent = Intent(context, PowerOff::class.java)
            intent.action = action
            return intent
        }
    }


    private fun irAction(context: Context?, state: PowerStateEnum) {
        val manager: ConsumerIrManager =
            context?.getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager
        if (manager.hasIrEmitter()) {
            when (state) {
                PowerStateEnum.OFF -> manager.transmit(38400, Osram.OFF.getSignal())
                PowerStateEnum.ON -> manager.transmit(38400, Osram.ON.getSignal())
            }
        }
    }
}