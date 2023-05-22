package com.bodakesatish.skeleton.ui.schemetransactions

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bodakesatish.skeleton.R
import com.bodakesatish.skeleton.databinding.FragmentInvestmentConfirmationBinding
import com.bodakesatish.skeleton.databinding.FragmentSchemeTransactionDetailBinding
import com.bodakesatish.skeleton.databinding.FragmentSchemeTransactionsBinding
import com.bodakesatish.skeleton.helper.CoroutineHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchemeTransactionDetailFragment : Fragment() {

    private val scope = CoroutineHelper().getScope()

    lateinit var viewContext: Context

    companion object {
        fun newInstance() = SchemeTransactionDetailFragment()
    }

    private lateinit var viewModel: SchemeTransactionDetailViewModel

    private var _binding: FragmentSchemeTransactionDetailBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSchemeTransactionDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SchemeTransactionDetailViewModel::class.java)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewContext = context
    }


}