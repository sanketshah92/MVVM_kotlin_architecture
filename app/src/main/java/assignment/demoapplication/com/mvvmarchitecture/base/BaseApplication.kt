package assignment.demoapplication.com.mvvmarchitecture.base

import android.content.ContextWrapper
import assignment.demoapplication.com.mvvmarchitecture.di.ApplicationComponent
import assignment.demoapplication.com.mvvmarchitecture.di.DaggerApplicationComponent
import assignment.demoapplication.com.mvvmarchitecture.util.Prefs
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component: ApplicationComponent = DaggerApplicationComponent.builder().application(this).build()
        component.inject(this)
        return component

    }

    override fun onCreate() {
        super.onCreate()

        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }
}