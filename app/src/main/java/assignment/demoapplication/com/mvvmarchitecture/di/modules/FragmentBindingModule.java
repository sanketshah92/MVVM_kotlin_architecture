package assignment.demoapplication.com.mvvmarchitecture.di.modules;

import assignment.demoapplication.com.mvvmarchitecture.sample.view.fragments.SampleFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract SampleFragment provideSampleFragment();


}