package com.bodakesatish.skeleton.ui.watchlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bodakesatish.skeleton.R
import com.satish.mutualfundmanager.ui.watchlist.WatchListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchListFragment : Fragment() {

//    @Inject
//    lateinit var summary : SchemeRepository
//
//    private val scope = CoroutineHelper().getScope()
//
//    lateinit var viewContext: Context
//
//    companion object {
//        fun newInstance() = WatchListFragment()
//    }
//
    private lateinit var viewModel: WatchListViewModel
//
//    @Inject
//    lateinit var searchMutualFundUseCase: SearchMutualFundUseCase
//
//    private lateinit var viewAdapter: MutualFundAdapter
//    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_watch_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WatchListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewAdapter = MutualFundAdapter()
//        viewManager = LinearLayoutManager(this.context)
//        rvMutualFund.apply {
//            setHasFixedSize(true)
//            layoutManager = viewManager
//            adapter = viewAdapter
//        }
//
//        et_search.setOnEditorActionListener { _, actionId, _ ->
//            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                searchMutualFund(et_search.text.toString())
//            }
//            true
//        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        viewContext = context
    }

    fun searchMutualFund(search: String) {
//        scope.launch {
//            try {
//                val requestModel = SearchMutualFundUseCase.Request()
//                val request = SearchSchemeRequest()
//                request.input = search
//                requestModel.setRequestModel(request)
//                val response = searchMutualFundUseCase.executeUseCase(requestModel)
//                scope.launch(Dispatchers.Main) {
//                    Toast.makeText(viewContext,""+response.getData()!!.size,Toast.LENGTH_SHORT).show()
//                    Log.i("responseSize", "->"+response.getData()!!.size)
//                    viewAdapter.updateList(response.getData()!!)
//                }
//            }
//            catch (e: Exception) {
//                scope.launch(Dispatchers.Main) {
//                }
//            }
//        }

    }

}