package com.github.putraxor.rxbindingexample

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import com.jakewharton.rxbinding2.widget.RxTextView

class HobbyActivity : BaseActivity<HobbyViewModel>() {
    private val TAG: String = this::class.java.name
    override val vmClass = HobbyViewModel::class.java
    private val viewModelRx = HobbyViewModelRx()
    private val inputName by lazy { findViewById(R.id.input_name) as EditText }
    private val inputHobby by lazy { findViewById(R.id.input_hobby) as EditText }
    private val labelName by lazy { findViewById(R.id.label_name) as TextView }
    private val labelHobby by lazy { findViewById(R.id.label_hobby) as TextView }
    private val labelTrace by lazy { findViewById(R.id.label_trace) as TextView }

    /**
     * Inflate layout
     */
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_main)
    }

    /**
     * Observe data saat tampilan masuk state resume
     */
    override fun onResume() {
        super.onResume()
        performReactive()
    }

    /**
     * Observe perubahan di viewModel ke UI dan
     * Subscribe perubahan dari UI ke viewModel
     */
    private fun performReactive() {
        //subscribe data hobby dari viewModel
        viewModel.hobby.observe(this, Observer<Hobby> {
            it?.let {
                labelName.text = it.name
                labelHobby.text = it.hobbies
                labelTrace.text = it.toString()
            }
        })
        viewModelRx.hobbyRx.subscribe {
            Log.d(TAG, "Reactive Hobby from ViewModelRx $it")
        }.into(disposable)

        //observe UI change ke viewModel
        RxTextView.textChanges(inputName)
                .subscribe {
                    viewModel.setName(it.toString())
                    viewModelRx.setName(it.toString())
                }.into(disposable)
        RxTextView.textChanges(inputHobby)
                .subscribe {
                    viewModel.setHobbyName(it.toString())
                    viewModelRx.setHobyName(it.toString())
                }.into(disposable)
    }
}
