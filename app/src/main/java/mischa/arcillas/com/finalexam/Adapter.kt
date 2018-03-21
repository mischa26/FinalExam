package mischa.arcillas.com.finalexam

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.song_layout.view.*

/**
 * Created by Mischa on 21/03/2018.
 */
class Adapter (var context: Context, val albumList: ArrayList<Albums>): RecyclerView.Adapter<AlbumViewHolder> (){

    override fun getItemCount(): Int {
        return albumList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.song_layout, parent,false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder?, position: Int) {
        holder?.view?.txtSong?.text = albumList[position].name
        holder?.view?.txtArtist?.text = albumList[position].artist
        val imgHolder = holder?.view?.imgAlbum
        Picasso.with(holder?.view?.context).load(albumList[position].text).into(imgHolder)
    }
}
class AlbumViewHolder (var view: View): RecyclerView.ViewHolder(view){

}