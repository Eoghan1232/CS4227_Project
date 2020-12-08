package com.cs4125.bookingapp.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cs4125.bookingapp.entities.Booking;
import com.cs4125.bookingapp.entities.Discount;
import com.cs4125.bookingapp.entities.Route;
import com.cs4125.bookingapp.entities.User;
import com.cs4125.bookingapp.repositories.BookingRepositoryImpl;
import com.cs4125.bookingapp.repositories.DiscountRepository;
import com.cs4125.bookingapp.repositories.DiscountRepositoryImpl;
import com.cs4125.bookingapp.repositories.ResultCallback;
import com.cs4125.bookingapp.repositories.RouteRepository;
import com.cs4125.bookingapp.repositories.RouteRepositoryImpl;
import com.cs4125.bookingapp.repositories.UserRepository;
import com.cs4125.bookingapp.repositories.BookingRepository;
import com.cs4125.bookingapp.repositories.BookingRepositoryImpl;

import okhttp3.Response;

//Admin not implemented yet, created for future implementation
public class AdminViewModel extends ViewModel
{
    private DiscountRepository repository;
    private BookingRepository repository2;
    private RouteRepository repository3;

    public void init(){
        this.repository = new DiscountRepositoryImpl();
        this.repository2 = new BookingRepositoryImpl();
        this.repository3 = new RouteRepositoryImpl();
    }

   public LiveData<String> getAllDiscounts(){
        MutableLiveData<String> liveString = new MutableLiveData<>();
        repository.getAllDiscounts(new ResultCallback()
        {
            @Override
            public void onResult(String result)
            {
                liveString.postValue(result);
            }

            @Override
            public void onFailure(Throwable error)
            {
                liveString.postValue(error.toString());
            }
        });

        return liveString;
    }
   public LiveData<String> getDiscountById(Discount discount){
        MutableLiveData<String> liveString = new MutableLiveData<>();
        repository.getDiscountById(discount, new ResultCallback()
        {
            @Override
            public void onResult(String result)
            {
                liveString.postValue(result);
            }

            @Override
            public void onFailure(Throwable error)
            {
                liveString.postValue(error.toString());
            }
        });

        return liveString;
    }
   public LiveData<String> getDiscountByCode(Discount discount){
        MutableLiveData<String> liveString = new MutableLiveData<>();
        repository.getDiscountByCode(discount, new ResultCallback()
        {
            @Override
            public void onResult(String result)
            {
                liveString.postValue(result);
            }

            @Override
            public void onFailure(Throwable error)
            {
                liveString.postValue(error.toString());
            }
        });

        return liveString;
    }
   public LiveData<String> newDiscount(Discount discount){
        MutableLiveData<String> liveString = new MutableLiveData<>();
        repository.newDiscount(discount, new ResultCallback()
        {
            @Override
            public void onResult(String result)
            {
                liveString.postValue(result);
            }

            @Override
            public void onFailure(Throwable error)
            {
                liveString.postValue(error.toString());
            }
        });

        return liveString;
    }
   public LiveData<String> updateDiscount(Discount discount){
        MutableLiveData<String> liveString = new MutableLiveData<>();
        repository.updateDiscount(discount, new ResultCallback()
        {
            @Override
            public void onResult(String result)
            {
                liveString.postValue(result);
            }

            @Override
            public void onFailure(Throwable error)
            {
                liveString.postValue(error.toString());
            }
        });

        return liveString;
    }
   public LiveData<String> removeOldDiscount(Discount discount){
        MutableLiveData<String> liveString = new MutableLiveData<>();
        repository.removeOldDiscount(discount, new ResultCallback()
        {
            @Override
            public void onResult(String result)
            {
                liveString.postValue(result);
            }

            @Override
            public void onFailure(Throwable error)
            {
                liveString.postValue(error.toString());
            }
        });

        return liveString;
    }
   public LiveData<String> getBooking(Booking booking){
        MutableLiveData<String> liveString = new MutableLiveData<>();
        repository2.getBooking(booking, new ResultCallback()
        {
            @Override
            public void onResult(String result)
            {
                liveString.postValue(result);
            }

            @Override
            public void onFailure(Throwable error)
            {
                liveString.postValue(error.toString());
            }
        });

        return liveString;
    }
   public LiveData<String> newRoute(Route route){
        MutableLiveData<String> liveString = new MutableLiveData<>();
        repository3.newRoute(route, new ResultCallback()
        {
            @Override
            public void onResult(String result)
            {
                liveString.postValue(result);
            }

            @Override
            public void onFailure(Throwable error)
            {
                liveString.postValue(error.toString());
            }
        });

        return liveString;
    }
   public LiveData<String> updateRoute(Route route){
        MutableLiveData<String> liveString = new MutableLiveData<>();
        repository3.updateRoute(route, new ResultCallback()
        {
            @Override
            public void onResult(String result)
            {
                liveString.postValue(result);
            }

            @Override
            public void onFailure(Throwable error)
            {
                liveString.postValue(error.toString());
            }
        });

        return liveString;
    }
   public LiveData<String> deleteRoute(Route route){
        MutableLiveData<String> liveString = new MutableLiveData<>();
        repository3.deleteRoute(route, new ResultCallback()
        {
            @Override
            public void onResult(String result)
            {
                liveString.postValue(result);
            }

            @Override
            public void onFailure(Throwable error)
            {
                liveString.postValue(error.toString());
            }
        });

        return liveString;
    }
}