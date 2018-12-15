package firstproject2.mcs.com.mvpproject.network;

        import android.database.Observable;

        import java.util.List;

        import firstproject2.mcs.com.mvpproject.data.CakesModel;
        import retrofit2.http.GET;
        //import rx.Observable;
//import android.database.Observable;
//import rx.Observable;
//import showtime.interview.com.apidemo.data.CakesModel;



public interface IRequestInterface {
    // GET request, PUT,
    // [ JSON ARRAY = LIST
    // { JSON OBJECT : Object


    @GET(API_Request.API_CAKES_LIST)
    Observable<List<CakesModel>> getCakesList();
}
