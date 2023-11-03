package com.kia.muslimmediaapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kia.muslimmediaapp.NewsViewModel
import com.kia.muslimmediaapp.adapter.NewsAdapter
import com.kia.muslimmediaapp.databinding.FragmentAboutAlQuranBinding


/**
 * A simple [Fragment] subclass.
 * Use the [AboutAlQuranFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AboutAlQuranFragment : Fragment() {

    private var _binding : FragmentAboutAlQuranBinding? = null
    //why we use val in here? to set fix values
    private val binding get() = _binding as FragmentAboutAlQuranBinding

    private var _quranNewsViewModel : NewsViewModel? = null
    private val quranNewsViewModel get() = _quranNewsViewModel as NewsViewModel

    //on fragment we use oncreate View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutAlQuranBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadingView.root.visibility = View.VISIBLE
        _quranNewsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        quranNewsViewModel.aboutAlQuranNews()
        quranNewsViewModel.aboutAlQuranNews.observe(viewLifecycleOwner) {
            val mAdapter = NewsAdapter()
            mAdapter.setData(it.articles)
            Log.i(
                "AboutAlQuranFragment",
                "onViewCreated: ${it.articles}"
            )
            binding.rvAlQuran.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(view.context)
            }
            binding.loadingView.root.visibility = View.GONE
        }
    }
}