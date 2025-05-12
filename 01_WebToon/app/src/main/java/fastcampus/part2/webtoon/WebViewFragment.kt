package fastcampus.part2.webtoon

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import fastcampus.part2.webtoon.databinding.FragmentWebviewBinding


class WebViewFragment constructor(
    private val position: Int,
    private val webViewUrl: String,
) : Fragment() {

    private lateinit var binding: FragmentWebviewBinding
    private val TAG = "WebViewFragment"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        binding = FragmentWebviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated")

        // 2. WebViewClient를 사용해야함
        binding.webView.webViewClient = WebtoonWebViewClient(binding.progressBar) { url ->
            activity?.getSharedPreferences("WEB_HISTORY", Context.MODE_PRIVATE)?.edit {
                putString("tab$position", url)
                Log.d("savedUrl", "$url")
            }
        }

        binding.webView.settings.javaScriptEnabled = true

        // 1. loadUrl만으로 설정한 URL로 webpage가 load되지 않음
        //binding.webView.loadUrl("https://comic.naver.com/webtoon/detail?titleId=758037&no=187&week=mon")
        binding.webView.loadUrl(webViewUrl)

        //back key
        binding.backToLastButton.setOnClickListener {
            val sharedPreference =
                requireContext().getSharedPreferences("WEB_HISTORY", Context.MODE_PRIVATE)
            val url = sharedPreference.getString("tab$position", "")
            if (url.isNullOrEmpty()) {
                Log.d("null", "url is null or empty")
                Toast.makeText(context, "no page exists", Toast.LENGTH_SHORT).show()
            } else {
                binding.webView.loadUrl(url.toString())
            }
        }

        //tab name change
        binding.changeTapNameButton.setOnClickListener {
            val editText = EditText(context)
            editText.hint = "내용 입력하세요"

            context?.let {
                AlertDialog.Builder(it).apply {
                    setTitle("Tap name editor")
                    setView(editText)
                    setPositiveButton("Save") { dialog, _ ->

                    }

                }
            }
        }
    }

    fun canGoBack(): Boolean {
        return binding.webView.canGoBack()
    }

    fun goBack() {
        binding.webView.goBack()
    }

}