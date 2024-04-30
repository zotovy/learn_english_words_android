package dev.zotov.words_data.utils

import org.junit.Assert
import org.junit.Test

class ApiMappersTest {

    @Test
    fun shouldMapWordLight() {
        val wordDefinition = TestData.WordDefinitionDtos.wordLight.toWordDefinition()
        Assert.assertEquals(TestData.WordDefinitions.wordLight, wordDefinition)
    }

    @Test
    fun shouldMapWordWithAudioPhonetic() {
        val wordDefinition = TestData.WordDefinitionDtos.wordDog.toWordDefinition()
        Assert.assertEquals(TestData.WordDefinitions.wordDog, wordDefinition)
    }

    @Test
    fun shouldMapWordWithManyPhonetics() {
        val wordDefinition = TestData.WordDefinitionDtos.wordKotlin.toWordDefinition()
        Assert.assertEquals(TestData.WordDefinitions.wordKotlin, wordDefinition)
    }

    @Test
    fun shouldMapWordWithEmptyPhonetics() {
        val wordDefinition = TestData.WordDefinitionDtos.wordRun.toWordDefinition()
        Assert.assertEquals(TestData.WordDefinitions.wordRun, wordDefinition)
    }
}