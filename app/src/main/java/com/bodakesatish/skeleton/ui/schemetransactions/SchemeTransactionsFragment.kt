package com.bodakesatish.skeleton.ui.schemetransactions

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bodakesatish.skeleton.R
import com.bodakesatish.skeleton.databinding.FragmentSchemeTransactionsBinding
import com.bodakesatish.skeleton.helper.CoroutineHelper
import com.bodakesatish.skeleton.ui.schemedetail.adapter.SchemeTransactionAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SchemeTransactionsFragment : Fragment() {

//    @Inject
//    lateinit var summary: SchemeRepository

    private val scope = CoroutineHelper().getScope()

    lateinit var viewContext: Context

//    @Inject
//    lateinit var schemeRepository: SchemeRepository

    companion object {
        fun newInstance() = SchemeTransactionsFragment()
    }

    private lateinit var viewModel: SchemeTransactionsViewModel

//    @Inject
//    lateinit var searchMutualFundUseCase: SearchMutualFundUseCase

    private lateinit var viewAdapter: SchemeTransactionAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var schemeCode: Int = 0
    private var schemeName: String = ""

    private var _binding: FragmentSchemeTransactionsBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSchemeTransactionsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SchemeTransactionsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewAdapter = SchemeTransactionAdapter()
        viewManager = LinearLayoutManager(this.context)
        arguments?.let {
            schemeCode = it.getInt("SchemeCode", 0)
            schemeName = it.getString("SchemeName", "")
            getSchemeMetaData(schemeCode)
        }
        binding.rvMutualFund.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        binding.btnAction.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("SchemeCode",schemeCode)
            bundle.putString("SchemeName", schemeName)
            findNavController().navigate(R.id.investment_confirmation, bundle) // OR
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewContext = context
    }

    private fun getSchemeMetaData(schemeCode: Int) {
        scope.launch {
            try {
//                val userSchemeTransactionList = schemeRepository.getUserSchemeTransactionList(schemeCode)//schemeCode
                scope.launch(Dispatchers.Main) {
//                    viewAdapter.updateList(userSchemeTransactionList)

                }

            } catch (e: Exception) {
                scope.launch(Dispatchers.Main) {
                }
            }
        }
    }

}