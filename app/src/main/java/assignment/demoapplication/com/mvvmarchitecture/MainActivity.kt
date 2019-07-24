package assignment.demoapplication.com.mvvmarchitecture

import android.os.Bundle
import assignment.demoapplication.com.mvvmarchitecture.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
    }
}
