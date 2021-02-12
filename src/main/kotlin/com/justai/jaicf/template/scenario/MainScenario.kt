package com.justai.jaicf.template.scenario

import com.justai.jaicf.activator.caila.caila
import com.justai.jaicf.channel.yandexalice.alice
import com.justai.jaicf.channel.yandexalice.model.AliceEvent
import com.justai.jaicf.model.scenario.Scenario

object MainScenario : Scenario() {

    init {
        state("start") {
            activators {
                regex("/start")
                intent("Hello")
            }
            action {
                reactions.run {
                    image("https://media.giphy.com/media/ICOgUNjpvO0PC/source.gif")
                    sayRandom(
                        "Hello! How can I help?",
                        "Hi there! How can I help you?"
                    )
                    buttons(
                        "Help me!",
                        "How are you?",
                        "What is your name?"
                    )
                }
            }
        }

        state("bye") {
            activators {
                intent("Bye")
            }

            action {
                reactions.sayRandom(
                    "See you soon!",
                    "Bye-bye!"
                )
                reactions.image("https://media.giphy.com/media/EE185t7OeMbTy/source.gif")
            }
        }

        state("smalltalk", noContext = true) {
            activators {
                anyIntent()
            }

            action(caila) {
                activator.topIntent.answer?.let { reactions.say(it) } ?: reactions.go("/fallback")
            }
        }

        fallback {
            reactions.sayRandom(
                "Sorry, I didn't get that...",
                "Sorry, could you repeat please?"
            )
        }


        state("alexa") {
            activators {
                intent("HelloWorldIntent")
            }
            action {
                reactions.sayRandom(
                    "Привет"
                )
                reactions.image("https://media.giphy.com/media/EE185t7OeMbTy/source.gif")
            }
        }

        state("main") {
            activators {
                event(AliceEvent.START)
            }

            action {
                reactions.say("Майор на связи. Докладывайте.")
                reactions.alice?.image(
                    url = "https://i.imgur.com/YOnWzLM.jpg",
                    title = "Майор на связи",
                    description = "Начните сообщение со слова \"Докладываю\""
                )

            }
        }

        state("report") {
            activators {
                regex("докладываю .+")
            }

            action {
                reactions.run {
                    say("Спасибо.")
                    sayRandom(
                        "Ваш донос зарегистрирован под номером ${random(1000, 9000)}.",
                        "Оставайтесь на месте. Не трогайте вещественные доказательства."
                    )
                    say("У вас есть еще какая-нибудь информация?")
                    buttons("Да", "Нет")
                }
            }

            state("yes") {
                activators {
                    regex("да")
                }

                action {
                    reactions.say("Докладывайте.")
                }
            }

            state("no") {
                activators {
                    regex("нет")
                    regex("отбой")
                }

                action {
                    reactions.sayRandom("Отбой.", "До связи.")
                    reactions.alice?.endSession()
                }
            }

        }

    }
}