package com.bodakesatish.skeleton.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bodakesatish.skeleton.domain.model.request.SearchSchemeRequest
import com.bodakesatish.skeleton.domain.model.response.SearchMutualFund
import com.bodakesatish.skeleton.domain.usecases.SearchMutualFundUseCase
import com.bodakesatish.skeleton.R
import com.bodakesatish.skeleton.databinding.FragmentSearchSchemeBinding
import com.bodakesatish.skeleton.helper.CoroutineHelper
import com.bodakesatish.skeleton.ui.search.adapter.MutualFundAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

@AndroidEntryPoint
class SearchSchemeFragment : Fragment() {

    private val scope = CoroutineHelper().getScope()

    private val viewModel: SearchSchemeViewModel by viewModels()

    private lateinit var viewAdapter: MutualFundAdapter

    private var _binding: FragmentSearchSchemeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchSchemeBinding.inflate(inflater, container, false)
        viewAdapter = MutualFundAdapter()
        binding.rvMutualFund.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewAdapter.setOnClickListener { item: SearchMutualFund ->
            val bundle = Bundle()
            bundle.putString("SchemeCode", item.schemeCode)
            findNavController().navigate(R.id.scheme_detail_dest, bundle) // OR
        }

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchMutualFund(binding.etSearch.text.toString())
            }
            true
        }

        viewModel.reportsResponse.observe(viewLifecycleOwner) {
            viewAdapter.updateList(it)
            hideKeyboard(binding.etSearch)
        }

    }

    private fun searchMutualFund(search: String) {
        scope.launch {
            val requestModel = SearchMutualFundUseCase.Request()
            val request = SearchSchemeRequest()
            request.input = search
            requestModel.setRequestModel(request)
            viewModel.getReport(requestModel)
            hideKeyboard(binding.etSearch)
        }

    }

    open fun hideKeyboard(@NonNull v: View) {
        val inputManager = v.context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(v.windowToken, 0)
    }

}