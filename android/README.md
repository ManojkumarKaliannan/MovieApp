Splash screen->
1.Application should not provide its own launch screen,
From API 31 onwards we need to use SplashScreen API
(Refer https://developer.android.com/guide/topics/ui/splash-screen/migrate*/)
2.Due to ICON dependency(), I have used custom icon for splash screen

Image caching ->
1.Used  glide library for loading the image 
2.DiskCacheStrategy - for storing the images in cache  

Glide.with(view.context)
.load(Singleton.imageBaseUrl + customImagePath)
.placeholder(placeholder)
.diskCacheStrategy(DiskCacheStrategy.ALL)
.apply(RequestOptions().error(placeholder))
.into(view)

Rating view ->
Used ShapeableImageView with bindable adapter

ViewPager ->
Customized the viewpager as per requirement (Most Popular screen)

Retrofit ->
For network API communication
Added Network interceptor 
Created generic class for Base responses

Koin->
For dependency injection

Navigation Graph->
For Fragment navigation

Coroutines->
For Async operations

Recyclerview->
Added animation while populating the data
Added animation while doing the scroll

Architecture-> MVVM 






