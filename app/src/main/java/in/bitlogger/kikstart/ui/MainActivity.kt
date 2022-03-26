package `in`.bitlogger.kikstart.ui

import `in`.bitlogger.kikstart.R
import `in`.bitlogger.kikstart.adapters.HomeIconAdapter
import `in`.bitlogger.kikstart.adapters.HomeNewsAdapter
import `in`.bitlogger.kikstart.databinding.ActivityMainBinding
import `in`.bitlogger.kikstart.db.model.HomeIcon
import `in`.bitlogger.kikstart.db.model.News
import `in`.bitlogger.kikstart.interfaces.CoroutineDataPassCallbacks
import `in`.bitlogger.kikstart.ui.vm.NewsVM
import `in`.bitlogger.kikstart.utils.Constants
import `in`.bitlogger.kikstart.utils.GridSpacingItemDecoration
import android.content.res.Resources
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var activityMainActivity: ActivityMainBinding

    private val newsVM: NewsVM by viewModels()
    private lateinit var actionableDrawer: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainActivity.root)

        actionableDrawer = ActionBarDrawerToggle(this, activityMainActivity.navDrawer, R.string.nav_open, R.string.nav_close)
        activityMainActivity.navDrawer.addDrawerListener(actionableDrawer)
        actionableDrawer.syncState()
        activityMainActivity.toolbar.toolbarProfile.setOnClickListener {
            activityMainActivity.navDrawer.openDrawer(Gravity.LEFT)
        }

        //Set Dynamic Layout
        setConstraint()
        setHomeIcons()
        setNews()
    }

    private fun setNews() {
        val callbacks = object : CoroutineDataPassCallbacks {
            override fun isDataLoading(dataLoading: Boolean) {
                Toast.makeText(this@MainActivity, "A: $dataLoading", Toast.LENGTH_SHORT).show()
            }

            override fun <T> onLoadComplete(data: T) {
                Toast.makeText(this@MainActivity, (data as Array<News>)[1].link, Toast.LENGTH_SHORT)
                    .show()
                val adapter = HomeNewsAdapter(data as Array<News>)
                activityMainActivity.bottomSheetHome.homeNewsRv.adapter = adapter
                activityMainActivity.bottomSheetHome.homeNewsRv.layoutManager =
                    LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
            }

            override fun onLoadFailed(errorCode: String, errorMessage: String) {
                Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
        newsVM.fetchAllNews(callbacks)
    }

    private fun setHomeIcons() {
        val data = arrayOf(
            HomeIcon(
                "Counselling",
                R.drawable.ic_councilling
            ),
            HomeIcon(
                "Register Company",
                R.drawable.ic_registration
            ),
            HomeIcon(
                "Courses",
                R.drawable.ic_courses
            ),
            HomeIcon(
                "Schemes",
                R.drawable.ic_scheme
            ),
            HomeIcon(
                "Incubators",
                R.drawable.ic_incubator
            ),
            HomeIcon(
                "Validate Idea",
                R.drawable.ic_idea
            ),
            HomeIcon(
                "Hire Me",
                R.drawable.ic_hire
            ),
            HomeIcon(
                "About US",
                R.drawable.ic_aboutus
            ),
        )
        val adapters = HomeIconAdapter(data)
        activityMainActivity.bottomSheetHome.sheetHomeIconsRv.apply {
            adapter = adapters
            layoutManager = GridLayoutManager(this@MainActivity, 4)
        }
    }

    private fun setConstraint() {
        val constraint = activityMainActivity.abcd
        val display = Resources.getSystem().displayMetrics.heightPixels
        val mBottomSheetBehaviour = BottomSheetBehavior.from(activityMainActivity.homeBottomSheet)
        val params = constraint.layoutParams

        mBottomSheetBehaviour.peekHeight = Constants.getPercentage(70, display)
        params.height = Constants.getPercentage(30, display)
    }
}