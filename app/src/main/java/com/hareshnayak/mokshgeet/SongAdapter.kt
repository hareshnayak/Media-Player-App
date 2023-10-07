package com.hareshnayak.mokshgeet

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hareshnayak.mokshgeet.databinding.SongViewBinding

class SongAdapter(private val context: Context, private val songList: ArrayList<String>): RecyclerView.Adapter<SongAdapter.SongHolder>() {

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
        holder.title.text = songList[position]
//        holder.artist.text = songList[position]
//        holder.image. = songList[position]
//        holder.duration.text = songList[position]
    }

    override fun getItemCount(): Int {
        return songList.size
    }
}