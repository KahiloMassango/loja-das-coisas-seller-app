﻿<h1 >Loja Das Coisas Seller CMS</h1>

![home](images/loja-das-coisas-banner.png)

## TL;DR

If you would like to use this application, fork the repository and link back
to [Kahilo Massango](https://github.com/KahiloMassango) for proper credits. Thanks in Advance!

## Loja Das Coisas - Seller CMS App
Seller CMS App for Loja das Coisas used by customer.
It's a multi-module app and uses MVVM architecture.

Modules:

- **App**: Brings everything together required for the app to function correctly. This includes UI
  scaffolding and navigation.
- **Feature**:
    - `:authentication` – Handles authentication flow.
    - `:finances` – Manages the finances of the store.
    - `:home` – Displays new pending and concluded orders.
    - `:products` – List all products in the store.
    - `:order-detail` – Displays detailed information of an orders.
    - `:product-detail` – Show product details and edit product.
    - `:product-items` – List all product items of a product.
    - `
- **Core**:
    - `:data`: Fetching app data from multiple sources, shared by different features.
    - `:ui`: UI components and resources used by feature modules, such as product card, dropdown
      menus, etc.
    - `:network`: Making network requests and handling responses from a remote data source.
    - `:datastore`: Storing persistent data using DataStore.
    - `:database`: Local database storage using Room.
    - `:model`: Model classes used throughout the app.

This project was built using these technologies.

- Kotlin
- Jetpack Compose
- Dagger-Hilt
- Datastore
- Retrofit
- Material Design 3
- Room Database
- Coil Compose
- Work Manager
- Gson

## 🤩 Features

- Seller can create, edit, update and delete product.
- Seller can create, edit, update and delete product items.
- Seller can make a product available or not.
- Seller can request a withdraw.

## Some Screenshots

<table>
 <tr>
   <td center >Home</td>
   <td center >Home - Dark</td>
   <td center >Product Detail</td>
 </tr>

 <tr>
   <td width="33.33%"><img src="./images/home.png"  alt="Home"></td>
   <td width="33.33%"><img src="./images/home-dark.png" alt="Home - Dark"></td>
   <td width="33.33%"><img src="images/products.png" alt="Product Detail"></td>
 </tr> 
 <tr>
   <td center >Finances</td>
   <td center >Finances - Dark</td>
 </tr>
<tr>
   <td width="33.33%"><img src="./images/finances.png" alt="Finances"></td>
   <td width="33.33%"><img src="images/finances-dark.png" alt="Finances - Dark"></td>
 </tr> 
</table>

## Contribute

This project is open for contributors and feel free to `fork` and make a `Pull Request` to the repo.
If you feel any enhancements are required don't hesitate to open an issue.

Store app [here](https://github.com/KahiloMassango/loja-das-coisas-app)

You can find the backend api [here](https://github.com/KahiloMassango/loja-das-coisas-api)

## Show your support

Follow me for more projects and don't forget to Give a ⭐ if you like this app!
