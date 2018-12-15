package firstproject2.mcs.com.mvpproject.cakelist;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import firstproject2.mcs.com.mvpproject.R;
import firstproject2.mcs.com.mvpproject.data.CakesModel;
import firstproject2.mcs.com.mvpproject.network.ICakes_Interacter;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
//import com.mvpproject2.flowers.mvp.data.FlowerModel;

//import com.bryancuneo.flowers.mvp.network.IFlower_Interacter;
//import io.reactivex.Schedulers.Schedulers;
//import rx.Observer;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;
//import android.database.Observable;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import firstproject2.mcs.com.mvpproject;
//import showtime.interview.com.apidemo.data.CakesModel;
//import showtime.interview.com.apidemo.network.ICakes_Interacter;



public class CakesList_PresenterImpl implements ICakeList_Contract.IPresenter_CakeList {

    private ICakeList_Contract.IView_CakeList iView_cakeList;
    private ICakes_Interacter iCakes_interacter;



    public CakesList_PresenterImpl(ICakes_Interacter iCakes_interacter) {
        this.iCakes_interacter = iCakes_interacter;
    }


    @Override
    public void onBind(ICakeList_Contract.IView_CakeList iView_cakeList) {
        this.iView_cakeList= iView_cakeList;
        //Log.i("getting the OnBind data", iView_cakeList._cakesList());



    }

    @Override
    public void getCakesFromAPI() {
        iCakes_interacter.getCakesList()
                .subscribeOn(Schedulers.io())// perform the request on separate thread
                .observeOn(AndroidSchedulers.mainThread()) //Responses are processed on the main thread
                .subscribe(new Observer<List<CakesModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<CakesModel> cakesModels) {
                        // Recycler view
                        iView_cakeList._cakesList(cakesModels);
                       }
                }) ;
    }


    @Override
    public void unBind() {
        this.iView_cakeList=null;
    }
}
