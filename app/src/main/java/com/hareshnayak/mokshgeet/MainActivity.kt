package com.hareshnayak.mokshgeet

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.hareshnayak.mokshgeet.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    // To bind together ActionBar and DrawerLayout
    private lateinit var toggle : ActionBarDrawerToggle
    private lateinit var songAdapter : SongAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeLayout()
        binding.shuffleBtn.setOnClickListener{
            val intent = Intent(this@MainActivity, PlayerActivity::class.java)
            startActivity(intent)
            Toast.makeText(this@MainActivity, "Shuffle Button Clicked", Toast.LENGTH_SHORT).show()
        }
        
        binding.favoritesBtn.setOnClickListener{
            startActivity(Intent(this@MainActivity, FavoriteActivity::class.java))
            Toast.makeText(this, "Favorites Button Clicked", Toast.LENGTH_SHORT).show()
        }

        binding.playlistBtn.setOnClickListener{
            startActivity(Intent(this@MainActivity, PlaylistActivity::class.java))
        }

        binding.navView.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.nav_feedback -> Toast.makeText(baseContext, "Feedback", Toast.LENGTH_SHORT).show()
                R.id.nav_settings -> Toast.makeText(baseContext, "Settings", Toast.LENGTH_SHORT).show()
                R.id.nav_about -> Toast.makeText(baseContext, "About", Toast.LENGTH_SHORT).show()
                R.id.nav_exit -> exitProcess(1)
            }
            true
        }
    }

    private fun requestRuntimePermission(){
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 13)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 13){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED )
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            else
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 13)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initializeLayout(){
        requestRuntimePermission()
        setTheme(R.style.coolBlueNav)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toggle = ActionBarDrawerToggle(this, binding.root, R.string.open, R.string.close)
        binding.root.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.songRecView.setHasFixedSize(true)

        val songsList = ArrayList<String>()
        songsList.add("First Song First Song First Song First Song First Song First Song First Song First Song")
        songsList.add("Second Song")
        songsList.add("Third Song")
        songsList.add("Fourth Song")
        songsList.add("Fifth Song")

        binding.songRecView.setItemViewCacheSize(10)
        binding.songRecView.layoutManager = LinearLayoutManager(this@MainActivity)
        songAdapter = SongAdapter(this@MainActivity, songsList)
        binding.songRecView.adapter = songAdapter
        binding.totalSongs.text = "Total Songs : "+songAdapter.itemCount
    }

    @SuppressLint("Range")
    private fun getAllSongs():ArrayList<Song>{
        val tempList = ArrayList<Song>()
        val selection = MediaStore.Audio.Media.IS_MUSIC + " !=0"
        val projection = arrayOf(MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE,
        MediaStore.Audio.Media.ALBUM, MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media.DATE_ADDED,MediaStore.Audio.Media.DATA)
        val cursor = this.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, selection, null, MediaStore.Audio.Media.DATE_ADDED + "DESC",  null)
        if(cursor!=null)
            if(cursor.moveToFirst())
                do{
                        val titleC = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                        val idC = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
                        val albumC = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM))
                        val artistC = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                        val pathC= cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                        val durationC = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))

                }while(cursor.moveToNext())
                cursor?.close()
        return tempList
    }
}