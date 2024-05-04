package dev.zotov.learnenglishwords.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

object WordDefinitionDialogFragmentRobot {

    fun checkWord(word: String) {
        onView(withId(dev.zotov.features.word_info.R.id.word))
            .check(matches(withText(word)))
            .check(matches(isDisplayed()))
    }

    fun checkPhonetic(phonetic: String) {
        onView(withId(dev.zotov.features.word_info.R.id.wordPhonetic))
            .check(matches(withText(phonetic)))
            .check(matches(isDisplayed()))
    }

    fun checkPhoneticAudio() {
        onView(withId(dev.zotov.features.word_info.R.id.playPhoneticIcon))
            .check(matches(isDisplayed()))
    }

    fun checkWordMeaning(partOfSpeech: String) {
        onView(withText(partOfSpeech))
            .check(matches(withId(dev.zotov.features.word_info.R.id.speechOfText)))
            .check(matches(isDisplayed()))
    }
}