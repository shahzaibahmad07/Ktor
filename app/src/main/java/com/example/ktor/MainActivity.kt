package com.example.ktor

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.ktor.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var datastoreManager: DatastoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //! datastore manager:
        datastoreManager = DatastoreManager(this)

        //! save button
        binding.btnSaveKey.setOnClickListener {
            val text = binding.inputText.text.toString()
            if (text.isNotEmpty()) {
                val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
                with(sharedPreferences.edit()) {
                    putString(getString(R.string.test_value), text)
                    apply().also {
                        Toast.makeText(this@MainActivity, "Value Saved", Toast.LENGTH_SHORT).show()
                        binding.inputText.text.clear()
                    }
                }
                lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        datastoreManager.addValue(text).also {
                            Toast.makeText(
                                this@MainActivity,
                                "Datastore value also saved",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Enter Value First!", Toast.LENGTH_SHORT).show()
            }
        }

        //! show data from datastore:
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                datastoreManager.getValue.collect {
                    if(it.isNotEmpty()) {
                        Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this@MainActivity, "No Value found in DataStore", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        //! show button
        binding.btnShow.setOnClickListener {
            val text =
                getPreferences(Context.MODE_PRIVATE).getString(getString(R.string.test_value), null)
            if (text != null) {
                binding.tvShowSaved.text = text
            } else {
                Toast.makeText(this, "No Text Found", Toast.LENGTH_SHORT).show()
            }
        }

        //! btn Next Activity:
        binding.btnNext.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

    }
}