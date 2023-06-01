package com.bodakesatish.skeleton.ui.user

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
import com.bodakesatish.skeleton.domain.repository.SchemeRepository
import com.bodakesatish.skeleton.R
import com.bodakesatish.skeleton.databinding.FragmentInvestmentConfirmationBinding
import com.bodakesatish.skeleton.databinding.FragmentUserSchemeListBinding
import com.bodakesatish.skeleton.helper.CoroutineHelper
import com.bodakesatish.skeleton.ui.user.adapter.UserSchemesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class UserSchemeListFragment : Fragment() {

    private val scope = CoroutineHelper().getScope()

    lateinit var viewContext: Context

    companion object {
        fun newInstance() = UserSchemeListFragment()
    }

    private lateinit var viewModel: UserSchemeListViewModel


    private lateinit var viewAdapter: UserSchemesAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager


//    @Inject
//    lateinit var schemeRepository : SchemeRepository


    private var _binding: FragmentUserSchemeListBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserSchemeListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserSchemeListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewAdapter = UserSchemesAdapter()
        viewManager = LinearLayoutManager(this.context)
        binding.rvMutualFund.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        showUserSchemes()
        viewAdapter.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("SchemeCode",it.schemeCode.toString())
            findNavController().navigate(R.id.scheme_detail_dest, bundle) // OR
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewContext = context
    }

    fun showUserSchemes() {
//        scope.launch {
//            try {
//                val response = schemeRepository.getUserSchemes()
//                scope.launch(Dispatchers.Main) {
//                    viewAdapter.updateList(response)
//                }
//            }
//            catch (e: Exception) {
//                scope.launch(Dispatchers.Main) {
//                }
//            }
//        }

    }

}