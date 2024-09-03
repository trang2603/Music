package com.demo.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.R
import com.demo.data.Add
import com.demo.data.Artist
import com.demo.data.DataUi
import com.demo.data.Playlist
import com.demo.data.Songs
import com.demo.data.Type
import com.demo.databinding.FragmentHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeAdapter

    val listItem: List<DataUi> =
        listOf(
            DataUi(
                type = Type.TYPE_PLAYLIST_HORIZONTAL,
                data =
                    listOf(
                        Playlist("1", "", "Playlist 1", "", "", Songs(), "15 songs"),
                        Playlist("2", "", "Playlist 2", "", "", Songs(), "25 songs"),
                        Playlist("3", "", "Playlist 3", "", "", Songs(), "20 songs"),
                    ),
            ),
            DataUi(
                type = Type.TYPE_RECENTLY,
                data =
                    listOf(
                        Songs("1", "", "Name song 1", "Name Artist 1", "", "", ""),
                        Songs("2", "", "Name song 2", "Name Artist 2", "", "", ""),
                        Songs("3", "", "Name song 3", "Name Artist 3", "", "", ""),
                        Songs("4", "", "Name song 4", "Name Artist 4", "", "", ""),
                        Songs("5", "", "Name song 5", "Name Artist 5", "", "", ""),
                        Artist("1", "", Songs("1", "", "Name song 1", "Name Artist 1", "", "", "")),
                        Artist("2", "", Songs("2", "", "Name song 2", "Name Artist 2", "", "", "")),
                    ),
            ),
            DataUi(
                type = Type.TYPE_FAVOURITE,
                data =
                    listOf(
                        Songs("1", "", "Name song 1", "Name Artist 1", "", "", ""),
                        Songs("2", "", "Name song 2", "Name Artist 2", "", "", ""),
                        Songs("3", "", "Name song 3", "Name Artist 3", "", "", ""),
                        Songs("4", "", "Name song 4", "Name Artist 4", "", "", ""),
                        Songs("5", "", "Name song 5", "Name Artist 5", "", "", ""),
                        Add("1", Songs()),
                    ),
            ),
            DataUi(
                type = Type.TYPE_PLAYLIST_VERTICAL,
                data =
                    Playlist(
                        "1",
                        "",
                        "Playlist 1",
                        "",
                        "",
                        Songs("1", "", "Name song 1", "Name Artist 1", "", "", ""),
                        "15 songs",
                    ),
            ),
            DataUi(
                type = Type.TYPE_PLAYLIST_VERTICAL,
                data =
                    Playlist(
                        "2",
                        "",
                        "Playlist 1",
                        "",
                        "",
                        Songs("2", "", "Name song 2", "Name Artist 2", "", "", ""),
                        "25 songs",
                    ),
            ),
            DataUi(
                type = Type.TYPE_PLAYLIST_VERTICAL,
                data =
                    Playlist(
                        "3",
                        "",
                        "Playlist 1",
                        "",
                        "",
                        Songs("3", "", "Name song 3", "Name Artist 3", "", "", ""),
                        "30 songs",
                    ),
            ),
            DataUi(
                type = Type.TYPE_PLAYLIST_VERTICAL,
                data =
                    Playlist(
                        "4",
                        "",
                        "Playlist 1",
                        "",
                        "",
                        Songs("4", "", "Name song 4", "Name Artist 4", "", "", ""),
                        "20 songs",
                    ),
            ),
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        adapter =
            HomeAdapter(onLongClickPlaylist = {
                val dialog = BottomSheetDialog(requireContext())
                val view = layoutInflater.inflate(R.layout.bottom_sheet, null)
                dialog.setContentView(view)
                dialog.show()
            })
        binding.recycleView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recycleView.adapter = adapter
        adapter.submitList(listItem)
    }
}
