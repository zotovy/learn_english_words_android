package dev.zotov.learnenglishwords.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

object WordGameResultFragmentRobot {

    fun checkScore(score: String) {
        onView(withId(dev.zotov.features.word_game.R.id.score))
            .check(matches(withText(score)))
            .check(matches(isDisplayed()))
    }

    fun checkResults(english: String, russian: String? = null) {
        onView(withText(english)).check(matches(isDisplayed()))
        if (russian != null) {
            onView(withText(russian)).check(matches(isDisplayed()))
        }
    }

    fun checkMessage(message: String) {
        onView(withId(dev.zotov.features.word_game.R.id.message))
            .check(matches(withText(message)))
            .check(matches(isDisplayed()))
    }
}
