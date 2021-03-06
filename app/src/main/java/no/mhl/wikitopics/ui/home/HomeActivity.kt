package no.mhl.wikitopics.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import dagger.hilt.android.AndroidEntryPoint
import no.mhl.wikitopics.R
import no.mhl.wikitopics.api.common.Status
import no.mhl.wikitopics.databinding.ActivityHomeBinding
import no.mhl.wikitopics.util.checkedIndex
import no.mhl.wikitopics.util.measureTimeMillis

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
        setupViews()
    }

    private fun setupViews() {
        binding.getPageButton.setOnClickListener {
            if (binding.topicInputText.text.isNullOrEmpty()) {
                binding.topicInputLayout.error = getString(R.string.error_empty)
            } else {
                binding.topicInputLayout.error = ""
                onGetPageClicked()
            }
        }
    }
    // endregion

    // region View Interaction
    private fun onGetPageClicked() {
        val topic: String = binding.topicInputText.text.toString()
        model.pageForTopic(topic).observe(this, { resource ->
            when (resource.status) {
                Status.Success -> resource.data?.page?.text?.let { setResultsForText(topic, it.raw) }
                Status.ApiError -> showMessageForError(getString(R.string.error_no_results))
                Status.NetworkError -> showMessageForError(resource.throwable?.localizedMessage ?: "")
            }
        })
    }

    private fun setResultsForText(topic: String, text: String) {
        binding.results.topicText.text = topic
        binding.results.occurrencesText.text = measureTimeMillis({ time ->
            binding.results.timeText.text = getString(R.string.result_time_template, time)
        }) {
            model.occurrencesForSelection(binding.searchMethodRadioGroup.checkedIndex(), text, topic)
        }.toString()
    }

    private fun showMessageForError(message: String) {
        binding.topicInputLayout.error = message
    }
    // endregion

}