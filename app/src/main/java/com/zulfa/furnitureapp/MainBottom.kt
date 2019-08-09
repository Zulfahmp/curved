package com.zulfa.furnitureapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_bottom.*

class MainBottom : AppCompatActivity() {

    private var content: FrameLayout? = null

    companion object{
        private const val ID_HOME =1
        private const val ID_EXPLORE =2
        private const val ID_MESSAGE =3
        private const val ID_NOTIFICATION =4
        private const val ID_ACCOUNT =5
    }

    fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
                .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_bottom)

        val fragment = HomeFragment()
        addFragment(fragment)

        bottomNavigation.add(MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_home_black_24dp))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_EXPLORE, R.drawable.ic_explore_black_24dp))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_MESSAGE, R.drawable.ic_message_black_24dp))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_NOTIFICATION, R.drawable.ic_notifications_black_24dp))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_ACCOUNT, R.drawable.ic_supervisor_account_black_24dp))

        bottomNavigation.setCount(ID_NOTIFICATION, "5")

        bottomNavigation.setOnShowListener {
            item -> when (item.id){
            ID_HOME -> {
                val fragment = HomeFragment()
                addFragment(fragment)
                return@setOnShowListener
                true
            }
            ID_EXPLORE -> {
                val fragment = SearchFragment()
                addFragment(fragment)
                return@setOnShowListener
                true
            }
            ID_MESSAGE -> {
                val fragment = PesanFragment()
                addFragment(fragment)
                return@setOnShowListener
                true
            }
            ID_NOTIFICATION -> {
                val fragment = NotifFragment()
                addFragment(fragment)
                return@setOnShowListener
                true
            }
            ID_ACCOUNT -> {
                val fragment = AccountFragment()
                addFragment(fragment)
                return@setOnShowListener
                true
            }
        }

//            val name = when (it.id){
//            ID_HOME -> "HOME"
//            ID_EXPLORE -> "EXPLORE"
//            ID_MESSAGE -> "MESSAGE"
//            ID_NOTIFICATION -> "NOTIFICATION"
//            ID_ACCOUNT -> "ACCOUNT"
//            else -> ""
//            }
//            tv_selected.text = "$name page is selected"
        }

        bottomNavigation.setOnClickMenuListener {
            val name = when (it.id){
            ID_HOME -> "HOME"
            ID_EXPLORE -> "EXPLORE"
            ID_MESSAGE -> "MESSAGE"
            ID_NOTIFICATION -> "NOTIFICATION"
            ID_ACCOUNT -> "ACCOUNT"
            else -> ""
            }
        }
    }
}
