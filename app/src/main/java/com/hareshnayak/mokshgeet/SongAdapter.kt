package com.hareshnayak.mokshgeet

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hareshnayak.mokshgeet.databinding.SongViewBinding

class SongAdapter(private val context: Context, private val songList: ArrayList<Song>): RecyclerView.Adapter<SongAdapter.SongHolder>() {

    class SongHolder(binding: SongViewBinding) : RecyclerView.ViewHolder(binding.root){
        val title = binding.songName
        val artist = binding.artistName
        val image = binding.imageSV
        val duration = binding.songDuration
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongAdapter.SongHolder {
        return SongHolder(SongViewBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: SongAdapter.SongHolder, position: Int) {
        holder.title.text = songList[position].title
        holder.artist.text = songList[position].artist
//        holder.image. = songList[position]
        holder.duration.text = songList[position].duration.toString()
    }

    override fun getItemCount(): Int {
        return songList.size
    }
}