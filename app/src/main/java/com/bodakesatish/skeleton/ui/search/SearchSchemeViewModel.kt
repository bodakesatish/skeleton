package com.bodakesatish.skeleton.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bodakesatish.skeleton.domain.model.response.ResponseCode
import com.bodakesatish.skeleton.domain.model.response.SearchMutualFund
import com.bodakesatish.skeleton.domain.usecases.SearchMutualFundUseCase
import com.bodakesatish.skeleton.helper.CoroutineHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchSchemeViewModel @Inject constructor(
    private val searchMutualFundUseCase: SearchMutualFundUseCase,
) : ViewModel() {

    init {
        Log.i("SearchSchemeViewModel", "SearchSchemeViewModel Init")
    }

    private val scope = CoroutineHelper().getScope()
    val reportsResponse = MutableLiveData<List<SearchMutualFund>>()

    fun getReport(requestModel: SearchMutualFundUseCase.Request) {
        Log.i("In DashboardViewModel", "getReport")
        scope.launch {
            val response = searchMutualFundUseCase.executeUseCase(requestModel)
            scope.launch(Dispatchers.Main) {
                when (response.getResponseCode()) {
                    is ResponseCode.Success, ResponseCode.Empty -> {
                        reportsResponse.postValue(response.getData()!!)
                        Log.i("In DashboardViewModel", "getReport Success")
                    }
                    is ResponseCode.Network -> {
                        if(response.getData()!!.isNotEmpty()) {
                            reportsResponse.postValue(response.getData()!!)
                        }
                        Log.i("In DashboardViewModel", "getReport Success")
                    }
                    else -> {
                        Log.i("In DashboardViewModel", "getReport Else")
                    }
                }
            }
        }
    }
}