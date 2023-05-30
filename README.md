# YEMEK TARİFİ

## AÇIKLAMA
### Bu proje kullanıcınin giriş aksiyonları, programda bulunan sadece adminlerin ekleyebildiği tariflere yorum yapıp puan vermesi için yazılmış bir projedir.

## KULLANILAN TEKNOLOJİLER,VERİ TABANLARI VE PROGLAMLAMA DİLLERİ
### Programlama dilleri:Java,Spring Boot
### Veritabanları: PostgreSQL,MongoDB
### Servisler arasındaki HTTP istekleri: OpenFeign
### Kuyruk teknolojisi: RabbitMQ
### Cacheleme işlemi: Redis
### Loglama işlemi: @Slf4j

#### Register methodunda şifre regex ve size ile sınırlandırılmıştır,repassword ile aynı verilmesi gerektiğide kontrol edilmiştir. 
#### Auth postgre veritabanı ile çalışıyor, userprofile mongo ile burada kayıt yapılınca userprofile veri tabanınada veriler gidiyor. 
![Auth Register](Swagger_Screenshots/Auth/Register.png)
#### Register yaptıktan sonra maile activate status kodunuz gelir,bu kod ile hesabınızı aktif edebilirsiniz.

![Auth Mail-Activate Status](Swagger_Screenshots/Auth/MailAktivasyonKodu.png)
#### Mailimize gelen aktivasyon kodu ile hesabımızı aktif ediyoruz.

![Auth Activate Status](Swagger_Screenshots/Auth/ActivateStatus.png)
#### Username ve password ile giriş yapıyoruz.

![Auth Login](Swagger_Screenshots/Auth/Login.png)

#### Parola unutulduğu zaman maile bir parola gitmesi için email ister.
![Auth Forgot Password](Swagger_Screenshots/Auth/ForgotPassword.png)
#### Şifre sıfırlamak için maile sistemin ürettiği yeni şifre geliyor.

![Auth ForgotPassword Epostası](Swagger_Screenshots/Auth/ForgotPasswordEpostası.png)
#### User update işlemi
![User UserUpdate](Swagger_Screenshots/User/UserUpdate.png)

![User UserUpdateCikti](Swagger_Screenshots/User/UserUpdateCikti.png)
#### Password Change
![User PasswordChange](Swagger_Screenshots/User/PasswordChange.png)
### Recipeleri favoriye ekliyoruz.
![User TakeRecipeToFavorites](Swagger_Screenshots/User/TakeRecipeToFavorites.png)
#### Delete User
![User DeleteUser](Swagger_Screenshots/User/DeleteUser.png)
#### Yorum yapılıyor, yapılan yorumlar recipeye de ekleniyor.
![Comment AddComment](Swagger_Screenshots/Comment/AddComment.png)
#### Yapılan yorumlar siliniyor,burada silinen yorumlar recipede de silinecek şekilde tasarlandı.
![Comment DeleteComment](Swagger_Screenshots/Comment/DeleteComment.png)
#### Puan veriliyor, verilen puanlar recipeye de ekleniyor.
![Point AddPoint](Swagger_Screenshots/Point/AddPoint.png)
#### Verilen puanlar siliniyor,burada silinen puanlar recipede de silinecek şekilde tasarlandı.
![Point DeletePoint](Swagger_Screenshots/Point/DeletePoint.png)
#### Kategoriler ekleniyor.
![Recipe AddCategory](Swagger_Screenshots/Recipe/AddCategory.png)
#### Tarifler ekleniyor.Burada puan,yorum ve içerikler yer almakta.
![Recipe AddRecipe](Swagger_Screenshots/Recipe/AddRecipe.png)

![Recipe AddRecipeCikti](Swagger_Screenshots/Recipe/AddRecipeCikti.png)
#### Favoriye eklediğiniz recipelerin kategorisinde bir yemek eklenirse size mail gelmesini sağlıyor.
![Recipe FavoriteCategoryFoodMail](Swagger_Screenshots/Recipe/FavoriteCategoryFoodMail.png)
#### Tariflerin silinmesini sağlıyor.
![Recipe RecipeDelete](Swagger_Screenshots/Recipe/RecipeDelete.png)
#### Tariflerin update edilmesini sağlar.
![Recipe RecipeUpdate](Swagger_Screenshots/Recipe/RecipeUpdate.png)

![Recipe RecipeUpdateCikti](Swagger_Screenshots/Recipe/RecipeUpdateCikti.png)
#### Bu filtreleme işlemi kaloriyi sıralamaya yarar.
![Recipe RecipeCalorieFilter](Swagger_Screenshots/Recipe/RecipeCalorieFilter.png)
#### Bu filtreleme kategorileri filtrelemeye yarar.
![Recipe RecipeCategoryFilter](Swagger_Screenshots/Recipe/RecipeCategoryFilter.png)
#### Tariflerin ismi ile filtreleme yapmaya yarar.
![Recipe RecipeFoodnameFilter](Swagger_Screenshots/Recipe/RecipeFoodnameFilter.png)
#### Tariflerin içerdiği yemek ismi ile filtreleme yapmaya yarar.
![Recipe RecipeIngredientFilter](Swagger_Screenshots/Recipe/RecipeIngredientFilter.png)






