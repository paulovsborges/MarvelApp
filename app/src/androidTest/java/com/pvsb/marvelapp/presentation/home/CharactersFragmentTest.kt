package com.pvsb.marvelapp.presentation.home

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pvsb.marvelapp.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class CharactersFragmentTest{

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp(){
        launchFragmentInHiltContainer<CharactersFragment>()
    }

    @Test
    fun should_show_characters_list_when_the_view_is_created(){

    }
}