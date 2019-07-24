package assignment.demoapplication.com.mvvmarchitecture.di.modules

import assignment.demoapplication.com.mvvmarchitecture.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
open abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [FragmentBindingModule::class])
    abstract fun binding(): MainActivity
}