package fastcampus.part2.webtoon

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import fastcampus.part2.webtoon.databinding.FragmentWebviewBinding


class WebViewFragment : Fragment() {

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
        binding.webView.webViewClient = WebtoonWebViewClient(binding.progressBar)

        binding.webView.settings.javaScriptEnabled = true

        // 1. loadUrl만으로 설정한 URL로 webpage가 load되지 않음
        //binding.webView.loadUrl("https://comic.naver.com/webtoon/detail?titleId=758037&no=187&week=mon")
        binding.webView.loadUrl("https://comic.naver.com/")
    }

    fun canGoBack() : Boolean {
        return binding.webView.canGoBack()
    }
    fun goBack() {
        binding.webView.goBack()
    }

}