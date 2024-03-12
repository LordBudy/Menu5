package com.example.menu.fragments


import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.menu.databinding.FragmentHomeBinding
import com.example.menu.managers.FragmentManager
import com.example.menu.managers.FragmentManagerText


class Home : Fragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Устанавливаем название фрагмента с использованием менеджера
        FragmentManagerText.onFragmentTitleChanged("Категории")

        setButtonListener()

        Glide.with(this)
            .load("https://img.freepik.com/free-photo/top-view-tasty-breakfast_23-2147991202.jpg?t=st=1709971481~exp=1709975081~hmac=596414fed39256a867f2bed0f189bc9f1a23b673cf4e5f6cb434b48c2db4732c&w=740")
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?,
                ) {
                    binding.catPekarny.background = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    binding.catPekarny.background = placeholder
                }
            })
        Glide.with(this)
            .load("https://img.freepik.com/free-photo/flat-lay-american-food-concept-with-copyspace_23-2148238453.jpg?t=st=1709971555~exp=1709975155~hmac=a50549e9afd0d9cfa1dd53b553a435898bb67b890c0788519756840de63bf844&w=740")
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?,
                ) {
                    binding.catFasfud.background = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    binding.catFasfud.background = placeholder
                }
            })
        Glide.with(this)
            .load("https://img.freepik.com/free-photo/japanese-traditional-salad-with-pieces-medium-rare-grilled-ahi-tuna-sesame-with-fresh-vegetable-salad-plate_2829-18366.jpg?t=st=1709971633~exp=1709975233~hmac=4b136ac43e0e4c5334b1c8bcaaba8dc84f9ee7e3e49fa3aa0dc1a9fdd36d69e5&w=740")
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?,
                ) {
                    binding.catAziat.background = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    binding.catAziat.background = placeholder
                }
            })
        Glide.with(this)
            .load("https://img.freepik.com/free-photo/top-view-autumn-food-with-copy-space_23-2148666923.jpg?t=st=1709972165~exp=1709975765~hmac=b45fdca07cc5968e1b4ef687b6afca7fee6940b695db72a3fac5d0d2f15d1df3&w=740")
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?,
                ) {
                    binding.catSupi.background = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    binding.catSupi.background = placeholder
                }
            })
    }

    //устанавливаем слушатель кнопки catPekarny и с помощью FragmentManager вызываем нужный фрагмент
    private fun setButtonListener() {
        binding.catPekarny.setOnClickListener {
            //в фрагмент менеджер передаем два параметра новый фрагмент и активити ,но так как
            // вызываем из фрагмента то указываем второй параметр не this,а requireActivity()
            // и явно указываем какое это активити as AppCompatActivity
            FragmentManager.setFragment(Menu.newInstance(), requireActivity() as AppCompatActivity)
        }
        binding.catFasfud.setOnClickListener {
            FragmentManager.setFragment(Menu.newInstance(), requireActivity() as AppCompatActivity)
        }
        binding.catAziat.setOnClickListener {
            FragmentManager.setFragment(Menu.newInstance(), requireActivity() as AppCompatActivity)
        }
        binding.catSupi.setOnClickListener {
            FragmentManager.setFragment(Menu.newInstance(), requireActivity() as AppCompatActivity)
        }

    }


    companion object {
        @JvmStatic
        fun newInstance() = Home()
    }
}