package com.github.putraxor.rxbindingexample

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by putraxor on 12/07/17.
 */
@Suppress("LeakingThis")
abstract class BaseActivity<T : ViewModel> : AppCompatActivity(), LifecycleRegistryOwner {

    abstract val vmClass: Class<T>
    protected lateinit var viewModel: T
    protected val disposable = CompositeDisposable()
    private val registry = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry = registry

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        viewModel = ViewModelProviders.of(this).get(vmClass)
    }

    override fun onPause() {
        super.onPause()
        disposable.dispose()
    }

    fun Disposable.into(compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(this)
    }
}