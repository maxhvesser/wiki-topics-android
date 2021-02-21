package no.mhl.wikitopics.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import dagger.hilt.android.AndroidEntryPoint
import no.mhl.wikitopics.R
import no.mhl.wikitopics.databinding.ActivityHomeBinding

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    // region Bindings
    private lateinit var model: HomeViewModel
    private lateinit var binding: ActivityHomeBinding
    // endregion

    // region Initialization
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        model = ViewModelProvider(this).get()

        setContentView(binding.root)
    }
    // endregion

}