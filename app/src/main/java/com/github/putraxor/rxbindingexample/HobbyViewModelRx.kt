package com.github.putraxor.rxbindingexample

import io.reactivex.subjects.BehaviorSubject

/**
 * Created by putraxor on 12/07/17.
 */
class HobbyViewModelRx {
    val hobbyRx: BehaviorSubject<Hobby> = BehaviorSubject.create()

    init {
        hobbyRx.onNext(Hobby())
    }

    fun getHobby(): Hobby = hobbyRx.value

    fun setName(name: String) {
        hobbyRx.onNext(getHobby().copy(name = name))
    }

    fun setHobyName(hobbyName: String) {
        hobbyRx.onNext(getHobby().copy(hobbies = hobbyName))
    }
}