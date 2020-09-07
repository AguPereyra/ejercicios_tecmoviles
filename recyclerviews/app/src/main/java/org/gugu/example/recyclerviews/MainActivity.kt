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

        recyclerView = findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = viewManager

            adapter = viewAdapter
        }
    }

    private fun createDataset() : List<Vacas> {
        val theList = mutableListOf<Vacas>()

        // Fill with data
        for (i in 0..30){
            val vaca = Vacas(caravana = "A" + i, peso = i * 100)
            theList.add(vaca)
        }
        return theList
    }
}