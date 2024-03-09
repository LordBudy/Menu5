package com.example.menu.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.menu.ImageClickListener
import com.example.menu.R
import com.example.menu.databinding.FragmentMenuBinding
import com.squareup.picasso.Picasso


class Menu : Fragment() {
    lateinit var binding: FragmentMenuBinding
    private lateinit var imageClickListener: ImageClickListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ImageClickListener) {
            imageClickListener = context
        } else {
            throw RuntimeException("$context должен реализовать ImageClickListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Устанавливаем название фрагмента с использованием менеджера
        FragmentManagerText.onFragmentTitleChanged("Меню")

        val ImageUrl1 = "https://img.freepik.com/premium-photo/plate-with-tasty-pilaf-white_392895-19996.jpg?w=740"
            Picasso.get()
            .load(ImageUrl1)
            .into(binding.image1)
       val name1 = "Рис с овощами"
       val price1 = "450 р."
       val weight1 = "560.0 г."
       val descroption1 = "В китайской кухне рис с овощами готовят " +
                "с добавлением грибов,бамбуковых побегов, моркови," +
                " лука и гороха. Эти " +
                "ингредиенты быстро обжариваются на сковороде " +
                "с соевым соусом и специями."

        binding.image1.setOnClickListener {
            // Передаем URL во второй фрагмент при нажатии на изображение
            imageClickListener.onImageClicked(ImageUrl1, name1, price1, weight1, descroption1)
        }
//--------------------------------------------------------------------------------------------------
        val Image2Url = "https://static.tildacdn.com/stor3838-6438-4537-b834-623038383830/17163536.jpg"
        Picasso.get()
            .load(Image2Url)
            .into(binding.image2)
       val name2 = "Салат по восточному"
       val price2 = "250 р."
       val weight2 = "320.0 г."
       val descroption2 = "Основными компонентами салата обычно являются свежие овощи," +
               " такие как морковь, капуста и помидор, " +
               "которые тщательно нарезаются и смешиваются. " +
               "Кроме того, в салат могут быть добавлены соленые огурцы, зелень, яйца," +
               " масло и приправы."
        binding.image2.setOnClickListener {
            // Передаем URL во второй фрагмент при нажатии на изображение
            imageClickListener.onImageClicked(Image2Url, name2, price2, weight2, descroption2)
        }
//--------------------------------------------------------------------------------------------------
        val Image3Url = "https://img.freepik.com/premium-photo/grilled-salmon-fillet-with-risotto_738298-3633.jpg?w=740"
             Picasso.get()
            .load(Image3Url)
            .into(binding.image3)
        val name3 = "Рыба с овощами и рисом"
        val price3 = "330 р."
        val weight3 = "470.0 г."
        val descroption3 = "Это - очень вкусное и очень простое домашнее блюдо:" +
                " рыба и рис с овощами готовятся одновременно"
        binding.image3.setOnClickListener {
            // Передаем URL во второй фрагмент при нажатии на изображение
            imageClickListener.onImageClicked(Image3Url, name3, price3, weight3, descroption3)
        }
//--------------------------------------------------------------------------------------------------
        val Image4Url = "https://www.balconesulmetauro.it/wp/wp-content/uploads/2019/07/tortellini-2.png"
             Picasso.get()
            .load(Image4Url)
            .into(binding.image4)
//--------------------------------------------------------------------------------------------------
        val Image5Url = "https://static.tildacdn.com/tild6438-6366-4632-a237-626132623132/1-salat-s-kuracim-ma.png"
        Picasso.get()
            .load(Image5Url)
            .into(binding.image5)
//--------------------------------------------------------------------------------------------------
        val Image6Url = "https://100foto.club/uploads/posts/2023-01/1672836368_11-100foto-club-p-trubochki-iz-vetchini-s-nachinkoi-18.jpg"
        Picasso.get()
            .load(Image6Url)
            .into(binding.image6)
//--------------------------------------------------------------------------------------------------
        val Image7Url =
            "https://img.freepik.com/premium-photo/spinach-fettuccine-with-mushroom_1339-24457.jpg?w=826"
        Picasso.get()
            .load(Image7Url)
            .into(binding.image7)
//--------------------------------------------------------------------------------------------------
        val Image8Url =
            "https://www.espri-restauration.fr/wp-content/uploads/2014/07/product_thumbnail_41699.jpg"
        Picasso.get()
            .load(Image8Url)
            .into(binding.image8)
//--------------------------------------------------------------------------------------------------
        val Image9Url = "https://recettessimples.fr/images/Ratatouille_Assiette_1.jpg"
        Picasso.get()
            .load(Image9Url)
            .into(binding.image9)
//--------------------------------------------------------------------------------------------------
        val Image10Url =
            "https://img.freepik.com/free-photo/delicious-food-white-plate_144627-34711.jpg?t=st=1709222938~exp=1709226538~hmac=0c398f5a22dd87524b512c959a03792e059e9f0db74f17624bee36ce324a3e75&w=826"
        Picasso.get()
            .load(Image10Url)
            .into(binding.image10)
//--------------------------------------------------------------------------------------------------
        val Image11Url =
            "https://img.freepik.com/premium-photo/russian-pork-dumplings-pelmeni_738298-4053.jpg?w=826"
        Picasso.get()
            .load(Image11Url)
            .into(binding.image11)
//--------------------------------------------------------------------------------------------------
        val Image12Url =
            "https://img.freepik.com/free-photo/delicious-pancakes-with-strawberry-jam_2829-15767.jpg?t=st=1709223250~exp=1709226850~hmac=241c2a3eb2651d89377f9bba91ce8a80b9ac3fae56f827b76bbc3ca84e0d6e0e&w=826"
        Picasso.get()
            .load(Image12Url)
            .into(binding.image12)
//--------------------------------------------------------------------------------------------------
        val Image13Url =
            "https://food.pibig.info/uploads/posts/2023-08/1693284195_food-pibig-info-p-yeda-tsezar-vkontakte-63.jpg"
        Picasso.get()
            .load(Image13Url)
            .into(binding.image13)
//--------------------------------------------------------------------------------------------------
        val Image14Url =
            "https://avatars.mds.yandex.net/i?id=be9b6e48854aa59ad9323bcf6b547f67e1746337-12168040-images-thumbs&n=13"
        Picasso.get()
            .load(Image14Url)
            .into(binding.image14)
//--------------------------------------------------------------------------------------------------
        val Image15Url =
            "https://media.leverans.ru/product_images_inactive/moscow/sam-am-beri/samamberi-ru-006.jpg"
        Picasso.get()
            .load(Image15Url)
            .into(binding.image15)
//--------------------------------------------------------------------------------------------------
        val Image16Url =
            "https://i.pinimg.com/originals/a6/d2/15/a6d215f534a432c20f8c201e50e31082.jpg"
        Picasso.get()
            .load(Image16Url)
            .into(binding.image16)
//--------------------------------------------------------------------------------------------------
        val Image17Url =
            "https://lime-sushi.com/wa-data/public/shop/products/43/00/43/images/54/54.970.png"
        Picasso.get()
            .load(Image17Url)
            .into(binding.image17)
//--------------------------------------------------------------------------------------------------
        val Image18Url =
            "https://cdn-img.perekrestok.ru/i/400x400-fit/xdelivery/files/48/e2/b46bfb25b1edde303b63b92ef3ee.jpg"
        Picasso.get()
            .load(Image18Url)
            .into(binding.image18)

    }

    companion object {
        @JvmStatic
        fun newInstance() = Menu()
    }

}