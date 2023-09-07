package com.valentinerutto.anime.ui.dashboard

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import coil.load
import com.valentinerutto.anime.databinding.FragmentDashboardBinding
import com.valentinerutto.anime.ui.MainViewModel
import com.valentinerutto.anime.util.ImageUtil.getRealPathFromUri
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.io.File

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val viewModel by sharedViewModel<MainViewModel>()
    var selectedImageUri: Uri? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        setOnClickListeners()
        binding.txtEpisode.text = "Click button to upload Images "

    }

    private fun setUpObservers() {
        binding.txtEpisode.text = "Click button to upload Images "

        viewModel.successfulImageListResponse.observe(viewLifecycleOwner) {
            binding.txtError.isVisible = false
            it?.map {
                binding.txtEpisode.text = it.episodes
                binding.txtTitle.text = it.fileName
                binding.img.load(it.fileImage)
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { showLoading ->
            binding.txtEpisode.text = ""
            binding.animeProgressBar.isVisible = showLoading


        }

        viewModel.errorImageResponse.observe(viewLifecycleOwner) { errorMsg ->
            binding.txtError.isVisible = true
            binding.txtError.text = errorMsg
        }
    }

    private fun setOnClickListeners() {
        binding.fabUpload.setOnClickListener {
            photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    private val photoPickerLauncher =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { imageUri ->
            if (imageUri != null) {

                selectedImageUri = imageUri

                val file = File(getRealPathFromUri(requireContext(), imageUri))
                val imagePart = MultipartBody.Part.createFormData(
                    name = "image", filename = file.name, body = file.asRequestBody()
                )

                viewModel.uploadImage(imagePart)

                Toast.makeText(
                    requireActivity(), "Uploading Image selected:${file.name}", Toast.LENGTH_SHORT
                ).show()


            } else {
                Toast.makeText(
                    requireActivity(), "No media selected", Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}