package com.example.menu.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.menu.interfaces.ImageClickListener
import com.example.menu.databinding.FragmentMenuBinding
import com.example.menu.models.AllDish
import com.example.menu.managers.FragmentManagerText
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

        // Указываем название фрагмента на котором находимся с использованием менеджера
        FragmentManagerText.onFragmentTitleChanged("Меню блюд")


        val dishes = listOf(
            AllDish(
                idDish = 1,
                imageUrl = "https://img.freepik.com/premium-photo/plate-with-tasty-pilaf-white_392895-19996.jpg?w=740",
                name = "Рис с овощами",
                price = "450",
                weight = "560.0гр.",
                description = "В китайской кухне рис с овощами готовят " +
                        "с добавлением грибов,бамбуковых побегов, моркови," +
                        " лука и гороха. Эти " +
                        "ингредиенты быстро обжариваются на сковороде " +
                        "с соевым соусом и специями."
            ),
//--------------------------------------------------------------------------------------------------
            AllDish(
                idDish = 2,
                imageUrl = "https://static.tildacdn.com/stor3838-6438-4537-b834-623038383830/17163536.jpg",
                name = "Салат по восточному",
                price = "250",
                weight = "320.0гр.",
                description = "Основными компонентами салата обычно являются свежие овощи," +
                        " такие как морковь, капуста и помидор, " +
                        "которые тщательно нарезаются и смешиваются. " +
                        "Кроме того, в салат могут быть добавлены соленые огурцы, зелень, яйца," +
                        " масло и приправы."
            ),
//--------------------------------------------------------------------------------------------------
            AllDish(
                idDish = 3,
                imageUrl = "https://img.freepik.com/premium-photo/grilled-salmon-fillet-with-risotto_738298-3633.jpg?w=740",
                name = "Рыба с овощами и рисом",
                price = "330",
                weight = "470.0гр.",
                description = "Это - очень вкусное и очень простое домашнее блюдо:" +
                        " рыба и рис с овощами готовятся одновременно"
            ),
//--------------------------------------------------------------------------------------------------
            AllDish(
                idDish = 4,
                imageUrl = "https://www.balconesulmetauro.it/wp/wp-content/uploads/2019/07/tortellini-2.png",
                name = "Тортелини",
                price = "225",
                weight = "200гр.",
                description = "Тортеллини — итальянские пельмени из пресного теста с мясом, сыром или овощами." +
                        "Историческая родина тортеллини — регион Эмилия, однако сейчас эту разновидность макаронных" +
                        " изделий готовят по всей Италии."
            ),
//--------------------------------------------------------------------------------------------------
            AllDish(
                idDish = 5,
                imageUrl = "https://static.tildacdn.com/tild6438-6366-4632-a237-626132623132/1-salat-s-kuracim-ma.png",
                name = "Зеленый салат",
                price = "125",
                weight = "150гр.",
                description = "Салат листовой — это однолетнее травянистое растение семейства Сложноцветковые." +
                        "Его выращивают повсеместно, в различных климатических зонах."
            ),
//--------------------------------------------------------------------------------------------------
        AllDish(
            idDish = 6,
            imageUrl = "https://100foto.club/uploads/posts/2023-01/1672836368_11-100foto-club-p-trubochki-iz-vetchini-s-nachinkoi-18.jpg",
            name = "Рулеты из ветчины",
            price = "400",
            weight = "385гр.",
            description = "Рулетики из ветчины — это изысканное и вкусное блюдо, которое завоевывает любовь гурманов по всему миру." +
                    "Это блюдо было придумано во Франции ещё в XVIII веке как изящная закуска для высших слоёв общества." +
                    " Именно благодаря французской кухне рулетики стали ассоциироваться с изысканностью и утончённостью."
        ),
//--------------------------------------------------------------------------------------------------
        AllDish(
            idDish = 7,
            imageUrl =   "https://img.freepik.com/premium-photo/spinach-fettuccine-with-mushroom_1339-24457.jpg?w=826",
            name = "Фетучини с грибами",
            price = "270",
            weight = "310гр.",
            description = "Фетучини Альфредо — итальянское блюдо из пасты фетучини, смешанной со сливочным маслом и" +
                    " молодым сыром пармезан и грибами. Блюдо названо в честь повара Альфредо Ди Лелио, который создал его в" +
                    " своем ресторане в Риме в начале XX века."
        ),
//--------------------------------------------------------------------------------------------------
        AllDish(
            idDish = 8,
            imageUrl =  "https://www.espri-restauration.fr/wp-content/uploads/2014/07/product_thumbnail_41699.jpg",
            name = "Паэлья из утки",
            price = "550",
            weight = "400гр.",
            description = "Паэ́лья — национальное испанское и валенсийское блюдо из риса, подкрашенного шафраном," +
                    " с добавлением оливкового масла и конечно же утки. Кроме этого в паэлью могут добавляться морепродукты"
        ),
//--------------------------------------------------------------------------------------------------
        AllDish(
            idDish = 9,
            imageUrl = "https://recettessimples.fr/images/Ratatouille_Assiette_1.jpg",
            name = "Рататуй",
            price = "175",
            weight = "200гр.",
            description = "Рататуй - традиционное овощное блюдо прованской кухни из перцев, баклажанов" +
                    " и кабачков. Первоначально блюдо было придумано в районе Ниццы. Рататуй был блюдом " +
                    "небогатых крестьян, которые готовили его летом из свежих овощей"
        ),
//--------------------------------------------------------------------------------------------------
        AllDish(
            idDish = 10,
            imageUrl = "https://img.freepik.com/free-photo/delicious-food-white-plate_144627-34711.jpg?t=st=1709222938~exp=1709226538~hmac=0c398f5a22dd87524b512c959a03792e059e9f0db74f17624bee36ce324a3e75&w=826",
            name = "Шницель",
            price = "240",
            weight = "250гр.",
            description = "Родиной шницеля является Австрия, а не Германия, как думают многие." +
                    " И появилось это блюдо на свет в XV веке. А вот в Австралию шницель попал из Италии" +
                    " в виде миланской отбивной из телятины, которую привезли итальянские купцы."
        ),
//--------------------------------------------------------------------------------------------------

        AllDish(
            idDish = 11,
            imageUrl = "https://img.freepik.com/premium-photo/russian-pork-dumplings-pelmeni_738298-4053.jpg?w=826",
            name = "Пельмени",
            price = "190",
            weight = "180гр.",
            description = "в Китае пельмень — блюдо национальное и символизирует достаток. По легенде," +
                    " пельмени придумали около 2,5 тысячи лет назад. Придворный повар слепил их в форме золотого" +
                    " слитка, которыми тогда расплачивались в Поднебесной. Слиток этот назывался юаньбао —" +
                    " и со временем он превратился из средства оплаты в символ достатка."
        ),
//--------------------------------------------------------------------------------------------------

        AllDish(
            idDish = 12,
            imageUrl = "https://img.freepik.com/free-photo/delicious-pancakes-with-strawberry-jam_2829-15767.jpg?t=st=1709223250~exp=1709226850~hmac=241c2a3eb2651d89377f9bba91ce8a80b9ac3fae56f827b76bbc3ca84e0d6e0e&w=826",
            name = "Олади",
            price = "100",
            weight = "200гр.",
            description = "Оладьи –традиционное русское блюдо. Они готовятся из различных ингредиентов," +
                    " таких как: мука, сахар, молоко и яйца, и обычно имеют круглую или овальную форму."
        ),
//--------------------------------------------------------------------------------------------------

        AllDish(
            idDish = 13,
            imageUrl = "https://food.pibig.info/uploads/posts/2023-08/1693284195_food-pibig-info-p-yeda-tsezar-vkontakte-63.jpg",
            name = "Салат цезарь",
            price = "390",
            weight = "240гр.",
            description = "Салат цезарь - был изобретен в 1903 году Джакомо Джуниа, итальянским поваром из Чикаго." +
                    " Он работал в ресторане The New York Cafe и готовил в основном американскую еду. "
        ),
//--------------------------------------------------------------------------------------------------

        AllDish(
            idDish = 14,
            imageUrl = "https://avatars.mds.yandex.net/i?id=be9b6e48854aa59ad9323bcf6b547f67e1746337-12168040-images-thumbs&n=13",
            name = "Салат королевский с говядиной",
            price = "600",
            weight = "420гр.",
            description = "Салат королевский с отварной говядиной имеет яркий вкус и прекрасно сочетается с " +
                    "другими блюдами на столе. Он обычно готовится заранее и хранится в холодильнике, чтобы " +
                    "ингредиенты хорошо пропитались и соединились вкусами."
        ),
//--------------------------------------------------------------------------------------------------

        AllDish(
            idDish = 15,
            imageUrl = "https://media.leverans.ru/product_images_inactive/moscow/sam-am-beri/samamberi-ru-006.jpg",
            name = "Мясо ассорти в соусе",
            price = "400",
            weight = "300гр.",
            description = "Украшением мясного ассорти вполне могут стать тонко нарезанный куриный рулет, индюшатина," +
                    " мясо кролика или дичь. Главное, чтобы на блюде было представлено не менее четырех видов мясных продуктов. "
        ),
//--------------------------------------------------------------------------------------------------
        AllDish(
            idDish = 16,
            imageUrl = "https://i.pinimg.com/originals/a6/d2/15/a6d215f534a432c20f8c201e50e31082.jpg",
            name = "Лагман удон",
            price = "160",
            weight = "200гр.",
            description = "Лапша удон появилась в Японии в VIII веке до нашей эры. Жители страны восходящего " +
                    "солнца позаимствовали рецепт у китайцев." +
                    "Способ приготовления удона в Японию привёз священник-буддист Кукай. Сам мастер называл эту" +
                    " лапшу «пищей для ума, духа и тела»."
        ),
//--------------------------------------------------------------------------------------------------
        AllDish(
            idDish = 17,
            imageUrl = "https://lime-sushi.com/wa-data/public/shop/products/43/00/43/images/54/54.970.png",
            name = "Фунчоза с креветками и овощами",
            price = "320",
            weight = "270гр.",
            description = "Фунчоза с креветками и овощами идеально подойдет для подачи на ужин с любимыми. Она хорошо" +
                    " сочетается с вином, сытная, но при этом легкая. И состав продуктов для " +
                    "нее можно изменять в зависимости от сезона и вкуса!"
        ),
//--------------------------------------------------------------------------------------------------

        AllDish(
            idDish = 18,
            imageUrl = "https://cdn-img.perekrestok.ru/i/400x400-fit/xdelivery/files/48/e2/b46bfb25b1edde303b63b92ef3ee.jpg",
            name = "Суп рамэн со свининой",
            price = "190",
            weight = "260гр.",
            description = "Сытный свиной суп рамен или рамен курицей и пекинской капустой, получаются невероятного вкуса" +
                    " и незабываемого аромата. Захотелось попробовать? Вы можете заказать суп Рамен у нас в меню и выбрать " +
                    "на свой вкус,а так же вы можете выбрать WOK или салаты."
        )
        )
        // Проходим по каждому блюду в списке и загружаем изображение с помощью Picasso
        dishes.forEachIndexed { index, dish ->
            val imageView = when (index) {
                // Добавляем в ImageView  все изображения по индексу
                0 -> binding.image1
                1 -> binding.image2
                2 -> binding.image3
                3 -> binding.image4
                4 -> binding.image5
                5 -> binding.image6
                6 -> binding.image7
                7 -> binding.image8
                8 -> binding.image9
                9 -> binding.image10
                10 -> binding.image11
                11 -> binding.image12
                12 -> binding.image13
                13 -> binding.image14
                14 -> binding.image15
                15 -> binding.image16
                16 -> binding.image17
                17 -> binding.image18

                else -> null
            }

            imageView?.let { picassoImageView ->
                Picasso.get()
                    .load(dish.imageUrl)
                    .into(picassoImageView)

                picassoImageView.setOnClickListener {
                    // Передаем данные о блюде во второй фрагмент при нажатии на изображение
                    imageClickListener.onImageClicked(
                        dish.idDish,
                        dish.imageUrl,
                        dish.name,
                        dish.price,
                        dish.weight,
                        dish.description
                    )
                }
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = Menu()
    }

}