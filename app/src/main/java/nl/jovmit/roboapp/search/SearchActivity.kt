package nl.jovmit.roboapp.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_search.*
import nl.jovmit.roboapp.R

class SearchActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_search)
    setupSearchView()
    observeSearchStates()
  }

  private fun setupSearchView() {
    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String?): Boolean {
        //TODO submit query to searcher
        return true
      }

      override fun onQueryTextChange(newText: String?): Boolean {
        return false
      }
    })
  }

  private fun observeSearchStates() {
    // TODO setup search state observer
  }
}