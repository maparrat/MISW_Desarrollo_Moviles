package com.example.vinilos.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R

import com.example.vinilos.databinding.ArtistaFragmentBinding

import com.example.vinilos.model.MusicianModel

import com.example.vinilos.view.adapters.MusiciansAdapter

import com.example.vinilos.viewmodels.MusicianViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@Suppress("DEPRECATION")
class ArtistaFragment : Fragment() {
    private var _binding: ArtistaFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MusicianViewModel
    private var viewModelAdapter: MusiciansAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ArtistaFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = MusiciansAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.artistaRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
        // Se define el título del action bar
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.artista_list)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.artista_list)
        viewModel = ViewModelProvider(this, MusicianViewModel.Factory(activity.application)).get(MusicianViewModel::class.java)
        viewModel.musicians.observe(viewLifecycleOwner, Observer<List<MusicianModel>> {
            it.apply {
                viewModelAdapter!!.musicians = this
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}