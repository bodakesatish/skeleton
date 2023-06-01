package com.bodakesatish.skeleton.ui.schemedetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bodakesatish.skeleton.domain.model.request.SchemeMetaDataRequest
import com.bodakesatish.skeleton.domain.model.response.SchemeMetaResponse
import com.bodakesatish.skeleton.domain.model.response.UserScheme
import com.bodakesatish.skeleton.domain.usecases.SchemeMetaDataUseCase
import com.bodakesatish.skeleton.R
import com.bodakesatish.skeleton.databinding.FragmentSchemeDetailBinding
import com.bodakesatish.skeleton.helper.CoroutineHelper
import com.bodakesatish.skeleton.helper.DateHelper
import com.bodakesatish.skeleton.ui.base.BaseFragment
import com.bodakesatish.skeleton.ui.schemedetail.adapter.SchemeNavAdapter
import com.bodakesatish.skeleton.ui.schemedetail.SchemeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class SchemeDetailFragment : BaseFragment() {

    private val scope = CoroutineHelper().getScope()

    lateinit var viewContext: Context

//    @Inject
//    lateinit var schemeMetaDataUseCase: SchemeMetaDataUseCase

    private lateinit var viewAdapter: SchemeNavAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

//    @Inject
//    lateinit var schemeRepository : SchemeRepository

    var schemeDetail : SchemeMetaResponse? = null

    private var _binding: FragmentSchemeDetailBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = SchemeDetailFragment()
    }

    private lateinit var viewModel: SchemeDetailViewModel
    private var schemeCode: Int = 0
    private var schemeName: String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSchemeDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SchemeDetailViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            schemeCode = it.getString("SchemeCode", "0").toInt()
            getSchemeMetaData(schemeCode)
        }
        viewAdapter = SchemeNavAdapter()
        viewManager = LinearLayoutManager(this.context)
        binding.rvSchemeNav.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        binding.schemeTotalInvestment.schemeTotalInvestment.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("SchemeCode",schemeCode)
            bundle.putString("SchemeName", schemeName)
            findNavController().navigate(R.id.scheme_transactions, bundle) // OR
        }

        binding.btnAction.setOnClickListener {
            if(it.getTag() == 1) {
                schemeDetail?.let {
                    scope.launch {
                        val userScheme = UserScheme(
                            0, it.meta.scheme_code, it.meta.scheme_name, 0.0,
                            0.0, 0, 0.0, 0.0, DateHelper.getCurrentDate()
                        )
//                        schemeRepository.addSchemeToUserPortfolio(userScheme)
                        scope.launch(Dispatchers.Main) {
                            binding.btnAction.text = "Remove from Portfolio"
                            binding.btnAction.tag = 0
                        }
                    }
                }
            } else {
                schemeDetail?.let {
                    scope.launch {
//                        schemeRepository.removeSchemeToUserPortfolio(it.meta.scheme_code)
                        scope.launch(Dispatchers.Main) {
                            binding.btnAction.text = "Add to Portfolio"
                            binding.btnAction.tag = 1
                        }
                    }
                }
            }
        }
    }


    private fun getSchemeMetaData(schemeCode: Int) {
        scope.launch {
            try {
                val requestModel = SchemeMetaDataUseCase.Request()
                val request = SchemeMetaDataRequest()
                request.schemeCode = schemeCode
                request.schemeLastSyncDate = DateHelper.getCurrentDate()
                requestModel.setRequestModel(request)
//                val response = schemeMetaDataUseCase.executeUseCase(requestModel)
//                val userScheme: UserScheme = schemeRepository.getUserScheme(schemeCode)
                scope.launch(Dispatchers.Main) {
                    binding.content.visibility = View.VISIBLE
//                    schemeDetail = response.getData()
                    schemeDetail?.let {
                        viewAdapter.updateList(it.data)
                        binding?.run {
                            tvFundHouse.text = it.meta.fund_house
                            tvSchemeType.text = it.meta.scheme_type
                            tvSchemeCategory.text = it.meta.scheme_category
                            tvSchemeCode.text = it.meta.scheme_code.toString()
                            tvSchemeName.text = it.meta.scheme_name
                            schemeName = it.meta.scheme_name
                            btnAction.visibility = View.VISIBLE
//                            if (userScheme.schemeCode == schemeCode) {
//                                btnAction.text = "Remove from Portfolio"
//                                btnAction.tag = 0
//                                schemeTotalInvestment.schemeTotalInvestment.visibility = View.VISIBLE
//                                schemeTotalInvestment.tvSchemeName.text = it.meta.scheme_name
//                                schemeTotalInvestment.tvInvestment.text =
//                                    userScheme.investment.toString()
//                                schemeTotalInvestment.tvCurrentValue.text =
//                                    userScheme.currentValue.toString()
//                                schemeTotalInvestment.tvCurrentReturns.text =
//                                    userScheme.returns.toString()
//                                schemeTotalInvestment.ivLaunch.visibility = View.VISIBLE
//                            } else {
//                                btnAction.text = "Add to Portfolio"
//                                btnAction.tag = 1
//                                schemeTotalInvestment.schemeTotalInvestment.visibility = View.GONE
//                            }
                        }
                    }
                }
            } catch (e: Exception) {
                scope.launch(Dispatchers.Main) {
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}