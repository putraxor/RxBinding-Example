package com.github.putraxor.rxbindingexample

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by putraxor on 12/07/17.
 */
class HobbyViewModel : ViewModel() {
    val hobby = MutableLiveData<Hobby>()

    init {
        hobby.value = Hobby()
    }

    fun setName(name: String) {
        hobby.postValue(hobby.value?.copy(name = name))
    }

    fun setHobbyName(hobbies: String) {
        hobby.postValue(hobby.value?.copy(hobbies = hobbies))
    }
}