package com.bodakesatish.skeleton.ui.schemetransactions

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.bodakesatish.skeleton.domain.model.request.SchemeMetaDataRequest
import com.bodakesatish.skeleton.domain.model.response.SchemeMetaResponse
import com.bodakesatish.skeleton.domain.model.response.UserSchemeTransaction
import com.bodakesatish.skeleton.domain.usecases.SchemeMetaDataUseCase
import com.bodakesatish.skeleton.helper.CoroutineHelper
import com.bodakesatish.skeleton.R
import com.bodakesatish.skeleton.databinding.FragmentInvestmentConfirmationBinding
import com.bodakesatish.skeleton.databinding.FragmentSchemeTransactionsBinding
import com.bodakesatish.skeleton.helper.DateHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class InvestmentConfirmationFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private val scope = CoroutineHelper().getScope()

    lateinit var viewContext: Context

    private lateinit var viewModel: InvestmentConfirmationViewModel

    private var schemeCode: Int = 0
    private var schemeName: String = ""

//    @Inject
//    lateinit var schemeRepository : SchemeRepository

    var schemeDetail : SchemeMetaResponse? = null


//    @Inject
//    lateinit var schemeMetaDataUseCase: SchemeMetaDataUseCase

    companion object {
        fun newInstance() = InvestmentConfirmationFragment()
    }


    private var _binding: FragmentInvestmentConfirmationBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInvestmentConfirmationBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(InvestmentConfirmationViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            schemeCode = it.getInt("SchemeCode", 0)
            schemeName = it.getString("SchemeName", "")
        }

        binding.btnAction.setOnClickListener {
            insertUserSchemeTransaction(schemeCode)
        }
        binding.tvDate.setOnClickListener {
            showDateSelectionDialog()
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewContext = context
    }

    private fun showDateSelectionDialog() {
        val calendar = Calendar.getInstance()
        val dialog = DatePickerDialog(
            viewContext, this,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        dialog.datePicker.maxDate = Calendar.getInstance().timeInMillis
        dialog.show()
    }

    private fun insertUserSchemeTransaction(schemeCode: Int) {
        val date = binding.tvDate.text.toString()
        scope.launch {
            try {
                val requestModel = SchemeMetaDataUseCase.Request()
                val request = SchemeMetaDataRequest()
                request.schemeCode = schemeCode
                request.schemeLastSyncDate = DateHelper.getCurrentDate()
                requestModel.setRequestModel(request)
//                val response = schemeMetaDataUseCase.executeUseCase(requestModel)
                scope.launch(Dispatchers.Main) {
//                    schemeDetail = response.getData()
                    schemeDetail?.let {
                        val nav = it.data.get(0).nav
                        val amount = binding.etAmount.getText().toString()
                        val units = (amount.toDouble()/nav.toDouble())
                        val transaction = UserSchemeTransaction(0,schemeCode,amount,units.toString(),nav,date)
//                        schemeRepository.insertUserSchemeTransaction(transaction)
                        val bundle = Bundle()
                        bundle.putInt("SchemeCode",schemeCode)
                        bundle.putString("SchemeName", schemeName)
                        findNavController().navigate(R.id.scheme_transactions, bundle) // OR
                    }
                }
            } catch (e: Exception) {
                scope.launch(Dispatchers.Main) {
                }
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        binding.tvDate.text = DateHelper.getDateAsStringFromLong(calendar.timeInMillis, "MMM dd, yyyy")
    }


}