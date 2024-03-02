package com.example.menu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.menu.databinding.FragmentMenuBinding
import com.squareup.picasso.Picasso


class Menu : Fragment() {
lateinit var binding: FragmentMenuBinding
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

      val  ImageUrl1 = "https://lh3.googleusercontent.com/fife/APg5EOb3-gGunBAb_3E7L5qZLGIx0Wm8kh4UjNn2yow-7Kvf50D7eFb9Iw5g_7W7TQLGKF29-G6VNa7dHS_zEWY8VaSMh9EqUql8UEFISB_WWgiO8nf_mt0YtUhsWFB5uzw-Bfi_eS9Cs-0vLUMiqaqTGgGFDvVhUvak4AypPMEbt2-3mEkxeZNcClEy29x8gEmUU6e9G8s5GDyCxR404OIsgnGHqtIaGIGD7afRoz7PtgtmZdlXC5v7dHujDNh5l28v249qxjpa1rqxoorBb-ywkRsu1bzqDEHGbRDnFNLXHwEGlHoLS1krz9KcD3opkmQckg7-m7PXzEhnQlayqMPiNGP-WpnFYrthFVfJ0TY4zsYFx1azSJZTLa59Xuqr32eagNn9xF6mCPpGqRbunBKrbD-oWidQ_iAXZRRrgjZrv280Joe8z73AluN0A-mXcqRkpVwQo1n3szZ00wX7sD44PriRwHGUfEenTC5IyLVv3MBsYMNXJ9ALa6FZgTrnUhO_ePGoPftELYGNp4yn-xkzfM_GvnQR8A6od0bR8AqqpNketd0kBavJTkaJXBwsfvxinTdLfOTfWsZAhF97XYNEA_9SmTgnnOjt3N8YUxmUeWcor174r7bNdDuDQqq7vWRgFZNorh5v6LANRk0CVsq6B9tVxP2R1zTCc1yXgpu4kNGJsEvNyxMRy-yM3cBvhu01ZUFSalvwAqcS2M9_eBoPCu00KVtcEvyFPoqm_QNHBEkDLjUdtJC7BGcmv7SPa-rV6oH_3zeIYstyKLrgN-Dzewe816A6J7IN4YxSvCIOWbNV9Q6O3hxua_ZrGSk6ijSoBKE0XHUC04cr1O6BzRxL9lwVUhzvBzYfzmBzMPb84Pq-WwBtfCxN2j34NReGu5iABuA1iDNgz0r8WE2Dvvz0XsD6Uc8neX57A7_19J6vyJEhuARJREXGs4tFWHMmjqg-xwYOOPJhxF1BkCaQrUEKtFYBq1pT2N7_h9fMV8JOLP92grJFHRP6TmNrEjPAf9HjfYRFouBAqFMRy614VrJu5hyweBcy-4WbJNYqrvKZH_bXIQyZ6qlv4omHhGTSMMT9cAYTyiMm12bEH5ccThAygPaXlfx6ydA3towLnMpoq0ieByM2-Nql2uh4xPxgAHcmzipRgEqlYDflDKNSfeTVFKKQ4vtTWa43wR505BTjdO3mk5CVoK4sOzwcF1mQA2joVXdW63wbUWtw4wtfa3e9EP1TV01b5M02KKPcr2yxZpNQCo8-Igp6M8t_vqWSKlkq-Z7NaRswW-xQyuFWjCufwgpd1m8i5Z4tnL72DhelmIiI2cXufJb70_eobyMV5VsMUab1nLKWoKwggolnEjl2A1PLrv6T0aRTYmjt3JqFDEV58aIjZhhyrcX6h6Bb_AJzc-OFvFPw1uAuswJaimihZUfYoVuwtNRI27BD7KGnXzMHKthOM4fArK3ICIt61g91DNHAu9qhpWxsj0FrcmujdWE2vJpBf7XoOcgxiRlRKpljQ2c3M6ULTdS1nuypZleouOtwIHOUoxGtT5HJeC8ZcsgI3przMt97iGmSv5Us7xL0j42wVNZbQnyQ6EsIyIG1ZOo2ah9CyA7RqixQUevdzbPhSnJk2w6weuuRCDkdC4H97doLyAV_=w1366-h617"
        Picasso.get()
            .load(ImageUrl1)
            .into(binding.image1)
//            "Рис с овощами",
//            799,
//            560.0,
//            "В китайской кухне рис с овощами готовят с добавлением грибов, " +
//            "бамбуковых побегов, моркови, лука и гороха. Эти ингредиенты быстро
//            обжариваются на сковороде с соевым соусом и специями.")
//--------------------------------------------------------------------------------------------------
       val Image2Url =
            "https://lh3.googleusercontent.com/fife/APg5EOafwbErJPvjpg_yUmo7-sLRLWZF08C8k6VCCW3lGRyMVGbSnmfUxl1sNzL0oVfwT2cBZDlCMhNi6XBmrMU3zfX_wCbuT3dXN-dVGbl5ccr0NZyKwmee6geavwEEjDPIpAI0TSSoSIW5ESD42sxXblAKnt4vpETfa4MZYRBj6_0FIY9z2iPJHViTigN-vDdCPnq5RgC3f2-HZ1Algs_e5iSbauXwknae6sINa8JiZY9XdGT-qwsXivIrNakp5X4SkYUXTPfm4B9iBBuoO2SlLzDmPU8i6gglA1hNCM6ou2A0OK4x-to0plYfCz3Gpbl3c42iNqyBDi6wgiEKNP5DwyMCwrlQvfCdiXOinp6zXHqGBhV-54nl1bySMOB7oTd-iIIiG9b-P6OkNdpeMXb6INgW2looGoM_LXbEoD9HZ_QsopwtqGF5eDbeNTm4n6D7wiZX4rCpkukKNhS48Ne-cUIPAonEs0LfBEReGhiPBAsieaGqKxCS3ovW93w72qsGehL6hg_Wmxe_LOkwu-UHXyjPbUX8LfMLcL0ZdME2VOSkNnqbnR70TPBk3SJHlH3UOfsEzkxra1voEtM9hVJHgiX8kPm5Ym2V7CPYIXxaAN268ui47S07NUu1vhTOrYcdmn2zmJR8k0l1-zcG1WAx1fgKbnqbtezZ9VfYKRlLAtlsD8h86C4yUhQt74DkbfENrHZmLzVORTlYCi40wlIlAL1UsOzvR26163HYl-ZkRA-71199d3Dxr2T2Nv5IPoJmDuJACqu6FzxT71t_Q3-ZKPzNgt7fGpDf_4Xc9dC7G9lvQKpCMv8d3AVkZjf6czQoETmC7Hf6-9GdAp4jqGGztYRmR7wN11mrucEUzg63Mh5hh5bmbt93SctR_gnlt6rbyqo1aYu0p7xw6z02kyQWspnCPpinOMxLmeOI0TIsaqjq7-2VMWD04vMsYA1uVY3gYaST8IXA62ltb1ngSF6A4Qo3FtBgm1LS2yC-KxywiWSCCWwuv0WOc79dUxMl22XcUt4J_bIB0uyjIOmqD-uPGzger-dP6ZiM8_rBhNz40pJ0gWblxWoc6tpWgbhpTPBCCK7e91t3_pmlbh56EQrsKycQHHfTIVe7clQblWHI-Y9qX12WW5jn8QgmAbeAcjEBiqGLfMpWWdCs97HTZAgNYXhikTBXcqCRayvTvZRK5SiNeDzcxSdpLRyG72H6glEyYBL96KuSB1VuylT1APbBcABbDzEvTyNZWZER_NQwu9X5qDxeLmTwyHr0xq_igNaFxMxYDyYqiaAaIuFwG5yQ9d8fnMVo4QYa1HjoB8vVJrHtd2EJltEKe8KgVxjAVQyaSI7ZWwAzJXt4_3bsIgqKzY8ZLdY-xQHtorLRa_GaCaX-KasXOKJKm7nUNQuX3uJS8BlnS_HqJZoIbjRfHEkC2bXEhT7o2NBwMb4Cr87CBQIDjKsXQs8P6bTDsUH96YC4IOiqCL9kVsl6S2rPXd-WDGjk1p9Eqeq6dNUKZVrhalk_dkbsFO6fvGVK3iI8ARoJYbrCThL8EFEUqY58iAYLjDnmmaJH8fN0Odi-QOCLolKiAXg3idpp_hbkqIsncxcmVXYOOSvm1xpMTxq93rV8w_hDEA4Sxi4EjSiom6m3uTWHo0CBtLPa=w1366-h617"
        Picasso.get()
            .load(Image2Url)
            .into(binding.image2)
//--------------------------------------------------------------------------------------------------
        val Image3Url =
            "https://lh3.googleusercontent.com/fife/APg5EOaSblNRXMPgDlLF2qWvWp0VsGv1OIVNgaGi44naVRSSGRXwj4PMzdHOXgxfAl_2oi_SZI7FNxIZMP8WbCCR85SwkX0bwsx10EeFy9eJ6jX58h-d7Lkuozafqad72jXrGFYXSgeIGoELeaRFj3ruE44NikGegMxOlrLBoNwRwi2UeV1uyAyQ_6DhWN0IdRWlgTRzYmXy8n1hKYUimjMy5IN5eKA73dcCpKBikxJzePGE8td20IH0DRuqccn8h_kc5FzNrF6Cyb_biFtfnWnxpHk9OAGvVflAD4AETlP2BwhMmM7E0pygrDa26cOE3PVCGbk28aqrb0eO3tBCXWqvDTjAR-MtQLe0OGRf_Cnhn5whhvY3q3VH6SPF36pDg8Tf6VyyiHVwQlQ-HGbGKg9LnD4M9isoK7bNiRy9zpCF-OJQNmVF_S1JupK6nN_Xerhl53E-zlnhdsBdy9A-BL_KWtsWvj5TVU85JMc1a2cc-RAG85cBg8jWYC59M0ekBIG6cmNsFOygdd-S286hxjO8ARdi8Uz0U7R84-k65i7CLFtJGqWppR8qyN0RArXHMl1-Eh0cMxfYLL1Od62xD2U1WGqNKlBQ7Iv0MzFo1AiGFZKk9__huj08_nNQQ7yzwLLTNHSGZBWturGu2waY-t3xYWJfLchVaM6j3YH2saf6n612aQbQUtpQrY5UJ0Cs_t--vOqhdpX2j_nD3AKJWwrG9vaQzRAbq7RXGTVTWH_Q42aY4kcpKox9xh0jbeq2jOYbKbetnO56lfDwR7QTsSt5bTcXYxtkxbdRNTz_cR9oGK3CNeLQJlL4vN4yCBB8uhg502BsX9JlvjEWbYEpfd3z0BS428f_rH4TgiDkH1LpEBUr_pT7IBcdUsEkB9ZmeEx1dZ4OTtzuHmVALF_WMHwWylEQO4DgtkYIKGo6Z2gZGDStDneuuJgk_BacgoIhkGCIqHPq2kqDR7yO5MHxTWf2Vmg2eRKm9Ebl8JwOOGCtX7t5H5Ce9Iw0_apio9moSPe_BR8bcgRGH8IvXroOo3rJudmroTX-TY_Lb7lzZ5YTir-9yVOis31kPGPm_9xuRVqcwVGoLo3K_NSHFz_quUEur6g8IMfuVYsYry-arZVxgf15sKLzytjdpU6edeCReJ1vpvYAb2nEOUc_gunhGw2SFmHKZ1QmpW1Vsvvh2RyDA0l8vXADkL5Jh5xP6CTGdMpn6txRiEpPnP2X9xwgVzIZf0NOizrn4_la39bB6nSCYW3m3-n44KlZcVM6o5zC1YYTK621XFwbmeavAx6dwkCkis_uub5deIsFCZMEq48LFwMJHoYfRhs4ybdoLwrKUps6mhW65KLN9krJjOUQ5T1AdCVRQhOABTz2QozeytI81MwHQKX2ONdPFquIu8YpyL0oqjPmfRMxNn2lcv1WYahTbmX5lAAcS1Qp1RQBV-6M-BrZ3CK9ujc7bbY_XmIRLA8QUq3m1QS0hOHz1bdBqgmPeCY-onmxkI_yOfAUyTy6fm_6uvdowgB_Gds4AQ6I_ACj7JEw6bjTGkDUBf9X3auUOpDDxGOileFDsXvkw4JHGeizI2zU5iMOgc9AFqqRSVY0UliCiqbCCUl0VVIeO9qjwrw5hXh8Lvv-iaVDGRS-4BmSfLzhtIlJ=w1366-h617"
        Picasso.get()
            .load(Image3Url)
            .into(binding.image3)
//--------------------------------------------------------------------------------------------------
        val Image4Url =
            "https://lh3.googleusercontent.com/fife/APg5EObFxhZxXiEeIRVkqT7oTjA_Ce6jUN-RZ3N3MgC4ilGm9z8L2ZTfFilZ4TH54iyy3NZWbWfYTXj13jSDuLCxmDclneD77gXA1ynOwJurSxKKhA_ZG9Vm_QdRZuWtX26-MIWMYyh-aQxoHAIW84GHwgjzNtRWyCYebJK-UEFTlHt7n7zzyFQRzm4jSr5IiMOet-4CepoguFIuxwmlPZpOr11QxQMNLmxSQUqQJkn2FBKsEeXkiAt8m9p06szzEdYcw2ucb2j-eiEW8w1NGEyyNc-OvLkhQVSaddxDSsVRcK7f8DfDAOERBYxWnJ8i5e9F6sPtG9pD5OxlwcY1JrmTukX1-8JP-nJTG1n_MnQhktE6IyQZ1I2jWkdvAZ54DV5zuHH1Ho8-po0XIZ7VByHA5IsP9k-tbII8sIkfOktC5SqKm8mr9JxJgKQdqarSIB_pjEOYw-YZ4CEXFjx1LPnA0aS5WRvR-1rFOArBoAXThuOp8sxITSVkEqGoHu0yDtGHtyG1ENi9fsXtNu2JrU3kmM6sfCuV9Y4SYAz8mbP5bZm7oGescubmPv45Rgbp6sGL7PXX2OdfK9NcdHHd9Bthc-tC__l5M25RgRD_dtyXvOlPgoeLk8wwHBa9tq_D1LDwKRqv196191T6onrLAOoJDqmC60oIwtsJrR7K4L2kmF9lxBeTROhUunkgOZvq6m4cQ8p1JyHZrQmCxUx4uQg1C7K3DgKkk2O52vlUPOOU0Z0yf_-jLmYaB16tUaf3U6P9hmvstMIooSeCl1JNERKPKVW07ZeQ6zYGvRLMpwVVfyaT1tSz29JJYd2XQtIC9pxplSH5qZhBv2B1tn_pGJPnwRBd9AzQs_zZhNXVEbDcAhkehrRec9no7D3o1IcoHaVUGoiYp2hzOUD8SWkvhBQ6av_uriqZgF4YP7r7YDkb_O-bbjyG7XkIGgUBxrnztxV-ZajL-e3SIsfiQ16hzZ6HK2wkK1B8A1rMtBdCQKrWlTi4c0iY0w0mNL1qpGhSDwd0d4MpAAtXGsQ_c3HfRMq3wqsoJTHeeZX7f_dS00yqWRRR8cEf77JPKav8tOA9GHglMf5nPbbKCN8tNWmVvfw-Y8xShzRQkjc1SIrIbZNvyI2t6KO3BL_8_1UCxc5IEOjpr46EUs2iQ87cW2irgE-cGTB_m8ivyV2EhSkIOnVnhh1ThwofWbR6h9W2tLt7z0ESMgD2tOb53KQq1BPJUzaW9qwDujVCA-8Hh9gMOdKUKDwCnlAqx16vTk82qvbpP2cqQo19zpso0HZSGdLwrQkKcJg8HTDGF2DcpVCTCUZcssTLCopvEEQEKYI4wOidyvkxbf4la0BAtQsjFRP-MOisThOswMIuYQeLgGsDLisxLQyKXQdjkRtFl72y786VLxv6hKSlmlyxEpICdV6DQBzJl571JpZeKaWYRb2XnHXi-nnUddmxmOyGoTethTtpKOv5JXymm4iclz6gZE6PjcAqdYYakK5ZcisXk_KXfDBhu0eXsRAUNMiJr2xdztOTcB5wmqFvFzELFHua6an0zIO2CnXjbzqA_iBi7RtldaYdEZZADww3xiiwn8OFun5GprXWhqlwU2_juL0nUMdWPZ8S2LUsuFwa0AsH_LWN7Wi7iFS8MB9EG0YP=w1366-h617"
        Picasso.get()
            .load(Image4Url)
            .into(binding.image4)
//--------------------------------------------------------------------------------------------------
        val Image5Url =
            "https://lh3.googleusercontent.com/fife/APg5EOaUlM5LIJ7RJAgnA_O260Q9EBTqlKUSR258lZFimED04hgD8ks7jYrhMLrp_atad3KPdx_jrnZCfyLNUh5dejumo5rNJfFFdLx_S0t_AJh09BwSjszu82QKFrwiwNdqw8OKbKGLnbcy8c4ZOGcuSL79ZRUyiFZKvQ-E230L5_S-fT4yEOKuQwwtnq2w66KBkqbNGV0p_moe_Be1cuezoz2xcYyfj7FbkXw8wl1vwOORgvXqQs7cY2qKpbTDU8jg4L5aHVoX4Y6GZfLmNwV-kBRUVXgwKRXurjkmrReoO05fauwwjK7OxQV4WEng88hkMdhXUmm4Cq_WyE2L-oNfiKfZyMrOG1r_7xx-G49mmpCnGI0Ct4aXWhW6drGtpu1mR1B51vs61AhlPz0nLsIPMwZvu7C62dTVXNSpPFT5n3pDdsOdTMo1Cora0r-ClhX2OCyYdg_vkgJRGagfYooM17ZIfc73H5NP6Cf4FizDkJl1UE4T2AdOq2qt2Z3GDY3R-0Do-U83sM0G-InegZLrE-Uqh0xEz8mPa8NE-yLvyjKf8lcPc7g2loSPOtnxhhTvKciORuZVajA5-Ee3y8BvisKuhxVnGMbkf-eQyBWBk7I4KCrTjunaq5SLr7Bhm1roE9zaEBC6YJYfvbrACXiymnH5DBK8XaW0yb8vy3NUPnCv-WnyqN4XIg0S1Qy_qmpY8iIVF9L32rvoLBVzkawlFpT4JUU_lqpj4KZrAOYeHk6CQi5W3MkcT2jz1ieN_GkTvrY3WhQPcw0pCxEqf375pV71JQn8NzlTVJKPtjTPhJ4TF_8MHzfY5V1_M9WhN4HO3CPg0xvY-tvWRDzzSwsuulyM_2kD7Lz63uzYmUAVjY4HoPDC1F-Q9Po0vjijUjvlTqOK9g60cGUFpurNFCgvwDLMg4rO-jPDh_YBBxhkv1DHWINUvIWhcar3r8VJDPkH0VYUF8HBKmgJwj-wf5GlEoarsADYj4j_ep5C7hu1Vj1AaTr9DMGglvQ1GAwLBZFrqLkj4WPMn0CvFY7sG4o7-sfRaKD6k5MwSy4riqILwRbQzgbzmu5WYsOEAAhB-pLyDpiOY2v9YQoStI_G45CObq8PAsl_BbjPz3R1gZaImsydjW9YJAtuzlPDxFAgG7mo16aRnMi_PoYrPS7EYKrDVMNVietx-yS4MlWZ_BktwWA-7zo4I1SAEPsPinyptTFMwgUAAi0tF1uqSEXBTpdoCQuik9SwhX77943m62loncliGr5MElnsncrtVeRtvtlGQowIgX4Cfvpph3C1ljZBX24TUp3UQHAuGYFw2kZ9nDM4wvhWQufh_RxsBlB4JYjLVQU0wask61jk9p7gwCo7089PFL7SRu5fN5cBAowwxipBpeujf9pZBIa4iTZxdj9mCqfavEuxGWSe_rCC4I_rdt5IbrVfaLR_sw4lh7QCkrvAnGVxitGrDXnyPsKkH1-vGUIregl4qohL_XJQsys51l-Mm040P3ufZCBGnXNtVUC5cezMi-PeEgQceWc1t9UAO666iqF_rcXacSBVxm7dwQQZbl2GjnX81FVDq0wCc6r0hL8DU0wlwwBMKg1gQ9KYB_2NGxoc5uqOb1u-KiOdPEjmtJF8seVW-9E41XEK-AP6NhuDqHbJ=w1366-h617"
        Picasso.get()
            .load(Image5Url)
            .into(binding.image5)
//--------------------------------------------------------------------------------------------------
        val Image6Url =
            "https://lh3.googleusercontent.com/fife/APg5EOZonD0kIAl8PMZw81_eRnbshomH14CBDOf22F8MdalF8do5hXWhMH-uPoRvjEmUU58I16fyFL0tKKxq2uPowwehpX7w7gKTtljrecf6PUvbQ9tU1z6h0Pz3L8ZL6Q7oqL2o6Lpvi5dCKwi-Po96PbFfkJLJSVsyX4vTw55POm6PTcKId1oP2q7NtPcYnTbPFyx2jYiG9CpS6GgNRU2cAPzKqAMzEqteek-Sjn461WC2AF-dSwVwXOo8G5X9cO_aHa_IoX_W-vVn_6E5J_KltWL6X05dv7ANSdzSUif-glHBmdDThn1wd-lizNokTZ52ADFmmkMPp7TQ6ox0vYsIgP3ZcjwSxr9VUNnLEIi5XxWG_kCSW1Oqif79GYsy_Yc2WBUzp4JIlWh3vk_iQZ6AS1z0QOEzwFUrREQdYd6gezptwv0S-N8dg2CpS_9Xckiy4vU4nQao6PoAKiqnxZtt-yWQAqxt-D3LQjz8EaXJ7Ei2msg44xSNZpHb22jz50NfGmA8aMd4mObhEC7hNNykeehqICEW46DfWdMZLMMd5D8b6VEnAk0mlLvu70PH8tFzbC3BeWy16xXh8eEKDwHWsfdaQXGb-YlmBVIgHdBJeO7p6aNgrsiBH4GvXj9ggXptCnU21rnrkJoGCDTBV2N3EocqotGaeP7vbDewBF1FgnJojRIlN_d4poUo92gp3JFk25QB1z5eI0EmWNCk_KSm5I8WbFxtikZiQZSfuzKjv6M7ahu1T87wtgDTbTWZhDOZ955glsrb19oc_VQu8S_BQt9WN6B8yKbYxZcLl8h40_LBFIryFgmEh1a3mdVbB3gHX_TB6Enwng9SY9PgcMgFj-2fGWzeolH0Whh1zxHG7_QlCCsCrhjJVbx28ef7y54tAPPRtIN56W8kzdSuJoWRIxdgx0gcCW643SHxV5GKJDLKAH5pslZ4fO3lQEAO-wSFaWq_CUbByw9gs6Cek1FfaCdNpS_QhDPnCkMicaG0zQrhyWj4txwDvYCPWkeEyKeZ2fcHudWmbN0wplOlAEbz22nYf_clnbCoDjGeMu7WB47-oEYX_aQK8cj5dg8FayiGLiHL9qIF30lBoLZLFzIoIe3QO3UA6WUYpDkLrHHxI3VnVbkAMgFi_i7R3QCqbjTBvtnaj6fpRsGSoMYWSAuoBZ6xw2eX0na_u-yXjdp1_wAv4l6wN10satoLG-8pIXrnmAh0fIEPxKgBHONy1fmsyCX8Qd52xL_tqg430KjEkCZMREFSjZCa1eu9EhZjfPtt5HzdxiW36naiWU3LtogjdomHDICDIJP7jVa1D36aY7iJXl4e1bPGOG81IdmtUHoeY19JpL8q-4srypKA_M7YAjoy2xlhgEoZR3pXR69sRVSn7cmD5VbmWV-53-soaPCRW0YsEj_qf82GFaz26I8h1aQK2E40Ayz_T-5Hkou3EBjfjT7vvYvPZGgayk8rULzPxEeWgs9OEEyGuJmgfR4aIQmHwj0qUGxIGini_MLZfIuy5OVKYD9G2xMVoRV6suqTUZ0nc0INyhMsBGKUE_U-T3Z71X7V6Fdjf_X0eY2WVRTMTn4vW0FD9583WE4PqUu94T0cDrHTyKljLcV3KJAlT7u19Ju0_3HOSEsowsNBoSy1X7vBswNj=w1366-h617"
        Picasso.get()
            .load(Image6Url)
            .into(binding.image6)
//--------------------------------------------------------------------------------------------------
        val Image7Url = "https://img.freepik.com/premium-photo/spinach-fettuccine-with-mushroom_1339-24457.jpg?w=826"
            Picasso.get()
            .load(Image7Url)
            .into(binding.image7)
//--------------------------------------------------------------------------------------------------
        val Image8Url = "https://www.espri-restauration.fr/wp-content/uploads/2014/07/product_thumbnail_41699.jpg"
             Picasso.get()
            .load(Image8Url)
            .into(binding.image8)
//--------------------------------------------------------------------------------------------------
        val Image9Url ="https://recettessimples.fr/images/Ratatouille_Assiette_1.jpg"
             Picasso.get()
            .load(Image9Url)
            .into(binding.image9)
//--------------------------------------------------------------------------------------------------
        val Image10Url ="https://img.freepik.com/free-photo/delicious-food-white-plate_144627-34711.jpg?t=st=1709222938~exp=1709226538~hmac=0c398f5a22dd87524b512c959a03792e059e9f0db74f17624bee36ce324a3e75&w=826"
             Picasso.get()
            .load(Image10Url)
            .into(binding.image10)
//--------------------------------------------------------------------------------------------------
        val Image11Url ="https://img.freepik.com/premium-photo/russian-pork-dumplings-pelmeni_738298-4053.jpg?w=826"
            Picasso.get()
            .load(Image11Url)
            .into(binding.image11)
//--------------------------------------------------------------------------------------------------
        val Image12Url ="https://img.freepik.com/free-photo/delicious-pancakes-with-strawberry-jam_2829-15767.jpg?t=st=1709223250~exp=1709226850~hmac=241c2a3eb2651d89377f9bba91ce8a80b9ac3fae56f827b76bbc3ca84e0d6e0e&w=826"
             Picasso.get()
            .load(Image12Url)
            .into(binding.image12)
//--------------------------------------------------------------------------------------------------
        val Image13Url ="https://food.pibig.info/uploads/posts/2023-08/1693284195_food-pibig-info-p-yeda-tsezar-vkontakte-63.jpg"
        Picasso.get()
            .load(Image13Url)
            .into(binding.image13)
//--------------------------------------------------------------------------------------------------
        val Image14Url ="https://avatars.mds.yandex.net/i?id=be9b6e48854aa59ad9323bcf6b547f67e1746337-12168040-images-thumbs&n=13"
        Picasso.get()
            .load(Image14Url)
            .into(binding.image14)
//--------------------------------------------------------------------------------------------------
        val Image15Url ="https://media.leverans.ru/product_images_inactive/moscow/sam-am-beri/samamberi-ru-006.jpg"
             Picasso.get()
            .load(Image15Url)
            .into(binding.image15)
//--------------------------------------------------------------------------------------------------
        val Image16Url = "https://i.pinimg.com/originals/a6/d2/15/a6d215f534a432c20f8c201e50e31082.jpg"
            Picasso.get()
            .load(Image16Url)
            .into(binding.image16)
//--------------------------------------------------------------------------------------------------
        val Image17Url = "https://lime-sushi.com/wa-data/public/shop/products/43/00/43/images/54/54.970.png"
             Picasso.get()
            .load(Image17Url)
            .into(binding.image17)
//--------------------------------------------------------------------------------------------------
        val Image18Url = "https://cdn-img.perekrestok.ru/i/400x400-fit/xdelivery/files/48/e2/b46bfb25b1edde303b63b92ef3ee.jpg"
           Picasso.get()
            .load(Image18Url)
            .into(binding.image18)

    }
    companion object {
        @JvmStatic
        fun newInstance() = Menu()
    }

}