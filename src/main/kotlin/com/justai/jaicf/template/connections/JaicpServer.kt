package com.justai.jaicf.template.connections

import com.justai.jaicf.channel.aimybox.AimyboxChannel
import com.justai.jaicf.channel.alexa.AlexaChannel
import com.justai.jaicf.channel.facebook.FacebookChannel
import com.justai.jaicf.channel.googleactions.jaicp.ActionsFulfillmentSDK
import com.justai.jaicf.channel.jaicp.JaicpServer
import com.justai.jaicf.channel.jaicp.channels.ChatApiChannel
import com.justai.jaicf.channel.jaicp.channels.ChatWidgetChannel
import com.justai.jaicf.channel.jaicp.channels.TelephonyChannel
import com.justai.jaicf.channel.slack.SlackChannel
import com.justai.jaicf.channel.telegram.TelegramChannel
import com.justai.jaicf.channel.yandexalice.AliceChannel
import com.justai.jaicf.template.accessToken
import com.justai.jaicf.template.templateBot

fun main() {
    JaicpServer(
        botApi = templateBot,
        accessToken = accessToken,
        channels = listOf(
            ChatApiChannel,
            ChatWidgetChannel,
            TelephonyChannel,
            TelegramChannel,
            ActionsFulfillmentSDK(),
            FacebookChannel,
            AlexaChannel,
            AimyboxChannel,
            SlackChannel,
            AliceChannel.Factory("")
        )
    ).start(wait = true)
}