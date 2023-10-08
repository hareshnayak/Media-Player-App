package com.hareshnayak.mokshgeet

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hareshnayak.mokshgeet.databinding.SongViewBinding

class SongAdapter(private val context: Context, private val songList: ArrayList<Song>): RecyclerView.Adapter<SongAdapter.SongHolder>() {

    class SongHolder(binding: SongViewBinding) : RecyclerView.ViewHolder(binding.root){
        val title = binding.songName
        val artist = binding.artistName
        val image = binding.imageSV
        val duration = binding.songDuration
        val root = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongAdapter.SongHolder {
        return SongHolder(SongViewBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: SongAdapter.SongHolder, position: Int) {
        holder.title.text = songList[position].title
        holder.artist.text = songList[position].artist
//        holder.image. = songList[position]
        holder.duration.text = formatDuration(songList[position].duration)
        Glide.with(context).load(songList[position].artUri).apply(RequestOptions().placeholder(R.drawable.music_icon_splashscreen)).centerCrop().into(holder.image)
        holder.root.setOnClickListener{
            val intent = Intent(context, PlayerActivity::class.java)
            ContextCompat.startActivity(context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return songList.size
    }
}