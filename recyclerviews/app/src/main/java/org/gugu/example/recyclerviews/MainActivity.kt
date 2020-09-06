package org.gugu.example.recyclerviews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var viewAdapter : RecyclerView.Adapter<*>
    private lateinit var viewManager : RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val myDataset = createDataset()
        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(myDataset)

        recyclerView = findViewById<RecyclerView>(R.id.my_recycler).apply {
            layoutManager = viewManager

            adapter = viewAdapter
        }
    }

    private fun createDataset() : List<String> {
        val theList = mutableListOf<String>()
        theList.add(getString(R.string.firstVal))
        theList.add(getString(R.string.secondVal))
        theList.add(getString(R.string.thirdVal))
        theList.add(getString(R.string.fourthVal))
        theList.add(getString(R.string.fifthVal))
        theList.add(getString(R.string.sixthVal))
        theList.add(getString(R.string.seventhVal))
        theList.add(getString(R.string.eighthVal))
        theList.add(getString(R.string.ninthVal))

        return theList
    }
}