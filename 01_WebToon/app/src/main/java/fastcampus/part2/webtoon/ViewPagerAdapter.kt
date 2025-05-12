package fastcampus.part2.webtoon

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(private val mainActivity: MainActivity) : FragmentStateAdapter(mainActivity){
    companion object {
        private const val DEFAULT_URL = "https://comic.naver.com/"
    }
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                return WebViewFragment(position, "")
            }
            1 -> {
                return WebViewFragment(position, DEFAULT_URL)
            }
            2 -> {
                return WebViewFragment(position, DEFAULT_URL)
            }

            else -> {
                return WebViewFragment(position, DEFAULT_URL)
            }
        }
    }

}