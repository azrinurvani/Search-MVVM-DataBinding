# Search-MVVM-DataBinding

This App Include : 
1. MVVM
2. DataBinding
3. Retrofit2

Note :
Perbedaan MutableLiveData dengan LiveData,dan MediatorLiveData
1. Live Data bukanlah public method, sehingga data tersebut tidak bisa dimodifikasi.
2. MutableLiveData datanya bisa di modifikasi, contoh :
   getUser().setValue(userObject) or 
   getUser().postValue(userObject)
3. MediatorLiveData adalah subclass LiveData yang memungkinkan Anda menggabungkan beberapa sumber LiveData. 

