package dsm.android.v3.ui.applyMusic

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import dsm.android.v3.R
import dsm.android.v3.adapter.ApplyMusicAdapter
import dsm.android.v3.databinding.FragmentApplyMusicLogBinding
import dsm.android.v3.util.DataBindingFragment
import kotlinx.android.synthetic.main.fragment_apply_music_log.*
import kotlinx.android.synthetic.main.fragment_apply_music_log.view.*
import org.jetbrains.anko.support.v4.toast

class ApplyMusicLogFragment : DataBindingFragment<FragmentApplyMusicLogBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_apply_music_log

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(activity!!).get(ApplyMusicViewModel::class.java)

        viewModel.dialogCallEvent.observe(this, Observer {
            ApplyMusicDialogFragment().show(fragmentManager, "ApplyMusicDialogFragment")
        })

        viewModel.dataSetChangedLiveEvent.observe(this, Observer {
            apply_music_musicList_rv.adapter!!.notifyDataSetChanged()
        })

        viewModel.toastLiveEvent.observe(this, Observer{ toast(it!!) })

        binding.viewModel = viewModel
        binding.root.apply_music_musicList_rv.adapter = ApplyMusicAdapter(viewModel)
    }
}
