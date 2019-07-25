# MVVMArchitecture
![alternativetext](/architecturediagram/MVVMArchitecure.jpg)


# View
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

# View Model
1. Viewmodels are created with Inject Constructors to provide dependencies and fullfil requirement of repository
2. 
# Model
# Repository
# DI(Dependency Injection By Dagger2)