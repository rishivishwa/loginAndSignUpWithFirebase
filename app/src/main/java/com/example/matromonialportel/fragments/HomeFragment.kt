package com.example.matromonialportel.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.matromonialportel.R
import com.example.matromonialportel.databinding.ActivityHomeBinding
import com.example.matromonialportel.databinding.FragmentHomesBinding


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomesBinding
    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomesBinding.inflate(layoutInflater)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        actionBarDrawerToggle = ActionBarDrawerToggle(activity, drawerLayout,R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle?.syncState()

        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.navStart.setOnClickListener {
            drawerLayout.open()
        }
        binding.drawerItems.llMap.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_mapsFragment)
            drawerLayout.closeDrawers()
        }
        return binding.root
    }

}