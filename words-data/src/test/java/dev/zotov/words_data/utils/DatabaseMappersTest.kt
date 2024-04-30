package dev.zotov.words_data.utils

import org.junit.Assert
import org.junit.Test

class DatabaseMappersTest {

    @Test
    fun shouldMapWordLight() {
        val word = TestData.WordDBOs.wordLight.toWord()
        Assert.assertEquals(word, TestData.Words.wordLight)
    }

    @Test
    fun shouldMapWordHeavy() {
        val word = TestData.WordDBOs.wordHeavy.toWord()
        Assert.assertEquals(word, TestData.Words.wordHeavy)
    }

    @Test
    fun shouldMapWordDefinitionLight() {
        val word = TestData.WordDefinitionDBOS.wordLight.toWordDefinition()
        Assert.assertEquals(word, TestData.WordDefinitions.wordLight)
    }

    @Test
    fun shouldMapWordDefinitionDog() {
        val word = TestData.WordDefinitionDBOS.wordDog.toWordDefinition()
        Assert.assertEquals(word, TestData.WordDefinitions.wordDog)
    }

    @Test
    fun shouldMapWordDefinitionRun() {
        val word = TestData.WordDefinitionDBOS.wordRun.toWordDefinition()
        Assert.assertEquals(word, TestData.WordDefinitions.wordRun)
    }
}