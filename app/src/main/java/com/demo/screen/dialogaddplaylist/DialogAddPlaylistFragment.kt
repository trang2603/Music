package com.demo.screen.dialogaddplaylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.base.BaseMVVMDialogFragment
import com.demo.data.model.Playlist
import com.demo.databinding.LayoutDialogBinding
import com.demo.screen.dialogaddplaylist.adapter.PlaylistDialogAdapter
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class DialogAddPlaylistFragment : BaseMVVMDialogFragment<DialogAddPlaylistViewModel>() {
    private val adapterDialog: PlaylistDialogAdapter =
        PlaylistDialogAdapter(
            onCheckboxClick = { itemClick ->
                viewModelDialog.sendAction(DialogAddPlaylistViewModel.Action.ClickCheckBox(itemClick))
            },
            onAddPlaylist = { namePlaylist ->
                viewModelDialog.sendAction(
                    DialogAddPlaylistViewModel.Action.AddPlaylist(
                        namePlaylist,
                    ),
                )
            },
        )
    private val viewModelDialog: DialogAddPlaylistViewModel = DialogAddPlaylistViewModel()
    private lateinit var binding: LayoutDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = LayoutDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycleView.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.recycleView.adapter = adapterDialog
        viewModelDialog.sendAction(DialogAddPlaylistViewModel.Action.GetList)
    }

    override fun observerState() {
        viewModelDialog.state
            .map { it.data }
            .distinctUntilChanged()
            .onEach { list ->
                adapterDialog.submitList(
                    list.toList() ?: listOf(),
                )
                val isButtonEnable = list.filterIsInstance<Playlist>().firstOrNull { it.isCheck }
                if (isButtonEnable != null) {
                    binding.button.isEnabled = true
                }
            }.launchIn(lifecycleScope)
    }

    override fun observerEffect() {
    }
}
