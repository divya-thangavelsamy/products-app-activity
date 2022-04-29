# Biirr

1.	Made use of product list https://api.punkapi.com/v2/beers for the first screen
2.	Used https://api.punkapi.com/v2/beers/<product_id> for the second screen (detail page)
3.	Used Hilt to manage dependencies
4.	LiveData for holding the data and updating the UI
5.	Used coroutines with Flow and retrofit
6.	Used Koin to load images.
7.	MVVM Architecture
8.	Used View binding to bind the xml layout
9.	Handled only basic internet connectivity check due to time constrain.  
10.	API Endpoint https://api.punkapi.com/v2/beers gets 25 products by default. However, it can be fetched until 350 using api pagination and infinite scrolling.
11.	Beer properties like IBU, ABV are constructed as individual text view. – This could be dynamically listed as table view to make it flexible for addition of more field items.
