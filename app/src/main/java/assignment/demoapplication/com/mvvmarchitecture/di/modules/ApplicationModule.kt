package assignment.demoapplication.com.mvvmarchitecture.di.modules

import dagger.Module

@Module(includes = [ViewModelModule::class, NetworkModule::class])
class ApplicationModule