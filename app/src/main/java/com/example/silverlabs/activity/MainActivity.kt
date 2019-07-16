package com.example.silverlabs.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.example.silverlabs.adapter.RecyclerAdapter
import com.example.silverlabs.datamodel.Celebrity
import kotlinx.android.synthetic.main.activity_main.*
import com.example.silverlabs.viewmodel.CelebrityViewModel


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter
    lateinit var celebrityViewModel: CelebrityViewModel
    lateinit var celebrity: List<Pair<String,Celebrity>>
    var sortBy:String = AGE


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.silverlabs.R.layout.activity_main)
        setSupportActionBar(toolbar)
        recyclerView = findViewById(com.example.silverlabs.R.id.recyclerview)
        recyclerAdapter = RecyclerAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter

        celebrityViewModel = ViewModelProviders.of(this,null).get(CelebrityViewModel::class.java)

        celebrityViewModel.getClelebrities().observe(this, Observer { data ->
            celebrity = data!!
            when(sortBy){
                AGE -> {
                    celebrity.sortedWith(compareBy({ it.second.age }))
                }
                POPULARITY -> {
                    celebrity.sortedWith(compareBy({ it.second.popularity }))
                }
                HEIGHT -> {
                    celebrity.sortedWith(compareBy({ it.second.height }))
                }
            }
            recyclerAdapter.setCelebrityListItems(celebrity)
        })
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(com.example.silverlabs.R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            com.example.silverlabs.R.id.action_age -> {
                sortBy = AGE
               celebrityViewModel.getClelebrities()
                true
            }
            com.example.silverlabs.R.id.action_popularity -> {
                sortBy = POPULARITY
                celebrityViewModel.getClelebrities()
                true

            }
            com.example.silverlabs.R.id.action_height -> {
                sortBy = HEIGHT
                celebrityViewModel.getClelebrities()
                true

            }
            else -> {
                false
            }

        }
    }

    companion object {
        private const val AGE = "age"
        private const val HEIGHT = "height"
        private const val POPULARITY = "popularity"

    }
}
