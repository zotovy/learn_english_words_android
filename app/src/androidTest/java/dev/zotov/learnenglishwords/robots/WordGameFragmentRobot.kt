package dev.zotov.learnenglishwords.robots

import androidx.test.core.app.takeScreenshot
import androidx.test.core.graphics.writeToTestStorage
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.allOf

object WordGameFragmentRobot {

    fun clickWord(word: String) = onView(withText(word)).perform(click())

    fun clickNext() = onView(allOf(withText("Продолжить"), isDisplayed())).perform(click())

    fun clickSkip() = onView(withText("Пропустить")).perform(click())

    fun checkTargetWord(word: String) {
        onView(withId(dev.zotov.features.word_game.R.id.target_word)).check(matches(withText(word)))
    }

    fun checkWordsVariants(words: List<String>) {
        words.forEach {
            onView(withText(it)).check(matches(isDisplayed()))
        }
    }

    fun captureScreenshot(name: String) {
        takeScreenshot().writeToTestStorage("${javaClass.simpleName}_${name}")
    }
}