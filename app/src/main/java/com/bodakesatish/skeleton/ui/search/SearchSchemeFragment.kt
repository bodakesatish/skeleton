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
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
import javax.inject.Inject

@AndroidEntryPoint
class SearchSchemeFragment : Fragment() {

//    @Inject
//    lateinit var summary : SchemeRepository

    private val scope = CoroutineHelper().getScope()

    lateinit var viewContext: Context

    companion object {
        fun newInstance() = SearchSchemeFragment()
    }

    private val viewModel: SearchSchemeViewModel by viewModels()

//    @Inject
//    lateinit var searchMutualFundUseCase: SearchMutualFundUseCase

    private lateinit var viewAdapter: MutualFundAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var _binding: FragmentSearchSchemeBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchSchemeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewAdapter = MutualFundAdapter()
        viewManager = LinearLayoutManager(this.context)
        viewAdapter.setOnClickListener { item : SearchMutualFund ->
            val bundle = Bundle()
            bundle.putString("SchemeCode",item.schemeCode)
            findNavController().navigate(R.id.scheme_detail_dest, bundle) // OR
        }
        binding.rvMutualFund.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewContext = context
    }

    private fun searchMutualFund(search: String) {
        scope.launch {
            try {
                val requestModel = SearchMutualFundUseCase.Request()
                val request = SearchSchemeRequest()
                request.input = search
                requestModel.setRequestModel(request)
                viewModel.getReport(requestModel)
//                val response = searchMutualFundUseCase.executeUseCase(requestModel)
//                scope.launch(Dispatchers.Main) {
//                    Toast.makeText(viewContext,""+response.getData()!!.size,Toast.LENGTH_SHORT).show()
//                    Log.i("responseSize", "->"+response.getData()!!.size)
//                    viewAdapter.updateList(response.getData()!!)
//                    hideKeyboard(binding.etSearch)
//                }
            }
            catch (e: Exception) {
                scope.launch(Dispatchers.Main) {
                }
            }
        }

    }

    open fun hideKeyboard(@NonNull v: View) {
        val inputManager = v.context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(v.windowToken, 0)
    }

}