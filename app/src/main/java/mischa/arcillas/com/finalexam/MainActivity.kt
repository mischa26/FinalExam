package mischa.arcillas.com.finalexam

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val url = "https://ws.audioscrobbler.com/2.0/?method=album.search&album="
    private val url1 = "&api_key=07b17a11c897d35ca6252225d00b68be&format=json"
    private val addAlbum = ArrayList<Albums>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar.visibility = View.VISIBLE

        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                AlbumSearch(query)
                return true
            }

        })
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
    private fun AlbumSearch(text: String) {
        for (i in 0..49) {
            doAsync {
                val tempText :String = text
                val resultJson = URL(url + tempText + url1).readText()
                val jsonObj = JSONObject(resultJson)

                val albumName = jsonObj.getJSONObject("results").getJSONObject("albummatches").getJSONArray("album")
                        .getJSONObject(i).getString("name")
                val artistName = jsonObj.getJSONObject("results").getJSONObject("albummatches").getJSONArray("album")
                        .getJSONObject(i).getString("artist")
                var imgName = ""

                imgName = if(jsonObj.getJSONObject("results").getJSONObject("albummatches").getJSONArray("album")
                                .getJSONObject(i).getJSONArray("image").getJSONObject(2).getString("#text") == ""){
                    jsonObj.getJSONObject("results").getJSONObject("albummatches").getJSONArray("album")
                            .getJSONObject(i).getJSONArray("image").getJSONObject(2).getString("")
                }else{
                    jsonObj.getJSONObject("results").getJSONObject("albummatches").getJSONArray("album")
                            .getJSONObject(i).getJSONArray("image").getJSONObject(2).getString("#text")
                }

                uiThread {
                    recyclerView.adapter = Adapter(this@MainActivity, addAlbum)
                    addAlbum.add(Albums(albumName,artistName, imgName))
                    progressBar.visibility = View.GONE
                }
            }
        }
    }
}