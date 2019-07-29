package assignment.demoapplication.com.mvvmarchitecture.base

import assignment.demoapplication.com.mvvmarchitecture.di.ApplicationComponent
import assignment.demoapplication.com.mvvmarchitecture.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component: ApplicationComponent = DaggerApplicationComponent.builder().application(this).build()
        component.inject(this)
        return component

    }

}