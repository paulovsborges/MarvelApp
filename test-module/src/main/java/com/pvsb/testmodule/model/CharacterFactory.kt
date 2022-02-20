package com.pvsb.testmodule.model

import com.pvsb.core.domain.model.Character

class CharacterFactory {

    fun create(hero: Hero)  = when(hero)  {
        Hero.IronMan ->{
            Character("iron man", "google.com/standard_amazing.jpg")
        }
        Hero.ABomb ->{
            Character("A-Bomb", "google.com/standard_amazing.jpg")
        }
    }

    sealed class Hero{
        object IronMan: Hero()
        object ABomb: Hero()
    }
}