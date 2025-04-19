package fastcampus.part2.webtoon

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(private val mainActivity: MainActivity) : FragmentStateAdapter(mainActivity){
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                return WebViewFragment()
            }
            1 -> {
                return BFragment()
            }
//            2 -> {
//                return WebViewFragment()
//            }

            else -> {
                return WebViewFragment()
            }
        }
    }

}