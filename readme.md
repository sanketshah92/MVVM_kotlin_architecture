# MVVMArchitecture
![alternativetext](/architecturediagram/MVVMArchitecure.jpg)


## View
1.  Purpose of the view to observe the proccessed data, which is exposed by corresponding Viewmodel
2.  View should be injecting ViewmodelProvider Factory, to get instance of desired viewmodel
3.  Your view is responsible for lifecycle ownership
4.  Your activity or fragment should be derived from Baseactivity or Basefragment

**SampleFragment**


    var binding: FragmentSampleBinding? = null
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val sampleViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SampleViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sample,
            container,
            false
        )
        return binding?.let { fragmentSampleBinding -> binding!!.root }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sampleViewModel.fetchSampleData()
        sampleViewModel.processedData.observe(
            this,
            Observer {
                binding!!.rvSample.apply {
                    adapter = SampleAdapter(it)
                }
                Log.e("Observed Result", ":" + it?.let { it -> it[0].getId() })
            })
    }

    override fun onDetach() {
        sampleViewModel.tearDown()
        super.onDetach()
    }

    @BindingAdapter("imageUrl")
    fun loadImage(imageView: ImageView, url: String?) {
        val requestOptions = RequestOptions()
        if (url != null && (url.contains("https://") || url.contains("http://"))) {
            Glide.with(imageView.getContext()).setDefaultRequestOptions(requestOptions).load(url).into(imageView)
        }
    }

## View Model
1. Viewmodels are created with Inject Constructors to provide dependencies and fullfil requirement of repository
2. Viewmodel should expose the data observers, which will work as a live bridge between view and viewmodel
3. Viewmodel should dispose the exposed observers whenever appropriate lifecycle owner goes remove from the memory.

```
open class SampleViewModel @Inject constructor(private val repository: SampleRepository) : ViewModel() {
    private var fetchedData: MutableLiveData<List<SampleData>> = MutableLiveData()

    var processedData: MutableLiveData<List<SampleData>> = MutableLiveData()

    fun fetchSampleData() {
        repository.getCats(fetchedData)
        fetchedData.observeForever(Observer {
            Log.e("Sample Data Processing", "::->" + it.size)
            processedData.postValue(it)
        })
    }

     fun tearDown() {
        repository.flush()
    }

}
```

## Model
1. Models are nothing but data containers, they are built as silly as possible because you need to treat it as a transportor. 
2. Models are the container which transport the data between repository to viewmodel and viewmodel to view.

## Repository
1. Creational of repository should be derived from BaseRepository as we have injected our viewmodels with Factory and factory has dependency of BaseRepository to provide universal injection
2. Our repository have capability to decide data flow, either data needed to be fetched from server or local database
3. Most important part of repository is inject constructor, because we will be needing API Service, Database handlers to process further, currently we are supporting only Network calls. 
4. As we are using RXJava our repository should maintain CompositeDisposible or Disposibles, whenever our lifecycle owner dies it should flush our RXjava observers too.

````
class SampleRepository @Inject constructor(val service: APIinterface) : BaseRepository {
    internal var disposable: Disposable? = null
    fun getCats(requestedData: MutableLiveData<List<SampleData>>) {
        service.getCats("20")
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<SampleData>> {
                override fun onNext(t: List<SampleData>) {
                    requestedData.postValue(t)
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }


                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {

                }
            })
    }

    fun flush() {
        if (!disposable!!.isDisposed) {
            disposable!!.dispose()
            disposable = null
        }

    }
}
````
## DI(Dependency Injection By Dagger2)
**Basics Of Latest Dagger**
-> @Binds
This annotation provides a replacement of @Provides methods which simply return the injected parameter.

    @Provides methods are instance methods and they need an instance of our module in order to be invoked. 
    If our Module is abstract and contains @Binds methods, 
    dagger will not instantiate our module and instead directly use the Provider of our injected parameter 
    
-> @ContributesAndroidInjector
    Dagger Android introduced an annotation which can reduce the Component Binds IntoSet Subcomponent ActivityKey FragmentKey etc. boilerplate for you.
    
![alternativetext](/DaggerTree2.png)

**We have Six Modules in a base, which are mentioned below:**

1. **ActivityBindingModule**
    It will create sub-component of activity to fragment
    For reference you can read more 'https://medium.com/tompee/dagger-2-scopes-and-subcomponents-d54d58511781'
2. **ApplicationModule**
    Application module is nothing but it includes Network Module and ViewModel Module to enhance better abstraction.
3. **ContextModule**
    Context Module do work to provide application context without creating it again.
4. **FragmentBindingModule**
    FragmentBinding Modules create subcomponents of each and every fragments of the project, so we can have injection of Viewmodel Factories.
    So in this architecture we need to log each and every fragments here with below syntax:
    
    ```
          @ContributesAndroidInjector
          abstract SampleFragment provideSampleFragment();
    ```
5. **NetworkModule** 
    1.It contains retrofit service providers with dependency of retrofit instance, which are injected at service layer.
    2.Benifit of this injection is to provide single instance of retrofit and APIinterface(Service) throught the application

````
    @Provides
    @Singleton
    fun provideAPIinterface(@NonNull retrofit: Retrofit) : APIinterface{
        return retrofit.create(APIinterface::class.java)
    }
````

6. **ViewModelModule**
    1. As we have inject constructors in our Viewmodel, so we need to map them with repository.
    2. It is mandatory to define each and every Viewmodel here as below snippet
    
    ```   
          @Binds
          @IntoMap
          @ViewModelKey(SampleViewModel::class)
          abstract fun bindListViewModel(listViewModel: SampleViewModel): ViewModel
    ```  
