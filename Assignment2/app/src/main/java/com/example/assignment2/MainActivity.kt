package com.example.assignment2

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewsAdapter
    private  var newsList: MutableList<NewsData> = mutableListOf()

    private var likedUrls: MutableSet<String> = mutableSetOf()

    companion object {
        private const val KEY_NEWS = "KEY_NEWS"
        private const val KEY_RV_STATE = "KEY_RV_STATE"
    }

    private var pendingRvState: Parcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WindowCompat.setDecorFitsSystemWindows(window, true)

        recyclerView = findViewById(R.id.recyclerView)
        val lm = LinearLayoutManager(this)
        recyclerView.layoutManager = lm


        likedUrls = LikesStorage.loadLikes(this)

        LikesStorage.saveLikes(this, likedUrls)

        if(savedInstanceState != null){
            savedInstanceState.getParcelableArrayList<NewsData>(KEY_NEWS)?.let{
                newsList = it.toMutableList()
            }
            pendingRvState = savedInstanceState.getParcelable(KEY_RV_STATE)
        }else{
            newsList = mutableListOf(

                NewsData(
                    "Meta’s Smart Glasses Might Make You Smarter. They’ll Certainly Make You More Awkward",
                    "Meta CEO Mark Zuckerberg claims anyone not wearing smart glasses will be at a “cognitive disadvantage” in the future. But you'll have to pay a hefty social price for those added smarts.",
                    "https://www.wired.com/story/meta-smart-glasses-cognitive-disadvantage/",
                    "https://media.wired.com/photos/68cda3213e66f0d9fd315ce2/master/w_1920,c_limit/Meta-Ray-Ban-Display-Gear-Meta-AI.jpg"
                ),
                NewsData(
                    "Microsoft hikes Xbox prices in US once again as tariff challenges persist",
                    "Ever since Apple unveiled the iPhone Air, calling it its most durable iPhone ever, it feels like all of the Apple YouTube community has been waiting to see the JerryRigEverything hands-on test.Sure, enough, he delivered. And the iPhone Air held up to Apple’s claims. ",
                    "https://www.reuters.com/business/microsoft-hikes-xbox-prices-us-once-again-tariff-challenges-persist-2025-09-19/",
                    "https://www.reuters.com/resizer/v2/TBGNM6IB3VNT7GU5WINCLOEFXY.jpg?auth=f75717767f2defcb06af223bc99e3e041e4d9bd21373706efa9f03a7be4ab9f7&width=720&quality=80"
                ),
                NewsData(
                    "Verizon is giving away the iPhone 17 Pro for free with this generous trade-in offer",
                    "Haven't you heard? Apple has announced the new line of iPhones, including the iPhone 17, iPhone Air, iPhone 17 Pro Max, and iPhone 17 Pro Max. The new range is officially launch date on Friday, Sept. 19. If you've been eyeing up these new iPhones, Verizon is offering a smart and free way to nab something for nothing.",
                    "https://9to5mac.com/2025/09/20/iphone-air-bend-test-most-durable-iphone-ever/",
                    "https://helios-i.mashable.com/imagery/articles/035rjXsFkDpwKmUxbGpqvTu/hero-image.fill.size_1248x702.v1758287679.jpg"
                ),

                NewsData(
                    "Mega Latias ex and More New Cards from the Pokémon TCG: Mega Evolution Expansion",
                    "We’ve already taken a look at some of the cool cards you’ll soon be able to find in the upcoming Pokémon Trading Card Game expansion, Mega Evolution. Still, we’re just so excited about this new collection of cards that we’re going back for another peek.",
                    "https://9to5mac.com/2025/09/20/iphone-air-bend-test-most-durable-iphone-ever/",
                    "https://www.pokemon.com/static-assets/content-assets/cms2/img/trading-card-game/_tiles/me/me01/preview-cards/me01-preview-cards-3-169-en.png"
                ),
                NewsData(
                    "Windows 11 is adding another Copilot button nobody asked for",
                    "Have enough Copilot buttons in your life? No you don’t — have another one! This one pops up in the latest Windows 11 Insider Preview when mousing over an open app in your taskbar; it lets you share the contents with Copilot Vision.",
                    "https://9to5mac.com/2025/09/20/iphone-air-bend-test-most-durable-iphone-ever/",
                    "https://platform.theverge.com/wp-content/uploads/sites/2/2025/08/STK259_MICROSOFT_COPILOT_2__B.png?quality=90&strip=all&crop=0%2C0%2C100%2C100&w=1080"
                ),
                NewsData(
                    "Samsung’s Project Moohan VR headset could launch in October — here’s everything we know",
                    "Samsung’s long-rumored Project Moohan looks like it’s finally about to drop. The company’s answer to Apple’s Vision Pro VR headset is reportedly set for an October reveal — complete with a hefty price tag. According to Upload VR, preorders will open on Sept. 29, with a staggered global launch: Oct. 13 in Korea, followed by Oct. 21 worldwide. ",
                    "https://9to5mac.com/2025/09/20/iphone-air-bend-test-most-durable-iphone-ever/",
                    "https://helios-i.mashable.com/imagery/articles/00gaUesjTetJ8RBcNUFwilN/hero-image.fill.size_1248x702.v1758385152.jpg"
                ),
                NewsData(
                    "U.S. Xbox fans hit with another hardware price hike just four months after the last increase",
                    "Xbox consoles are about to become more expensive in the United States. Again.The hike comes just months after Microsoft raised prices of its products in May, when the Xbox Series S and X increased by between $80 and $130, depending on the spec. While May's cost hike was felt across the world, this time the increase is limited to the U.S., with Microsoft confirming: Pricing in countries outside the U.S. remains the same.",
                    "https://9to5mac.com/2025/09/20/iphone-air-bend-test-most-durable-iphone-ever/",
                    "https://assetsio.gnwcdn.com/Xbox-Series-X-%26-S.jpg?width=570&quality=80&format=jpg&auto=webp"
                ),
                NewsData(
                    "A major iPhone feature breaks after updating to iOS 26, but you can fix it right now!",
                    "It's a little ironic, don't you think? Here I used my iPhone 15 Pro Max from June through September, running the iOS 26 beta with just one little issue.  Well, okay, it might have been a larger issue. For about three days during the summer, my iPhone stopped ringing when it received an incoming call. ",
                    "https://9to5mac.com/2025/09/20/iphone-air-bend-test-most-durable-iphone-ever/",
                    "https://m-cdn.phonearena.com/images/article/174221-wide-two_1200/A-major-iPhone-feature-breaks-after-updating-to-iOS-26-but-you-can-fix-it-right-now.webp?1758400582"
                ),
                NewsData(
                    "OpenAI is building Apple-style gadgets — with Apple’s people",
                    "Earlier on Friday, The Information reported that Sam Altman’s company has been busy scooping up Apple veterans — designers, hardware engineers, and even wearable specialists — and talking with the go-to manufacturers that make Apple’s crown jewels.",
                    "https://9to5mac.com/2025/09/20/iphone-air-bend-test-most-durable-iphone-ever/",
                    "https://qz.com/cdn-cgi/image/width=1920,quality=85,format=auto/https://assets.qz.com/media/GettyImages-2153474303.jpg"
                ),
                NewsData(
                    "Apple's new AirPods Pro 3 are already on sale",
                    "It's barely been two weeks since Apple announced the AirPods Pro 3, but you can already find them at a slight discount. The new earbuds are currently listed as $239 on Amazon, which is $10 cheaper than their normal price. ",
                    "https://9to5mac.com/2025/09/20/iphone-air-bend-test-most-durable-iphone-ever/",
                    "https://images.macrumors.com/t/y5UXYVDLgl76EeRoDi849fjk2YM=/1600x0/article-new/2025/09/airpods-pro-3-blue.jpeg"
                ),
            )
        }

        newsList.forEach { item ->
            item.liked = likedUrls.contains(item.url)
        }

        adapter = NewsAdapter(newsList) { position, liked ->
            val news = newsList[position]
            news.liked = liked
            if (liked) {
                likedUrls.add(news.url)
            } else {
                likedUrls.remove(news.url)
            }
            LikesStorage.saveLikes(this, likedUrls)
        }

        recyclerView.adapter = adapter

        pendingRvState?.let { state ->
            recyclerView.layoutManager?.onRestoreInstanceState(state)
            pendingRvState = null
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelableArrayList(KEY_NEWS, ArrayList(newsList))

        recyclerView.layoutManager?.onSaveInstanceState()?.let{
            outState.putParcelable(KEY_RV_STATE,it)
        }
    }

}