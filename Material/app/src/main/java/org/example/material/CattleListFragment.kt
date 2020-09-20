package org.example.material

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cattle_list_fragment.view.*

class CattleListFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Indicate that it has an options menu
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.cattle_list_fragment, container, false)

        // Set up the tool bar
        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)

        // Set up the Recycler
        val viewManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        val cattleList = createCattleList()
        val viewAdapter = CattleCardRecyclerViewAdapter(cattleList)
        view.findViewById<RecyclerView>(R.id.recycler_cattle_list).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        // Adjust spacing between cards
        val largePadding = resources.getDimensionPixelSize(R.dimen.cattle_grid_spacing)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.cattle_grid_spacing_small)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun createCattleList(): List<Cattle> {
        val cattleList = mutableListOf<Cattle>()
        for (i in 0..30) {
            val bovine = Cattle(caravan = "A" + i + "B" + i*2, weight = i * 200 + (0..45).random())
            cattleList.add(bovine)
        }

        return cattleList
    }
}