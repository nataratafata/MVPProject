package firstproject2.mcs.com.mvpproject.network;

import android.database.Observable;

import java.util.List;

import firstproject2.mcs.com.mvpproject.data.CakesModel;
//import rx.Observable;
//import showtime.interview.com.apidemo.data.CakesModel;
//import io.reactivex.Observable;

public interface ICakes_Interacter {

    Observable<List<CakesModel>> getCakesList();

}
