package com.pvsb.testmodule.model

import com.pvsb.core.domain.model.Character

class CharacterFactory {

    fun create(hero: Hero)  = when(hero)  {
        Hero.IronMan ->{
            Character("iron man", "https://google.com")
        }
        Hero.ABomb ->{
            Character("A-Bomb", "https://google.com")
        }
    }

    sealed class Hero{
        object IronMan: Hero()
        object ABomb: Hero()
    }
}