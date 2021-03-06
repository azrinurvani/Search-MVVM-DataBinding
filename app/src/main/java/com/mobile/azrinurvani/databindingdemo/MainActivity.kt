package com.mobile.azrinurvani.databindingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.azrinurvani.databindingdemo.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val viewModel = makeApiCall()

        setupBinding(viewModel)
    }

    //TODO 11 - Create setupBinding() method for binding viewModel with Activity and layout
    fun setupBinding(viewModel: MainActivityViewModel) {

        val activityMainBinding : ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        activityMainBinding.setVariable(BR.viewModel,viewModel)
        activityMainBinding.executePendingBindings()

        //Design recyclerView
        recyclerView.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(this@MainActivity, VERTICAL)
            addItemDecoration(decoration)
        }

    }

    //TODO 12 - Create makeApiCall() method for setAdapter and getData From API
    fun makeApiCall(): MainActivityViewModel{
        val viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        viewModel.getRecyclerListDataObserver().observe(this, Observer<RecyclerList> {
            progressBar.visibility = GONE
            if (it!=null){
                //update the adapter
                viewModel.setAdapterData(it.items)
            }else{
                Toast.makeText(this@MainActivity,"Error in fetching data...",Toast.LENGTH_LONG).show()
            }
        })

        viewModel.makeApiCall("newyork")

        return viewModel
    }

}